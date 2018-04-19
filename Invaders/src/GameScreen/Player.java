
package GameScreen;

import Main.Main;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
/**
 * La nave del Jugador, sus movimientos y lista de balas
 */
public class Player implements KeyListener{

    private final double speed = 5.0d;

    private BufferedImage pSprite;
    private Rectangle rect;
    private double xPos, yPos, startXPos, startYPos;
    private int width, height;

    private static Player player;
    private boolean left = false, right = false, shoot = false;

    public PlayerWeapon playerWeapons;

    //Singleton, constructor private
    private Player(double xPos, double yPos, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.startXPos = xPos;
        this.startYPos = yPos;
        this.width = width;
        this.height = height;

        rect = new Rectangle((int) xPos,(int) yPos+25, width, height-25);

        try{
            URL url = this.getClass().getResource("/images/player.png");
            pSprite = ImageIO.read(url);
        }catch(IOException e){};
        playerWeapons = new PlayerWeapon();
    }
    /**
     * Para la instanciacion del unico objeto tipo Player
     * no permite que se creen mas
     * @param xPos coordenada x
     * @param yPos coordenada y
     * @param width ancho
     * @param height alto
     * @return el jugador con las caracteristicas requeridas
     */
    public static Player getPlayer(double xPos, double yPos, int width, int height){
        if(player == null){
            player = new Player(xPos, yPos, width, height);
        }else{
            System.out.println("No se puede crear otra nave porque ya existe una nave de la clase Player");
        }
        return player;
    }
    /**
     * Lo dibuja en pantalla con las caracterisiticas requeridas asi como la lista de las balas
     * @param g 
     */
    public void draw(Graphics2D g){
            g.drawImage(pSprite,(int) xPos,(int) yPos, width, height, null);
            playerWeapons.draw(g);
    }
    /**
     * Movimiento de la nave, Main.WIDTH-width para que no se pase del 
     * lado derecho de la Game Screen
     * @param delta para ajustar a la velocidad fps adecuada
     */
    public void update(double delta){
            if(right && !left && xPos < Main.getWIDTH()-width){
                    xPos += speed * delta;
                    rect.x = (int) xPos;
            }if(!right && left && xPos > 10){
                    xPos -= speed * delta;
                    rect.x = (int) xPos;
            }

            playerWeapons.update(delta);

            if(shoot){
                playerWeapons.shootBullet(xPos, yPos, 5, 5);
            }
    }
    /**
     * Para los controles del teclado para manipular a la nave
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
                    right = true;
            }else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
                    left = true;
            }

            if (key == KeyEvent.VK_SPACE){
                    shoot = true;
            }
    }
    /**
     * Para cuando se dejan de presionar las teclas que controlan a la nave
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
                    right = false;
            }else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
                    left = false;
            }

            if (key == KeyEvent.VK_SPACE){
                    shoot = false;
            }
    }
    @Override
    public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

    }

    public Rectangle getRect() {
            return rect;
    }
    /**
     * Devuelve a la nave al mismo punto de partida, con los mismos valores
     */
    public void reset() {

            left = false;
            right = false;
            shoot = false;

            xPos = startXPos;
            yPos = startYPos;
            rect.x = (int) xPos;
            rect.y = (int) yPos+25;
            playerWeapons.reset();
    }
}

