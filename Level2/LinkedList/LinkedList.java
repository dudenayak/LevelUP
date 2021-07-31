package Level2.LinkedList;

public class LinkedList {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // MIDNODE
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

    // 2nd MIDNODE
    public ListNode middleNode(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // REVERSE
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, current = head;
        while (current != null) {
            ListNode forw = current.next; // backup
            current.next = prev; // link
            prev = current; // move
            current = forw;
        }
        return prev;
    }

    // PALINDROME
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;
        newHead = reverse(newHead);

        boolean flag = true;
        ListNode c1 = head, c2 = newHead;
        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                flag = false;
                break;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        newHead = reverse(newHead);
        mid.next = newHead;
        return flag;

    }

    // FOLD
    public static void fold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;
        newHead = reverse(newHead);

        ListNode c1 = head, c2 = newHead;
        while (c2 != null) {
            ListNode f1 = c1.next, f2 = c2.next;
            c1.next = c2;
            c2.next = f1;
            c1 = f1;
            c2 = f2;

        }

    }

    // UNFOLD
    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode c1 = head, c2 = head.next, newHead = c2;
        while (c2 != null && c2.next != null) {
            ListNode f1 = c2.next;
            c1.next = f1;
            c2.next = f1.next;

            c1 = c1.next; // c1=f1 kyuki upar c1.m=next f1 ko point krrha hai toh c1 ab c1 k
            // next k equal kr denge (because c1 ka next is updated toh yaha use kr sakte
            // hai..same reason goes for c2.next)
            c2 = c2.next; // c2=c1.next
        }

        c1.next = null; // jese reverse mei prev equlas null ko point karrate hai vo vali cheez
        newHead = reverse(newHead);
        c1.next = newHead; // reversed ll ko link krrhe hai a,b,c,d se.....mtlb a,b,c,d,e,f,g

        // Let say original ll is a,b,c,d,e,f,g...usko fold kia toh a,g,b,f,c,e,d....and
        // isko unfold krna hai toh a,b,c,d...and g,f,e toh ab is g,f,e ko reverse kr k
        // a,b,c,d k sath link kr lenge and c1.next=nhead se hum link hi krrhe hai...so
        // our unfolded list would be ...a,b,c,d,e,f,g

    }

    // Merge Two Sorted Linkedlist
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

    // MERGESORT LL
    public static ListNode mergeSort(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode mid = midNode(head);
        ListNode newHead = mid.next;
        mid.next = null;

        return mergeTwoLists(mergeSort(head), mergeSort(newHead));
    }

    // CLASS 2
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists_01(lists);
        // return mergeKLists_02(lists);
        // return mergeKLists_03(lists);
    }

    // LEETCODE Q.23
    public static ListNode mergeKLists_01(ListNode[] arr) { // we made [] because array is of listnode type
        ListNode res = null;
        for (ListNode list : arr) {
            res = mergeTwoLists(res, list);
        }
        return res;
    }

    // 2nd Approach for same question
    public ListNode getTail(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        while (curr.next != null)
            curr = curr.next;

        return curr;
    }

    public ListNode mergeKLists_02(ListNode[] arr) {
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
    } // add midnode mergesort mergetwolist

    // 3rd Approach for same question
    public ListNode mergeKListss(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        return mergeKLists_03(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists_03(ListNode[] arr, int si, int ei) {
        if (si == ei)
            return arr[si];

        int mid = (si + ei) / 2;
        return mergeTwoLists(mergeKLists_03(arr, si, mid), mergeKLists_03(arr, mid + 1, ei));
    } // add mergetwolists

    // Segregate Even And Odd Nodes In A Linkedlist
    public static ListNode segregateEvenOdd(ListNode head) {
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

    // Remove Nth Node From End Of Linkedlist
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, forw = dummy;
        while (n-- > 0)
            forw = forw.next;

        while (forw.next != null) {
            forw = forw.next;
            prev = prev.next;
        }

        ListNode dNode = prev.next;
        prev.next = dNode.next;

        dNode.next = null; // delete dNode

        return dummy.next;
    }

    // Is Cycle Present In Linkedlist
    public static boolean isCyclePresentInLL(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }
        return false;
    }

}
