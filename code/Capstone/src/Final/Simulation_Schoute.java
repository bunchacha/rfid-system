package Final;

import java.io.*;

import jxl.write.*;
import jxl.write.biff.*;

public class Simulation_Schoute{
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
		
		final int Si_times=1000;   //시뮬레이션 횟수
		double S_TP = 0, S_time=0;
		
		double data[][] = new double[10001][101];    //Excel data 크기
			
		for(int a=100 ; a<=2000 ; a+=100){
			int index=1;   //데이터 배열 순서
					
				for(int j=1; j<Si_times+1 ; j++){
							
					double s = - a * Math.log (1 - Math.random());	 //태그가 지수분포를 따를 때
					int N0 = (int) Math.ceil(s);
						
					Process Schoute = new Process();
					
					Schoute.Tag = new double[N0][4];
					int n0=N0, C_round=1; 
					
					int C[] = Schoute.firstRound(n0, 16);	  
						
					for(; C[1] != 0 ;){				
						C = Schoute.round1(n0, C[1], C[0]);
						C_round += 1;  
					}
							
					double Throughput = (double)Schoute.SumS/(Schoute.SumS+Schoute.SumC+Schoute.SumI+Schoute.SumE);
										
					double TimeQ = Schoute.SumQ;
					
					double TotalTime = basic.time;
								
					//data[j][(int)a/100] = Throughput;	//이거를 Throughput이랑 TimeQ 바꿀 것!
												
					Schoute.SumQ = 0;
										
					Schoute.SumS = 0;
					Schoute.SumC = 0;
					Schoute.SumI = 0;
					Schoute.SumE = 0;
					
					basic.time = 0;
										
					S_TP += Throughput;	
					S_time += TotalTime;		
				}
				
			//data[index][(int)a/100] = S_TP/Si_times;	//이거를 Throughput이랑 TimeQ 바꿀 것!
			data[index][(int)a/100] = S_time/Si_times;
			
			S_TP = 0;
			S_time = 0;
						
			index++;
			
			System.out.println("태그 수 : " +a);
			}
		
		Excel.output(data);                                                                                                                                                                                                                                                                                                                                                      
	}
}