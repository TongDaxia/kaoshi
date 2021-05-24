package com.kaoshi.tyg.leetcode.其他面试题;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class 大文件处理demo {

    public static void main(String[] args) throws IOException {
        File file = new File("");
        FileInputStream in = new FileInputStream(file);
        int available = in.available();
        byte[] bytes = new byte[1024];
        in.read(bytes);
        FileChannel channel = in.getChannel();


        ByteBuffer buf = ByteBuffer.allocate(48);

        buf.clear();

        buf.put("234567".getBytes());

        buf.flip();



        while(buf.hasRemaining()) {

            channel.write(buf);

        }

    }


    public void parallelReadFile(final String path) throws IOException {
        /********************************************************************
         * calculate partitions size to map file
         ********************************************************************/
        long K_BYTE = 1024L;            // 1KB
        long M_BYTE = 1024 * 1024L;     // 1MB
        long defaultPartitionSize = 50 * M_BYTE;

        List<Long> partitions = new ArrayList<>();


        RandomAccessFile fra = new RandomAccessFile(path, "r");
        FileChannel fc_fra = fra.getChannel();
        long fileSize = fc_fra.size();

        long offset = 0;
        while ((offset + defaultPartitionSize) < fileSize) {
            offset += defaultPartitionSize;
            fc_fra.position(offset);

            Scanner scanner = new Scanner(fc_fra);
            String line = scanner.nextLine();

            partitions.add(defaultPartitionSize + line.length());
            offset += line.length();
        }

        partitions.add(fileSize - offset);
        fc_fra.close();
        fra.close();

        /********************************************************************
         * map file & parse in threads
         ********************************************************************/
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        FileInputStream fis = new FileInputStream(path);
        FileChannel fc_fis = fis.getChannel();

        offset = 0;
        for (Long size : partitions) {
            if (size > defaultPartitionSize * 2) {
                continue;
            }

            final long areaFrom = offset;
            final long areaTo = offset + size;
            final MappedByteBuffer byteBuffer = fc_fis.map(FileChannel.MapMode.READ_ONLY, offset, size);
            offset += size;

            threadPool.execute(() -> {
                try {
                    Charset charset = Charset.forName("iso-8859-1");
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer charBuffer = decoder.decode(byteBuffer);
                    Scanner scanner = new Scanner(charBuffer);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        //TODO: parse `line`



                    }
                } catch (CharacterCodingException e) {
                    //TODO
                    e.printStackTrace();
                }
            });
        }

        /********************************************************************
         * wait until thread pool terminaled
         ********************************************************************/
        threadPool.shutdown();
        try {
            while (!threadPool.isTerminated()) {
                threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fc_fis.close();
        fis.close();
    }
}
