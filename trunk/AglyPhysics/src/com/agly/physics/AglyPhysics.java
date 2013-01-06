package com.agly.physics;

import com.agly.physics.view.screens.GameScreen;
import com.badlogic.gdx.Game;

public class AglyPhysics extends Game {
	
    // constant useful for logging
    public static final String LOG = AglyPhysics.class.getSimpleName();

    // whether we are in development mode
    public static final boolean DEV_MODE = false;

	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
}
