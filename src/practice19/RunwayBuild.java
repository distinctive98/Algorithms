/**
 * 2019-04-26 완료
 */

package practice19;

import java.io.*;
import java.util.*;



public class RunwayBuild {

	static boolean isPossibleBuild(int[] runway, int N, int X) {
		
		int cur = 0, next = 0;
		int moveResult;
		
		int count = 1;
		
		while(true) {
			
			//이동 가능 판단
			moveResult = moveNext(runway, N, next, next+1);
			
			switch(moveResult) {
			//이동 가능 하면
			case 0:
				next++;
				break;
				
			//상승 계단을 만나면	
			case 1:
				if(increaseStair(runway, X, cur, next+1)) {
					next += 1;
					cur = next;
				}
				else {
					return false;
				}
				break;
				
			//하강 계단을 만나면
			case 2:
				if(decreaseStair(runway, N, X, next+1)) {
					//하강 계단의 끝이 경계면
					if(next+X+1 >= N){
						return true;
					}
					//계단 끝과 그 다음이 같은 층이라면
					else if(runway[next+X] == runway[next+X+1]) {
						next = next+X+1;
						cur = next;
					}
					//계단 끝 다음에 또 내리막길
					else if(runway[next+X] - runway[next+X+1] == 1) {
						next = next+X;
						cur = next;
					}
					else {
						return false;
					}
				}
				//하강 계단 생성이 불가하면
				else {
					return false;
				}
				break;
				
			case 99:
				return true;
				
			//앞으로 이동이 불가하면(높이 차이가 2 이상 날 때)
			default:
				return false;
			}
			
			//System.out.println("카운트:" + count + " cur:" + cur + " next" + next);
			
		}
		
	}
	
	static boolean increaseStair(int[] runway, int X, int cur, int next) {
		if(next-cur >= X) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static boolean decreaseStair(int[] runway, int N, int X, int next) {
		if(!(next+X > N)) {
			for(int i=next; i<next+X; i++) {
				if(runway[next] != runway[i]) {
					return false;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	//문제 없음=0 상승계단=1 하강계단=2 탈락=-1
	static int moveNext(int[] runway, int N, int cur, int next) {
		if(next != N) {
			if(runway[cur] != runway[next]) {
				if(runway[cur] - runway[next] == -1) {
					return 1;
				}
				else if(runway[cur] - runway[next] == 1) {
					return 2;
				}
				else
					return -1;
			}
			else {
				return 0;
			}
		}
		else {
			return 99;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int[][] field = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					field[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			
			int[] runway = new int[N];
			int isOkay = 0;
			
			//가로방향
			for(int i=0; i<N; i++){
				
		
				for(int j=0; j<N; j++) {
					runway[j] = field[i][j];
					
					
					
				}
				if(isPossibleBuild(runway, N, X)) {
					isOkay++;
					//System.out.println("행: " + i);
				}
			}
				
			//세로방향
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++) {
					runway[j] = field[j][i];
					
					
				}
				if(isPossibleBuild(runway, N, X)) {
					isOkay++;
					//System.out.println("열: " + i);
				}
			}
			
			System.out.println("#" + t + " " + isOkay);
			
			/* show
			for(int i=0; i<N; i++) {
				System.out.println();
				for(int j=0; j<N; j++) {
					System.out.print(runway[i][j]);
				}
			}
			*/
			
			
		} //for(t)
		
	}

}
