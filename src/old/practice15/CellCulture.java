/**
 * 2019.04.25 완료
 */

package practice15;

import java.io.*;
import java.util.*;

class Cell {
	int n, m;
	int X, curX;
	Status status;
	int timeOfAct;
	int index;
	
	Cell(int n, int m, int X, Status status){
		this.n = n;
		this.m = m;
		this.X = X;
		this.curX = X;
		this.status = status;
		this.timeOfAct = -1;
		this.index = -1;
	}
	
	Cell(int n, int m, int X){
		this.n = n;
		this.m = m;
		this.X = X;
		this.curX = X;
		this.status = Status.Non_Activation;
		this.timeOfAct = -1;
		this.index = -1;
	}
	
	void decreaseOfCurX() {
		curX = curX - 1;
	}
	
	int getN() {
		return n;
	}
	
	int getM() {
		return m;
	}
	
	int getX() {
		return X;
	}
	
	int getCurX(){
		return curX;
	}
	
	Status getStatus() {
		return status;
	}
	
	int getTimeOfAct() {
		return timeOfAct;
	}
	
	int getIndex() {
		return index;
	}
	
	void setN(int N) {
		this.n = N;
	}
	
	void setM(int M) {
		this.m = M;
	}
	
	void setX(int X) {
		this.X = X;
	}
	
	void setCurX(int curX) {
		this.curX = curX;
	}
	
	void setStatus(Status status) {
		this.status = status;
	}
	
	void setTimeOfAct(int time) {
		this.timeOfAct = time;
	}
	
	void setIndex(int index) {
		this.index = index;
	}
	
	void renewOfCurX() {
		this.curX = X;
	}
	
	void increaseOfTime() {
		timeOfAct = timeOfAct + 1;
	}
	
	void show() {
		System.out.println(this.X);
		System.out.println(curX);
		System.out.println(status);
		System.out.println(timeOfAct);
	}
}

enum Status {
	Non_Activation, Activation, Death, Empty, Occupy
}

enum Sign {
	Up, Down, Left, Right
}

public class CellCulture {
	
	static void statusChange(Cell[][] cell, int n, int m) {
		if(cell[n][m].status == Status.Activation || cell[n][m].status == Status.Non_Activation) {
			cell[n][m].decreaseOfCurX();
			
			//현재 상태가 '활성화'라면 timeOfActivation 1 증가
			if(cell[n][m].status == Status.Activation) {
				cell[n][m].increaseOfTime();
			}
			
			//현재 생명력이 0이 됐을 때 상태 변환
			if(cell[n][m].getCurX() == 0) {
				if(cell[n][m].getStatus() == Status.Non_Activation) {
					cell[n][m].setStatus(Status.Activation);
					//상태가 바뀌었으므로 현재 생명력을 갱신함
					cell[n][m].renewOfCurX();
					//상태가 '활성화'됐을 때 timeOfActivation 1 증가
					//초기에 timeOfActivation은 0이 됨
					cell[n][m].increaseOfTime();
				}
				//현재 상태가 '활성화'라면
				else {
					cell[n][m].setStatus(Status.Death);
				}
			}
		}
		else {
			if(cell[n][m].getStatus() == Status.Death && cell[n][m].getTimeOfAct() == 1) {
				cell[n][m].setTimeOfAct(-1);
			}
		}
	}
	
	static void breeding(Cell[][] cell, ArrayList<Cell> newCell, boolean[] boundary, int n, int m, int maxN, int maxM, Sign sign) {
		if(cell[n][m].getTimeOfAct() == 1) {			
			if(n == 0 || n == maxN-1 || m == 0 || m == maxM-1) {
				breedingBoundary(cell, newCell, boundary, n, m, maxN, maxM, sign);
			}
			else {
				breedingNotBoundary(cell, newCell, n, m, sign);
			}
		}
	}
	
	static void breedingBoundary(Cell[][] cell, ArrayList<Cell> newCell, boolean[] boundary, int n, int m, int maxN, int maxM, Sign sign) {
		//방향 확인
		int newN, newM, index;
		if(sign == Sign.Up) {
			newN = -1;
			newM = m;
			index = 0;
		}
		else if(sign == Sign.Down) {
			newN = maxN;
			newM = m;
			index = 1;
		}
		else if(sign == Sign.Left) {
			newN = n;
			newM = -1;
			index = 2;
		}
		else {
			newN = n;
			newM = maxM;
			index = 3;
		}
		
		newCell.add(new Cell(newN, newM, cell[n][m].getX()));
		boundary[index] = true;
	}
	
	static void breedingNotBoundary(Cell[][] cell, ArrayList<Cell> newCell, int n, int m, Sign sign) {
		//방향 확인
		int newN, newM;
		if(sign == Sign.Up) {
			newN = n-1;
			newM = m;
		}
		else if(sign == Sign.Down) {
			newN = n+1;
			newM = m;
		}
		else if(sign == Sign.Left) {
			newN = n;
			newM = m-1;
		}
		else {
			newN = n;
			newM = m+1;
		}
		
		//신규 세포와 충돌할 때
		if(cell[newN][newM].getStatus() == Status.Occupy) {
			int index =  cell[newN][newM].getIndex();
			if(cell[n][m].getX() > newCell.get(index).getX()) {
				newCell.get(index).setX(cell[n][m].getX());
				
			}
		}
		
		//빈 공간일 때
		if(cell[newN][newM].getStatus() == Status.Empty) {
			newCell.add(new Cell(newN, newM, cell[n][m].getX()));
			cell[newN][newM].setStatus(Status.Occupy);
			cell[newN][newM].setIndex(newCell.size()-1);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\YG\\input\\sample_input(1).txt"));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			
			st = new StringTokenizer(br.readLine());
					
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Cell[][] cell = new Cell[N][M];
			
			//배열 초기화
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				for(int m=0; m<M; m++) {
					int X = Integer.parseInt(st.nextToken());
					Status status;
					if(X == 0)
						status =Status.Empty;
					else
						status = Status.Non_Activation;
					cell[n][m] = new Cell(n, m, X, status);
				}					
			}
			
			
			int maxN = N;
			int maxM = M;
			//K번 만큼 반복
			for(int k=0; k<K; k++) {
				
				for(int a=0; a<maxN; a++){
					for(int b=0; b<maxM; b++) {
						System.out.print(cell[a][b].getX());
					}
					System.out.println();
				}
				System.out.println();
				
				//새로운 세포 저장하는 ArrayList
				ArrayList<Cell> newCell = new ArrayList<>();
				
				//경계값 이탈 판단 변수
				//boolean up = false,  down = false,  left = false, right = false;
				boolean[] boundary = new boolean[] {false, false, false, false};
				
				
				for(int n = 0; n < maxN; n++) {
					for(int m=0; m<maxM; m++) {
						
						//Step1
						//생명력 감소 및 상태 변화
						statusChange(cell, n, m);
						/*

						//상태가 '죽음', '빈'이 아니라면 생명력 감소
						if(cell[n][m].status == Status.Activation || cell[n][m].status == Status.Non_Activation) {
							cell[n][m].decreaseOfCurX();
							
							//현재 상태가 '활성화'라면 timeOfActivation 1 증가
							if(cell[n][m].status == Status.Activation) {
								cell[n][m].increaseOfTime();
							}
							
							//현재 생명력이 0이 됐을 때 상태 변환
							if(cell[n][m].getCurX() == 0) {
								if(cell[n][m].getStatus() == Status.Non_Activation) {
									cell[n][m].setStatus(Status.Activation);
									//상태가 바뀌었으므로 현재 생명력을 갱신함
									cell[n][m].renewOfCurX();
									//상태가 '활성화'됐을 때 timeOfActivation 1 증가
									//초기에 timeOfActivation은 0이 됨
									cell[n][m].increaseOfTime();
								}
								//현재 상태가 '활성화'라면
								else {
									cell[n][m].setStatus(Status.Death);
								}
							}
						}
						else {
							if(cell[n][m].getStatus() == Status.Death && cell[n][m].getTimeOfAct() == 1) {
								cell[n][m].setTimeOfAct(-1);
							}
						}
						
						*/
						
						//Step2
						//번식활동
						
						breeding(cell, newCell, boundary, n, m, maxN, maxM, Sign.Up);
						breeding(cell, newCell, boundary, n, m, maxN, maxM, Sign.Down);
						breeding(cell, newCell, boundary, n, m, maxN, maxM, Sign.Left);
						breeding(cell, newCell, boundary, n, m, maxN, maxM, Sign.Right);
						
						/*
						//번식 준비가 됐다면
						if(cell[n][m].getTimeOfAct() == 1) {
							
							//Step2-1
							//상단 부분 확인
							
							//경계에 있을 때
							if(n == 0) {
								newCell.add(new Cell(-1, m, cell[n][m].getX()));
								//up = true;
								boundary[0] = true;
							}
							//경계가 아닐 때
							else {
								//신규 세포와 충돌할 때
								if(cell[n-1][m].getStatus() == Status.Occupy) {
									int index =  cell[n-1][m].getIndex();
									if(cell[n][m].getX() > newCell.get(index).getX()) {
										newCell.get(index).setX(cell[n][m].getX());
										
									}
								}
								
								//빈 공간일 때
								if(cell[n-1][m].getStatus() == Status.Empty) {
									newCell.add(new Cell(n-1, m, cell[n][m].getX()));
									cell[n-1][m].setStatus(Status.Occupy);
									cell[n-1][m].setIndex(newCell.size()-1);
								}
							}
							

							//Step2-2
							//하단 부분 확인
							
							//경계에 있을 때
							if(n == maxN-1) {
								newCell.add(new Cell(maxN, m, cell[n][m].getX()));
								//down = true;
								boundary[1] = true;
							}
							//경계가 아닐 때
							else {
								//신규 세포와 충돌할 때
								if(cell[n+1][m].getStatus() == Status.Occupy) {
									int index =  cell[n+1][m].getIndex();
									if(cell[n][m].getX() > newCell.get(index).getX()) {
										newCell.get(index).setX(cell[n][m].getX());
									}
								}
								
								//빈 공간일 때
								if(cell[n+1][m].getStatus() == Status.Empty) {
									newCell.add(new Cell(n+1, m, cell[n][m].getX()));
									cell[n+1][m].setStatus(Status.Occupy);
									cell[n+1][m].setIndex(newCell.size()-1);
								}
							}
							
						
							//Step2-3
							//좌측 부분 확인
							
							//경계에 있을 때
							if(m == 0) {
								newCell.add(new Cell(n, -1, cell[n][m].getX()));
								//left = true;
								boundary[2] = true;
							}
							//경계가 아닐 때
							else {
								//신규 세포와 충돌할 때
								if(cell[n][m-1].getStatus() == Status.Occupy) {
									int index =  cell[n][m-1].getIndex();
									if(cell[n][m].getX() > newCell.get(index).getX()) {
										newCell.get(index).setX(cell[n][m].getX());
									}
								}
								
								//빈 공간일 때
								if(cell[n][m-1].getStatus() == Status.Empty) {
									newCell.add(new Cell(n, m-1, cell[n][m].getX()));
									cell[n][m-1].setStatus(Status.Occupy);
									cell[n][m-1].setIndex(newCell.size()-1);
								}
							}
							
							//Step2-4
							//우측 부분 확인
							
							//경계에 있을 때
							if(m == maxM-1) {
								newCell.add(new Cell(n, maxM, cell[n][m].getX()));
								//right = true;
								boundary[3] = true;
							}
							//경계가 아닐 때
							else {
								//신규 세포와 충돌할 때
								if(cell[n][m+1].getStatus() == Status.Occupy) {
									int index =  cell[n][m+1].getIndex();
									if(cell[n][m].getX() > newCell.get(index).getX()) {
										newCell.get(index).setX(cell[n][m].getX());
									}
								}
								
								//빈 공간일 때
								if(cell[n][m+1].getStatus() == Status.Empty) {
									newCell.add(new Cell(n, m+1, cell[n][m].getX()));
									cell[n][m+1].setStatus(Status.Occupy);
									cell[n][m+1].setIndex(newCell.size()-1);
								}
							}
						}
						*/
					}
				}
				

				//Step3
				//갱신
				if(boundary[0]) {
					//기존 세포들의 N값 1 증가
					for(int i=0; i<maxN; i++) {
						for(int j=0; j<maxM; j++) {
							cell[i][j].setN(cell[i][j].getN()+1);
						}
					}
					
					//신규 세포들의 N값 1 증가
					for(int i=0; i<newCell.size(); i++) {
						newCell.get(i).setN(newCell.get(i).getN()+1);
					}
				}
				
				if(boundary[2]) {
					//기존 세포들의 M값 1 증가
					for(int i=0; i<maxN; i++) {
						for(int j=0; j<maxM; j++) {
							cell[i][j].setM(cell[i][j].getM()+1);
						}
					}
					
					//신규 세포들의 M값 1 증가
					for(int i=0; i<newCell.size(); i++) {
						newCell.get(i).setM(newCell.get(i).getM()+1);
					}
				}
				
				int newMaxN = maxN;
				int newMaxM = maxM;
				
				//newN, newM 갱신
				if(boundary[0]) {
					newMaxN++;
				}
				
				if(boundary[1]) {
					newMaxN++;
				}
				
				if(boundary[2]) {
					newMaxM++;
				}
				
				if(boundary[3]) {
					newMaxM++;
				}

				
				//Step4
				//그리드 재 설정
				
				//임시 그리드 생성
				Cell[][] temp = new Cell[newMaxN][newMaxM];
				
				//기존 그리드의 세포값을 신규 그리드로 이동 
				for(int n=0; n<maxN; n++) {
					for(int m=0; m<maxM; m++) {
						int newN = cell[n][m].getN();
						int newM = cell[n][m].getM();
						temp[newN][newM] = cell[n][m];
					}
				}
				
				//신규 세포들을 신규 그리드로 이동
				for(int i=0; i<newCell.size(); i++) {
					int newN = newCell.get(i).getN();
					int newM = newCell.get(i).getM();
					int newX = newCell.get(i).getX();
					temp[newN][newM] = new Cell(newN, newM, newX, Status.Non_Activation);
				}
				
				//빈 그리드 채우기
				for(int n=0; n<newMaxN; n++) {
					for(int m=0; m<newMaxM; m++) {
						if(temp[n][m] == null)
							temp[n][m] = new Cell(n, m, 0, Status.Empty);
					}
				}
				
				
				//기존 그리드 갱신
				maxN = newMaxN;
				maxM = newMaxM;
				cell = new Cell[maxN][maxM];
				
				//임시 그리드에서 새로운 그리드로 이동
				for(int n=0; n<maxN; n++) {
					for(int m=0; m<maxM; m++) {
						cell[n][m] = temp[n][m];
					}
				}
			}
			
			
			//활성화, 비 활성화 개수 파악
			int count = 0;
			
			for(int n=0; n<maxN; n++) {
				for(int m=0; m<maxM; m++) {
					if(cell[n][m].status == Status.Non_Activation || 
							cell[n][m].status == Status.Activation) {
						count++;
					}
				}
			}
			
			System.out.println("#" + (t+1) + " " + count);
			
		}
		
	}
}