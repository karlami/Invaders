
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
        
    }
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    
    public Main(){
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
