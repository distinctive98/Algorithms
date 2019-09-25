
package practice31;

import java.util.*;

class Solution {
	int[][] pic = null;
	PriorityQueue<Integer> pq = null;
	int m = 0;
	int n = 0;
	int cnt = 0;

	public int[] solution(int m, int n, int[][] picture) {
		pic = picture;
		this.m = m;
		this.n = n;

		pq = new PriorityQueue<>((x, y) -> y - x);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pic[i][j] != 0)
					search(i, j);
			}
		}

		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		if (!pq.isEmpty()) {
			numberOfArea = pq.size();
			maxSizeOfOneArea = pq.peek();
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	void search(int i, int j) {
		dfs(pic[i][j], i, j);
		pq.add(cnt);
		System.out.println(pq.toString());
		cnt = 0;
	}

	void dfs(int value, int i, int j) {
		pic[i][j] = 0;
		cnt++;
		System.out.println(cnt);
		
		// top
		if (i - 1 >= 0 && pic[i - 1][j] == value) {
			dfs(value, i - 1, j);
		}

		// bottom
		if (i + 1 < m && pic[i + 1][j] == value) {
			dfs(value, i + 1, j);
		}

		// left
		if (j - 1 >= 0 && pic[i][j - 1] == value) {
			dfs(value, i, j - 1);
		}

		// right
		if (j + 1 < n && pic[i][j + 1] == value) {
			dfs(value, i, j + 1);
		}
	}

}

public class ColoringBook {

	public static void main(String[] args) { // TODO Auto-generated method stub
		int m = 6;
		int n = 4;
		int[][] picture = { { 1, 1, 1, 1 }, { 1, 2, 2, 2 }, { 1, 1, 1, 1 }, { 0, 0, 0, 1 }, { 1, 1, 1, 1 },
				{ 1, 3, 0, 1 } };

		Solution s = new Solution();
		int a[] = s.solution(m, n, picture);
		System.out.println(a[0] + ", " + a[1]);
	}

}
