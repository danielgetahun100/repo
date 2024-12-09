import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation()
	   {
	      class AnimationRunnable implements Runnable
	      {
	    	
	         public void run()
	         {
	            try
	            {
	               for(int i=0;i<10;i++)
	               {
	            	   direction = carQueue.deleteQueue();
	            	   
	            
	            	
	            	// Get the width and height of the panel
	                    int Width = getWidth();
	                    int Height = getHeight();

	            	   switch(direction) {
	            	   case 0://up
	            		   y-=10;
	            		   if(y<0) {
	            			   y=0;
	            			   direction=1;
	            			
	            		   }
	            		   break;
	            	   case 1://down
	            		   y+=10;
	            		   if (y > Height-30) { // If car hits bottom bound
                               y = Height-30; // Set car at the bottom
                               direction = 0; // Reverse direction
                             
                           }
	            		   break;
	            	   case 2://right
	            		   x+=95;
	            		   if (x > Width-60) { // If car hits right bound
                               x = Width-60; // Set car at the rightmost position
                               direction = 3; // Reverse direction
                             
                           }
	            		   break;
	            	   case 3://left
	            		   x-=10;
	            		   if(x<0) {
	            			   x=0;
	            		   direction=2;
	            		   
	            		   }
	            		   
	            		   break;
	            		   
	            	   }
	            	   repaint();
	            	   Thread.sleep(delay*1000);
	            	   
	               }
	            }
	            catch (InterruptedException exception)
	            {
	            	
	            }
	            finally
	            {
	            	
	            }
	         }
	      }
	      
	      Runnable r = new AnimationRunnable();
	      Thread t = new Thread(r);
	      t.start();
	   }
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}