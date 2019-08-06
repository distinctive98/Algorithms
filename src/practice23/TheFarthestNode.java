/*2019-08-06 완료
 * 프로그래머스 그래프 1번 문제*/

package practice23;

import java.util.*;

class Node {
	int n;
	List<Node> c;
	int depth;
	
	public Node(int n) {
		this.n = n;
		c = new LinkedList<Node>();
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}

class Graph {
	Node[] nodes;
}

class Solution {
	Graph g;
	Queue<Node> q = new LinkedList<Node>();
	HashMap<Integer, Integer> result = new HashMap<>();
	boolean[] isVisit;
	int maxDepth;
	
	
	public int solution(int n, int[][] edge) {
    	//그래프 세팅
		g = new Graph();
    	g.nodes = new Node[n+1];
    	for(int i=0; i<=n; i++) {
    		g.nodes[i] = new Node(i);
    	}
    	
    	for(int i=0; i<edge.length; i++) {
    		int v = edge[i][0];
    		int u = edge[i][1];
    		g.nodes[v].c.add(g.nodes[u]);
    		g.nodes[u].c.add(g.nodes[v]);
    	}
    	
    	//bfs를 위한 초기세팅들
    	isVisit = new boolean[n+1];
    	q.add(g.nodes[1]);
    	g.nodes[1].setDepth(1);
    	isVisit[1] = true;
    	result.put(1, 1);
    	maxDepth = 1;
    	bfs();
    	
		/*
		 * Iterator iterator = result.keySet().iterator(); while(iterator.hasNext()) {
		 * int temp = (int) iterator.next(); System.out.println(temp + " = " +
		 * result.get(temp)); }
		 */
    	
        int answer = result.get(maxDepth);
        return answer;
    }
	
	public void bfs() {
		while(!q.isEmpty()) {
			Node temp = q.poll();
			int depth = temp.getDepth()+1;
			//해당 node의 자식들 queue에 넣고
			//자식들의 depth 지정하고
			//HashMap에 depth를 카운트하고
			//maxDepth 갱신
			for(Node node : temp.c) {
				if(!isVisit[node.n]) {
					q.add(node);
					node.setDepth(depth);
					isVisit[node.n] = true;
					//HashMap 저장
					if(maxDepth <= depth) {
						if(result.containsKey(depth)) {
							result.put(depth, result.get(depth)+1);
						} else {
							result.put(depth, 1);
						}
						if(maxDepth < depth) {
							maxDepth = depth;
						}
					}
					
					//System.out.println(node.n + ":" + depth);
				}
			}
		}
	}
}

public class TheFarthestNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		Solution s = new Solution();
		int answer = s.solution(n, edge);
		System.out.println(answer);
	}
}
