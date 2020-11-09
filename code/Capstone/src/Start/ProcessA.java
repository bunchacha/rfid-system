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
			else Tag[i][1] = 10000;  //프로그램 상 코드(timer값 '없음'을 대신)
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
						Tag[j][2] += Time.TimeSlot * Power.TagResponse;
						Tag[j][3] += Time.TimeSlot;
					}
					else {
						Tag[j][2] += Time.TimeSlot * Power.Sleeptimer;
						Tag[j][3] += Time.TimeSlot;
					}
				}
			}
			basic.time += Time.TimeSlot;
		}
	}
}

class TagValue{	
	static int SuccessSlot(double Reader[][], int Q){
		int S_Tag = 0;
		for(int i=0 ; i<Q ; i++){
			if(Reader[i][0]==1) S_Tag += 1;
		}
		
		return S_Tag;
	}
	
	static int failSlot(double Reader[][], int Q){
		int F_slot = 0;
		for(int i=0 ; i<Q ; i++)
			if(Reader[i][0]>1) F_slot += 1;
		
		return F_slot;
	}
}

public class ProcessA {
	static double SumPower=0;
	static int SumQ=0, Repeat_Round=0;
	static double Tag[][];
	
	static int[] firstRound(int nTag, int Q){		
		double Reader[][] = new double[Q][2];
		
		for(int i=0 ; i<nTag ; i++)
			Tag[i][0] = 1;
		
		AboutTag.timer(Tag, nTag, Q);
		TagResponse.Response(Tag, Reader, nTag, Q);
		
		int N = AntiCollision.ALOHA(Reader, Q);   //충돌 알고리즘(이 부분을 바꿔주세요!!)
		int S = TagValue.SuccessSlot(Reader, Q);
		
		SumQ += Q;
	
		int result[] = new int[2];
		result[0] = N;
		result[1] = nTag-S;
		
		return result;
	}

	static int[] round1(int n0, int nTag, int Q){
		double Reader[][] = new double [Q][2];
		
		for(int i=0 ; i<n0 ; i++) Tag[i][1]=0;

		AboutTag.timer(Tag, n0, Q);
		TagResponse.Response(Tag, Reader, n0, Q);
		
		int N = AntiCollision.ALOHA(Reader, Q);//충돌 알고리즘(이 부분을 바꿔주세요!!)
		
		int S = TagValue.SuccessSlot(Reader, Q);
			
		SumQ += Q;
		
		int result[] = new int[2];
		result[0] = N;
		result[1] = nTag-S;
		
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
}
