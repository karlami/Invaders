
package Main;
import EstructurasDatos.ListaDoble;
import State.StateMachine;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
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
        frame.setLayout(new BorderLayout());
                
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
    
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    public int FPS;
    
    public static StateMachine state;
    
    public Main(){
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
        
        state = new StateMachine(this);
        state.setState();
        
    }

    @Override
    public void run() {
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int targetFPS = 60;
        final long optimalTime = 1000000000 / targetFPS;
        int frames = 0;
        
        this.createBufferStrategy(3);
        BufferStrategy bs = this.getBufferStrategy();
        
        while(running){

            long now = System.nanoTime();
            long updateLenght = now - lastLoopTime;
            lastLoopTime = now;
            //
            double delta = updateLenght / ((double)optimalTime);
            
            frames ++;
            
            if(System.currentTimeMillis()-timer > 1000){
                timer += 1000;
                FPS = frames;
                frames = 0;

            }
            draw(bs);
            update(delta);
            try{
                /**
                ListaDoble l = new ListaDoble();
                l.add(1);
                l.add(2);
                l.add(3);
                l.add(4);
                l.add(5);
                l.print();
                l.intercambiar(0, 2);
                l.print();
                */
                Thread.sleep(((lastLoopTime - System.nanoTime()) + optimalTime)/1000000);
            }catch(Exception e){
                
            }
        }
    }
    /**
     * Evita el parpadeo de los objetos a dibujar,
     * mostrando cada vez la situacion actual que fue dibujado
     * @param bs usado para un BufferedImage
     */
    public void draw(BufferStrategy bs) {
        do {
            do {
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                g.setColor(Color.darkGray);
                g.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);

                state.draw(g);

                g.dispose();
            } while (bs.contentsRestored());
		bs.show();
	} while (bs.contentsLost());
	}
    public void update(double delta) {
            state.update(delta);
	}

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
    

