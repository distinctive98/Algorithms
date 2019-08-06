package practice24;

import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
    	int answer = n - lost.length;
    	HashMap<Integer, Integer> mapReserve = new HashMap<>();
    	HashMap<Integer, Integer> mapLost = new HashMap<>();
        
    	//hashlize
        for(int i=0; i<reserve.length; i++) {
        	mapReserve.put(reserve[i], i);
        }
        for(int i=0; i<lost.length; i++) {
        	mapLost.put(lost[i], i);
        }
        
        for(int i=0; i<lost.length; i++) {
        	//잃어버린 학생이 여분이 있는 학생이면
        	if(mapReserve.containsKey(lost[i])) {
        		answer++;
        		continue;
        	}
        	
        	//도난당한 학생의 전 번호가 여분이 있는 학생의 번호면
        	//그리고 그 여분이 있는 학생이 도난당한 학생이 아니면
        	if(mapReserve.containsKey(lost[i]-1) && !mapLost.containsKey(lost[i]-1)) {
        		mapReserve.remove(lost[i]-1);
        		answer++;
        		continue;
        	}
        	
        	//앞 방향에서 다시 확인
        	if(mapReserve.containsKey(lost[i]+1) && !mapLost.containsKey(lost[i]+1)) {
        		mapReserve.remove(lost[i]+1);
        		answer++;
        		continue;
        	}
        }
    	
        return answer;
    }
}

public class GymClothes {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		int n = 5;
		int lost[] = {2, 4};
		int reserve[] = {3};
		int answer = s.solution(n, lost, reserve);
		System.out.println(answer);
	}

}
