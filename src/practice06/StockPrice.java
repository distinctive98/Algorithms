/*
 * 1try 문제 잘못 이해함
 * 2nd 시도
 */

package practice06;

import java.util.*;

class Calculator{
	Queue<Integer> q;
	int[] prices;
	
	Calculator(int[] prices){
		q = new LinkedList<Integer>();
		this.prices = prices;
	}
	
	int[] getResult() {
		int[] result = new int[prices.length];
		
		q.add(prices[0]);
		for(int i=1, j=0, cnt=1; i<result.length; i++, cnt++) {
			q.add(prices[i]);
			if(q.peek() > prices[i] || i==result.length-1) {
				while(q.size() != 1) {
					q.remove();
					if(q.size() != 2)
						result[j] = cnt;
					else
						result[j] = cnt-1;
					j++;
					cnt--;
				}
				//cnt=0;
			}
		}
		
		return result;
	}
	
}

class Solution {
    public int[] solution(int[] prices) {
    	Calculator c = new Calculator(prices);
        return c.getResult();
    }
}

public class StockPrice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] r = {1, 2, 3, 2, 3};
		int[] result;
		Calculator c = new Calculator(r);
		result = c.getResult();
		for(int i=0; i<result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
