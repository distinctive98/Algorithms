/**
 * 2019-08-25 완료
 * 프로그래머스 힙 이중순위우선큐
 * 큐를 두개 사용했다
 * 람다를 사용해봤다
 * 어쩌다보니 풀려버렸다
 * remove() 메소드에 값을 넣으면 그 값이 제거된다(..!!!!!)
 */

package practice30;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

	PriorityQueue<Integer> pqMax;
	PriorityQueue<Integer> pqMin;
	
	List<Integer> pqMaxSupport;
	List<Integer> pqMinSupport;
	
    public int[] solution(String[] operations) {
    	pqMax = new PriorityQueue<>((x, y) -> y-x);
    	pqMin = new PriorityQueue<>();
    	pqMaxSupport = new LinkedList<>();
    	pqMinSupport = new LinkedList<>();
    
    	for(int i=0; i<operations.length; i++) {
    		operateFunc(operations[i].split(" "));
    		//checkFunc();
    	}
    	
    	int[] answer = new int[2];
    	answer[0] = getValue(0);
    	answer[1] = getValue(1);
    	
        return answer;
    }
    
    void operateFunc(String[] split) {
    	if(split[0].equals("I")) {
    		int value = Integer.parseInt(split[1]);
    		pqMax.add(value);
    		pqMin.add(value);
    	} else {
			//최댓값 삭제
			if(split[1].equals("1")) {
				if(!pqMax.isEmpty()) {
					pqMin.remove(pqMax.remove());
					//int value = pqMax.remove();
					//System.out.println("삭제 : " + value);
					//pqMinSupport.add(value);
				}
			//최솟값 삭제
			} else {
				if(!pqMin.isEmpty()) {
					pqMax.remove(pqMin.remove());
					//int value = pqMin.remove();
					//pqMaxSupport.add(value);
				}
			}    			
    	}
    }
    
    /*
    void checkFunc() {
    	for(int i=0; i<pqMaxSupport.size(); i++) {
    		if(pqMax.peek() == pqMaxSupport.get(i)) {
    			pqMax.remove();
    			pqMaxSupport.remove(i);
    		}
    	}
    	
    	for(int i=0; i<pqMinSupport.size(); i++) {
    		if(pqMin.peek() == pqMinSupport.get(i)) {
    			pqMin.remove();
    			pqMinSupport.remove(i);
    		}
    	}
    }
    */
    
    int getValue(int num) {
    	PriorityQueue<Integer> pq;
    	if(num==0)
    		pq = pqMax;
    	else
    		pq = pqMin;
    	
    	if(pq.isEmpty())
    		return 0;
    	else
    		return pq.peek();
    }
}

public class DoublePriorityQueue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		//String[] operations = {"I 7", "I 5", "I -5", "D -1"};
		String[] operations = {"I 7", "I 5", "I -5", "D -1"};
		int[] answer = new Solution().solution(operations);
		System.out.println(answer[0] + "," + answer[1]);
	}
}