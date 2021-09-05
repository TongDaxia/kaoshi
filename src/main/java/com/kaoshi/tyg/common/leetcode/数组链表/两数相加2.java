package com.kaoshi.tyg.common.leetcode.数组链表;

public class 两数相加2 {

    public static void main(String[] args) {
        ListNode root = new ListNode(0);
        root.next = new ListNode(1);
        root.next.next = new ListNode(2);

        printListNode(root);

        ListNode revert = revert(root);

        printListNode(revert);
    }

    private static void printListNode(ListNode revert) {
        System.out.println("--------------------");
        if (revert == null) {
            System.out.println("NULL");
        }
        ListNode cur = revert;
        while (cur != null) {
            System.out.print(cur.node + "-->");
            cur = cur.next;
        }
        System.out.println("--------------------");

    }


    /**
     * 1 2 3 7 5
     * 0 3 4 5 0
     * 1 5 8 2 5
     *
     * @param l1 1 2 3 7 5
     * @param l2 3 4 5 0
     * @return 1 5 8 2 5
     */
    public ListNode addTwo(ListNode l1, ListNode l2) {

        ListNode ll1 = revert(l1);
        ListNode ll2 = revert(l2);


        ListNode listNode = new ListNode(0);
        return listNode;
    }

    private static ListNode revert(ListNode l2) {

        if (l2 == null || l2.next == null) {
            return l2;
        }


        ListNode cur = l2;
        ListNode next = cur.next;
        while (next != null) {
            ListNode nextTemp = next.next;

            next.next = cur;

            cur = next;

            next = nextTemp;
        }

        return cur;



    }
}
