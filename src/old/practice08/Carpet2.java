package practice08;

class Solution {
	
	int[] answer = new int[2];
	int brown;
	
    public int[] solution(int brown, int red) {
    	this.brown = brown;
    	int sum = brown + red;
    	int pivot = sum/2;
    	
    	search(pivot, 1, sum);
    	
        return answer;
    }
    
    void search(int pivot1, int pivot2, int sum) {
    	int temp = pivot1 * pivot2;
    	if(temp < sum) {
    		search(pivot1, ++pivot2, sum);		
    	}
    	else if(temp > sum) {
    		search(--pivot1, 1, sum);
    	}
    	else{ //temp == sum;
    		if(2 * (pivot1+pivot2) - 4 == brown) {
	    		answer[0] = pivot2;
	    		answer[1] = pivot1;
    		}
    		return;
    	}
    	return;
    }
}

public class Carpet2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		System.out.println(s.solution(24, 24)[0]);
		System.out.println(s.solution(24, 24)[1]);
	}

}
