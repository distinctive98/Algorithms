/**
 * 백준 17142번 연구소3
 */

package practice21;

import java.io.*;
import java.util.*;

class Virus {
	int x;
	int y;
	
	public Virus(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}

class Solution {
	
	int N;
	int C;
	int[][] lab;
	Virus[] v;
	
	public int solution() throws IOException {
		init();
		
		return 0;
	}
	
	public void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		lab = new int[N][N];
		Virus[] v_temp = new Virus[10];
		
		int v_cnt = 0;
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp==2) {
					v_temp[v_cnt] = new Virus(i, j);
					v_cnt++;
				}
				lab[i][j] = temp;
			}
		}
		
		v = new Virus[v_cnt];
		for(int i=0; i<v_cnt; i++) {
			v[i] = v_temp[i];
			//System.out.println(v[i].getX() + ", " + v[i].getY());
		}
	}
	
	public void virusDFS() {
		
	}
	
	
	
}

public class Lab3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		s.init();
	}

}
