package LinkedList;


//You are given an array of integers nums and the head of a linked list.
// Return the head of the modified linked list after removing all nodes
// from the linked list that have a value that exists in nums.
//nums = [1, 2, 3]
//        LinkedList = 1 → 2 → 4 → 5 → 3 → 6
//Output
//        4 → 5 → 6


import java.util.HashSet;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class RemoveNode {
    public static ListNode modifiedList(int[] nums, ListNode head) {
        // Step 1: Add nums to a HashSet for O(1) lookup
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        // Step 2: Use a dummy node to handle removals easily
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        // Step 3: Traverse the list and remove matching nodes
        while (curr != null) {
            if (set.contains(curr.val)) {
                prev.next = curr.next;  // remove node
            } else {
                prev = curr;            // move prev forward
            }
            curr = curr.next;           // move curr forward
        }

        // Step 4: Return new head
        return dummy.next;
    }

    // Utility function to print the list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        // Linked list: 1 -> 2 -> 4 -> 5 -> 3 -> 6
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(6);

        head = modifiedList(nums, head);
        printList(head);  // Output: 4 5 6
    }
}
