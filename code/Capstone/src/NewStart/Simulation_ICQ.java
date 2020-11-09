package NewStart;

import java.io.*;

import jxl.write.*;
import jxl.write.biff.*;

public class Simulation_ICQ{
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
		
		final int Si_times=10000;   //시뮬레이션 횟수
		int Recognition = 0;
		double S_TP = 0;
		
		double data[][] = new double[10001][101];
		double data1[][] = new double[10001][101];
		double data2[][] = new double[10001][101];
		double data3[][] = new double[10001][101];
		double data4[][] = new double[10001][101];
		
		double TagPower[] = new double[10001];
		double DayPower[][] = new double[10001][101];
		double Day_P[] = new double[2];
		double Final[][] = new double[101][2];
		
		double round[] = new double[101];
		double slot[] = new double[101];
		double result[] = new double[101];
	    
		
		for(int a=100 ; a<=2000 ; a+=100){
			int index=1;   //데이터 배열 순서
			
			for(int i=3 ; i<=3 ; i++){
				
				for(double c=1 ; c<=1 ; c+=0.2){
					
					for(int x=4 ; x<=4 ; x++){  
					
						for(int j=1; j<Si_times+1 ; j++){
							
							double s = - a * Math.log (1 - Math.random());
							int N0 = (int) Math.ceil(s);
							
							Process.Tag = new double[N0][4];
							int n0=N0, C_round=1; 
							
							int nextQ = Process.EstMota(n0, x, i, c);//Mota 추정
							//data[j][(int)a/100] = nextQ;
							
							int C[] = Process.firstRound(n0, nextQ);	
						
							for(; C[1] != 0 ;){				
								C = Process.round1(n0, C[1], C[0]);
								C_round += 1;  
							}
							
							double Throughput = (double)Process.SumS/(Process.SumS+Process.SumC+Process.SumI+Process.SumE);
							//double Throughput = Process.SumThroughput/C_round;
										
							double TimeQ = Process.SumQ;
								
							//data[j][(int)a/100] = Throughput;	//이거를 Throughput이랑 TimeQ 바꿀 것!
							
							/*		
							data1[j][(int)a/100] = Process.SumE;
							data2[j][(int)a/100] = Process.SumS+Process.SumC+Process.SumI;
							data3[j][(int)a/100] = (double)Process.SumE/i;
							data4[j][(int)a/100] = C_round;
							*/ 
							
							Process.SumQ = 0;
										
							Process.SumS = 0;
							Process.SumC = 0;
							Process.SumI = 0;
							
							Process.SumE = 0;
										
							S_TP += Throughput;	
							
						}
						data[index][(int)a/100] = S_TP/Si_times;	//이거를 Throughput이랑 TimeQ 바꿀 것!
						S_TP = 0;
						
						index++;
						
						//System.out.println("("+i+", "+c+")");
						
						}
				}
			}
			System.out.println("태그 수 : " + a);
		}
		
		//data = Final;
		Excel.output(data);                                                                                                                                                                                                                                                                                                                                                      

	}
}