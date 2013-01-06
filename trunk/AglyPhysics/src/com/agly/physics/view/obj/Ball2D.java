package com.agly.physics.view.obj;

import com.agly.physics.controller.DeskController;
import com.agly.physics.model.Ball;
import com.agly.physics.model.Desk;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class Ball2D extends Actor {
	
	private Ball ball;
	
	public Ball2D() {
		super.setTouchable(Touchable.disabled);

		this.setWidth(Ball.SIZE);
		this.setHeight(Ball.SIZE);
		this.ball = new Ball();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		moveBall(delta);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		RenderHelper.shapeRenderer.begin(ShapeType.FilledCircle);

		RenderHelper.shapeRenderer.setColor(new Color(1, 0, 0, 1));
		RenderHelper.shapeRenderer
				.filledCircle(getX(), getY(), Ball.SIZE);
		RenderHelper.shapeRenderer.end();
		
		
		if (RenderHelper.isDebug() == true) {
		RenderHelper.box2dRenderer.render(
				Desk.world, DeskController.stage.getCamera().combined);
		}
	}

	private void moveBall(float delta)

	{
		
		//body.applyLinearImpulse(new Vector2(velocity.x, velocity.y),
		//		body.getWorldCenter());
		
		
		this.setX(ball.getPosition().x);
		this.setY(ball.getPosition().y);
	}

	@Override
	public void setPosition(float x, float y) {
		x = Math.round(x);
		y = Math.round(y);
		super.setPosition(x, y);
		ball.setPosition(new Vector2(x,y));
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}
	
	

}
