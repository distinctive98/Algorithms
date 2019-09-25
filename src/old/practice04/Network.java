package practice04;

class Solution {
	
	int answer = 0;
	boolean[][] check;
	
    public int solution(int n, int[][] computers) {
    	//check 배열변수 초기화
    	check = new boolean[n][n];
    	for(int i=0; i<n; i++) {
    		for(int j=i; j<n; j++) {
    			if(computers[i][j]==1)
    				check[i][j] = true;
    		}
    	}
    	
    	//check 배열 순회
    	for(int i=0; i<n; i++) {
    		for(int j=i; j<n; j++) {
    			if(check[i][j])
    				dfs(true, i, j);
    		}
    	}
        
        return answer;
    }
    
    void dfs(boolean isFirst, int i, int j) {
    	//System.out.println(i +"," + j);
    	//첫 방문인 경우
    	if(isFirst) {
    		answer++;
    		isFirst = false;
    	}
    	
    	//현재 연결돼있는 경우
    	if(check[i][j] && i!=j)
    		dfs(isFirst, j, j);
    	//경계가 아닌 이상 열 전진
    	if(check[i].length > j+1)
    		dfs(isFirst, i, j+1);
    	
    	check[i][j] = false;
    }
}


public class Network {
	
	public static void main(String[] args) {
		//int[][] computers = new int[][] {{1, 1, 0},{1, 1, 0},{0, 0, 1}};
		//int[][] computers = new int[][] {{1, 1, 0},{1, 1, 1},{0, 1, 1}};
		int[][] computers = new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

		int n=3;
		
		Solution s = new Solution();
		int answer = s.solution(n, computers);
		System.out.println(answer);
	}

}











/*
class Search{
	int n;
	int answer;
	boolean[][] isVisit;
	
	Search(int n, int[][] computers){
		this.n = n;
		answer = 0;
		isVisit = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				if(computers[i][j]==1)
					isVisit[i][j] = true;
			}
		}
	}
	
	void dfs(int depth, int i, int j) {
		if(depth==1) 
			answer++;
		
		if(i!=j && isVisit[i][j])
			dfs(depth+1, j, j);
		if(j+1 < n)
			dfs(depth+1, i, j+1);
		
		isVisit[i][j] = false;
	}
}

class Solution {
    public int solution(int n, int[][] computers) {
 
    	Search s = new Search(n, computers);
    	
    	for(int i=0; i<n; i++) {
    		for(int j=i; j<n; j++) {
    			if(s.isVisit[i][j])
    				s.dfs(1, i, j);
    		}
    	}
    	
        int answer = s.answer;
        return answer;
    }
}

public class Network {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[][] computers = new int[][] {{1, 1, 0},{1, 1, 0},{0, 0, 1}};
		//int[][] computers = new int[][] {{1, 1, 0},{1, 1, 1},{0, 1, 1}};
		int[][] computers = new int[][] {{1, 0, 0, 0, 0, 0},{0, 1,0, 0, 0, 0},{0, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 1}, {0, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 1}};
		
		
		Solution s = new Solution();
		int answer = s.solution(6, computers);
		System.out.println(answer);
		
		
	}

}
*/