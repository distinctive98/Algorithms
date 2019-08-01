package practice11;


class Solution {
	public int solution(int[][] triangle) {
		int max_depth = triangle.length;
		int temp[] = triangle[max_depth-1];
		
		for(int i=max_depth-1; i>0; i--) {
			for(int j=0; j<i; j++)
				temp[j] = temp[j] > temp[j+1] ? temp[j] + triangle[i-1][j] : temp[j+1] + triangle[i-1][j]; 
		}	
	    return temp[0];
	}
}

public class Triangle2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		Solution s = new Solution();
		int r = s.solution(triangle);
		System.out.println(r);
	}

}
