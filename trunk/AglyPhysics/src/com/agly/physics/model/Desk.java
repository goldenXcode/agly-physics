package com.agly.physics.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Desk {

	public static World world = new World(new Vector2(0, 0), true);

	public Desk() {

	}

	public static void cleanWorld() {
		while (Desk.world.getBodies().hasNext()) {
			Desk.world.destroyBody(Desk.world.getBodies().next());
		}
	}

}
