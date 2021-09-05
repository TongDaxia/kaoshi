package com.kaoshi.tyg.common.leetcode.数组链表;

public class 链表K组反转 {

    public static void main(String[] args) {


        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(5);
        listNode.next.next = new ListNode(7);
        listNode.next.next.next = new ListNode(8);
        listNode.next.next.next.next = new ListNode(9);
        listNode.next.next.next.next.next = new ListNode(8);

        ListNode copyHead = listNode;
        while (copyHead.next != null) {
            System.out.print(copyHead.node + "-->");
            copyHead = copyHead.next;
        }
        System.out.println(copyHead.node + "-->null");


        reverseKGroup(listNode, 2);

        copyHead = listNode;
        while (copyHead.next != null) {
            System.out.print(copyHead.node + "-->");
            copyHead = copyHead.next;
        }
        System.out.println(copyHead.node + "-->null");

    }

    /**
     * K 个链表元素进行反转
     * 1234567812  4
     * 4321876521
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverse(ListNode head, int k) {


        ListNode root = head ;










        return null;

    }

    /**
     * K 个链表元素进行反转
     * 1234567812  4
     * 4321876521
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode begin;
        if (head == null || head.next == null || k == 1){
            return head;
        }
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        begin = dummyhead;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyhead.next;

    }

    public static ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode next, first;
        ListNode prev = begin;
        first = curr;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }


}
