package EX2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
class Meal {
	 @Override
	public String toString() {
		return "Meal [orderNum=" + orderNum + "]";
	}

	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
		
	}

class Waiter implements Runnable {

	private Restaurant restaurant;
	public Waiter(Restaurant restaurant) {
		this.restaurant=restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized(this)
				{
					while (restaurant.meal==null) {
						wait();
						}
				}
				System.out.println("waiter got"+restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal=null;
					restaurant.chef.notifyAll();
				}
			}
			
		} catch (InterruptedException e) {
			System.out.println("waiter interruptted!");
		}
	}

}
class Chef implements Runnable {

	private Restaurant restaurant;
	private int count=0;
	public Chef(Restaurant restaurant) {
		this.restaurant=restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized(this)
				{
					while (restaurant.meal!=null) {
						wait();
					}
				}
				if(++count==10)
				{
					System.out.println("Out of food,closing");
					restaurant.exe.shutdownNow();
				}
				System.out.println("Order up!");
				synchronized(restaurant.waiter)
				{
				
					restaurant.meal=new Meal(count);
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(3);
			}
		} catch (InterruptedException e) {
			System.out.println("chef interrupted");
		}
		
	}
}

public class Restaurant {

	Meal meal;
	ExecutorService exe=Executors.newCachedThreadPool();
	Waiter waiter=new Waiter(this);
	Chef chef=new Chef(this);
	public Restaurant() {
		exe.execute(chef);
		exe.execute(waiter);
	}
	public static void main(String[] args) {
		new Restaurant();
	}
}
