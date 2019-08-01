package practice05;

class Solution {
	long[] fibo;
	
    public long solution(int N) {
    	fibo = new long[N];
    	
    	if(N==1) 
			fibo[0] = 4;
		else{
			fibo[0] = 4;
			fibo[1] = 6;
		}
    	
    	if(N > 2) {
    		fibonacci(N, 2);
    	}
    	
        return fibo[N-1];
    }
    
    void fibonacci(int N, int n) {
		if(N == n)
			return;
		
		fibo[n] = fibo[n-1] + fibo[n-2];
		fibonacci(N, n+1);
	}
}

public class Tile {

	public static void main(String[] args) {
		
		Solution s = new Solution();
		System.out.println(s.solution(6));
	}
}
