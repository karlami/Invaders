
package Bullets;

import Main.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MachineGun extends PlayerWeaponType{
 
	private Rectangle bullet;
	private final double speed = 2.5d;
	
	public MachineGun(double xPos, double yPos, int width,int height){
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setWidth(width);
		this.setHeight(height);
		
		this.bullet = new Rectangle((int) getxPos(),(int) getyPos(), getWidth(), getHeight());
	}
	/**
         * Dibuja la bala que es un rectangulo de color anaranjado
         * @param g para el objeto tipo Graphics2D a dibujar
         */
	@Override
	public void draw(Graphics2D g) {
            if(bullet == null)
                return;

            g.setColor(Color.ORANGE);
            g.fill(bullet);
	}

	@Override
	public void update(double delta) {
            if(bullet == null)
                return;

            this.setyPos(getyPos() - (delta * speed));
            bullet.y = (int) this.getyPos();
            isOutofBounds();
	}
        
        /**
         * Cuando colisionan dos rectangulos, se da una interseccion entre ambos
         * @param rect que va a ser con el que va a chocar
         * @return true si intersecan, false si no
         */

	@Override
	public boolean collisionRect(Rectangle rect) {
            if(this.bullet == null)
                return false;

            if(bullet.intersects(rect)){
                this.bullet = null;
                return true;
            }

            return false;
	}

	@Override
	public boolean destroy() {
            if(bullet == null)
                return true;

            return false;
	}
        
        /**
         * Cuando una bala sale de la pantalla, se vuelve nula porque ya no es funcional
         */

	@Override
	protected void isOutofBounds() {
            if(this.bullet == null)
                return;

            if(bullet.y < 0 || bullet.y > Main.getHEIGHT() || bullet.x < 0 || bullet.x > Main.getWIDTH()){
                bullet = null;
            }
	}

}
