package CreatureSimulator;

import CreatureSimulator.creatures.Creature;
import javafx.geometry.Point2D;

public class SmoothMove implements MoveStrategy {

	private static final double MOVE_RATE = 0.0177; //based on calculations for around 40 frame average to change direction
	@Override
	public Point2D move(Creature creature) {
		int frames = creature.getFramesSinceMoveChange();
		
		/*
		 *  at 10 frames there should be a .5 chance of changing directions
		 *  it would probably be an exponential function
		 *  variables are our random value and frames
		 *  frame: 0, chance 0.0
		 *  frame: 5, chance 0.015625
		 *  frame 6, 0.03125
		 *  frame 7, 0.0625
		 *  frame 8, 0.125
		 *  frame 9, 0.25
		 *  frame: 10, chance = 0.5
		 *  
		 *  (frame * x)^2 = chance
		 *  frame * x = sqrt(chance)
		 *  x = sqrt(chance) / frame
		 *  
		 *  should work like:
		 *  if rand.nextDouble > func(frames) then continue;
		 */
		double r = creature.getRandom().nextDouble();
		double deg, deltaX, deltaY = 0.0;
		System.out.println("move potential: " + Math.pow(frames * MOVE_RATE, 2) );
		if ( Math.pow(frames * MOVE_RATE, 2) > 0.3) {
			deltaX = 0.0;
			deltaY = 0.0;
			creature.setFramesSinceMoveChange(0);
		} else if(r < Math.pow(frames * MOVE_RATE, 2) && frames != 0) {
			System.out.println("Moved");
			deg = creature.getRandom().nextDouble() * 360;
			deltaX = Math.cos(deg) * creature.getSpeed();
			deltaY = Math.sin(deg) * creature.getSpeed();
			creature.setFramesSinceMoveChange(0);
		} else {
			deltaX = creature.getDeltaX();
			deltaY = creature.getDeltaY();
			creature.setFramesSinceMoveChange(creature.getFramesSinceMoveChange()+1);
		}
		Point2D delta = new Point2D(deltaX, deltaY);
		
		return delta;
	}

	@Override
	public void pickDirection() {
		// TODO Auto-generated method stub

	}

}
