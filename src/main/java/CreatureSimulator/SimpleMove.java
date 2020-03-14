package CreatureSimulator;

import CreatureSimulator.creatures.Creature;
import javafx.geometry.Point2D;

public class SimpleMove implements MoveStrategy {

	@Override
	public Point2D move(Creature creature) {
		double deg = creature.getRandom().nextDouble() * 360;
		System.out.println("random deg: " + deg);
		double deltaX = Math.cos(deg) * creature.getSpeed();
		double deltaY = Math.sin(deg) * creature.getSpeed();
		Point2D delta = new Point2D(deltaX, deltaY);
		
		return delta;
	}

	@Override
	public void pickDirection() {
		// TODO Auto-generated method stub

	}

}
