package Start;

//A의 Time
/*
class Time{
	static double WakeUpReceive = 2450;
	static double Query = 1.308 + (12 * 0.032) + 0.051;
	static double TimeSlot = 1.296 + (20 * 0.032) + 0.051 + 2; 
	static double QueryRepeat(int Q, int S_Tag){
		return 1.308 + (7 + ((double)Q/8) + (double)S_Tag)*0.032 + 0.051 + 2 + 2 ; 
	}
	static double QueryAdjust(int Q, int S_Tag){
		return 1.308 + (8 + ((double)Q/8) + (double)S_Tag)*0.032 + 0.051 + 2 + 2 ;		
	}
	static double SleepCommand = 1.308 + (14 * 0.032) + 0.051 ;
}
*/

//B의 Time
/*
class Time{
	static double WakeUpReceive = 21.25 + 8.5 + 12.75;
	static double Query = 1.308 + (10 * 0.032) + 0.051 + 2 + 2 ;
	static double TimeSlot = 1.296 + (17 * 0.032) + 0.051 + 1 ; 
	static double QueryRepeat(int Q, int S_Tag){
		return 1.308 + (7 + ((double)Q/8) + (double)S_Tag)*0.032 + 0.051 + 2 + 2 ; 
	}
	static double QueryAdjust(int Q, int S_Tag){
		return 1.308 + (8 + ((double)Q/8) + (double)S_Tag)*0.032 + 0.051 + 2 + 2 ;		
	}
	static double SleepCommand = 1.308 + (14 * 0.032) + 0.051 ;
}
*/

//EachAck의 Time
class Time{
	static double WakeUpReceive = 21.25 + 8.5 + 12.75;
	static double Query = 1.308 + (12 * 0.032) + 0.051 + 2 + 2 ;
	static double TimeSlot = 1.296 + (17 * 0.032) + 0.051 + 1 ;
	static double QueryRepeat(int Q, int S_Tag){
		return 1.308 + (7 + ((double)Q/8) + (double)S_Tag)*0.032 + 0.051 + 2 + 2 ; 
	}
	static double QueryAdjust(int Q, int S_Tag){
		return 1.308 + (8 + ((double)Q/8) + (double)S_Tag)*0.032 + 0.051 + 2 + 2 ;		
	}
	static double SleepCommand = 1.308 + (14 * 0.032) + 0.051 ;
}

//A의 Power
/*
class Power{
	static double WakeUpPower = 6.5 + 24.3;
	static double Query = 6.5 + 24.3;
	static double Sleeptimer = 6.5;
	static double TagResponse = 6.5 + 28.7;		
}
*/

//B의 Power
class Power{
	static double WakeUpPower = (0.013 * 21.25 + 0.004 * 8.5 + 6.5 * 12.75)/21.25;
	static double Query = 0.0002 + 6.5 + 24.3;
	static double Sleeptimer = 0.0002 + 0.0016;
	static double TagResponse = 0.0002 + 6.5 + 28.7;		
}


class Life{
	static double Day_P[] = new double[2];
	
	static void Calculate(double Tag[][], int n0){
		double Sleep[] = new double [n0];
		double DayPower[] = new double [n0];
		
		for(int a = 0; a < n0 ; a++){
			//A Sleep 전류
			//Sleep[a] = (24*3600 - (double)Tag[a][3]/1000) * (0.0016 + 0.0308);
			
			//B Sleep 전류
			Sleep[a] = (24*3600 - (double)Tag[a][3]/1000) * 0.0044;
			
			DayPower[a] = (double)(Sleep[a] + (double)Tag[a][2]/1000)/3600;
			Day_P[0] += DayPower[a];
			//double PowerYear = 365 * ((double)CollectionDay/1000 + Sleep)/3600;
		}
	}
}