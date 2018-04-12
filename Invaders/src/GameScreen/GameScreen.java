
package GameScreen;

import Levels.Level1;
import State.StateMachine;
import State.SuperStateMachine;
import java.awt.Canvas;
import java.awt.Font;
import Timer.TickTimer;
import java.awt.Color;
import java.awt.Graphics2D;
import Main.Main;

public class GameScreen extends SuperStateMachine{
    
    private Player player;
    
    private Level1 level;
    
    public static int score = 0;
    
    private Font gameScreen = new Font("Arial", Font.PLAIN, 48);
    
    private TickTimer gameOverTimer = new TickTimer(180);
    private TickTimer completeTimer = new TickTimer(180);
     
    public GameScreen(StateMachine stateMachine){
       super(stateMachine);
       
       player = Player.getPlayer(375,550,50,40);
       level = new Level1(player);
    }

    

    @Override
    public void update(double delta) {
        player.update(delta);
        level.update(delta);

        if (level.isGameOver()) {
            gameOverTimer.tick(delta);
            if (gameOverTimer.isEventReady()) {
                level.reset();
                getStateMachine().setState((byte) 0);
                score = 0;
            }
        }

        if (level.isComplete()) {
            completeTimer.tick(delta);
            if (completeTimer.isEventReady()) {
                level.reset();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("Score: " + score, 5, 15);

        g.setColor(Color.red);
        g.drawString("Health: " + player.getHealth(), 5, 35);

        player.draw(g);
        level.draw(g);

        if (level.isGameOver()) {
            g.setColor(Color.red);
            g.setFont(gameScreen);
            String gameOver = "GAME OVER!";
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOver);
            g.drawString(gameOver, (Main.getWIDTH()/2)-(gameOverWidth/2), Main.getHEIGHT()/2);
        }

        if (level.isComplete()) {
            g.setColor(Color.green);
            g.setFont(gameScreen);
            String complete = "LEVEL COMPLETE!";
            int completeWidth = g.getFontMetrics().stringWidth(complete);
            g.drawString(complete, (Main.getWIDTH()/2)-(completeWidth/2), Main.getHEIGHT()/2);
        }
    }

    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(player);
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameScreen.score = score;
    }
    
    

}
