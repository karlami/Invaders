
package Bullets;

import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 *Clase abstracta para darle al Jugador la posibilidad de tener diferentes tipos de municion
 */
public abstract class PlayerWeaponType {
    
    protected double xPos, yPos;
    protected int width, height;

    public abstract void draw(Graphics2D g);
    public abstract void update(double delta);
    public abstract boolean collisionRect(Rectangle rect);
    public abstract boolean destroy();

    protected abstract void isOutofBounds();

    public double getxPos() {
            return xPos;
    }
    public void setxPos(double xPos) {
            this.xPos = xPos;
    }
    public double getyPos() {
            return yPos;
    }
    public void setyPos(double yPos) {
            this.yPos = yPos;
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
    
}
