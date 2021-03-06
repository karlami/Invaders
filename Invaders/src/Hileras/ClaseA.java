
package Hileras;

import EnemyTypes.EnemyType;
import EnemyTypes.EnemyTypeBasic;
import EnemyTypes.EnemyTypeBoss;
import EstructurasDatos.ListaSimple;
import GameScreen.Player;
import java.awt.Graphics2D;
/**
 * Hilera tipo A de enemigos
 * @author HP_15
 */
public class ClaseA implements SuperLevel{

    private Player player;
    
    private static double speed = 2.0d;
    
    private static String nombre = "CLASE A";
    
    private ListaSimple<EnemyType> enemies = new ListaSimple();

    public ClaseA(Player player){
        this.player = player;
        addEnemies();
    }
    /**
     * Dibuja la lista de enemigos
     * @param g 
     */
    @Override
    public void draw(Graphics2D g) {
        if(enemies == null)
            return;

        for(int i = 0; i < enemies.getSize(); i++){
            enemies.get(i).draw(g);
        }

    }
    /**
     * Movimiento de los enemigos y verificacion de las colisiones
     * @param delta 
     */
    @Override
    public void update(double delta) {
        if(enemies == null)
            return;

        for(int i = 0; i < enemies.getSize(); i++){
            enemies.get(i).update(delta, player);
        }
        for(int i = 0; i < enemies.getSize(); i++){
            enemies.get(i).collide(i, player, enemies);
        }

        hasDirectionChange(delta);
    }
/**
 * Para revisar si debe cambiar de direccion
 * @param delta 
 */
    @Override
    public void hasDirectionChange(double delta) {
        if(enemies == null)
            return;

        for(int i = 0; i < enemies.getSize(); i++){
            if(enemies.get(i).isOutOfBounds()){
                changeDirectionAllEnemys(delta);
            }
        }
    }
    /**
     * Para cambiar la direccion de los enemigos y que vayan bajando
     * @param delta 
     */
    @Override
    public void changeDirectionAllEnemys(double delta) {
        for(int i = 0; i < enemies.getSize(); i++){
            enemies.get(i).changeDirection(delta);
        }
    }
    /**
     * Para cuando termina la hilera
     * @return 
     */
    @Override
    public boolean isGameOver() {
        for(int i =0; i<enemies.getSize();i++){
            if(enemies.get(i).posicPlayer())
                return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
    /**
     * Devuelve los valores a los originales
     */
    @Override
    public void reset() {
        player.reset();
        enemies.clear();
        addEnemies();

    }
    /**
     * agrega los enemigos a la lista, agrega al jefe en posicion random
     */
    public void addEnemies() {
        int random = (int)Math.floor(Math.random()*5);
        for(int y = 0; y < 1; y++){
            for(int x = 0; x < 5; x++){
                if(x == random){
                   EnemyTypeBoss e = new EnemyTypeBoss(150 + (x * 40), 100 + (y * 40), 1, 3, 2, speed);
                    enemies.add(e);  
                }else{
                    EnemyTypeBasic e = new EnemyTypeBasic(150 + (x * 40), 100 + (y * 40), 1, 3, 1, speed);
                    enemies.add(e); 
                }            
            }
        }
    }

    /**
     * Verificar cuando la lista fue totalmente eliminada
     * @return 
     */
    @Override
    public boolean isComplete() {
        return enemies.isEmpty();
    }
    @Override
    public String getNombre() {
        return nombre;
    }
    
    public static void aumentarSpeed(){
        ClaseA.speed += 1.0d;
    }
}
