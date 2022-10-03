/*Question:-Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 */

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        for(int i=0;i<arr.length;i++){
            int mod = Math.abs(arr[i]-x);
            pq.add(new int[] {mod,i});
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<k;i++){
            int[] curr = pq.poll();
            list.add(arr[curr[1]]);
        }
        Collections.sort(list);
        return list;
    }
}
