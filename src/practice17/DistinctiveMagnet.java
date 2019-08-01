/**
 * 2019-04-26 완료!
 */

package practice17;

import java.io.*;
import java.util.*;

public class DistinctiveMagnet {

	final static int MAGNET_NUM = 4;
	final static int ELEMENT_NUM = 8;
	
	static void rotation(int[][] magnet, int num, int dir) {
		
		int r_Cnt = 0, l_Cnt = 0;
		
		//오른쪽 방향 카운트
		for(int i=num; i<MAGNET_NUM; i++) {
			if(magnet[i][2] != magnet[i+1][6]) {
				r_Cnt++;
			}
			else
				break;
		}
		
		//왼쪽 방향 카운트
		for(int i=num; i>1; i--) {
			if(magnet[i][6] != magnet[i-1][2]) {
				l_Cnt++;
			}
			else
				break;
		}
		
		//System.out.println(r_Cnt + " " + l_Cnt);
		
		rotationMagnet(magnet[num], dir);
		if(r_Cnt > 0) {
			int newDir = dir;
			for(int i=num+1; i<=num+r_Cnt; i++) {
				newDir = -newDir;
				rotationMagnet(magnet[i], newDir);
			}
		}
		
		if(l_Cnt > 0) {
			int newDir = dir;
			for(int i=num-1; i>=num-l_Cnt; i--) {
				newDir = -newDir;
				rotationMagnet(magnet[i], newDir);
			}
		}
		
	}
	
	static void rotationMagnet(int[] magnet, int dir) {
		if(dir == 1) {
			int temp = magnet[7];
			for(int i=ELEMENT_NUM-1; i>0; i--) {
				magnet[i] = magnet[i-1];
			}
			magnet[0] = temp;
		}
		else {
			int temp = magnet[0];
			for(int i=0; i<ELEMENT_NUM-1; i++) {
				magnet[i] = magnet[i+1];
			}
			magnet[7] = temp;
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			int K = Integer.parseInt(br.readLine());
			
			int[][] magnet = new int[MAGNET_NUM+1][ELEMENT_NUM+1];
			
			for(int i=1; i<=MAGNET_NUM; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j=0; j<ELEMENT_NUM; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
	
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				rotation(magnet, num, dir);
			}
			
			/*
			for(int[] rowData : magnet) {
				for(int data : rowData)
					System.out.print(data + " ");
				System.out.println();
			}
			*/
			
			int result = 0;
			for(int i=1; i<=MAGNET_NUM; i++) {
				if(magnet[i][0] == 1) {
					if(i == 1) result += 1;
					if(i == 2) result += 2;
					if(i == 3) result += 4;
					if(i == 4) result += 8;
				}
			}
			
			System.out.println("#" + t + " " + result);
			
		} //for(t)
		
		
		
	}

}
