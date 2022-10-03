/*Question:-A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
*/

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeMap<Integer, List<int[]>> coor=new TreeMap<>(); //treeset for maintaing sorted coordinates
        for(int[] b: buildings) {
            //add left coordinate
            int arr[]=new int[2];
            List<int[]> l=coor.getOrDefault(b[0], new ArrayList<>());
            arr[0]=b[1]; //right coordinate
            arr[1]=b[2]; //height
            l.add(arr);
            coor.put(b[0], l);
            //add right coordinate
            arr=new int[2];
            l=coor.getOrDefault(b[1], new ArrayList<>());
            arr[0]=b[1]; //right coordinate
            arr[1]=b[2];//height
            l.add(arr);
            coor.put(b[1], l);
        }
        Queue<int[]> pq=new PriorityQueue<>((i, j)->(j[1]-i[1])); //max heap
        List<List<Integer>> ret=new ArrayList<>(); //answer list
        int lastLineHeight=-1; //last height 
        for(Map.Entry<Integer, List<int[]>> e: coor.entrySet()) {
            int x=e.getKey();
            List<int[]> val=e.getValue();
			//adding values in priority queue
            for(int[] a: val) {
                pq.add(a);
            }
            //removing those buildings whose right coordinate is already passed x
            while(!pq.isEmpty() && (pq.peek())[0]<=x) 
                pq.poll();
            
            int cur[]=new int[2];
            if(!pq.isEmpty()) {
                cur=pq.peek(); 
            } else {
			//priority queue is empty so adding 0
                cur[0]=x;
                cur[1]=0;
            }
            List<Integer> ans=new ArrayList<>();
            
            if(lastLineHeight!=cur[1]) { //last line should not be equal to current largest height building
                ans.add(x);
                ans.add(cur[1]);
                lastLineHeight=cur[1];
            }
            if(!ans.isEmpty())
            ret.add(ans);
        }
        return ret; //returning answer
    }
}
