/*
 * Comparator를 사용한 우선순위 큐
 */

package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = {999, 120, 0, 0, 9};
		PriorityQueue<String> pq = new PriorityQueue<>(numbers.length, new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				int len = s1.length() > s2.length() ? s1.length() : s2.length();
				for(int i=0; i<len; i++) {
					//비교 숫자 배치
					int num1, num2;
					if(s1.length() <= i) {
						num1 = s1.charAt(s1.length()-1);
					} else {
						num1 = s1.charAt(i);
					}
					if(s2.length() <= i) {
						num2 = s2.charAt(s2.length()-1);
					} else {
						num2 = s2.charAt(i);
					}
					
					//숫자 비교
					if(num1 > num2) {
						return -1;
					} else if(num1 < num2) {
						return 1;
					} else {
						continue;
					}
				}
				return 0;
			}
			
		});
		
		for(int i=0; i<numbers.length; i++) {
			pq.add(Integer.toString(numbers[i]));
		}
		
		StringBuilder answer = new StringBuilder();
		for(int i=0; i<numbers.length; i++) {
			StringBuilder temp = new StringBuilder(pq.peek());
			pq.remove();
			answer.append(temp);
		}
		
		System.out.println(answer.toString());		
	}
}
