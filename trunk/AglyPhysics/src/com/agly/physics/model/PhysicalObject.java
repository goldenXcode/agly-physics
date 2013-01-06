package com.agly.physics.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class PhysicalObject {
	
	private Body body;
	
	public Vector2 getPosition() {
		return body.getPosition();
	}

}
