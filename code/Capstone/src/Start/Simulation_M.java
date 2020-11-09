package Start;

import java.io.*;

import jxl.write.*;
import jxl.write.biff.*;

public class Simulation_M {
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
		double result[] = new double[101];
	    
		for(int a=100 ; a<=1800 ; a+=100){
	
			for(int i=1; i<Si_times+1 ; i++){
					Process.Tag = new double[a][4];
					/*
					double s = - 10 * Math.log (1 - Math.random());
					int L = (int) Math.round(s);
					*/

					for(int j = 0 ; j < 1 ; j++){
						int n0=a, C_round=1; 
						
						int nextQ = Process.EstMota(n0, 16);
						
						int C[] = Process.firstRound(n0, nextQ);
						
						for(; C[1] != 0 ;){				
							C = Process.round1(n0, C[1], C[0]);
							C_round += 1;
						}
			
						//double Throughput = (double)Process.SumS/(Process.SumS+Process.SumC+Process.SumI);
						double Throughput = Process.SumThroughput/C_round;
						
						double TimeQ = Process.SumQ;
						//double Time = Output.Time();
						
						data[i][(int)a/100] = Throughput;	//이거를 Throughput이랑 TimeQ 바꿀 것!
						
						Process.SumQ = 0;
						
						Process.SumS = 0;
						Process.SumC = 0;
						Process.SumI = 0;
						
						Process.SumThroughput = 0;
						
						basic.time = 0;				
					}					
				}
			
				/*
				double R_rate = (double)Recognition/Si_times;
				data[1][a] = R_rate;
				R_rate = 0;
				*/

				System.out.println("태그 수 : " + a);
				
			}

		//data = Final;
		Excel.output(data);                                                                                                                                                                                                                                                                                                                                                      

	}
}