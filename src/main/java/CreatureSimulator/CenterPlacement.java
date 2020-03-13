package CreatureSimulator;

import javafx.geometry.Point2D;

public class CenterPlacement implements PlacementStrategy {



	@Override
	public Point2D setOrigin(double x, double y, double width, double height) {
		Point2D origin = new Point2D(x - width/2.0, y - height/2.0);
		return origin;
	}

}
