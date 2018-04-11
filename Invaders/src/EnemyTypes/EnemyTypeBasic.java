
package EnemyTypes;

import EstructurasDatos.ListaSimple;
import GameScreen.Player;
import GameScreen.PlayerWeapon;
import Main.Main;
import Sprite.SpriteAnimation;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class EnemyTypeBasic implements EnemyType{
    
    private double speed = 2.0d;
    
    private Rectangle rect;
    private SpriteAnimation enemySprite;
    
    public EnemyTypeBasic(double xPos, double yPos, int rows, int columns){
        enemySprite = new SpriteAnimation(xPos, yPos, rows, columns, 500, "/images/en1.png");
        enemySprite.setWidth(35);
        enemySprite.setHeight(40);
        enemySprite.setLimit(2);

        this.setRect(new Rectangle((int)enemySprite.getPosX(), (int)enemySprite.getPosY(), enemySprite.getWidth(), enemySprite.getHeight()));   
        enemySprite.setLoop(true);
    }

    @Override
    public void draw(Graphics2D g) {
        enemySprite.draw(g);
    }

    @Override
    public void update(double delta, Player player) {
        enemySprite.update(delta);
        
        enemySprite.setPosX(enemySprite.getPosX()-(delta*speed));
        this.getRect().x = (int) enemySprite.getPosX();
    }

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

    @Override
    public boolean collide(int i, Player player, ListaSimple<EnemyType> enemys) {
       if(enemySprite.isPlay()){
           if(enemys.get(i).deathScene()){
               enemys.print();
               enemys.remove(i);
           }
           return false;
       }
        
        for(int w = 0; w < player.playerWeapons.weapons.getSize(); w++){
            if(enemys != null && player.playerWeapons.weapons.get(w).collisionRect(((EnemyTypeBasic) enemys.get(i)).getRect())){
                enemySprite.resetLimit();
                enemySprite.setAnimationSpeed(100);
                enemySprite.setPlay(true, true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOutOfBounds() {
        if(rect.x > 0 && rect.x < (Main.WIDTH - rect.width))
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
    
}
