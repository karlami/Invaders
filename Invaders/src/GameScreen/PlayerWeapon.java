
package GameScreen;

import Bullets.MachineGun;
import Bullets.PlayerWeaponType;
import EstructurasDatos.ListaSimple;
import Timer.Timer;
import java.awt.Graphics2D;

public class PlayerWeapon {
    private Timer timer;
    //private ExplosionManager explosionManager;
    public ListaSimple<PlayerWeaponType> weapons = new ListaSimple();


    public PlayerWeapon(){
        //explosionManager = new ExplosionManager();
        timer = new Timer();
            

    }

    public void draw(Graphics2D g){
        //explosionManager.draw(g);
        for(int i = 0; i < weapons.getSize(); i++){
            weapons.get(i).draw(g);
        }
    }

    public void update(double delta){
        //explosionManager.update(delta);
        for(int i = 0; i < weapons.getSize(); i++){
            weapons.get(i).update(delta);
            if(weapons.get(i).destroy()) {
                //ExplosionManager.createPixelExplosion(weapons.get(i).getxPos(), weapons.get(i).getyPos());
                weapons.remove(i);
            }
        }
    }

    public void shootBullet(double xPos, double yPos, int width, int height){
        if(timer.timerEvent(200)) { 
            weapons.add(new MachineGun(xPos + 22, yPos + 15, width, height));
        }
    }

    public void reset() {
        weapons.clear();
    }
    
}
