package CreatureSimulator;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Toolkit;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import CreatureSimulator.creatures.Creature;
import CreatureSimulator.creatures.RoundCreature;
import CreatureSimulator.creatures.SimpleCreature;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CreatureSimulatorApplication extends GraphicsApp {
	/*
	 * create a creature class that draws
	 */
	
	List<Creature> creatures;
	Class selected;

	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void setup() {
		selected = SimpleCreature.class;
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	frames(20);
	    width = (int)screen.getWidth()-300;
	    height = (int)screen.getHeight()-100;
	    
	    GridPane gpCreaturesRoot = creatureSelectorRoot();
	    TitledPane tpCreatures = new TitledPane("Creatures", gpCreaturesRoot);
        tpCreatures.setCollapsible(false);
        setLeft(tpCreatures);
	    
	    creatures = new ArrayList<Creature>();
	    
	    graphicContext.getCanvas().setOnMouseClicked(e -> {
	    	Creature c = null;
    		Constructor constr = null;
			try {
				constr = selected.getConstructor(Double.TYPE, Double.TYPE);
		    	if(e.getButton() == MouseButton.PRIMARY) {
		    		c = (Creature)constr.newInstance(e.getX(), e.getY());
			    	creatures.add(c);
		    	}
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				e1.printStackTrace();
			}
	    });
	    title("Creature Simulator");
	}

	@Override
	public void draw() {
		update();
		for(Creature creature: creatures) {
			creature.draw(graphicContext);
		}
	}

	private void update() {
		for(Creature creature: creatures) {
			creature.move();
		}
	}
	
	
	public GridPane creatureSelectorRoot() {
		final double HGAP = 10.0;
		final double VGAP = 10.0;
		GridPane view = new GridPane();
		view.setHgap(HGAP);
		view.setVgap(VGAP);
		
		Button btn = new Button();
		Canvas canvas = new Canvas(Creature.WIDTH * 2, Creature.WIDTH * 2);
		Creature c = new RoundCreature(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
		c.draw(canvas.getGraphicsContext2D());
		btn.setGraphic(canvas);
		btn.setOnMouseClicked(e -> {

			selected = RoundCreature.class;
		});
		
		Button btn2 = new Button();
		Canvas canvas2 = new Canvas(Creature.WIDTH * 2, Creature.WIDTH * 2);
		Creature c2 = new SimpleCreature(canvas2.getWidth() / 2.0, canvas2.getHeight() / 2.0);
		c2.setPlacementStrategy(new CenterPlacement());
		c2.draw(canvas2.getGraphicsContext2D());
		btn2.setGraphic(canvas2);
		btn2.setOnMouseClicked(e -> {

			selected = SimpleCreature.class;
		});
		
		
		view.addRow(0, btn, btn2);
		
		return view;
	}
}
