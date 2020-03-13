package CreatureSimulator;


import javafx.geometry.Point2D;

public interface PlacementStrategy {
	public Point2D setOrigin(double x, double y, double width, double height);
}
