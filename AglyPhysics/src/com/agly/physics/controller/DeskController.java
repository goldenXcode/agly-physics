package com.agly.physics.controller;

import com.agly.physics.model.Ball;
import com.agly.physics.model.Click;
import com.agly.physics.model.Desk;
import com.agly.physics.model.Magnet;
import com.agly.physics.model.Wall;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DeskController {

	// constant useful for logging
	public static final String LOG = DeskController.class.getSimpleName();

	Desk desk;

	private Ball ball;


	public static Stage stage;

	public DeskController(final Stage stage) {
		
		DeskController.stage = stage;

		this.ball = new Ball();

		
		
		//RenderHelper.shapeRenderer.identity();
		
		// add the ball to the stage
		stage.addActor(ball);
		
		float width = stage.getCamera().viewportWidth;
		float height = stage.getCamera().viewportHeight;
		
		float indent = 70;
		Magnet magnet = new Magnet(ball);
		magnet.setPosition(indent, indent);
		stage.addActor(magnet);
		
		Magnet magnet2 = new Magnet(ball);
		magnet2.setPosition(width - indent, indent);
		stage.addActor(magnet2);
		
		Magnet magnet3 = new Magnet(ball);
		magnet3.setPosition(indent, height - indent);
		stage.addActor(magnet3);
		
		Magnet magnet4 = new Magnet(ball);
		magnet4.setPosition(width - indent, height - indent);
		stage.addActor(magnet4);
		
		buildBounds();
		
		
	}
	
	private void buildBounds() {
		
		
		Wall westWall = new Wall(stage.getCamera().viewportHeight);
		westWall.setPosition(westWall.getWidth() / 2, westWall.getHeight() / 2);
		stage.addActor(westWall);
		
		Wall eastWall = new Wall(stage.getCamera().viewportHeight);
		eastWall.setPosition(stage.getCamera().viewportWidth - eastWall.getWidth() / 2,
				eastWall.getHeight() / 2);
		stage.addActor(eastWall);
		
		Wall southWall = new Wall(stage.getCamera().viewportWidth, 90);
		southWall.setPosition(stage.getCamera().viewportWidth / 2, 0);
		stage.addActor(southWall);
		
		Wall northWall = new Wall(stage.getCamera().viewportWidth, 90);
		northWall.setPosition(stage.getCamera().viewportWidth / 2,
				stage.getCamera().viewportHeight);
		stage.addActor(northWall);
	}

	public void update(float delta) {
		//clickCollision();
		Desk.world.step(1 / 60f, 6, 2);
		
	}

}
