/**
 * programmers > ����Ž�� > ī��
 * 
 * ��1 : R+B = xy
 * ��2 : B = 2(x+y)-4
 * 2�� ������ : 2x^2 - (B+4)x + 2(R+B) = 0 (y�� ���� �ĵ� ����)
 */

package practice07;

class Solution {
	public int[] solution(int brown, int red) {
		//�Ǻ���
		int d = ((int)Math.pow(brown+4, 2)) - (4*2*(2*(brown+red))) ;
		
		int[] answer = new int[2];
		if(d == 0) {
			int x = (brown + 4) / 4;
			answer[0] = x;
			answer[1] = x;
			//System.out.println(x);
		}
		else {
			int x1 = getX1(brown, red, d);
			int x2 = getX2(brown, red, d);
			answer[0] = x1;
			answer[1] = x2;
			//System.out.println(x1 + ", " + x2);
		}
		
		return answer;
	}
	
	//���� ����
	int getX1(int b, int r, int d) {
		return ((b+4) + ((int)Math.sqrt(d)))/4;
	}
	
	int getX2(int b, int r, int d) {
		return ((b+4) - ((int)Math.sqrt(d)))/4;
	}
}

public class Carpet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int b = 24;
		int r = 24;
		
		Solution s = new Solution();
		int[] a = s.solution(b, r);
	}
}