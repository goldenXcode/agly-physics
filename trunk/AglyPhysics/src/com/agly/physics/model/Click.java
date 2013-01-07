package com.agly.physics.model;

import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Click extends Actor {
	
    // constant useful for logging
    public static final String LOG = Click.class.getSimpleName();
    
	public enum State {
		ACTIVE, PASSIVE 
	}
	
	public State state = State.PASSIVE;

    /**
     * The Objects's size
     */
    public static final float SIZE = 10f;
    
    private Vector2 position;

    
    public Click() {
    	position = new Vector2();
    }
    
	@Override
	public void draw(SpriteBatch batch,
            float parentAlpha) {
		
		RenderHelper.shapeRenderer.begin(ShapeType.FilledCircle);
		
		//render target
		RenderHelper.shapeRenderer.setColor(new Color(0, 1, 0, 1));
		RenderHelper.shapeRenderer.filledCircle(this.getX(),this.getY(),
				Click.SIZE, 10);
		RenderHelper.shapeRenderer.end();
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
		position.set(x, y);
		Gdx.app.log(LOG, "Set position " + this.getPosition());
	}
	
}
