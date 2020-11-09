package Start;

import java.io.*;

import Start.Process;
import jxl.write.*;
import jxl.write.biff.*;

public class Simulation_TP {
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
		
		final int Si_times=10000;
		int Recognition = 0;
		
		double data[][] = new double[10001][101];
		double TagPower[] = new double[10001];
		double DayPower[][] = new double[10001][101];
		double Day_P[] = new double[2];
		double Final[][] = new double[101][2];
		
		double round[] = new double[101];
		double slot[] = new double[101];
		double adjust[][] = new double[101][2];
		double repeat[][] = new double[101][2];
		double result[] = new double[101];
	    
	
		for(int a=1000 ; a<=1000 ; a+=100){
	
			for(int i=1; i<Si_times+1 ; i++){
					Process.Tag = new double[a][4];
					/*
					double s = - 10 * Math.log (1 - Math.random());
					int L = (int) Math.round(s);
					*/

					for(int j = -990 ; j < 1001 ; j+=20){
						int n0=a, C_round=1 ;
						int C[] = Process.firstRound(n0,a+j);
						
						for(; C[1] > 0 ;){				
							C = Process.round1(n0,C[1],C[0]);
							C_round += 1;
						}
						
						//double Throughput = (double)Process.SumS/(Process.SumS+Process.SumC+Process.SumI);
						double Throughput = Process.SumThroughput/C_round;
						
						double TimeQ = Process.SumQ;
						//double Time = Output.Time();
				
						//Recognition += Output.Recognition();
						
						/*
						System.out.println("Throughput : " + Throughput);
						System.out.println("Time : " + Time);
						System.out.println("Recognition : " + Recognition);
						*/
						
						data[i][(a+j+10)/20] = Throughput;
						
						Process.SumQ = 0;
						
						Process.SumS = 0;
						Process.SumC = 0;
						Process.SumI = 0;
						
						Process.SumThroughput = 0;
						
						basic.time = 0;
							
					}
					if(i%10==0)
					System.out.println("시뮬레이션 횟수 : " + i);
					
					//Life.Calculate(Process.Tag, a);
				}
				/*
				double R_rate = (double)Recognition/Si_times;
				data[1][a] = R_rate;
				R_rate = 0;
				*/

				//System.out.println("태그 수 : " + a);
			}
			/*
			double b = (double)(Life.Day_P[0]/10000);
			Final[N][0] = (double)b/N;
			Life.Day_P[0] = 0;
			*/
		//data = Final;
		Excel.output(data);                                                                                                                                                                                                                                                                                                                                                      

	}
}
