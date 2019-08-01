package practice13;

import java.util.*;

class Set{
	int index;
	int play;
	
	Set(int index, int play){
		this.index = index;
		this.play = play;
	}
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        HashMap<String, Set> map1 = new HashMap<String, Set>();
        HashMap<String, Set> map2 = new HashMap<String, Set>();
        HashMap<String, Integer> mapSum = new HashMap<String, Integer>();
       
        for(int i=0; i<genres.length; i++) {
        	if(mapSum.containsKey(genres[i])) {
        		int temp = mapSum.get(genres[i]);
        		mapSum.put(genres[i], plays[i]+temp);
        	} else {
        		mapSum.put(genres[i], plays[i]);
        	}
        	
        	Set set = new Set(i, plays[i]);
        	if(map1.containsKey(genres[i])) {
        		Set temp = map1.get(genres[i]);
        		if(plays[i] > temp.play) {
        			map1.put(genres[i], set);
        			if(map2.containsKey(genres[i])) {
        				if(temp.play > map2.get(genres[i]).play) {
        					map2.put(genres[i], temp);
        				}
        			} else {
        				map2.put(genres[i], temp);
        			}
        		}
        	} else {
        		map1.put(genres[i], set);
        	}
        }
        
        answer = new int[mapSum.size()*2];
        
        List<String> list = new ArrayList();
        list.addAll(mapSum.keySet());
        
        Collections.sort(list, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				Object v1 = mapSum.get(o1);
				Object v2 = mapSum.get(o2);
				
				return ((Comparable)v2).compareTo(v1);
			}
        	
        });
        
        int i=0;
        Iterator it = list.iterator();
        while(it.hasNext()) {
        	String temp = (String)it.next();
        	if(map1.containsKey(temp)) {
        		answer[i] = map1.get(temp).index;
        		System.out.println(answer[i]);
        		i++;
        	}
        	if(map2.containsKey(temp)) {
        		answer[i] = map2.get(temp).index;
        		System.out.println(answer[i]);
        		i++;
        	}
        
        	//System.out.println(temp + "=" + mapSum.get(temp));
        }
        
        return answer;
    }
}

public class BestAlbum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] g = {"classic", "pop", "classic", "classic", "pop"};
		int[] p = {500, 600, 800, 800, 2500};
		
		Solution s = new Solution();
		s.solution(g, p);

	}

}
