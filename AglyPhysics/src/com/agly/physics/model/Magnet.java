package com.agly.physics.model;

import com.agly.physics.controller.DeskController;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;



public class Magnet extends Actor {
	
	public enum State {
		ACTIVE, PASSIVE 
	}
	
	public State state = State.PASSIVE;
	
	private Ball ball;
	
    /**
     * The Object's size
     */
    public static final float SIZE = 10f;
	
    private Vector2 position;
    
    private Vector2 force = new Vector2();
    
	public Magnet(Ball ball) {
		this.ball = ball;
		position = new Vector2();
		
		super.setTouchable(Touchable.disabled);
		this.setWidth(Magnet.SIZE);
		this.setHeight(Magnet.SIZE);

	}
	
	@Override
	public void act(float delta) {
		super.act(delta);

		ball.getBody().applyForceToCenter(force.mul(-1));
		
		float deltaX = this.getPosition().x - ball.getPosition().x;
		float deltaY = this.getPosition().y - ball.getPosition().y;
		
		Vector2 direction = new Vector2(deltaX, deltaY).nor();
		
		float dist = (float) Math.sqrt(ball.getPosition().dst(this.getPosition()));
		
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
    
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		x = Math.round(x);
		y = Math.round(y);
		position = new Vector2(x, y);
	}

}
