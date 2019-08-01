/**
 * 프로그래머스 동적계획법(DP) 도둑질 Level4
 */

package practice22;

import java.util.*;

class Node implements Comparable<Node>{
	int index;
	int money;
	public Node(int index, int money) {
		this.index = index;
		this.money = money;
	}
	public int getIndex() {
		return index;
	}
	public int getMoney() {
		return money;
	}
	
	@Override
	public int compareTo(Node o) {
		int target = o.getMoney();
		if(target == money)
			return 0;
		else if(target > money) 
			return 1;
		else
			return -1;
	}
}

class Solution {
	Node[] node;
	int length;
	
	public int solution(int[] money) {
        length = money.length;
		node = new Node[length];
		
		for(int i=0; i<length; i++) {
			node[i] = new Node(i, money[i]);
		}
		
		Arrays.sort(node);
		
		//for(Node temp : node) {
		//	System.out.println(temp.getMoney());
		//}
		
		int answer = node[0].getMoney();
		int preIndex = node[0].getIndex();
		int curIndex;
		for(int i=1; i<length; i++) {
			curIndex = node[i].getIndex(); 
			if(!(curIndex==0&&preIndex==length-1) && !(curIndex==length-1&&preIndex==0) && curIndex != preIndex-1 && curIndex != preIndex+1) {
				answer += node[i].getMoney();
			}
		}
		
        return answer;
    }
}

public class Stealing {

	public static void main(String[] args) {
		int[] money = {1, 2, 3, 1};
		
		Solution s = new Solution();
		int answer = s.solution(money);
		System.out.println(answer);
	}

}
