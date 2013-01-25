package com.agly.physics.controller;

import java.util.Random;

import com.agly.physics.model.Ball;
import com.agly.physics.model.Desk;
import com.agly.physics.model.Magnet;
import com.agly.physics.model.Target;
import com.agly.physics.model.Wall;
import com.agly.physics.view.screens.GameScreen;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DeskController {

	// constant useful for logging
	public static final String LOG = DeskController.class.getSimpleName();

	private GameScreen gameScreen;

	private Ball ball;
	
	public static Stage stage;
	
	private int score;
	private boolean restart;
	private Random random = new Random();
	
	
	public DeskController(final GameScreen gameScreen) {
		
		this.gameScreen = gameScreen;
		DeskController.stage = gameScreen.getStage();
		
		initGame();
	}
	
	private void initGame() {
		this.ball = new Ball();
		
		// add the ball to the stage
		stage.addActor(ball);
		
		float width = stage.getCamera().viewportWidth;
		float height = stage.getCamera().viewportHeight;
		
		int wallWidth = 5;
		ball.setPosition(random.nextInt((int)width - wallWidth) + wallWidth,
				random.nextInt((int)height - wallWidth) + wallWidth);
		
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
		
		Target target = new Target();
		target.setPosition(width / 2, height / 2);
		stage.addActor(target);
		
		Desk.world.setContactListener(new ContactListener() {
	
			@Override
			public void endContact(Contact contact) {
			}
			
			@Override
			public void beginContact(Contact contact) {

				if (contact.getFixtureA().getBody().getUserData()
						.equals(Target.class.getSimpleName()) ||
						contact.getFixtureB().getBody().getUserData()
							.equals(Target.class.getSimpleName()))
				{
					score++;
					restart = true;
				}
			}
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			 }

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				
			}

		});
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
		if (restart)
			this.restart();
		Desk.world.step(1 / 60f, 6, 2);
	}
	
	public void restart() {
		Desk.cleanWorld(); 
		DeskController.stage.clear();
		
		initGame();
		
		restart = false;
	}
	
	public void gameOver() {
		
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

}
