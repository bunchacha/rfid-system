package Start;

import java.util.*;

class basic {
	public static double time = 0;
	public static double power = 0;
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
						
						/*
						Tag[j][2] += Time.TimeSlot * Power.TagResponse;
						Tag[j][3] += Time.TimeSlot;
						*/
						
					}
					else {
						/*
						Tag[j][2] += Time.TimeSlot * Power.Sleeptimer;
						Tag[j][3] += Time.TimeSlot;
						*/
					}
				}
			}
			//basic.time += Time.TimeSlot;
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
	static double SumPower=0, SumThroughput=0;
	static int SumQ=0, Repeat_Round=0, SumS=0, SumC=0, SumI=0;
	static double Tag[][];
	
	static int[] firstRound(int nTag, int Q){		
		double Reader[][] = new double[Q][2];
		
		for(int i=0 ; i<nTag ; i++)
			Tag[i][0] = 1;
		/*
		basic.time += Time.WakeUpReceive;
		
		for(int i=0 ; i<nTag ; i++){
			Tag[i][2] += Power.WakeUpPower;
			Tag[i][3] += Time.WakeUpReceive;
		}
		
		basic.time += Time.Query;
		
		for(int i=0 ; i<nTag ; i++){
			Tag[i][2] += Time.Query * Power.Query;
			Tag[i][3] += Time.Query;
		}
		*/
		AboutTag.timer(Tag, nTag, Q);
		TagResponse.Response(Tag, Reader, nTag, Q);
		
		int N = AntiCollision.K1K2(Reader, Q);   //충돌 알고리즘(이 부분을 바꿔주세요!!)
		
		int S = TagValue.SuccessSlot(Reader, Q);
		int C = TagValue.failSlot(Reader, Q);
		int I = TagValue.idleSlot(Reader, Q);
		
		SumThroughput += (double)S/(S+C+I);
		
		SumS += S;
		SumC += C;
		SumI += I;
		
		SumQ += Q;
		/*
		if(N==Q){
			basic.RepeatRound += 1;
			basic.time += Time.QueryRepeat(Q, S);
			
			for(int i=0 ; i<nTag ; i++){
				Tag[i][2] += Time.QueryRepeat(Q, S) * Power.Query;
				Tag[i][3] += Time.QueryRepeat(Q, S);
			}
		}
		else{
			basic.AdjustRound += 1;
			basic.time += Time.QueryAdjust(Q, S);
			
			for(int i=0 ; i<nTag ; i++){
				Tag[i][2] += Time.QueryAdjust(Q, S) * Power.Query;
				Tag[i][3] += Time.QueryAdjust(Q, S);
			}
		}
		*/
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
		
		int N = AntiCollision.K1K2(Reader, Q);//충돌 알고리즘(이 부분을 바꿔주세요!!)
		
		int S = TagValue.SuccessSlot(Reader, Q);
		int C = TagValue.failSlot(Reader, Q);
		int I = TagValue.idleSlot(Reader, Q);
		
		SumThroughput += (double)S/(S+C+I);
		
		SumS += S;
		SumC += C;
		SumI += I;
		
		SumQ += Q;
		/*
		if(N==Q){
			basic.RepeatRound += 1;
			basic.time += Time.QueryRepeat(Q, S);
			
			for(int i=0 ; i<n0 ; i++){
				if(Tag[i][0]==1){
					Tag[i][2] += Time.QueryRepeat(Q, S) * Power.Query;
					Tag[i][3] += Time.QueryRepeat(Q, S);
				}
			}
		}
		else if(N==0){
			basic.RepeatRound += 1;
			basic.time += Time.QueryRepeat(Q, S);
			
			for(int i=0 ; i<n0 ; i++){
				if(Tag[i][0]==1){
					Tag[i][2] += Time.QueryRepeat(Q, S) * Power.Query;
					Tag[i][3] += Time.QueryRepeat(Q, S);
				}
			}
		}
		else{
			basic.AdjustRound += 1;
			basic.time += Time.QueryAdjust(Q, S);
		
			for(int i=0 ; i<n0 ; i++){
				if(Tag[i][0]==1){
					Tag[i][2] += Time.QueryAdjust(Q, S) * Power.Query;
					Tag[i][3] += Time.QueryAdjust(Q, S);
				}
			}
		}
		*/
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
	
	static int[] firstRoundQA(int nTag, int Q){		
		double Reader[][] = new double[Q][2];
		double Q_Value = 4;
		
		for(int i=0 ; i<nTag ; i++)
			Tag[i][0] = 1;

		AboutTag.timer(Tag, nTag, Q);
		TagResponse.Response(Tag, Reader, nTag, Q);
		
		int S = TagValue.SuccessSlot(Reader, Q);
		int C = TagValue.failSlot(Reader, Q);
		int I = TagValue.idleSlot(Reader, Q);
		
		
		for(int j=0 ; j<Q ; j++){
			if(Reader[j][0]>1){
				Q_Value = Q_Value + 1;
				if(Q_Value > 15) Q_Value = 15;
				
			}
			else if(Reader[j][0]==0){
				Q_Value = Q_Value - 1;
				if(Q_Value < 0) Q_Value = 0;
			
			}
			else nTag = nTag - 1;
		}
		
		SumS += S;
		SumC += C;
		SumI += I;
		
		SumQ += Q;

		System.out.println("Q : " + Q);
		System.out.println(Math.pow(2, (int)Math.round(Q_Value)));
		int result[] = new int[4];
		result[0] = (int)Math.pow(2, (int)Math.round(Q_Value));
		result[1] = nTag;		
		result[2] = (int)Q_Value;
		result[3] = C;
		
		for(int k=0 ; k<Q ; k++)
			if(Reader[k][0]==1)
				Tag[(int)(Reader[k][1])][0] = 0;
		
		return result;
	}

	static int[] roundQA(int n0, int nTag, int Q, int Q_Val){
		double Reader[][] = new double [Q][2];

		for(int i=0 ; i<nTag ; i++) Tag[i][1]=0;

		AboutTag.timer(Tag, nTag, Q);
		TagResponse.Response(Tag, Reader, nTag, Q);
		
		int S = TagValue.SuccessSlot(Reader, Q);
		int C = TagValue.failSlot(Reader, Q);
		int I = TagValue.idleSlot(Reader, Q);
		
		double Q_Value = (double) Q_Val;
		
		for(int j=0 ; j<Q ; j++){
			if(Reader[j][0]>1){
				Q_Value = Q_Value + 1;
				if(Q_Value > 15) Q_Value = 15;
			}
			else if(Reader[j][0]==0){
				Q_Value = Q_Value - 1;
				if(Q_Value < 0) Q_Value = 0;
			}
			else nTag = nTag - 1;
		}
		
		SumS += S;
		SumC += C;
		SumI += I;
		
		SumQ += Q;
		
		System.out.println("Q : " + Q);
		System.out.println(Math.pow(2, (int)Math.round(Q_Value)));
		int result[] = new int[4];
		result[0] = (int)Math.pow(2, (int)Math.round(Q_Value));
		result[1] = nTag;		
		result[2] = (int)Q_Value;
		result[3] = C;
		
		for(int k=0 ; k<Q ; k++)
			if(Reader[k][0]==1)
				Tag[(int)(Reader[k][1])][0] = 0;
		
		return result;
	}

	static int EstMota(int nTag, int Q){
		double Q_Value=4;
		int FinalQ = 0;
		int end=0;
		
		
		for( ; ; ){
			for( ; ; ){
				double Reader[][] = new double[Q][2];
				SumQ += Q;
				
				for(int i=0 ; i<nTag ; i++) {Tag[i][0] = 1;}
				
				AboutTag.timer(Tag, nTag, Q);
				TagResponse.Response(Tag, Reader, nTag, Q);
				
				if(Reader[0][0]>1 && Reader[1][0]>1 && Reader[2][0]>1){
					Q_Value = Q_Value + 1; Q = (int)Math.pow(2, Q_Value);
				}
				else if(Reader[0][0]==0 && Reader[1][0]==0 && Reader[2][0]==0){
					Q_Value = Q_Value - 1; Q = (int)Math.pow(2, Q_Value);
				}
				else break;	
			}
		
			for( ; ; ){
				double Reader[][] = new double[Q][2];
				SumQ += Q;
				
				for(int i=0 ; i<nTag ; i++) Tag[i][0] = 1;
	
				AboutTag.timer(Tag, nTag, Q);
				TagResponse.Response(Tag, Reader, nTag, Q);
				
				if(Reader[0][0]>1 && Reader[1][0]>1 && Reader[2][0]>1){
					Q_Value = Q_Value + 1; Q = (int)Math.pow(2, Q_Value); break;
				}
				else if(Reader[0][0]==0 && Reader[1][0]==0 && Reader[2][0]==0){
					Q_Value = Q_Value - 1; Q = (int)Math.pow(2, Q_Value); break;
				}
				else end=1; break; //종료조건 end
			}
			if(end==1) break;
		}
		
		FinalQ = Q;
		
		return FinalQ;
	}
	
}
	
