package Level2.LinkedList;

public class Class1 {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link
            prev = curr; // move
            curr = forw;
        }
        return prev;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverse(nhead);

        boolean flag = true;
        ListNode c1 = head, c2 = head;
        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                flag = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        nhead = reverse(nhead);
        mid.next = nhead;
        return flag;
    }

    public static void fold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        nhead = reverse(nhead);

        ListNode c1 = head, c2 = nhead;

        while (c1 != null && c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode c1 = head, c2 = head.next, nHead = c2;
        while (c2 != null && c2.next != null) {
            ListNode f1 = c2.next;

            c1.next = f1;
            c2.next = f1.next;

            c1 = c1.next; // c1 = f1;
            c2 = c2.next; // c2 = c1.next;
        }

        c1.next = null;
        nHead = reverse(nHead);
        c1.next = nHead;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = c1 != null ? c1 : c2;
        ListNode head = dummy.next;
        dummy.next = null; // delete dummy;
        return head;
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode mid = midNode(head);
        ListNode nHead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(nHead));
    }

    public ListNode mergeSortedList_01(ListNode[] arr) {
        ListNode res = null;
        for (ListNode list : arr) {
            res = mergeTwoLists(res, list);
        }
        return res;
    }

    public static ListNode getTail(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }

    public ListNode mergeKSortedList_02(ListNode[] arr) {
        if (arr.length == 0)
            return null;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        for (int i = 0; i < arr.length; i++) {
            ListNode tail = getTail(arr[i]);
            if (tail != null) {
                prev.next = arr[i];
                prev = tail;
            }
        }

        return mergeSort(dummy.next);
    }

    public ListNode mergeKSortedList_03(ListNode[] arr, int si, int ei) {
        if (si == ei)
            return arr[si];

        int mid = (si + ei) / 2;
        return mergeTwoLists(mergeKSortedList_03(arr, si, mid), mergeKSortedList_03(arr, mid + 1, ei));
    }

    public ListNode seggregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1), odd = new ListNode(-1);
        ListNode ep = even, op = odd, curr = head;
        while (curr != null) {
            if (curr.val % 2 != 0) {
                op.next = curr;
                op = op.next;
            } else {
                ep.next = curr;
                ep = ep.next;
            }
            curr = curr.next;
        }
        ep.next = null;
        op.next = null;
        ep.next = odd.next;
        return even.next;
    }

    public int length(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    public ListNode reverseInKGroup(ListNode head, int k) {
        return null;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pivotNode = getTail(head);

        ListNode large = new ListNode(-1), small = new ListNode(-1), sp = small, lp = large, curr = head;
        while (curr != null) {
            if (curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = lp.next = null;
        sp.next = large.next;
        return small.next;
    }

    public static ListNode[] segregate(ListNode head, int pivotIdx) {
        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        ListNode large = new ListNode(-1), small = new ListNode(-1), sp = small, lp = large, curr = head;
        while (curr != null) {
            if (curr != pivotNode && curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else if (curr != pivotNode) {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = lp.next = pivotNode.next = null;
        // sp.next = pivotNode;
        // pivotNode.next = large.next;
        // return small.next;

        ListNode leftHead = small.next == pivotNode ? null : small.next;
        ListNode rightHead = large.next;
        return new ListNode[] { leftHead, pivotNode, rightHead };
    }

    public static ListNode[] mergeLists(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        ListNode[] myRes = new ListNode[2];
        if (left[0] != null && right[0] != null) {
            myRes[0] = left[0];
            myRes[1] = right[1];

            left[1].next = pivotNode;
            pivotNode.next = right[0];
        } else if (right[0] != null) {
            myRes[0] = pivotNode;
            myRes[1] = right[1];

            pivotNode.next = right[0];
        } else {
            myRes[0] = left[0];
            myRes[1] = pivotNode;

            left[1].next = pivotNode;
        }

        return myRes;
    }

    public static int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }

    // {head, tail}
    public static ListNode[] quickSort_(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        int len = getLength(head);
        int pivotIndex = len / 2; // 0, len - 1, len/2,
        ListNode[] partition = segregate(head, pivotIndex);

        ListNode[] left = quickSort_(partition[0]);
        ListNode[] right = quickSort_(partition[2]);

        return mergeLists(left, partition[1], right);
    }

    public static ListNode quickSort(ListNode head) {
        return quickSort_(head)[0];
    }

}