package com.kaoshi.tyg.entity.dto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AddExerciseDTO {


    public static void main(String[] args) {

        ListNode noed1 = new ListNode(2);
        noed1.next = new ListNode(4);
        noed1.next.next = new ListNode(3);
        noed1.next.next.next = new ListNode(4);
        noed1.next.next.next.next = new ListNode(6);

        ListNode noed2 = new ListNode(2);
        noed2.next = new ListNode(5);
        noed2.next.next = new ListNode(7);
        noed2.next.next.next = new ListNode(8);
        noed2.next.next.next.next = new ListNode(9);
        noed2.next.next.next.next.next = new ListNode(8);

        ListNode listNode = testNode(noed1, noed1);
        while (listNode.next != null) {
            System.out.print(listNode.node + "-->");
            listNode = listNode.next;
        }
        System.out.print(listNode.node + "-->null");

    }

    /**
     * 计算数字中两个数字和指定时数值的位置
     *
     * @param arr
     * @param sum
     */
    public static void test(int[] arr, int sum) {

        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer s = sum - arr[i];
            if (hash.containsKey(s)) {
                System.out.println(hash.get(s) + "    " + i);
                break;
            } else {
                hash.put(arr[i], i);
            }
        }

    }

    /**
     * 返回两个链表
     * - - 2-4-3-4-6
     * - 2-5-7-8-9-8
     * 0 1 2 3 4 5 6
     * <p>
     * 0 2-8-2-2-4-4
     *
     * @param node1
     * @param node2
     */
    public static ListNode testNode(ListNode node1, ListNode node2) {
        int length1 = getListNodeLength(node1);//5
        int length2 = getListNodeLength(node2);//6
        int[] sum;
        if (length1 < length2) {
            sum = getSumArr(node1, node2, length1, length2);
            //sum ：[0, 2, 7, 11, 11, 13, 14]
            System.out.println("sum：" + Arrays.toString(sum));
        } else {
            sum = getSumArr(node2, node1, length2, length1);
            //sum ：[0, 2, 7, 11, 11, 13, 14]
            System.out.println("sum：" + Arrays.toString(sum));
        }
        for (int length = sum.length - 1; length >= 0; length--) {
            if (sum[length] > 10) {
                sum[length - 1] = sum[length - 1] + 1;
                sum[length] = sum[length] % 10;
            }
        }
        System.out.println("sum：" + Arrays.toString(sum));

        ListNode head = new ListNode(sum[0]);
        ListNode node = head;
        for (int i = 1; i < sum.length; i++) {
            node.next = new ListNode(sum[i]);
            node = node.next;
        }
        if (head.node == 0) {
            return head.next;
        }
        return head;

    }

    private static int[] getSumArr(ListNode node1, ListNode node2, int length1, int length2) {
        int[] sum = new int[length2 + 1];//7
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            if (sum.length - length1 > i) {
                sum[i] = node2.node + 0;
                node2 = node2.next;
            } else {
                sum[i] = node2.node + node1.node;
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        return sum;
    }

    private static int getListNodeLength(ListNode node1) {

        int length1;
        ListNode l1 = node1;

        if (node1 == null) {
            length1 = 0;
        } else {
            length1 = 1;
            while (l1.next != null) {
                length1++;
                l1 = l1.next;
            }
        }
        return length1;
    }


    static class ListNode {
        public ListNode(Integer node) {
            this.node = node;
        }

        Integer node;
        ListNode next;
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
