package Start;

public class AntiCollision {

	//선험적 방법(B1)
	static int ALOHA(double Reader[][], int Q){
		int T = TagValue.failSlot(Reader, Q);
		int nextQ = 0;
		if(T < (double)Q/8) nextQ = (int) Math.round((double)Q/2);
		else if(T >= (double)Q/4) nextQ = 2*Q;
		else nextQ = Q;
	
		return nextQ;
	}


	//Schoute(B2)
	static int Schoute(double Reader[][], int Q){
		int Slot_count=0;
		double k2=0;
		for(int i=0 ; i<Q ; i++)
			if(Reader[i][0]>1)	Slot_count += 1;
		
		k2 = 2.3922;
		return (int) Math.ceil(k2*Slot_count);
	}
	
	//k1, k2 계산(B3)
	static int K1K2(double Reader[][], int Q){
		int Slot_count=0;
		double k1=0, k2=0;
		for(int i=0 ; i<Q ; i++){
			if(Reader[i][0]>1)	Slot_count += 1;
		}
			
		k1 = (double)Slot_count/Q;
		if(k1<0.2642) k2=2.2057;
		else if(k1<0.5940) k2=2.6699;
		else if(k1<0.8009) k2=3.2576;
		else if(k1<0.9927) k2=5.2610;
		else k2=8.5673;
		
		return (int) Math.ceil(k2*Slot_count);
	}
	
	//Mota
	static int Mota(double Reader[][], int Q){
		int Slot_count=0;
		double k2=0;
		for(int i=0 ; i<Q ; i++)
			if(Reader[i][0]>1)	Slot_count += 1;
		
		k2 = 2.62;
		return (int) Math.ceil(k2*Slot_count);
	}
	
}
