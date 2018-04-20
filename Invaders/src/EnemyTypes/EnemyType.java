
package EnemyTypes;

import EstructurasDatos.ListaDoble;
import EstructurasDatos.ListaSimple;
import GameScreen.Player;
import java.awt.Graphics2D;
/**
 * Molde para los tipos de enemigos
 */
public interface EnemyType {
    
    public void draw(Graphics2D g);
    public void update(double delta, Player player);
    public void changeDirection(double delta);

    public boolean deathScene();
    public boolean collide(int i, Player player, ListaSimple<EnemyType> enemys);
    public boolean collide(int i, Player player, ListaDoble<EnemyType> enemys);
    public boolean isOutOfBounds();
    public boolean posicPlayer();

    }

