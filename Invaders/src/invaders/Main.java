
package invaders;

import java.awt.Canvas;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{

    public static void main(String[] args) {
        Main display = new Main();
        JFrame frame = new JFrame();
        frame.add(display);
        frame.pack();
        frame.setTitle("Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        display.start();
        
    }
    private boolean running = false;
    private Thread thread;
    //para sychronized para compartir los recursos cada vez que thread
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();    
    }
    
    public synchronized void stop() throws InterruptedException{
        if(!running){
            return;
        }
        running = false;
        thread.join(); 
    }
    
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    
    public Main(){
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
        
    }

    @Override
    public void run() {
        while(running){
            System.out.println("Running");
        }
    }
    
}
