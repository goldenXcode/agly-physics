package com.agly.physics;

import com.agly.physics.services.HighScoresManager;
import com.agly.physics.view.screens.MenuScreen;
import com.badlogic.gdx.Game;

public class AglyPhysics extends Game {
	
    // constant useful for logging
    public static final String LOG = AglyPhysics.class.getSimpleName();

    // whether we are in development mode
    public static final boolean DEV_MODE = true;

	private HighScoresManager highScoresManager;
	
	@Override
	public void create() {
		
		highScoresManager = new HighScoresManager();
		highScoresManager.retrieveHighScores();
		
		setScreen(new MenuScreen(this));
	}

	/**
	 * @return the highScoresManager
	 */
	public HighScoresManager getHighScoresManager() {
		return highScoresManager;
	}

}
