package CreatureSimulator;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import CreatureSimulator.creatures.Creature;
import CreatureSimulator.creatures.RoundCreature;
import CreatureSimulator.creatures.SimpleCreature;
import javafx.application.Application;
import javafx.scene.Scene;
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

	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void setup() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	frames(20);
	    width = (int)screen.getWidth();
	    height = (int)screen.getHeight()-100;
	    
	    creatures = new ArrayList<Creature>();
	    
	    graphicContext.getCanvas().setOnMouseClicked(e -> {
	    	Creature c = null;
	    	if(e.getButton() == MouseButton.PRIMARY)
	    		c = new SimpleCreature((int)e.getX(), (int)e.getY());
	    	else if(e.getButton() == MouseButton.SECONDARY)
	    		c = new RoundCreature((int)e.getX(), (int)e.getY());
	    	creatures.add(c);
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
}
