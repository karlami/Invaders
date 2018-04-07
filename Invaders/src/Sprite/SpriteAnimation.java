
package Sprite;

import EstructurasDatos.ListaSimple;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SpriteAnimation {
    
    private ListaSimple<BufferedImage> sprites = new ListaSimple();
    
    private boolean loop = false;
    private boolean play = false;
    private boolean destroyAfterAnimation = false;
    
    private int animationSpeed;
    
    private double posX;
    private double posY;
    
    public SpriteAnimation(double posX, double posY, int animationSpeed){
        this.animationSpeed = animationSpeed;
        this.posX = posX;
        this.posY = posY;
    }
    
    public void draw(Graphics2D g){
        
    }
    
    public void update(double delta){
        
    }
    
    public void addSprite(BufferedImage spriteMap, int posX, int posY, int width, int height) {
        sprites.add(spriteMap.getSubimage(posX, posY, width, height));
	}
    
    public void playerAnimation(boolean play, boolean destroyAfterAnim){
        this.play = play;
        this.destroyAfterAnimation = destroyAfterAnim;
    }
    
    
    
}
