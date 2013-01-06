package com.agly.physics.model;

import com.badlogic.gdx.math.Vector2;

public class Click {
	
	public enum State {
		ACTIVE, PASSIVE 
	}
	
	public State state = State.PASSIVE;

    /**
     * The ball's size
     */
    public static final float SIZE = 10f;
    
    private Vector2 position;

    
    public Click() {
    	position = new Vector2();
    }
    
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}
