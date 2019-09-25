/**
 * 2019-08-25 �Ϸ�
 * ���α׷��ӽ� �� ��ũ��Ʈ�ѷ�
 * �� ������ ������
 * 1. �� �ð��뿡 �������� job�� ��û�� �� ����
 * 2. job�� �������̶� ���� ����
 */

package practice29;

import java.util.*;

class Job implements Comparable<Job>{
	int time;
	int start;
	int end;
	
	public Job(int time, int start){
		this.time = time;
		this.start = start;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getRunTime() {
		return end-start;
	}

	@Override
	public int compareTo(Job o) {
		return time >= o.time ? 1 : -1;
	}
}

class Solution {
    public int solution(int[][] jobs) {
    	
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i<jobs.length; i++) {
        	if(map.containsKey(jobs[i][0])) {
        		List<Integer> list = map.get(jobs[i][0]);
        		list.add(jobs[i][1]);
        		map.put(jobs[i][0], list);
        	} else {
        		List<Integer> list = new LinkedList<Integer>();
        		list.add(jobs[i][1]);
        		map.put(jobs[i][0], list);
        	}
        	
        }
        
    	PriorityQueue<Job> pq = new PriorityQueue<>();
    	List<Integer> result = new LinkedList<>();
    	
    	TreeMap<Integer,List<Integer>> tm = new TreeMap<>(map);
    	int time = tm.keySet().iterator().next();
    	//boolean isRun = false;
    	int endFlag = 0;
    	while(true) {
    		System.out.println(time);
    		
    		if(map.containsKey(time)) {
    			for(int value : map.get(time)) {
    				pq.add(new Job(value, time));
    			}
    			map.remove(time);
    		}
    		
    		if(time >= endFlag) {
    			if(!pq.isEmpty()) {
    				Job job = pq.remove();
        			job.setEnd(time+job.getTime());
        			result.add(job.getRunTime());
        			endFlag = time + job.getTime();
        			System.out.println("EndFlag ����!");
        			//System.out.println(time + " : " + job.getRunTime());
    			} else {
    				if(map.isEmpty())
    					break;
    			}
    		}
    		
    		time++;
    	}
    	
    	int answer = 0;
    	for(int r : result) {
    		answer += r;
    	}
    	
        return answer / result.size();
    }
}

public class DiskController{
	public static void main(String args[]) {
		//int[][] jobs = {{0,3}, {0,4}, {0,5}, {0,6}, {0,7}};
		int[][] jobs = {{0,3}, {4,4}, {4,1}, {5,3}};
		Solution s = new Solution();
		int a = s.solution(jobs);
		System.out.println(a);
	}
}