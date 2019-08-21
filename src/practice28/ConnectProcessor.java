/**
 * SW Expert 모의 테스트 - 프로세서 연결하기 
 * 2019-08-21 완료
 * DFS 풀이
 */

package practice28;

import java.io.*;
import java.util.*;

class Node {
	int i;
	int j;
	Node(int i, int j){
		this.i = i;
		this.j = j;
	}
}

public class ConnectProcessor {
	
	static int N;
	static int[][] exynos = null;
	static List<Node> nodes = null;
	static List<Integer> result = null;
	static HashMap<Integer, Integer> map = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {	
			
			//초기 셋팅
			N = Integer.parseInt(br.readLine());
			exynos = new int[N][N];
			nodes = new LinkedList<Node>();
			result = new LinkedList<Integer>(); 
			map = new HashMap<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					exynos[i][j] = Integer.parseInt(st.nextToken());
					if(1 <= i && i < N-1 && 1 <= j && j < N-1 && exynos[i][j] == 1) {
						nodes.add(new Node(i, j));
					}
				}
			}
					
			//dfs
			int[][] e = exynos;
			dfs(e, 0, 0, 0);
			
			int answer = 0;
			for(int i=nodes.size(); i>=0; i--) {
				if(map.containsKey(i)) {
					answer = map.get(i);
					break;
				}
			}
			System.out.println("#" + (t+1) + " " + answer);
		}
	}
	
	static void dfs(int[][] e, int depth, int sum, int on) {
		if(depth >= nodes.size()) {
			if(map.containsKey(on)) {
				if(sum < map.get(on)) {
					map.put(on, sum);
				}
			} else {
				map.put(on, sum);
			}
			
			return;
		}
		Node node = nodes.get(depth);
		
		//상
		boolean topFlag = false;
		for(int i=0; i<node.i; i++) {
			if(exynos[i][node.j]==1) {
				topFlag = true;
				break;
			}
		}
		if(!topFlag) {
			updateMatrix(e, "top", node, 1);
			dfs(e, depth+1, sum+node.i, on+1);
			updateMatrix(e, "top", node, 0);
		}
		
		//하
		boolean bottomFlag = false;
		for(int i=node.i+1; i<N; i++) {
			if(exynos[i][node.j]==1) {
				bottomFlag = true;
				break;
			}
		}
		if(!bottomFlag) {
			updateMatrix(e, "bottom", node, 1);
			dfs(e, depth+1, sum+N-1-node.i, on+1);
			updateMatrix(e, "bottom", node, 0);
		}
		
		//좌
		boolean leftFlag = false;
		for(int j=0; j<node.j; j++) {
			if(exynos[node.i][j]==1) {
				leftFlag = true;
				break;
			}
		}
		if(!leftFlag) {
			updateMatrix(e, "left", node, 1);
			dfs(e, depth+1, sum+node.j, on+1);
			updateMatrix(e, "left", node, 0);
		}
		
		//우
		boolean rightFlag = false;
		for(int j=node.j+1; j<N; j++) {
			if(exynos[node.i][j]==1) {
				rightFlag = true;
				break;
			}
		}
		if(!rightFlag) {
			updateMatrix(e, "right", node, 1);
			dfs(e, depth+1, sum+N-1-node.j, on+1);
			updateMatrix(e, "right", node, 0);
		}

		dfs(e, depth+1, sum, on);
	}
	
	static void updateMatrix(int[][] e, String flag, Node node, int num) {
		if(flag.equals("top")) {
			int j = node.j;
			for(int i=0; i<node.i; i++)
				e[i][j] = num;
		} else if(flag.equals("bottom")) {
			int j = node.j;
			for(int i=node.i+1; i<N; i++) 
				e[i][j] = num;
		} else if(flag.equals("left")) {
			int i = node.i;
			for(int j=0; j<node.j; j++)
				e[i][j] = num;
		} else {
			int i = node.i;
			for(int j=node.j+1; j<N; j++)
				e[i][j] = num;
		}
			
	}

}
