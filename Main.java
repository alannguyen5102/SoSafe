package sosafesystems;
import java.io.IOException;

import javax.swing.SwingUtilities;

public class Main {
 public static void main(String[] args) throws IOException {
	 
	 
	 Thread aWorker = new Thread() {
		 
         public void run(){       // Report the result using invokeLater()

         SwingUtilities.invokeLater( new Runnable(){
           
           public void run(){
        		try {
					LogInWindow login = new LogInWindow();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           }
          });// End of SwingUtilities.invokeLater
         }
         };// anonymous-class for aWorker

          aWorker.start();  // So we don�t hold up the event dispatch thread

}
}
