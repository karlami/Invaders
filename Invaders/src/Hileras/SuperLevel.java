
package Hileras;

import java.awt.Graphics2D;
/**
 * Molde o guia para la creacion de las hileras
 */
public interface SuperLevel {

    void draw(Graphics2D g);
    void update(double delta);
    void hasDirectionChange(double delta);
    void changeDirectionAllEnemys(double delta);
    void destroy();
    void reset();

    boolean isGameOver();
    boolean isComplete();
    
    String getNombre();

}