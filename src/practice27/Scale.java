package practice27;

import java.util.*;

class Node {
	int n;
	List<Integer> nodes;
	
	Node(int n){
		this.n = n;
		nodes = new LinkedList<>();
	}
}

class Solution {
    public int solution(int[] weight) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //hashlize
        for(int w : weight) {
        	if(map.containsKey(w)){
        		map.put(w, map.get(w)+1);
        	} else {
        		map.put(w, 1);
        	}
        }
        
        //탑 쌓기
        List<Node> nodes = new LinkedList<>();
        nodes.add(null); //0번째 index 막음
        int t = 1; //target
        Node n = null;
        List<Integer> temp = null;
        int sum = 0;
        while(true) {
        	n = new Node(t);
    		temp = new LinkedList<>();
    		for(int i=t; i>0; i--) {
    			if(map.containsKey(i)) {
    				temp.add(i);
    				sum += i;
    				if(t==sum) {
    					n.nodes = temp;
    				} 
    			}
    		}
    		
    		break;
        }
        
        
        

        return 0;
    }
}

public class Scale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
