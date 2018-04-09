
package EnemyTypes;

import EstructurasDatos.ListaSimple;
import GameScreen.Player;
import java.awt.Graphics2D;

public interface EnemyType {
	

    public void draw(Graphics2D g);
    public void update(double delta, Player player);
    public void changeDirection(double delta);

    public boolean deathScene();
    public boolean collide(int i, Player player, ListaSimple<EnemyType> enemys);
    public boolean isOutOfBounds();

    
    }

