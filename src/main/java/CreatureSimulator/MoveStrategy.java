package CreatureSimulator;


import CreatureSimulator.creatures.Creature;
import javafx.geometry.Point2D;

public interface MoveStrategy {
	public Point2D move(Creature creature);
	public void pickDirection();
}
