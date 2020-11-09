package Start;

import java.io.*;

import jxl.write.*;
import jxl.write.biff.*;

public class Simulation2 {
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {
		
		final int Si_times=10000;
		int Recognition = 0;
		int Round = 0;
		int SSumQ = 0;
		
		double data[][] = new double[129][101];
		double TagPower[] = new double[10001];
		double DayPower[][] = new double[10001][101];
		double Day_P[] = new double[2];
		double Final[][] = new double[101][2];
		/*
		double round[] = new double[101];
		double slot[] = new double[101];
		double adjust[][] = new double[101][2];
		double repeat[][] = new double[101][2];
		double result[] = new double[101];
	    */             
		for(int Q0=1 ; Q0<129 ; Q0++){
			
			for(int a=1 ; a<101 ; a++){
	
				for(int i=1; i<Si_times+1 ; i++){
					ProcessA.Tag = new double[a][4];
					/*
					double s = - 10 * Math.log (1 - Math.random());
					int L = (int) Math.round(s);
					*/
					for(int j = 0 ; j < 1 ; j++){
						int n0=a, C_round=1 ;
						int C[] = ProcessA.firstRound(n0,Q0);
				
						for(; C[1] > 0 ;){				
							C = ProcessA.round1(n0,C[1],C[0]);
							C_round += 1;
						}
				
						//double Throughput = Output.Throughput(a, ProcessA.SumQ);
				
						//double Time = Output.Time();/
				
						//Recognition += Output.Recognition();
						
						
						/*
						System.out.println("Throughput : " + Throughput);
						System.out.println("Time : " + Time);
						System.out.println("Recognition : " + Recognition);
						*/
								
						
						basic.time = 0;
						//Round += C_round;
						SSumQ += ProcessA.SumQ;
						ProcessA.SumQ = 0;
					}
				}
				//data[Q0][a] = (double)Round/Si_times;
				data[Q0][a] = (double)SSumQ/Si_times;
				Round = 0;
				SSumQ = 0;
				}
			System.out.println("Q0 : " + Q0);
		}

		//Excel.output(data);
		Excel.output(data);
	}
}
