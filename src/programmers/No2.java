package programmers;

class Solution2{
    public int solution(int n, int a, int b){
        int answer = getRound(a, b, 1);
        return answer;
    }
    
    int getRound(int a, int b, int depth) {
    	a = a%2!=0?a+1:a;
    	b = b%2!=0?b+1:b;
    	if(a==b) {
    		return depth;
    	}
    	
    	return getRound(a/2, b/2, depth+1);
    }
}

public class No2 {

	public static void main(String[] args) {
		Solution2 s = new Solution2();
		int a = s.solution(8, 4, 7);
		System.out.println(a);

	}

}
