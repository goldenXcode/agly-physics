package com.agly.physics.model;

import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Target extends Actor {
	
	/**
	 * The Object's size
	 */
	public static final float SIZE = 10f;
	
	private Body body;
	
	public Target() {

		//setting visual objects width and height
		this.setWidth(SIZE);
		this.setHeight(SIZE);
		
		createBody();
	}
	
	private void createBody() {

		// Create our body definition
		BodyDef groundBodyDef = new BodyDef();

		// Create a body from the defintion and add it to the world
		body = Desk.world.createBody(groundBodyDef);

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(SIZE);
		
		// Create a fixture from our polygon shape and add it to our ground body
		body.createFixture(circle, 0.0f);
		// Clean up after ourselves
		circle.dispose();
		
		body.setUserData(Target.class.getSimpleName());
	}
	
	@Override
	public void act(float delta) {
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
				
		RenderHelper.shapeRenderer.begin(ShapeType.FilledCircle);
		
		RenderHelper.shapeRenderer.identity();
		RenderHelper.shapeRenderer.setColor(Color.WHITE);
		RenderHelper.shapeRenderer
				.filledCircle(getX(), getY(), SIZE);
		RenderHelper.shapeRenderer.end();

	}
	
	public Vector2 getPosition() {
		return body.getPosition();
	}
	
	@Override
	public void setPosition(float x, float y) {
		x = Math.round(x);
		y = Math.round(y);
		super.setPosition(x, y);
		body.setTransform(new Vector2(x, y),
				body.getAngle());
	}

}
