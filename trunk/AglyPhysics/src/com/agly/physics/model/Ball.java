package com.agly.physics.model;

import com.agly.physics.controller.DeskController;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Ball extends Actor {

    // constant useful for logging
    public static final String LOG = Ball.class.getSimpleName();
    
	/**
	 * The ball's size
	 */
	public static final float SIZE = 20f;

	/**
	 * The ball's speed
	 */
	public static final float SPEED = 50f;

	private Body body;
	
	Texture pixmaptex;
	
	public Ball() {

		createBody();

		super.setTouchable(Touchable.disabled);
		
		//setting visual objects width and height
		this.setWidth(32);
		this.setHeight(32);
		

	}

	private void createBody() {
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesnt
		// move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;

		// Create our body in the world using our body definition
		body = Desk.world.createBody(bodyDef);

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(Ball.SIZE);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);
		
		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
		
		//setting mass
        MassData massData = new MassData();
        massData.mass = 1;
        body.setMassData(massData);
        
        //like a friction
        body.setLinearDamping(0.5f);
        //set angular damping?
        
        this.setPosition(Ball.SIZE, Ball.SIZE);
        
        
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		//moving the image to position
		this.setX(this.getPosition().x);
		this.setY(this.getPosition().y);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
				
		RenderHelper.shapeRenderer.begin(ShapeType.FilledCircle);
		
		RenderHelper.shapeRenderer.identity();
		RenderHelper.shapeRenderer.setColor(Color.RED);
		RenderHelper.shapeRenderer
				.filledCircle(getX(), getY(), Ball.SIZE);
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

	public void setVelocity(Vector2 v) {
		body.setLinearVelocity(v);
	}

	public Body getBody() {
		return body;
	}
}
