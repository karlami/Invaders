
package State;

import java.awt.Canvas;
import java.awt.Graphics2D;


import EstructurasDatos.ListaSimple;

public class StateMachine {

	private ListaSimple<SuperStateMachine> states = new ListaSimple();
	private Canvas canvas;
	private byte selectState = 0;
	
	public StateMachine(Canvas canvas){
		//SuperStateMachine game = new GameScreen(this);
		//SuperStateMachine menu = new MenuScreen(this);
		//states.add(menu);
		//states.add(game);
		
		this.canvas = canvas;
	}
	/**
         * Para dibujar la pantalla de la lista de states(canvas)
         * @param g para dibujar el objeto seleccionado de tipo Graphics2D
         */
	public void draw(Graphics2D g){
		states.get(selectState).draw(g);
	}
	
	public void update(double delta){
		states.get(selectState).update(delta);
	}
	/**
         * Cambiar de pantalla de menu a juego
         * @param i para seleccionar la pantalla a visualizar
         */
	public void setState(byte i){
		for(int r = 0; r < canvas.getKeyListeners().length; r++)
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
                        selectState = i;
                        states.get(selectState).init(canvas);
	}
}