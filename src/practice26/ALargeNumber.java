/*
 * 2019-08-08 완료
 * 프로그래머스 탐욕법 큰 수 만들기
 */

package practice26;

class Solution {
	public String solution(String number, int k) {
		StringBuffer sb = new StringBuffer();
		sb.append(number);

		int max = 0, min = 9;
		for (int i = 0; i < sb.length(); i++) {
			max = Math.max(sb.charAt(i) - '0', max);
			min = Math.min(sb.charAt(i) - '0', min);
		}

		String answer = "";
		int length = sb.length() - k; // 결과값의 length
		int start = 0;
		int end = sb.length() - length + 1;
		while (answer.length() != length) {
			int index = start;
			loop: for (int n = max; n >= min; n--) {
				for (int i = start; i < end; i++) {
					if (sb.charAt(i)-'0' == n) {
						index = i;
						answer += n;
						break loop;
					}
				}
			}

			start = index + 1;
			end++;
		}

		return answer;
	}
}

public class ALargeNumber {

	public static void main(String[] args) {
		String number = "999999999";
		int k = 4;

		Solution s = new Solution();
		String a = s.solution(number, k);
		System.out.println(a);

	}
}
