package practice14;

class Solution {		
	int[][] puddles;
	int m, n;
	int length = 100000;
	int cnt = 0;
	
    public int solution(int m, int n, int[][] puddles) {
    	this.puddles = puddles;
    	this.m = m;
    	this.n = n;
    	
    	dfs(0, 0, 0);
       
        return cnt%1000000007;
    }
    
    boolean isPuddles(int i, int j){
		for(int k=0; k<puddles.length; k++) {
			if(puddles[k][0]-1 == i && puddles[k][1]-1 == j) {
				return true;
			}
		}
		return false;
	}
	
	void dfs(int i, int j, int depth) {
		if(i==n-1 && j==m-1) {
			if(length > depth) {
				length = depth;
				cnt = 0;
			}
			cnt++;
			System.out.println(depth + " " + cnt);
		}
		
		//오른쪽
		if(j+1 < m && !isPuddles(i, j+1)) {
			dfs(i, j+1, depth+1);
		}
		//아래
		if(i+1 < n && !isPuddles(i+1, j)) {
			dfs(i+1, j, depth+1);
		}
	}
}

public class TheWayToSchool {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=4;
		int n=3;
		int puddles[][] = {{1,2}, {2,2}, {2,3}, {2,4}};
		
		Solution s = new Solution();
		int r= s.solution(m, n, puddles);
		System.out.println(r);
	}
}
