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
}

class Graph {
	Node[] nodes;
}

class Solution {
	Graph g;
	Queue<Node> q = new LinkedList<Node>();
	boolean[] isVisit;
	
	
	public int solution(int n, int[][] edge) {
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
    	
    	//for(int i=0; i<g.nodes.length; i++) {
    	//	System.out.println(g.nodes[i].c.size());
    	//}
    	
    	isVisit = new boolean[n+1];
    	q.add(g.nodes[1]);
    	
        int answer = 0;
        return answer;
    }
	
	public void bfs(Node node, int target, int depth) {
		if(node.n == target) {
			return;
		}
		
		bfs(node.c.get(depth), target, depth+1);
		return;
	}
}

public class TheFarthestNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		Solution s = new Solution();
		s.solution(n, edge);
	}

}
