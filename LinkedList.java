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
}
