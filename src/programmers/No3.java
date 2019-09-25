package programmers;

import java.util.LinkedList;
import java.util.Queue;

class Truck{
	int len;
	int weight;
	
	Truck(int len, int weight){
		this.len = len;
		this.weight = weight;
	}
}

class Solution3 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> wait = new LinkedList<Integer>();
        LinkedList<Truck> truck = new LinkedList<Truck>();
        for(int i : truck_weights) {
        	wait.add(i);
        }
        
        int time=0;
        int curWeight=0;
        int len = bridge_length;
        while(true) {
        	if(wait.isEmpty()) {
        		time += len;
        		break;
        	}
        	
        	time++;
        	if(wait.peek()+curWeight <= weight) {
        		int t = wait.remove();
        		curWeight += t;
        		truck.add(new Truck(len, t));
        	}
        	
        	int arriveCnt = 0;
        	for(int i=0; i<truck.size(); i++) {
        		int temp = --truck.get(i).len;
        		if(temp==0) {
        			arriveCnt++;
        		}
        	}
        	
        	for(int i=0; i<arriveCnt; i++) {
        		curWeight -= truck.get(0).weight;
        		truck.remove(0);
        	}
        }
    	
    	int answer = time;
        
        return answer;
    }
}

public class No3 {

	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7, 4, 5, 6};
		
		int a = new Solution3().solution(bridge_length, weight, truck_weights);
		System.out.println(a);
	}
}
