package Start;

public class Output {
	static double Throughput(int n0, int SumQ){
		return 1;   //잘못 구했음
	}
	
	static double Time(){
		return basic.time - Time.WakeUpReceive;
	}
	
	static double Recognition(){
		int recog = 0;
		double R_time = basic.time - Time.WakeUpReceive;
		if(R_time <= 1000) recog = 1;
		return recog;
	}

}
