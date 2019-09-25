package practice09;

class Solution {
	static String[] words;
	static int n = 0;
	int answer = 10000;
	boolean isTarget = false;
	
    public int solution(String begin, String target, String[] words) {
        n = words.length;
    	Solution.words = words;
    	
    	boolean isVisit[] = new boolean[n];
    	
    	dfs(isVisit, begin, target, 0);
    	
    	if(answer==10000)
    		return 0;
        return answer;
    }
    
    //�ٸ� �κ��� 1���� ��쿡�� true ��ȯ
    boolean isChange(String str1, String str2) {
    	int cnt = 0;
    	for(int i=0; i<str1.length(); i++) {
    		if(str1.charAt(i) != str2.charAt(i))
    			cnt++;
    		if(cnt > 1)
    			break;
    	}
    	if(cnt==1)
    		return true;
    	else
    		return false;
    }
    
    void dfs(boolean isVisit[], String begin, String target, int depth) {
    	System.out.println(begin + " " + depth);
    	
    	//begin�� target�� ���ٸ� answer update�ϰ� ����
    	if(begin.equals(target)) {
    		if(depth < answer)
    			answer = depth;
    		isTarget = true;
    		return;
    	}
    	
    	for(int i=0; i<n; i++) {
    		//���� �湮 �� �Ѱ� �߿���
    		if(!isVisit[i]) {
    			//begin���� words[i]�� ���� �����ϸ�
    			if(isChange(begin, words[i])) {
    				isVisit[i] = true;
    				dfs(isVisit, words[i], target, depth+1);
    				isVisit[i] = false;
    			}
    		}
    	}
    }
}

public class WordChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log"};
		
		Solution s = new Solution();
		int result = s.solution(begin, target, words);
		System.out.println(result);
	}

}
