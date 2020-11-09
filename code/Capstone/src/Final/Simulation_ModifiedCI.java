package Final;

import java.io.*;

import jxl.write.*;
import jxl.write.biff.*;

public class Simulation_ModifiedCI{
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
		
		final int Si_times=100;   //시뮬레이션 횟수
		
		double S_TP = 0, S_time=0;
		
		double data[][] = new double[10001][101];
	   
		for(int a=100 ; a<=2000 ; a+=100){
			int index=1;   //데이터 배열 순서
			
			for(int i=3 ; i<=3 ; i++){
				
				for(double c=1 ; c<=1 ; c+=0.2){
					
					for(int x=4 ; x<=4 ; x++){  
					
						for(int j=1; j<Si_times+1 ; j++){
							
							double s = - a * Math.log (1 - Math.random());
							int N0 = (int) Math.ceil(s);
							
							Process CI = new Process();
							
							CI.Tag = new double[N0][4];
							int n0=N0, C_round=1; 
							
							int nextQ = CI.EstModifiedCI(n0, x, i, c);//Mota 추정
							//data[j][(int)a/100] = nextQ;
							
							int C[] = CI.firstRound(n0, nextQ);	
						
							for(; C[1] != 0 ;){				
								C = CI.round1(n0, C[1], C[0]);
								C_round += 1;  
							}
							
							double Throughput = (double)CI.SumS/(CI.SumS+CI.SumC+CI.SumI+CI.SumE);
										
							double TimeQ = CI.SumQ;
								
							double TotalTime = basic.time;
							
							//data[j][(int)a/100] = Throughput;	//이거를 Throughput이랑 TimeQ 바꿀 것!
						
							CI.SumQ = 0;
										
							CI.SumS = 0;
							CI.SumC = 0;
							CI.SumI = 0;
							CI.SumE = 0;
							
							basic.time = 0;
							
							S_TP += Throughput;	
							S_time += TotalTime;	
						}
						
						//data[index][(int)a/100] = S_TP/Si_times;	//이거를 Throughput이랑 TimeQ 바꿀 것!
						data[index][(int)a/100] = S_time/Si_times;
						
						S_TP = 0;
						S_time = 0;
						
						index++;
					}
				}
			}
			System.out.println("태그 수 : " + a);
		}
		
		Excel.output(data);                                                                                                                                                                                                                                                                                                                                                      
	}
}

