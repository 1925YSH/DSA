class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Dummy node makes building the result easier
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        int carry = 0;

        // Continue while either list has nodes
        // OR there is a remaining carry
        while (l1 != null || l2 != null || carry != 0) {

            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Current digit
            current.next = new ListNode(sum % 10);
            current = current.next;

            // Carry for next position
            carry = sum / 10;
        }

        return dummy.next;
    }
}