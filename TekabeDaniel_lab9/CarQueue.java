import java.util.Queue;
import java.util.Random;
import java.util.ArrayDeque;


public class CarQueue {
	
	Queue<Integer> myQueue;
	Random num;
	
	public CarQueue() {
		myQueue=new ArrayDeque<Integer>();
		num=new Random();
		
	
		myQueue.add(num.nextInt(4));
		myQueue.add(num.nextInt(4));
		myQueue.add(num.nextInt(4));
		myQueue.add(num.nextInt(4));
		myQueue.add(num.nextInt(4));
	
	}

	public void addToQueue() {
		
	class QueueRunnable implements Runnable{

		@Override
		public void run() {
		
			
			try {
				
				while(true) {
				myQueue.add(num.nextInt(4));
				Thread.sleep(200);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	Runnable r=new QueueRunnable();
	Thread x=new Thread(r);
	x.start();
		
	}
	
	public int deleteQueue() {
		
		
		return myQueue.remove();
		
	}
}
