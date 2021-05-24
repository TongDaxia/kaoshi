package com.kaoshi.tyg.util;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Type {


    /**
     * 0 单选题，1多项选择题，2 判断题，3，母题（只有题干
     */
    public static Integer DANXUAN = 0;
    public static Integer DUOXUAN = 1;
    public static Integer PANDUAN = 2;
    public static Integer MUTI = 3;

    public static String MUTISTR = "子母题";
    public static String NOMUTISTR = "独立题";


    //使用BufferedInputStream和BufferedOutputStream
    private static void bufferedStreamByteOperation() throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src.txt"));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("target.txt"));) {
            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        }
    }

    //额外使用一个8KB缓冲，再使用BufferedInputStream和BufferedOutputStream
    private static void bufferedStreamBufferOperation() throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src.txt"));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("target.txt"))) {
            byte[] buffer = new byte[8192];
            int len = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1)

            {
                bufferedOutputStream.write(buffer, 0, len);
            }
        }
    }

    //直接使用FileInputStream和FileOutputStream，再使用一个8KB的缓冲
    private static void largerBufferOperation() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("src.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("target.txt")) {
            byte[] buffer = new byte[8192];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        }
    }

    private static void fileChannelOperation() throws IOException {
        FileChannel in = FileChannel.open(Paths.get("src.txt"), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Paths.get("dest.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        in.transferTo(0, in.size(), out);


        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000).forEach(i -> {
            try (Stream<String> lines = Files.lines(Paths.get("demo.txt"))) {
                lines.forEach(line -> longAdder.increment());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        log.info("total : {}", longAdder.longValue());

        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
