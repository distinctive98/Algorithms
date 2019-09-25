/**
 * 프로그래머스 동적계획법(DP) 도둑질 Level4
 */

package practice22;

class Solution {
	int[] m;

	// top-down
	public int solution(int[] money) {
		m = money;
		//stealMoney(0, money.length - 2);
		//stealMoney(1, money.length-1);
		//return 0;
		return Math.max(stealMoney(0, money.length-2), stealMoney(1,money.length-1));
	}

	public int stealMoney(int start, int end) {
		int money = m[start];
		while (end==m.length-1 ? start < end-1 : start < end) {
			if(start+2 <= end) {
				int sum1 = m[start] + m[start + 2];
				int sum2 = m[start] + m[start + 3 >= end ? start + 2 : start + 3];
				if (sum1 >= sum2) {
					start += 2;
				} else {
					start += 3;
				}
			}
			money += m[start];
			//System.out.println(money);
			
		}

		return money;
	}
}

public class Stealing {

	public static void main(String[] args) {
		int[] money = {1,2,3,1};

		Solution s = new Solution();
		int answer = s.solution(money);
		System.out.println(answer);
	}

}
