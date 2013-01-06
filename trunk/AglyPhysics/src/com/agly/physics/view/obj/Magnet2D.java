package com.agly.physics.view.obj;

import com.agly.physics.controller.DeskController;
import com.agly.physics.model.Ball;
import com.agly.physics.model.Desk;
import com.agly.physics.model.Magnet;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Magnet2D extends Actor {
	
	private Magnet magnet;
	private Ball ball;
	
	private Vector2 force = new Vector2();
	
	int i;
	public Magnet2D(Ball ball) {
		super.setTouchable(Touchable.disabled);

		this.setWidth(Magnet.SIZE);
		this.setHeight(Magnet.SIZE);
		this.magnet = new Magnet();
		this.ball = ball;
		i = 0;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		

		ball.getBody().applyForceToCenter(force.mul(-1));
		
		float deltaX = magnet.getPosition().x - ball.getPosition().x;
		float deltaY = magnet.getPosition().y - ball.getPosition().y;
		
		Vector2 direction = new Vector2(deltaX, deltaY).nor();
		
		float dist = (float) Math.sqrt(ball.getPosition().dst(magnet.getPosition()));
		
		force = direction.div(dist).mul(5 * 10000);
		ball.getBody().applyForceToCenter(force);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {

		RenderHelper.shapeRenderer.begin(ShapeType.FilledCircle);

		RenderHelper.shapeRenderer.setColor(new Color(0, 0, 1, 1));
		RenderHelper.shapeRenderer
				.filledCircle(getX(), getY(), Magnet.SIZE);
		RenderHelper.shapeRenderer.end();
		
		
		if (RenderHelper.isDebug() == true) {
		RenderHelper.box2dRenderer.render(
				Desk.world, DeskController.stage.getCamera().combined);
		}
	}

	@Override
	public void setPosition(float x, float y) {
		x = Math.round(x);
		y = Math.round(y);
		super.setPosition(x, y);
		magnet.setPosition(new Vector2(x, y));
	}

}
