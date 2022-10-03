/*Question:-Given the head of a linked list, remove the nth node from the end of the list and return its head.*/

 
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size=0;
        ListNode temp=head;
        while(temp!=null){
            temp=temp.next;
            size++;
        }
        if(n==size){
            head=head.next;
            return head;
        }
        size-=n;
        ListNode t=head;int i=0;
        while(i<(size-1)){
            t=t.next;
            i++;
        }
        t.next=t.next.next;
        return head;
    }
}

 

