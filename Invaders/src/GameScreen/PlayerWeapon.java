
package GameScreen;

import Bullets.MachineGun;
import Bullets.PlayerWeaponType;
import EstructurasDatos.ListaSimple;
import Timer.Timer;
import java.awt.Graphics2D;
/**
 * Balas de la nave del jugador
 */
public class PlayerWeapon {
    private Timer timer;

    public ListaSimple<PlayerWeaponType> weapons = new ListaSimple();


    public PlayerWeapon(){

        timer = new Timer();
            

    }
    /**
     * Para dibujar cada nave de la lista de naves
     * @param g 
     */
    public void draw(Graphics2D g){

        for(int i = 0; i < weapons.getSize(); i++){
            weapons.get(i).draw(g);
        }
    }
    /**
     * Movimiento de las balas
     * @param delta 
     */
    public void update(double delta){

        for(int i = 0; i < weapons.getSize(); i++){
            weapons.get(i).update(delta);
            if(weapons.get(i).destroy()) {
                
                weapons.remove(i);
            }
        }
    }
    /**
     * Movimiento de las balas en un tiempo determinado 
     * @param xPos la posicion en coordenada x
     * @param yPos posicion en coordenada y
     * @param width ancho
     * @param height alto
     */
    public void shootBullet(double xPos, double yPos, int width, int height){
        if(timer.timerEvent(200)) { 
            weapons.add(new MachineGun(xPos + 22, yPos + 15, width, height));
        }
    }
    /**
     * Vacia la lista de balas para que no sean mas dibujadas
     */
    public void reset() {
        weapons.clear();
    }
    
}
