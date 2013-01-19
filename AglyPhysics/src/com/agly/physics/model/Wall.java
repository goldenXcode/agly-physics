package com.agly.physics.model;

import javax.swing.text.Position;

import com.agly.physics.controller.DeskController;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Wall extends Actor {

	// constant useful for logging
	public static final String LOG = Wall.class.getSimpleName();

	//private float height;

	//private float width;

	private float angle;
	
	private Body body;

	Color color;
	
	
	Sprite sprite;
	public Wall(float height, float width, float angle) {
		this.setHeight(height);
		this.setWidth(width);
		this.angle = angle;
		
		color = Color.ORANGE;
		createBody();
	}
	
	public Wall(float height, float angle) {
		this.setHeight(height);
		this.setWidth(5f);
		this.angle = angle;
		
		color = Color.ORANGE;
		createBody();
	}
	
	public Wall(float height) {
		this.setHeight(height);
		this.setWidth(5f);
		
		color = Color.ORANGE;
		createBody();
		//createSprite();
	}
	
	public void createSprite() {
		
		
		Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGB888);
		pixmap.setColor(Color.ORANGE);
		
		//pixmap.drawRectangle(getX() - width/2, getY() - height/2, width, height)
		pixmap.drawRectangle(0, 0, (int) getWidth(), (int) getHeight());
		//pixmap.fill();
		Texture texture = new Texture(pixmap);

		pixmap.dispose();
		
		sprite = new Sprite(texture);
		sprite.rotate90(true);

	}

	private void createBody() {

		// Create our body definition
		BodyDef groundBodyDef = new BodyDef();

		// Create a body from the defintion and add it to the world
		body = Desk.world.createBody(groundBodyDef);

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();

		groundBox.setAsBox(getWidth() / 2, getHeight() / 2);
		// Create a fixture from our polygon shape and add it to our ground body
		body.createFixture(groundBox, 0.0f);
		// Clean up after ourselves
		groundBox.dispose();

		body.setTransform(body.getPosition(), (float) angle * MathUtils.degreesToRadians);

		
		//this.setX(body.getPosition().x);
		//this.setY(body.getPosition().y);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		/*
		RenderHelper.shapeRenderer.begin(ShapeType.FilledRectangle);
		
		RenderHelper.shapeRenderer.identity();
		RenderHelper.shapeRenderer.setColor(color);
		
		RenderHelper.shapeRenderer.translate(getX() - getWidth() /2,
				getY() - getHeight()/2, 0);
		
		RenderHelper.shapeRenderer.filledRect(0,0,getWidth(),getHeight());
		RenderHelper.shapeRenderer.end();
		*/
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	@Override
	public void setPosition(float x, float y) {
		x = Math.round(x);
		y = Math.round(y);
		super.setPosition(x, y);
		body.setTransform(new Vector2(x, y), body.getAngle());
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
