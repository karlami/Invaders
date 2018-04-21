
package State;

import java.awt.Canvas;
import java.awt.Graphics2D;
import GameScreen.GameScreen;
/**
 * Ventana principal, en ella se generan todas las ventanas que se requieran
 * @author HP_15
 */
public class StateMachine {

    private SuperStateMachine game;
    private Canvas canvas;

    public StateMachine(Canvas canvas){
        game = new GameScreen(this);
        this.canvas = canvas;
    }
    /**
     * Para dibujar la pantalla de la lista de states(canvas)
     * @param g para dibujar el objeto seleccionado de tipo Graphics2D
     */
    public void draw(Graphics2D g){
        game.draw(g);
    }
    /**
     * Para actualizar las ventanas
     * @param delta 
     */
    public void update(double delta){
        game.update(delta);
    }
    /**
     * Inicializar de nuevo la pantalla del game en el canvas
     */
    public void setState(){
        game.init(canvas);
}
}