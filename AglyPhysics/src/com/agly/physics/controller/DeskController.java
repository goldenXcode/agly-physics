package com.agly.physics.controller;

import com.agly.physics.model.Ball;
import com.agly.physics.model.Click;
import com.agly.physics.model.Desk;
import com.agly.physics.view.obj.Ball2D;
import com.agly.physics.view.obj.Click2D;
import com.agly.physics.view.obj.Magnet2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DeskController {

	// constant useful for logging
	public static final String LOG = DeskController.class.getSimpleName();

	Desk desk;

	private Click2D click2d;
	private Ball2D ball2d;

	private Ball ball;
	private Click click;

	public static Stage stage;

	public DeskController(final Stage stage) {
		
		DeskController.stage = stage;

		this.ball2d = new Ball2D();
		this.click2d = new Click2D();
		
		ball = ball2d.getBall();
		click = click2d.getClick();

		// center the ball horizontally
		ball2d.setPosition((stage.getWidth() / 2 - ball2d.getWidth() / 2),
				ball2d.getHeight());

		// add the ball to the stage
		stage.addActor(ball2d);
		
		stage.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (click.state == Click.State.PASSIVE) {
					stage.addActor(click2d);
					click.state = Click.State.ACTIVE;
				}
				click2d.setPosition(x, y);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
		});

		Magnet2D magnet2d = new Magnet2D(ball);
		magnet2d.setPosition(300, 200);
		stage.addActor(magnet2d);
		
		Magnet2D magnet2d2 = new Magnet2D(ball);
		magnet2d2.setPosition(80, 200);
		stage.addActor(magnet2d2);
		
		
	}

	public void update(float delta) {
		clickCollision();
		//targetColision();
		Desk.world.step(1 / 60f, 6, 2);
	}

	private void clickCollision() {

		if (click.state == Click.State.PASSIVE)
			return;
		
		/*
		Gdx.app.log( LOG, "Click no round " + click.getPosition()); 
		Gdx.app.log( LOG, "Ball no round " + ball.getPosition()); 
		Gdx.app.log( LOG, "Click round " +
				new Vector2(Math.round(click.getPosition().x),
						(Math.round(click.getPosition().y)))); 
		Gdx.app.log( LOG, "Ball round " +
				new Vector2(Math.round(ball.getPosition().x),
						(Math.round(ball.getPosition().y)))); 
		*/
		
		if (Math.round(click.getPosition().x) == Math
				.round(ball.getPosition().x)
				&& Math.round(click.getPosition().y) == Math.round(ball
						.getPosition().y)) {

			ball.setVelocity(new Vector2(0, 0));

			Gdx.app.log(
					LOG,
					"Reached position " + click.getPosition());
			click.state = Click.State.PASSIVE;

			stage.getActors().removeValue(click2d, true);
			return;
		}

		Vector2 BallVelocity = new Vector2();

		if (Math.round(ball.getPosition().x) < click.getPosition().x) {
			BallVelocity.x = Ball.SPEED;
		} else if (Math.round(ball.getPosition().x) > click.getPosition().x) {
			BallVelocity.x = -Ball.SPEED;
		} else {
			BallVelocity.x = 0;
		}

		if (Math.round(ball.getPosition().y) < click.getPosition().y) {
			BallVelocity.y = Ball.SPEED;
		} else if (Math.round(ball.getPosition().x) > click.getPosition().y) {
			BallVelocity.y = -Ball.SPEED;
		} else {
			BallVelocity.y = 0;
		}
		System.out.println(ball.getBody().getLinearVelocity());
		ball.setVelocity(BallVelocity);
	}

	private void targetColision() {

		//Ball ball = ball2d.getBall();
		//Click click = click2d.getClick();

	}

}
