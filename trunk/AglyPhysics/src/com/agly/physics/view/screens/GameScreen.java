package com.agly.physics.view.screens;

import com.agly.physics.AglyPhysics;
import com.agly.physics.controller.DeskController;
import com.agly.physics.model.Desk;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends AbstractScreen {

	private DeskController controller;
	private double seconds;

	public GameScreen(AglyPhysics game) {
		super(game);

	}

	@Override
	protected boolean isGameScreen() {
		return true;
	}

	@Override
	public void show() {
		super.show();

		controller = new DeskController(this);

	}

	@Override
	public void render(float delta) {

		super.render(delta);
		controller.update(delta);

		if (RenderHelper.isDebug() == true) {
			RenderHelper.box2dRenderer.render(Desk.world,
					DeskController.stage.getCamera().combined);
		}

		seconds += delta;
		
		getBatch().begin();
		getFont().draw(getBatch(),
				Double.toString(Math.round(seconds * 10d) / 10d), 20, 20);
		getFont().draw(getBatch(),
				new Integer(controller.getScore()).toString(), 50, 20);
		getBatch().end();
		
		
		if (seconds > 10) {
			this.gameOver();
		}
	}
	
	public void restart() {
		controller.restart();
	}
	
	public void gameOver() {
		this.controller.gameOver();
		
        this.stage.clear();
       // Desk.cleanWorld();
        controller = new DeskController(this);
        seconds = 0;
	}
	
	public Stage getStage() {
		return this.stage;
	}

		
		
	
	

}
