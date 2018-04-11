
package Sprite;

import EstructurasDatos.ListaSimple;
import Timer.Timer;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class SpriteAnimation {
    
    private ListaSimple<BufferedImage> sprites = new ListaSimple();
    
    private byte currentSprite;
    
    private boolean loop = false;
    private boolean play = false;
    private boolean destroyAfterAnim = false;
    
    private Timer timer;
    
    private int animationSpeed;
    private int width, height;
    private int limit;
    
    private double posX;
    private double posY;
    
    public SpriteAnimation(double posX, double posY,int rows, int columns, int animationSpeed, String imgPath){
        this.animationSpeed = animationSpeed;
        this.posX = posX;
        this.posY = posY;
        
        try{
                URL url = this.getClass().getResource(imgPath);
                BufferedImage pSprite = ImageIO.read(url);
                int spriteWidth = pSprite.getWidth() / columns;
                int spriteHeight = pSprite.getHeight() / rows;
                for(int y = 0; y < rows; y++){
                    for(int x = 0; x < columns; x++)
                        addSprite(pSprite
                                , 0 + (x * spriteWidth)
                                , 0 + (y * spriteHeight)
                                , spriteWidth
                                , spriteHeight);
                }
            }catch(IOException e){};
        
        timer = new Timer();
        // pues el tamaño no incluye al cero indice
        limit = sprites.getSize() - 1;
    }
    
    public void draw(Graphics2D g){
        if (isSpriteAnimDestroyed())
            return;
        g.drawImage(sprites.get(currentSprite), (int)getPosX(), (int)getPosY(), width, height, null);
    }
    
    public void update(double delta){
        if (isSpriteAnimDestroyed())
            return;

	if (loop && !play)
            loopAnimation();
	if (play && !loop)
            playAnimation();
    }
    
    public void stopAnimation() {
	loop = false;
	play = false;
    }

    public void resetSprite() {
	loop = false;
	play = false;
	currentSprite = 0;
    }
    
    
    private void loopAnimation() {
	if (timer.isTimerReady(animationSpeed) && currentSprite == limit){
            currentSprite = 0;
            timer.resetTimer();
	}else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
            currentSprite++;
	} 
    }

    
    private void playAnimation() {
	if (timer.isTimerReady(animationSpeed) && currentSprite != limit && !getDestroyAfterAnim()) {
            play = false;
            currentSprite = 0;
	} else if (timer.isTimerReady(animationSpeed) && currentSprite == limit && getDestroyAfterAnim()) {
            sprites = null;
	}else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
            currentSprite++;
	}
    }
    /**
     * ver si el sprite fue destruido
     * @return true y luego es destruido
     */
    public boolean isSpriteAnimDestroyed() {
	if (sprites == null)
            return true;

        return false;
    }
    
    public void addSprite(BufferedImage spriteMap, int posX, int posY, int width, int height) {
        sprites.add(spriteMap.getSubimage(posX, posY, width, height));
	}
    
    public void setPlay(boolean play, boolean destroyAfterAnim){
        if(loop){
            loop = false;
        }
        this.play = play;
        this.destroyAfterAnim = destroyAfterAnim;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean getDestroyAfterAnim() {
        return destroyAfterAnim;
    }

    public void setDestroyAfterAnim(boolean destroyAfterAnimation) {
        this.destroyAfterAnim = destroyAfterAnimation;
    }

    public byte getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(byte currentSprite) {
        this.currentSprite = currentSprite;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public int getLimit() {
        return limit;
    }
    /**
     * Para el tamaño de la lista - 1 por los indices, pues empiezan en 0
     * @param limit que va usarse para mandar el tamaño de la lista segun la cantidad 
     * que haya, si es 0 cambia el valor por el solicitado tal y como lo solicita
     */

    public void setLimit(int limit) {
        if(limit > 0){
            this.limit = limit - 1;
        }else{
            this.limit = limit;
        }
    }
    
    public void resetLimit(){
        limit = sprites.getSize() - 1;
    }

    }
    
