package CreatureSimulator;

import javafx.geometry.Point2D;

public class SimplePlacement implements PlacementStrategy {


	@Override
	public Point2D setOrigin(double x, double y, double width, double height) {
		Point2D origin = new Point2D(x, y);
		return origin;
	}

}
