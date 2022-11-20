package EX2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



class Car {
	private boolean waxed=false;

	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxed==false) {
			wait();
		}
		
	}

	public synchronized void waxed() {
		waxed=true;
		notifyAll();
	}

	public synchronized void waitForBuffering() throws InterruptedException {
		while (waxed==true) {
			wait();
		}
	}

	public synchronized void buffed() {
		waxed=false;
		notifyAll();
	}
}

class WaxOff implements Runnable {

	private Car car;
	
	public WaxOff(Car car) {
		this.car=car;
		
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				System.out.println("Wax off!");
				TimeUnit.MILLISECONDS.sleep(300);
				car.buffed();
				
			}
		} catch (InterruptedException e) {
			System.out.println("exit via interrupt");
		}
		System.out.println("end of buffer task!");
	}

}


class WaxOn implements Runnable {

	private Car car;
	
	public WaxOn(Car car) {
		this.car=car;
		
	}


	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffering();
			}
		} catch (InterruptedException e) {
			System.out.println("exit via interrupt");
		}

		System.out.println("end of wax task!");
	}

}
public class WaxMaster {

	public static void main(String[] args) throws InterruptedException {
		Car car=new Car();
		ExecutorService exe=Executors.newCachedThreadPool();
		exe.execute(new WaxOff(car));
		exe.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exe.shutdownNow();
	}
	
}
