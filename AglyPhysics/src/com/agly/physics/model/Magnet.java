package com.agly.physics.model;

import com.agly.physics.controller.DeskController;
import com.agly.physics.model.Magnet.State;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Magnet extends Actor {
	
	// constant useful for logging
	public static final String LOG = Magnet.class.getSimpleName();
	
	public enum State {
		ACTIVE, PASSIVE
	}

	public State state = State.PASSIVE;
	
	private Ball ball;
	
	private Color color;
	/**
	 * The Object's size
	 */
	public static final float SIZE = 10f;

	private Vector2 position;

	private Vector2 force = new Vector2();

	public Magnet(Ball ball) {
		this.ball = ball;
		position = new Vector2();

		super.setTouchable(Touchable.enabled);
		this.setWidth(Magnet.SIZE * 2);
		this.setHeight(Magnet.SIZE * 2);

		if (state == State.ACTIVE) 
			this.color = Color.BLUE;
		else
			this.color = Color.GRAY;
		
		this.addListener(new ClickListener() {

			@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
	
					if (state == State.ACTIVE) {
						state = State.PASSIVE;
						color = Color.GRAY;
						Gdx.app.log(
								LOG, "The magnet setted to passive");
						
					}
					else {
						state = State.ACTIVE;
						color = Color.BLUE;
						Gdx.app.log(
								LOG, "The magnet setted to active");
						
					}
				}
	
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}
		});

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (this.state == Magnet.State.ACTIVE) {
			float deltaX = this.getPosition().x - ball.getPosition().x;
			float deltaY = this.getPosition().y - ball.getPosition().y;

			Vector2 direction = new Vector2(deltaX, deltaY).nor();

			float dist = (float) Math.sqrt(ball.getPosition().dst(
					this.getPosition()));

			force = direction.div(dist).mul(5 * 10000);
			ball.getBody().applyForceToCenter(force);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		RenderHelper.shapeRenderer.begin(ShapeType.FilledCircle);
		
		RenderHelper.shapeRenderer.identity();
		RenderHelper.shapeRenderer.setColor(color);
		RenderHelper.shapeRenderer.filledCircle(getX() + Magnet.SIZE, getY() + Magnet.SIZE, Magnet.SIZE);

		RenderHelper.shapeRenderer.end();

	}

	public Vector2 getPosition() {
		return position;
	}

	@Override
	public void setPosition(float x, float y) {
		x = Math.round(x);
		y = Math.round(y);
		super.setPosition(x - SIZE , y - SIZE);
		position.set(x, y);
	}

}
