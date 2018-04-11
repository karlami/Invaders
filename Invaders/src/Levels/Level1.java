// quitar esta clase, el nivel es controlafo en el Main
//mientrad vidas dif 0, hacer los niveles infinitod 
package Levels;

import EnemyTypes.EnemyType;
import EnemyTypes.EnemyTypeBasic;
import EstructurasDatos.ListaSimple;
import GameScreen.Player;
import java.awt.Graphics2D;

public class Level1 implements SuperLevel{

	private Player player;
	private ListaSimple<EnemyType> enemies = new ListaSimple();
	
	public Level1(Player player){
            this.player = player;
            for(int y = 0; y < 1; y++){
                for(int x = 0; x< 5; x++ ){
                    EnemyType enem = new EnemyTypeBasic(200 +(x * 40), 75 + (y * 40), 1, 3);
                    enemies.add(enem);
                }
            }
		
	}
	
	@Override
	public void draw(Graphics2D g) {
            if(enemies == null)
                return;

            for(int i = 0; i < enemies.getSize(); i++){
                enemies.get(i).draw(g);
            }

	}

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

	@Override
	public void changeDirectionAllEnemys(double delta) {
            for(int i = 0; i < enemies.getSize(); i++){
                enemies.get(i).changeDirection(delta);
            }
        }

	@Override
	public boolean isGameOver() {
            //return player.getHealth() <= 0;
            return false;
        }

	@Override
	public void destroy() {
		
	}

	@Override
	public void reset() {
            player.reset();
            enemies.clear();
            //addEnemies();
		
	}
	/**
	public void addEnemies() {
            for(int y = 0; y < 2; y++){
                for(int x = 0; x < 2; x++){
                    EnemyTypeBasic e = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1, 3);
                    enemies.add(e);
                }
            }
	}*/

	@Override
	public boolean isComplete() {
            return enemies.isEmpty();
	}
}
