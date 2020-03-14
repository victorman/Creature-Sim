package CreatureSimulator.creatures;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimpleCreature extends Creature {

	public SimpleCreature(double x, double y) {
		super(x, y, WIDTH, WIDTH);
	}

	@Override
	public void draw(GraphicsContext context) {
		context.setFill(Color.DARKGREEN);
		context.fillRect(getX(), getY(), getWidth(), getHeight());
	}


}
