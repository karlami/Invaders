//sirve por si queremos crear mas niveles
package Levels;

import java.awt.Graphics2D;

public interface SuperLevel {

	void draw(Graphics2D g);
	void update(double delta);
	void hasDirectionChange(double delta);
	void changeDirectionAllEnemys(double delta);
	
	boolean isGameOver();
	boolean isComplete();
	
	void destroy();
	void reset();
}