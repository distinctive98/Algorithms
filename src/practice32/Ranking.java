package practice32;

import java.util.*;

class Node {
	int value;
	List<Node> nodes;
	
	Node(int value){
		this.value = value;
		nodes = new LinkedList<>();
	}
}

class Graph {
	Node[] graph;
	Graph(int n){
		graph = new Node[n+1];
	}
}

class Solution {
    public int solution(int n, int[][] results) {
        
    	Graph g = initGraph(n, results);
    	HashMap<Node, Integer> map = initHashMap(g, n);
    	HashMap<Node, Integer> map2 = initHashMap(g, n);
    	
    	//search
    	search(g, map, map2);
    	
    	//key sort
    	LinkedList<Integer> values = new LinkedList<Integer>(map.values());
    	Collections.sort(values, (x, y)->y-x);
    	
    	LinkedList<Integer> values2 = new LinkedList<Integer>(map2.values());
    	Collections.sort(values2, (x, y)->y-x);
    	//System.out.println(values);
    	
    	//get answer
    	int answer = 0;
    	for(int i=0; i<values.size(); i++) {
    		if(values.get(i) == values.size()-i)
    			answer++;
    		else
    			break;
    	}
    	
    	for(int i=0; i<values2.size()-answer; i++) {
    		if(values2.get(i) == values2.size()-i)
    			answer++;
    		else
    			break;
    	}
    	
        return answer;
    }
    
    Graph initGraph(int n, int[][] results) {
    	Graph g = new Graph(n);
    	for(int i=1; i<=n; i++) {
    		g.graph[i] = new Node(i);
    	}
    	
    	for(int i=0; i<results.length; i++) {
    		g.graph[results[i][0]].nodes.add(g.graph[results[i][1]]);
    	}
    	
    	return g;
    }
    
    HashMap<Node, Integer> initHashMap(Graph g, int n){
    	HashMap<Node, Integer> map = new HashMap<>();
    	for(int i=1; i<=n; i++) {
    		map.put(g.graph[i], 0);
    	}
    	
    	return map;
    }
    
    void search(Graph g, HashMap<Node, Integer> map, HashMap<Node, Integer> map2) {
    	for(int i=1; i<g.graph.length; i++) {
    		boolean[] isVisit = new boolean[g.graph.length];
    		//System.out.println(g.graph.length);
    		dfs(map, map2, g.graph[i], g.graph[i], isVisit);
    	}
    }
    
    void dfs(HashMap<Node, Integer> map, HashMap<Node, Integer> map2, Node node, Node initNode, boolean[] isVisit) {
    	map.put(node, map.get(node)+1);
    	map2.put(initNode, map2.get(initNode)+1);
    	isVisit[node.value] = true;
    	for(int i=0; i<node.nodes.size(); i++) {
    		if(!isVisit[node.nodes.get(i).value]) {
    			dfs(map, map2, node.nodes.get(i),initNode, isVisit);
    		}
    	}
    }
}

public class Ranking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=5;
		//int[][] results = {{4,3},{3,2},{4,2},{1,2},{2,5}};
		int[][] results = {{1,2},{1,3},{1,4},{1,5}, {2,3}, {3, 4}, {4, 5}};
		Solution s = new Solution();
		int a = s.solution(n, results);
		System.out.println(a);
	}

}
