package findPrimeNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreadTest {
	static final int gap=1000;
	
    public static void main(String[] args) {
        TreadTest pn = new TreadTest();
        //default value
        int numToFindMin=1,numToFindMax=3000;
        System.out.println("input the range [min,max] to find primes");
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        try {
			numToFindMin=Integer.parseInt(in.readLine());
			numToFindMax=Integer.parseInt(in.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int treadNum=(numToFindMax-numToFindMin+1)/1000;
        for (int i = 0; i < treadNum; i++) {
		pn.new PrimeFinderTread(numToFindMin+i*gap,numToFindMin+(i+1)*gap,i+1).start();
		};
    }
    class PrimeFinderTread extends Thread {
        private int start;
        private int end;
        private int treadName;
        public PrimeFinderTread(int start, int end,int treadName) {
            this.start = start;
            this.end = end;
            this.treadName=treadName;
        }
        public void run() {
            for (int i = start; i < end; i++) {
                    if (isPrime(i)) {
                     System.out.println("Tread-"+treadName+":"+i);                  
                }
            }
        }
		private boolean isPrime(int i) {
			if(i==1)
				return false;
			else if(i==2)
				return true;
			else {
				for (int j = 2; j <= Math.ceil(Math.sqrt(i)); j++) {
				    if (i % j == 0) {
				        return false;
				    }
				}
				return true;
			}
			
		}
	}
}