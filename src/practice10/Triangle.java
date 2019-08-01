package practice10;

class Solution {
	
	int[][] triangle;
	int answer = 0;
	int max_depth = 0; //ÃÖ´ë ±íÀÌ
	int max_sum = 0;
	
    public int solution(int[][] triangle) {
    	this.triangle = triangle;
    	max_depth = triangle.length-1;
    	max_sum = triangle[0][0];
    	
    	dfs(0, 0, max_sum);
    	
        return max_sum;
    }
    
    void dfs(int i, int j, int sum) {
    	if(i==max_depth) {
    		if(sum > max_sum)
    			max_sum = sum;
    		return;
    	}   	
    	dfs(i+1, j, sum+triangle[i+1][j]);
    	dfs(i+1, j+1, sum+triangle[i+1][j+1]);
    }
}

public class Triangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		
		Solution s = new Solution();
		int r = s.solution(triangle);
		
		System.out.println(r);
		
		
	}

}
