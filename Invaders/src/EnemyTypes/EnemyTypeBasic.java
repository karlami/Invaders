
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
    
    public EnemyTypeBasic(double xPos, double yPos, int width, int height, String imgPath){
        enemySprite = new SpriteAnimation(xPos, yPos, width, height, 300);
        
        try{
                URL url = this.getClass().getResource(imgPath);
                BufferedImage pSprite = ImageIO.read(url);
                for(int i = 0; i < 2; i++){
                    enemySprite.addSprite(pSprite, 0 + (i * 85), 0 , 102, 86);
                }
            }catch(IOException e){};
        this.setRect(new Rectangle((int)enemySprite.getPosX(), (int)enemySprite.getPosY(), width, height));   
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
        speed *= -1.05d;
        enemySprite.setPosX(enemySprite.getPosX() - (delta * speed));
        this.getRect().x = (int) enemySprite.getPosX();
        
        enemySprite.setPosY(enemySprite.getPosY() + (delta * 20));
        this.getRect().y = (int) enemySprite.getPosY();
    }

    @Override
    public boolean deathScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean collide(int i, Player player, ListaSimple<EnemyType> enemys) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
