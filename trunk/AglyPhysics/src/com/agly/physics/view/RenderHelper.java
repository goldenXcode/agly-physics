package com.agly.physics.view;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class RenderHelper {
	
	public static ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public static Box2DDebugRenderer box2dRenderer = new Box2DDebugRenderer();
	
	private static boolean debug = false;

	public static boolean isDebug() {
		return debug;
	}

}
