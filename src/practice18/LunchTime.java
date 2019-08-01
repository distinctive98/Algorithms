package practice18;

/*
class Graph{
	Worker start;
	Worker end;
	
	Graph(Worker start, Worker end){
		this.start = start; 
		this.end = end;
	}

	public Worker getStart() {
		return start;
	}

	public Worker getEnd() {
		return end;
	}
}
*/

/*
class Stair{
	int n;
	int m;
	int length;
	
	Stair(int n, int m, int length){
		this.n = n;
		this.m = m;
		this.length = length;
	}
	
	int getN() {
		return n;
	}
	
	int getM() {
		return m;
	}
	
	int getLength() {
		return length;
	}
}

class Worker{
	int n;
	int m;
	//int timeA, timeB;
	int time;
	int count;
	
	Worker(int n, int m){
		this.n = n;
		this.m = m;
		count = 0;
	}
	
	void setCount(int count) {
		this.count = count;
	}
	
	void setTime(int time) {
		this.time = time;
	}
	
	int getTime() {
		return time;
	}
	
	int getCount() {
		return count;
	}
	
	int getN() {
		return n;
	}
	
	int getM() {
		return m;
	}
	
	void count() {
		count++;
	}
}

public class LunchTime {
	
	static int[][] map;
	static int N;
	static int wCount; //countOfWorker
	
	static Stair[] stair;
	static Worker[] worker;
	
	//static Graph[] graph;
	//static ArrayList<Graph> graph;
	
	static int graph[][];
	
	static ArrayList<Worker> groupA, groupB;
	static Queue<Worker> stairA, stairB;
	static boolean[][] isVisit;
	
	static void initIsVisit() {
		isVisit = new boolean[N][N];
	}
	
	
	static void initGraph() {
	
		graph = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i < j) {
					graph[i][j] = 1;
				}
				else {
					graph[i][j] = 0;
				}
			}
		}
	
	}
	
	static void showGraph() {
		//for(Graph data : graph){
		//	System.out.println(data.start. + " " + data.end);
		//}
	}
	
	static void initStair(int i, int j) {
		
		//stair[stair.length] = new Stair(i, j, map[i][j]);
		
		
		if(stair[0] == null) {
			stair[0] = new Stair(i, j, map[i][j]);
		}
		else {
			stair[1] = new Stair(i, j, map[i][j]);
		}
		
	}
	
	static void initWorker(int i, int j, int count) {
		worker[count] = new Worker(i, j);
		//worker[count].setTimeA(Math.abs(i-stair[0].getN() + Math.abs(j-stair[0].getM())));
		//worker[count].setTimeB(Math.abs(i-stair[1].getN() + Math.abs(j-stair[1].getM())));
		
	}
	
	static void initTime(ArrayList<Worker> group, int n) {
		for(int i=0; i<group.size(); i++) {
			int time = Math.abs(group.get(i).getN()-stair[n].getN() + Math.abs(group.get(i).getM()-stair[n].getM()));
			group.get(i).setTime(time);
		}
	}
	
	static void show() {
		for(int i=0; i<stair.length; i++) {
			System.out.println(i + " 번째 계단 정보");
			System.out.println("N : " + stair[i].n);
			System.out.println("M : " + stair[i].m);
			System.out.println("length : " + stair[i].length);
		}
		
		for(int i=0; i<wCount; i++) {
			System.out.println(i + " 번째 사원 정보");
			System.out.println("N : " + worker[i].n);
			System.out.println("M : " + worker[i].m);
		}
	}
	
	
	
	

	
	//개선 필요
	static int findMinTime() {
		
		int min = 0;
		int temp; 
		boolean[] check;
		
		//grouping 멤버 개수
		for(int i=1; i<N; i++) {
			//j = root
			for(int j=0; j<N; j++) {
				
				initIsVisit();
				
				while(true) {
					check = new boolean[N];
					initGroupA(i, j, check);
					initGroupB(check);
					
					temp = calculateMinTime();
					if(min > temp) {
						min = temp;
					}
					
					//GroupA 탐색이 끝나면
					//if()
					//	break;
				}
				
			}
		}
						
		return min;
	}
	
	//매개변수: 멤버 수, root
	static void initGroupA(int mNum, int root, boolean[] check){
		
		
		groupA = new ArrayList<Worker>();
		
		
		
		
		//for(int i=0; i<mNum; i++) {
		//	Worker start = graph.get(i).getStart();
		//	Worker end = graph.get(i).getEnd();
		//	
		//	groupA.add(start);
		//	
		//}
		
		
		
	}
	
	static void initGroupB(boolean[] check){
		
		groupB = new ArrayList<Worker>();
		
		for(int i=0; i<N; i++){
			if(!check[i]) {
				groupB.add(worker[i]);
			}
		}
		
	}
	
	static void calculateStair(int count) {
		//계단 계산
		count = 0;
		for(Worker data : stairA) {
			data.count();
			if(data.getCount() == stair[0].getLength()) {
				count++;
			}
		}
		
		for(int i=0; i<count; i++) {
			stairA.remove();
		}
		
		count = 0;
		for(Worker data : stairB) {
			data.count();
			if(data.getCount() == stair[1].getLength()) {
				count++;
			}
		}
		
		for(int i=0; i<count; i++) {
			stairB.remove();
		}
	}
	
	static void calculateStay(int count) {
		initTime(groupA, 0);
		initTime(groupB, 1);
		
		for(Worker data : groupA) {
			data.count();
			if(data.getCount() == data.getTime()) {
				count++;
			}
		}
		
		for(int i=0; i<count; i++) {
			stairA.add(groupA.remove(0));
		}
		
		count = 0;
		for(Worker data : groupB) {
			data.count();
			if(data.getCount() == data.getTime()) {
				count++;
			}
		}
		
		for(int i=0; i<count; i++) {
			stairA.add(groupB.remove(0));
		}
	}
	
	static void sortGroup(ArrayList<Worker> w) {
		
		Collections.sort(w, new Comparator<Worker>() {
			
			@Override
			public int compare(Worker w1, Worker w2) {
				if(w1.getTime() > w2.getTime()) {
					return 1;
				}
				else if(w1.getTime() < w2.getTime()) {
					return -1;
				}
				else {
					return 0;
				}
			}
		});
		
	}
	
	static int calculateMinTime() {
		
		//연습용
		//삭제요망
		
		groupA = new ArrayList<Worker>();
		groupB = new ArrayList<Worker>();
		
		groupA.add(worker[0]);
		groupA.add(worker[1]);
		groupA.add(worker[2]);
		groupB.add(worker[3]);
		groupB.add(worker[4]);
		
		sortGroup(groupA);
		sortGroup(groupB);
		
		stairA = new LinkedList<Worker>();
		stairB = new LinkedList<Worker>();
			
		
		//여기까지
		int count = 0;
		int minute = 0;
		
		while(true) {
			
			minute++;
			
			calculateStair(count);
			calculateStay(count);
			
			if(groupA.isEmpty() && groupB.isEmpty() && stairA.isEmpty() && stairB.isEmpty())
				break;
						
		}
		
		
		return minute;
	}
	
	

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			stair = new Stair[2];
			worker = new Worker[10];
			
			int count = 0;
			for(int i=0; i<N; i++) {
				
				st = new StringTokenizer(br.readLine());
				
				for(int j=0; j<N; j++) {
					
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] == 1) {
						initWorker(i, j, count);
						count++;
					}					
					else if(map[i][j] != 0){
						initStair(i, j);
					}
					else {
						continue;
					}
				}
			}
			
			wCount = count;
			
			
			//show();
			
			//initGraph();
			//showGraph();
			
			//int result = calculateMinTime();
			
			groupA = new ArrayList<Worker>();
			groupB = new ArrayList<Worker>();
			
			groupA.add(worker[0]);
			groupA.add(worker[1]);
			groupA.add(worker[2]);
			groupB.add(worker[3]);
			groupB.add(worker[4]);
			
			
			sortGroup(groupA);
			sortGroup(groupB);
			
			initTime(groupA, 0);
			initTime(groupB, 1);
			
			
			for(Worker data : groupA) {
				System.out.println(data.getTime());
			}
			
			//System.out.println("#" + t + " " + result);
			
		}//for(t)
		
	}


}

*/
