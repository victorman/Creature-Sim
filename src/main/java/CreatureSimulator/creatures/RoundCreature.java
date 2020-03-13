package CreatureSimulator.creatures;

import CreatureSimulator.CenterPlacement;
import CreatureSimulator.SmoothMove;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoundCreature extends Creature {
	
	public RoundCreature(double x, double y) {
		super(x, y, WIDTH, WIDTH, new CenterPlacement(), new SmoothMove());
	}
	@Override
	public void draw(GraphicsContext context) {

		context.setFill(Color.YELLOW);
		context.fillOval(getX(), getY(), getWidth(), getHeight());
	}

}
