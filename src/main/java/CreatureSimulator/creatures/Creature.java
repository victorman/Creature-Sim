package CreatureSimulator.creatures;


import java.util.Random;

import CreatureSimulator.MoveStrategy;
import CreatureSimulator.PlacementStrategy;
import CreatureSimulator.SimpleMove;
import CreatureSimulator.SimplePlacement;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Creature {

	public static final double WIDTH = 30.0;
	public static final double DEFAULT_SPEED = 1.0;
	
	protected double mX;
	protected double mY;
	protected double mWidth;
	protected double mHeight;
	          
	protected double mSpeed;
	protected double mDeltaX;
	protected double mDeltaY;
	protected int mFramesSinceMoveChange;
	
	private Random rand;
	
	
	private PlacementStrategy placementStrategy;
	private MoveStrategy moveStrategy;
	public Creature(double x, double y, double width, double height, PlacementStrategy placementStrategy, MoveStrategy moveStrategy) {
		
		if(placementStrategy == null)
			this.placementStrategy = new SimplePlacement();
		else
			this.placementStrategy = placementStrategy;
		
		if(moveStrategy == null)
			this.moveStrategy = new SimpleMove();
		else
			this.moveStrategy = moveStrategy;
		
		Point2D origin = this.placementStrategy.setOrigin(x, y, width, height);
		mX = (int)origin.getX();
		mY = (int)origin.getY();
		mWidth = width;
		mHeight = height;
		
		mSpeed = DEFAULT_SPEED;
		rand = new Random(System.currentTimeMillis());
		randomizeDelta();
		setFramesSinceMoveChange(0);
	}
	
	public Creature(double x, double y, double width, double height) {
		this(x,y,width,height,null,null);
	}
	
	public abstract void draw(GraphicsContext context);
	
	public double getX() {
		return mX;
	}
	public double getY() {
		return mY;
	}
	
	public double getWidth() {
		return mWidth;
	}
	
	public double getHeight() {
		return mHeight;
	}
	

	public double getDeltaX() {
		return mDeltaX;
	}

	public double getDeltaY() {
		return mDeltaY;
	}

	public void move() {
		Point2D delta = moveStrategy.move(this);
		mDeltaX = delta.getX();
		mDeltaY = delta.getY();
		mX += mDeltaX;
		mY += mDeltaY;
	}

	public double getSpeed() {
		return mSpeed;
	}

	public Random getRandom() {
		return rand;
	}

	public int getFramesSinceMoveChange() {
		return mFramesSinceMoveChange;
	}
	public void setFramesSinceMoveChange(int frames) {
		mFramesSinceMoveChange = frames;
	}
	
	public void randomizeDelta() {
		double deg = getRandom().nextDouble() * 360;
		mDeltaX = Math.cos(deg) * getSpeed();
		mDeltaY = Math.sin(deg) * getSpeed();
	}

	
	public void setPlacementStrategy(PlacementStrategy strategy) {
		placementStrategy = strategy;
		Point2D origin = this.placementStrategy.setOrigin(mX, mY, mWidth, mHeight);
		mX = (int)origin.getX();
		mY = (int)origin.getY();
	}
}
