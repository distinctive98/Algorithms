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
        	//�Ҿ���� �л��� ������ �ִ� �л��̸�
        	if(mapReserve.containsKey(lost[i])) {
        		answer++;
        		continue;
        	}
        	
        	//�������� �л��� �� ��ȣ�� ������ �ִ� �л��� ��ȣ��
        	//�׸��� �� ������ �ִ� �л��� �������� �л��� �ƴϸ�
        	if(mapReserve.containsKey(lost[i]-1) && !mapLost.containsKey(lost[i]-1)) {
        		mapReserve.remove(lost[i]-1);
        		answer++;
        		continue;
        	}
        	
        	//�� ���⿡�� �ٽ� Ȯ��
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
