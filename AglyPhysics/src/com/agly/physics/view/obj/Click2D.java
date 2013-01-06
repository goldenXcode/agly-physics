package com.agly.physics.view.obj;

import com.agly.physics.model.Click;
import com.agly.physics.view.RenderHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Click2D extends Actor {
	
    // constant useful for logging
    public static final String LOG = Click2D.class.getSimpleName();
    
    
    private Click click;
    
    
    public Click2D() {
    	this.click = new Click();
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

	public Click getClick() {
		return click;
	}

	public void setClick(Click click) {
		this.click = click;
	}

	@Override
	public void setPosition(float x, float y) {
		x = Math.round(x);
		y = Math.round(y);
		super.setPosition(x, y);
		click.setPosition(new Vector2(x, y));
		Gdx.app.log(LOG, "Set position " +click.getPosition());
	}
	
}
