/**
 * LYG
 * 2019-04-29 완료
 * 근데 어떻게 성공했는지 모르겠음(search 메소드 부분)
 * 첫 DFS!
 */

package practice16;

import java.io.*;
import java.util.*;

public class DestroyBlock {
	
	static int[][][] map;
	static int N, W, H;
	
	static void showMap() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int countBlock(int[][] map) {
		int count = 0;
		
		for(int[] rowData : map) {
			for(int data : rowData) {
				if(data != 0) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	static void renewMap(int map[][]) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for(int j=0; j<W; j++) {			
			for(int i=H-1; i>=0; i--) {
				if(map[i][j] != 0) {
					if(!queue.isEmpty()) {
						map[queue.remove()][j] = map[i][j];
						map[i][j] = 0;
					}
				}
				
				if(map[i][j] == 0)
					queue.add(i);
			}
			
			while(!queue.isEmpty()) {
				queue.remove();
			}
			
		}
		
	}
	
	static int searchTheBestWay() {
		
		int index = 0;
		int depth = 1;
		int min = 999;
		
		for(int i=0; i<W; i++) {
			int count = search(map, i, depth, min);
			if(min > count) {
				min = count;
			}
		}
		return min;
	}
	
	static int search(int[][][] map, int index, int depth, int min) {
		
		copyMap(map, depth);
		attackBlock(map[depth], index);
		
		if(depth == N) {
			int count = countBlock(map[depth]);
			if(min > count) {
				min = count;
			}
			return min;
		}
		else {
			for(int i=0; i<W; i++) {
				min = search(map, i, depth+1, min);
			}
		}
		
		return min;
	}
	
	static void copyMap(int[][][] map, int depth) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {

				map[depth][i][j] = map[depth-1][i][j];
			
			}
		}
	}
		
	static void attackBlock(int[][] map, int index) {
		int indexi = 0;
		int indexj = index;
			
		//0이 아닐 때까지 내려오기
		for(int i=0; i<H; i++) {
			if(map[i][indexj] != 0) {
				indexi = i;
				break;
			}
		}
		
		destroyBlock(map, indexi, indexj);
		renewMap(map);
	}
	
	static void destroyBlock(int[][] map, int indexi, int indexj) {
		
		int num = map[indexi][indexj];
		map[indexi][indexj] = 0;
		
		//좌측 정리
		destroyLeftBlock(map, num, indexi, indexj);
		
		//우측 정리
		destroyRightBlock(map, num, indexi, indexj);
		
		
		//상단 정리
		destroyTopBlock(map, num, indexi, indexj);	
		
		//하단 정리
		destroyBottomBlock(map, num, indexi, indexj);
		
		
		
	}
	
	static void destroyLeftBlock(int[][] map, int num, int indexi, int indexj) {
		
		for(int j=1; j<num; j++) {
			
			//경계선이면 break;
			if(indexj-j < 0)
				break;
			
			if(map[indexi][indexj-j] == 1) { 
				map[indexi][indexj-j] = 0;
			}
			
			else if(map[indexi][indexj-j] == 0) { 
				continue;
			}
			
			else { 
				destroyBlock(map, indexi, indexj-j);
			}
			
		}
	}
	
	static void destroyRightBlock(int[][] map, int num, int indexi, int indexj) {
	
		for(int j=1; j<num; j++) {

			if(indexj+j > W-1)
				break;
			
			if(map[indexi][indexj+j] == 1) {
				map[indexi][indexj+j] = 0;
			}
			
			else if(map[indexi][indexj+j] == 0) {
				continue;
			}
			
			else { 
				destroyBlock(map, indexi, indexj+j);
			}
					
		}	
	}
	
	static void destroyTopBlock(int[][] map, int num, int indexi, int indexj) {
		
		for(int i=1; i<num; i++) {
			
			if(indexi-i < 0)
				break;
			
			if(map[indexi-i][indexj] == 1) {
				map[indexi-i][indexj] = 0;
			}
			
			else if(map[indexi-i][indexj] == 0) {
				continue;
			}
			
			else { 
				destroyBlock(map, indexi-i, indexj);
			}
			
		}
	}
	
	static void destroyBottomBlock(int[][] map, int num, int indexi, int indexj) {
	
		for(int i=1; i<num; i++) {
			
			if(indexi+i > H-1)
				break;
			
			if(map[indexi+i][indexj] == 1) {
				map[indexi+i][indexj] = 0;
			}
			
			else if(map[indexi+i][indexj] == 0) {
				continue;
			}
			
			else { 
				destroyBlock(map, indexi+i, indexj);
			}
					
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
					
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j=0; j<W; j++) {
					map[0][i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int result = searchTheBestWay();
			System.out.println("#" + t + " " + result);
			
			
		}
		
	}
}
