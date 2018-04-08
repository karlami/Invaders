
package GameScreen;

import State.SuperStateMachine;
import java.awt.Canvas;
import java.awt.Graphics2D;

public class GameScreen implements SuperStateMachine{
    
    private Player player;
    
    public GameScreen(){
        player = new Player(375,550,50,40);
    }

    @Override
    public void update(double delta) {
        player.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        player.draw(g);
    }

    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(player);
    }
    
    
}
