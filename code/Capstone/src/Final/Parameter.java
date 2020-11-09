package Final;

//Time Parameter
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