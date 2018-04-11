
package GameScreen;

import Levels.Level1;
import State.SuperStateMachine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;

public class GameScreen implements SuperStateMachine{
    
    private Player player;
    
    private Level1 level;
    
    public static int score = 0;
    
    public GameScreen(){
       player = Player.getPlayer(375,550,50,40);
       level = new Level1(player);
    }

    @Override
    public void update(double delta) {
        player.update(delta);
        level.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("Score: " + score, 5, 15);
        
        player.draw(g);
        level.draw(g);
    }

    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(player);
    }
    
    
}
