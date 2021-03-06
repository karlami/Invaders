
package EnemyTypes;

import EstructurasDatos.ListaDoble;
import EstructurasDatos.ListaSimple;
import GameScreen.GameScreen;
import GameScreen.Player;
import Main.Main;
import Sprite.SpriteAnimation;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EnemyTypeBoss implements EnemyType{
    
    private double speed;
    
    private Rectangle rect;
    private SpriteAnimation enemySprite;
    
    private int vida;
    
    public EnemyTypeBoss(double xPos, double yPos, int rows, int columns, int vida, double speed){
        this.vida = vida;
        this.speed = speed;
        
        enemySprite = new SpriteAnimation(xPos, yPos, rows, columns, 500, "/images/boss.png");
        enemySprite.setWidth(35);
        enemySprite.setHeight(40);
        enemySprite.setLimit(2);

        this.setRect(new Rectangle((int)enemySprite.getPosX(), (int)enemySprite.getPosY(), enemySprite.getWidth(), enemySprite.getHeight()));   
        enemySprite.setLoop(true);
    }
    /**
     * Dibuja la animacion del enemigo
     * @param g 
     */
    @Override
    public void draw(Graphics2D g) {
        enemySprite.draw(g);
    }
    /**
     * Mueve el enemigo en el eje "x"
     * @param delta
     * @param player 
     */
    @Override
    public void update(double delta, Player player) {
        enemySprite.update(delta);
        
        enemySprite.setPosX(enemySprite.getPosX()-(delta*speed));
        this.getRect().x = (int) enemySprite.getPosX();
    }
    /**
     * Hace el movimiento de que cuando llegue a un extremo baje y siga del otro lado
     * @param delta 
     */    
    @Override
    public void changeDirection(double delta) {
        speed *= -1.15d;
        enemySprite.setPosX(enemySprite.getPosX() - (delta * speed));
        this.getRect().x = (int) enemySprite.getPosX();
        
        enemySprite.setPosY(enemySprite.getPosY() + (delta * 20));
        this.getRect().y = (int) enemySprite.getPosY();
    }
    /**
     * Para saber cuando se debe eliminar un enemigo
     * @return false si el enemigo esta en pantalla o true si esta destruido
     */

    @Override
    public boolean deathScene() {
        if(!enemySprite.isPlay()){
            return false;
        }
        if(enemySprite.isSpriteAnimDestroyed()){
            return true;
        }
        return false;
    }
    /**
     * Logica de las colisiones con las balas del jugador y los enemigos
     * cuando colisionan los dos se eliminan
     * @param i contador
     * @param player para tomar la lista de balas
     * @param enemys lista de enemigos
     * @return true o false si colisionan o no
     */
    @Override
    public boolean collide(int i, Player player, ListaSimple<EnemyType> enemys) {
       if(enemySprite.isPlay()){
           if(enemys.get(i).deathScene()){
               
               enemys.remove(i);
           }
           return false;
       }
        
        for(int w = 0; w < player.playerWeapons.weapons.getSize(); w++){
            if(enemys != null && player.playerWeapons.weapons.get(w).collisionRect(((EnemyTypeBoss) enemys.get(i)).getRect())){
                this.vida--;
            }if(vida == 0){
                enemySprite.resetLimit();
                enemySprite.setAnimationSpeed(100);
                enemySprite.setPlay(true, true);
                GameScreen.plusScore(10);
                enemys.clear();
                return true;
            }
        }
        return false;
    }
    /**
     * Para cuando esta en el borde de los lados
     * @return 
     */
    @Override
    public boolean isOutOfBounds() {
        if(rect.x > 0 && rect.x < (Main.getWIDTH() - rect.width))
            return false;
        return true;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public SpriteAnimation getEnemySprite() {
        return enemySprite;
    }

    public void setEnemySprite(SpriteAnimation enemySprite) {
        this.enemySprite = enemySprite;
    }

    @Override
    public boolean collide(int i, Player player, ListaDoble<EnemyType> enemys) {
        if(enemySprite.isPlay()){
           if(enemys.get(i).deathScene()){
               
               enemys.remove(i);
           }
           return false;
       }
        
        for(int w = 0; w < player.playerWeapons.weapons.getSize(); w++){
            if(enemys != null && player.playerWeapons.weapons.get(w).collisionRect(((EnemyTypeBoss) enemys.get(i)).getRect())){
                this.vida--;
            }if(vida == 0){
                enemySprite.resetLimit();
                enemySprite.setAnimationSpeed(100);
                enemySprite.setPlay(true, true);
                GameScreen.plusScore(10);
                enemys.clear();
                return true;
            }
        }
        return false;
    }
    /**
     * Da la posicion del jugador para las balas
     * @return true o false
     */
    @Override
    public boolean posicPlayer() {
        if(rect.y > Main.getHEIGHT() - 50)
            return true;
        return false;
    }

}
