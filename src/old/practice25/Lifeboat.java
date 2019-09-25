/*
 * 2019-08-07 완료
 * 프로그래머스 탐욕법 구명보트
 */


package practice25;

import java.util.*;

class Solution {
	public int solution(int[] people, int limit) {
		int answer = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < people.length; i++) {
			if (people[i] <= limit - 40) {
				if (map.containsKey(people[i])) {
					map.put(people[i], map.get(people[i]) + 1);
				} else {
					map.put(people[i], 1);
				}
			} else {
				answer++;
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			if(entry.getValue() >= 1) {
				while(map.get(key)!=0) {
					boolean check = false;
					map.put(key, map.get(key)-1);
					for(int i=limit-key; i>=40; i--) {
						if(map.containsKey(i) && map.get(i) >= 1) {
							map.put(i, map.get(i)-1);
							answer++;
							check = true;
							break;
						}
					}
					if(!check) {
						answer += (map.get(key)+1);
						break;
					}
				}
			}
			
		}
		return answer;
	}
}

public class Lifeboat {

	public static void main(String[] args) {
		int[] people = {40, 40, 40,50, 50, 50, 50};
		int limit = 100;

		Solution s = new Solution();
		int answer = s.solution(people, limit);
		System.out.println(answer);

	}

}
