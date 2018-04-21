
package GameScreen;

import EstructurasDatos.ListaSimple;
import Hileras.HileraBasic;
import Hileras.ClaseA;
import Hileras.ClaseB;
import Hileras.SuperLevel;
import State.StateMachine;
import State.SuperStateMachine;
import java.awt.Canvas;
import java.awt.Font;
import Timer.TickTimer;
import java.awt.Color;
import java.awt.Graphics2D;
import Main.Main;
/**
 * Ventana donde se da el juego
 */
public class GameScreen extends SuperStateMachine{
    
    private Player player;
    
    
    private int nivel = 1;
    private int numHilera = (int)Math.floor(Math.random()*3);;
    private int contadorHileras = 0;
    
    private static int score = 0;
    
    private Font gameScreen = new Font("Arial", Font.PLAIN, 48);
    
    private TickTimer gameOverTimer = new TickTimer(180);
    private TickTimer completeTimer = new TickTimer(180);
    
    private ListaSimple<SuperLevel> hileras = new ListaSimple();
     
    public GameScreen(StateMachine stateMachine){
       super(stateMachine);
       
       player = Player.getPlayer(375,550,50,40);
       addHileras();
       
       
    }
    /**
     * Para los movimientos de los objetos y para dar aviso cuando se pierde o gana niveles
     * @param delta 
     */
    @Override
    public void update(double delta) {
        player.update(delta);
        hileras.get(numHilera).update(delta);
        
            if (hileras.get(numHilera).isGameOver()) {
                gameOverTimer.tick(delta);
                if (gameOverTimer.isEventReady()) {
                    hileras.get(numHilera).reset();
                    getStateMachine().setState();
                    score = 0;
                    nivel = 0;
                }
            }
            for(int nivelMaximo = 100; nivelMaximo>nivel; nivelMaximo--){
                if(contadorHileras == 2){
                    nivel+=1;
                    ClaseA.aumentarSpeed();
                    ClaseB.aumentarSpeed();
                    HileraBasic.aumentarSpeed();
                    contadorHileras = 0;
                }
            }
            
                if(hileras.get(numHilera).isComplete()) { 
                    completeTimer.tick(delta);
                    if (completeTimer.isEventReady()) {
                        if(numHilera < hileras.getSize()-1){
                            numHilera++;
                        }else{
                            numHilera = (int)Math.floor(Math.random()*3);
                        }
                        contadorHileras++;
                        hileras.get(numHilera).reset();

                    }
                }
    }
    
    /**
     * Dibuja la ventana y los objetos en ella
     * @param g 
     */
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.drawString("Puntos: " + score, 5, 15);
        
        g.setColor(Color.CYAN);
        g.drawString("Nivel: " + nivel, 5, 35);

        player.draw(g);
        hileras.get(numHilera).draw(g);
        
        g.setColor(Color.MAGENTA);
        g.drawString("Hilera actual: " + hileras.get(numHilera).getNombre(), 600, 15);
        
        g.setColor(Color.white);
        //g.drawString("Hilera siguiente: " + hileras.get(numHilera + 1).getNombre(), 600, 35);
        
        
            if (hileras.get(numHilera).isGameOver()) {
                g.setColor(Color.red);
                g.setFont(gameScreen);
                String gameOver = "GAME OVER!";
                int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
                g.drawString(gameOver, (Main.getWIDTH()/2)-(gameOverWidth/2), Main.getHEIGHT()/2);
            }

            if (hileras.get(numHilera).isComplete()) {
                g.setColor(Color.PINK);
                g.setFont(gameScreen);
                String complete = ("HILERA " + hileras.get(numHilera).getNombre() + " COMPLETA!");
                int completeWidth = g.getFontMetrics().stringWidth(complete);
                g.drawString(complete, (Main.getWIDTH()/2)-(completeWidth/2), Main.getHEIGHT()/2);
            }

    }
    /**
     * Inicializar las entradas del teclado para el jugador
     * @param canvas para la ventana del juego
     */
    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(player);
    }
    /**
     * Para ir aumentando el puntaje del jugador
     * @param score 
     */
    public static void plusScore(int score) {
        GameScreen.score += score;
    }
    
    /**
     * Agrega a la lista de hileras, los tres tipos de hileras existentes
     */
    private void addHileras() {
       ClaseB hil1 = new ClaseB(player);
       ClaseA hil2 = new ClaseA(player);
       HileraBasic hil3 = new HileraBasic(player);
       hileras.add(hil1);
       hileras.add(hil2);
       hileras.add(hil3);
    }
   
}
