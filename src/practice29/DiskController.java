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
    	
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<jobs.length; i++) {
        	map.put(i, jobs[i][1]);
        }
        
    	PriorityQueue<Job> pq = new PriorityQueue<>();
    	List<Integer> result = new LinkedList<>();
    	
    	TreeMap<Integer,Integer> tm = new TreeMap<>(map);
    	int time = tm.keySet().iterator().next();
    	//boolean isRun = false;
    	int endFlag = 0;
    	while(true) {
    		if(map.containsKey(time)) {
    			pq.add(new Job(map.get(time), time));
    			map.remove(time);
    		}
    		
    		if(time >= endFlag) {
    			if(!pq.isEmpty()) {
    				Job job = pq.remove();
        			job.setEnd(time+job.getTime());
        			result.add(job.getRunTime());
        			endFlag = time + job.getTime();
        			//System.out.println(time + " : " + job.getRunTime());
    			} else {
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

public class DiskController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] jobs = {{0,3}, {1,9}, {2,6}};
		Solution s = new Solution();
		int a = s.solution(jobs);
		System.out.println(a);
		
	}

}
