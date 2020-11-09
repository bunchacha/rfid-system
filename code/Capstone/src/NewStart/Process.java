package NewStart;

import java.util.*;

class basic {
	public static double QueryAdjust = 0;
	public static double QueryRepeat = 0;
	public static double AdjustRound = 0;
	public static double RepeatRound = 0;
}

class AboutTag{
	static double R_Unif(int a, int b){
		Random rand = new Random();
		double R = rand.nextDouble(); 
		return Math.round(a+(b-a)*R);		//난수 발생 식을 쓸것
	}
	 
	static void timer(double Tag[][], int nTag, int Q){		
		for(int i=0 ; i<nTag ; i++){
			if(Tag[i][0]==1)
				Tag[i][1] = R_Unif(0, Q-1);
			else Tag[i][1] = 10000000;  //프로그램 상 코드(timer값 '없음'을 대신)
		}
	}
}

class TagResponse{
	static void Response(double Tag[][], double Reader[][], int nTag, int Q){
		for(int i=0 ; i<Q ; i++){
			for(int j=0 ; j<nTag ; j++){
				if(Tag[j][0]==1){
					if(Tag[j][1]==i){
						Reader[i][0]+=1;
						Reader[i][1]+=j;    //태그 번호 누적
					}
				}
			}
		}
	}
}

class TagValue{	
	static int SuccessSlot(double Reader[][], int Q){
		int S_slot = 0;
		for(int i=0 ; i<Q ; i++){
			if(Reader[i][0]==1) S_slot += 1;
		}
		
		return S_slot;
	}
	
	static int failSlot(double Reader[][], int Q){
		int F_slot = 0;
		for(int i=0 ; i<Q ; i++)
			if(Reader[i][0]>1) F_slot += 1;
		
		return F_slot;
	}
	
	static int idleSlot(double Reader[][], int Q){
		int I_slot = 0;
		for(int i=0 ; i<Q ; i++)
			if(Reader[i][0]==0) I_slot += 1;
		
		return I_slot;
	}
}

public class Process {
	static double SumThroughput=0;
	static int SumQ=0, Repeat_Round=0, SumS=0, SumC=0, SumI=0, SumE=0;
	static double Tag[][];
	
	static int[] firstRound(int nTag, int Q){		
		double Reader[][] = new double[Q][2];
		
		for(int i=0 ; i<nTag ; i++)
			Tag[i][0] = 1;
		
		AboutTag.timer(Tag, nTag, Q);
		TagResponse.Response(Tag, Reader, nTag, Q);
		
		int N = AntiCollision.Schoute(Reader, Q);   //충돌 알고리즘(이 부분을 바꿔주세요!!)
		
		int S = TagValue.SuccessSlot(Reader, Q);
		int C = TagValue.failSlot(Reader, Q);
		int I = TagValue.idleSlot(Reader, Q);
		
		SumThroughput += (double)S/(S+C+I);
		
		SumS += S;
		SumC += C;
		SumI += I;
		
		SumQ += Q;
		
		int result[] = new int[3];
		result[0] = N;
		result[1] = nTag-S;
		
		for(int i=0 ; i<Q ; i++)
			if(Reader[i][0]==1)
				Tag[(int)(Reader[i][1])][0] = 0;
		
		//모델 검증
		/*
			for(int i=0 ; i<Q ; i++){
				for(int j=0 ; j<2 ; j++)
					System.out.print(Reader[i][j]+" ");
				System.out.println();
			}
				
			System.out.println("줄바꿈");
				
			for(int k=0 ; k<nTag ; k++){
				for(int l=0 ; l<3 ; l++)
					System.out.print(Tag[k][l]+" ");
				System.out.println();
			}
		*/	
		return result;
	}

	static int[] round1(int n0, int nTag, int Q){
		double Reader[][] = new double [Q][2];

		for(int i=0 ; i<n0 ; i++) Tag[i][1]=0;
		
		AboutTag.timer(Tag, n0, Q);
		TagResponse.Response(Tag, Reader, n0, Q);
		
		int N = AntiCollision.Schoute(Reader, Q);//충돌 알고리즘(이 부분을 바꿔주세요!!)
		
		int S = TagValue.SuccessSlot(Reader, Q);
		int C = TagValue.failSlot(Reader, Q);
		int I = TagValue.idleSlot(Reader, Q);
		
		SumThroughput += (double)S/(S+C+I);
		
		SumS += S;
		SumC += C;
		SumI += I;
		
		SumQ += Q;
		
		int result[] = new int[3];
		result[0] = N;
		result[1] = nTag-S;
		
		for(int i=0 ; i<Q ; i++){
			if(Reader[i][0]==1)
				Tag[(int)(Reader[i][1])][0] = 0;
		}
		
		//모델 검증
		/*
		for(int i=0 ; i<Q ; i++){
			for(int j=0 ; j<2 ; j++)
				System.out.print(Reader[i][j]+" ");
			System.out.println();
		}
			
		System.out.println("줄바꿈");
			
		for(int k=0 ; k<n0 ; k++){
			for(int l=0 ; l<3 ; l++)
				System.out.print(Tag[k][l]+" ");
			System.out.println();
		}
		*/
		return result;
	}

	static int EstMota(int nTag, int x, int i, double c){
		double Q_Value=x;
		int FinalQ = 0;
		int end=0;
		int Q = (int) Math.pow(2, x);
		
		for( ; ; ){
			for( ; ; ){
				int check1=0, check2=0;
				
				double Reader[][] = new double[Q][2];
				SumE += i;
				
				for(int j=0 ; j<nTag ; j++) {Tag[j][0] = 1;}
				
				AboutTag.timer(Tag, nTag, Q);
				TagResponse.Response(Tag, Reader, nTag, Q);
				
				for(int k=0 ; k<i ; k++){
					if(Reader[k][0]>1) check1+=1;    //check값으로 코드 구현
					else break;
				}
				if(check1==i){
					Q_Value = Q_Value + c; Q = (int)Math.round(Math.pow(2, Q_Value));
					continue;
				}
				
				for(int l=0 ; l<i ; l++){
					if(Reader[l][0]==0) check2+=1;    //check값으로 코드 구현
					else break;
				}
				if(check2==i){
					Q_Value = Q_Value - c; Q = (int)Math.round(Math.pow(2, Q_Value));
					continue;
				}
				break;	
			}
				
			for( ; ; ){
				int check1=0, check2=0;
				
				double Reader[][] = new double[Q][2];
				SumE += i;
				
				for(int j=0 ; j<nTag ; j++) Tag[j][0] = 1;
	
				AboutTag.timer(Tag, nTag, Q);
				TagResponse.Response(Tag, Reader, nTag, Q);
				
				for(int k=0 ; k<i ; k++){
					if(Reader[k][0]>1) check1+=1;    //check값으로 코드 구현
					else break;
				}
				if(check1==i){
					Q_Value = Q_Value + c; Q = (int)Math.round(Math.pow(2, Q_Value));
					break;
				}
				
				for(int l=0 ; l<i ; l++){
					if(Reader[l][0]==0) check2+=1;    //check값으로 코드 구현
					else break;
				}
				if(check2==i){
					Q_Value = Q_Value - c; Q = (int)Math.round(Math.pow(2, Q_Value)); 
					break;
				}
				
				 end=1;
				
				//Ours_K
				/*
				double k1=0, k2=0;
				k1 = (double)check1/i;
				
				if(k1<0.2642) k2=2.2057;
				else if(k1<0.5940) k2=2.6699;
				else if(k1<0.8009) k2=3.2576;
				else if(k1<0.9927)k2=5.2610;
				else k2=8.5673;
				FinalQ = Q+(int)Math.round((double)Q/i*check1*k2)-(int)Math.round((double)Q/2/i*check2);
				*/
				 
				//Ours
				//FinalQ = Q+(int)Math.round((double)Q/i*check1)-(int)Math.round((double)Q/2/i*check2);
				
				//Ours
				/*
				if(check1>=1) FinalQ = (Q+(int)Math.round((double)Q/i*check1)-(int)Math.round((double)Q/2/i*check2))*(int) Math.round((i+(double)(check1/i)*k2-(double)(check2/i))/i);
				else FinalQ = (Q-(int)Math.round((double)Q/2/i*check2))*(int) Math.round((i+(double)(check1/i)*k2-(double)(check2/i))/i);
				*/
				
				//Mota
				
				if(check1>=1) FinalQ = Q;
				else FinalQ = Q;
				
				
				break;	
			}
			if(end==1) break;
		}	
		//System.out.println(FinalQ);
		
		return (int)Math.round(0.67*FinalQ);
		//return FinalQ;
	}
	
	static int EstOurs(int nTag, int Q_value, int i){
		int FinalQ = 0;
		int end=0;
		int Q = Q_value;
		
		for( ; ; ){
			for( ; ; ){
				int check1=0, check2=0;  //check1 : collision, check2 : idle
				double k1=0, k2=0, n1=0, result=0, Q2=0;
			
				double Reader[][] = new double[Q][2];
				SumE += i;
				
				for(int j=0 ; j<nTag ; j++) {Tag[j][0] = 1;}
				
				AboutTag.timer(Tag, nTag, Q);
				TagResponse.Response(Tag, Reader, nTag, Q);
				
				
				for(int k=0 ; k<i ; k++){
					if(Reader[k][0]>1) check1+=1;    //check값으로 코드 구현
					else break;
				}
				
				for(int l=0 ; l<i ; l++){
					if(Reader[l][0]==0) check2+=1;    //check값으로 코드 구현
					else break;
				}
				
				k1 = (double)check1/i;
				n1 = (double)check2/i;
				
				System.out.println("n1 "+ n1);
				
				if(k1<0.2642) k2=2.2057;
				else if(k1<0.5940) k2=2.6699;
				else if(k1<0.8009) k2=3.2576;
				else if(k1<0.9927) k2=5.2610;
				else k2=8.5673;
				
				System.out.println("k1" + k1);
				System.out.println("k2 "+ k2);
				System.out.println("n1" + n1);
				Q2 = (double)Q;
				System.out.println("Q2 "+ Q2);
				
				if(k1==1 || n1==1){
					result = Q2 + Q2 * (k1 * k2 - n1);
					System.out.println("result" + result);
					Q = (int)Math.round(result);
					System.out.println("Q" + Q);
					continue;
				}
				break;	
			}
				
			for( ; ; ){
				int check1=0, check2=0;
				double k1=0, k2=0, n1=0, result=0, Q2=0;
				
				double Reader[][] = new double[Q][2];
				SumE += i;
				
				for(int j=0 ; j<nTag ; j++) Tag[j][0] = 1;
	
				AboutTag.timer(Tag, nTag, Q);
				TagResponse.Response(Tag, Reader, nTag, Q);
				
				for(int k=0 ; k<i ; k++){
					if(Reader[k][0]>1) check1+=1;    //check값으로 코드 구현
					else break;
				}
				
				for(int l=0 ; l<i ; l++){
					if(Reader[l][0]==0) check2+=1;    //check값으로 코드 구현
					else break;
				}
				
				k1 = (double)check1/i;
				n1 = (double)check2/i;
				
				if(k1<0.2642) k2=2.2057;
				else if(k1<0.5940) k2=2.6699;
				else if(k1<0.8009) k2=3.2576;
				else if(k1<0.9927) k2=5.2610;
				else k2=8.5673;
				
				Q2 = (double)Q;
				//System.out.println(Q2);
				if(check1==i || check2==i){
					result = Q2+Q2*(k1*k2 - n1);
					Q = (int)Math.round(result);
					break;
				}
				
				end=1;
				
				FinalQ = Q;
				break;	
			}
			if(end==1) break;
		}	
		//System.out.println(FinalQ);
		
		//return FinalQ;
		return FinalQ;
	}
}
	
