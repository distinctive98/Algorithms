/**
 * 2019-04-30 완료
 * 난이도 쉬움
 */

package practice20;

import java.io.*;
import java.util.*;

public class SecretBoxPassword {
	
	static int N, K;
	static char[] pw;
	
	static int unit;
	static ArrayList<String> pwList16;
	static ArrayList<Integer> pwList10;
	
	static void showPW() {
		for(char data : pw) {
			System.out.print(data + " ");
		}
		System.out.println();
	}
	
	static void showPWList16() {
		for(String data : pwList16) {
			System.out.print(data);
		}
		System.out.println();
	}
	
	static void showPWList10() {
		for(int data : pwList10) {
			System.out.println(data);
		}
		System.out.println();
	}
	
	static int findPassword() {
		
		organizePWList16();
		organizePWList10();
		
		int pw = pwList10.get(K-1);
		return pw;
	}
	
	static void removeRedundancy() {
		Queue<Integer> rQueue = new LinkedList<>();
		
		for(int i=0; i<pwList16.size()-1; i++) {
			for(int j=i+1; j<pwList16.size(); j++) {
				if(pwList16.get(i).equals(pwList16.get(j))) {
					rQueue.add(i);
				}
			}
		}
		
		while(!rQueue.isEmpty()) {
			int index = rQueue.remove();
			pwList16.remove(index);
		}
	}
	
	static void organizePWList10() {
		pwList10 = new ArrayList<Integer>();
		
		char[] temp;
		
		for(String data : pwList16) {
			temp = data.toCharArray();
			
			int num = 0;
			for(int i=unit-1, j=0; i>=0; i--, j++) {
				num += parse16To10(temp[i], j);
			}
			
			pwList10.add(num);
		}
		
		pwList10.sort(Comparator.reverseOrder());
	}
	
	static int parse16To10(char c, int unit) {
		
		int num;
		
		if(c >= 65) {
			num = c - 55;
		}
		else {
			num = c - 48;
		}
		 
		return num * (int)Math.pow(16, unit);
	}
	
	static void organizePWList16() {
		
		pwList16 = new ArrayList<String>();
		
		String temp = "";
		
		for(int i=0; i<N; i++) {	
			for(int j=0; j<unit; j++) {
				if(i+j < N) {
					temp += pw[i+j];
				}
				else {
					temp += pw[i+j-N];
				}
			}
				
			pwList16.add(temp);
			//System.out.println(temp);
			temp = "";	
		}
		
		removeRedundancy();
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T;
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			String str = br.readLine();
			pw = str.toCharArray();
			
			unit = N/4;
			
			int pw = findPassword();
			
			System.out.println("#" + t + " " + pw);
			
		}//for(t)
	}

}
