package com.agly.physics.view.screens;

import java.util.List;

import com.agly.physics.AglyPhysics;
import com.agly.physics.domain.HighScores;
import com.agly.physics.domain.Score;
import com.agly.physics.utils.DefaultActorListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class HighScoresScreen extends AbstractScreen {
	
	private int scoreValue;
	public HighScoresScreen(AglyPhysics game) {
		super(game);
	}
	
	public HighScoresScreen(AglyPhysics game, int score) {
		super(game);
		this.scoreValue = score;
	}

	@Override
	public void show() {
		super.show();

		HighScores highScores = game.getHighScoresManager()
				.retrieveHighScores();

		// retrieve the default table actor
		Table table = super.getTable();
		table.defaults().spaceBottom(30);
		table.add("High scores").colspan(2);
		
		
		int scorePosition = highScores.getScorePosition(scoreValue);

		List<Score> results = highScores.getHighScores();
		//List<Integer> results = highScores.getHighScores();
		for (int i = 0; i < highScores.getCapacity(); i++) {
			
			if (scorePosition == i) {
				highScores.addScore("leon", scoreValue);
			}
			
			String name = results.get(i).getName();
			Integer value = results.get(i).getValue();
			
			//String name = "leon";
			//Integer value = results.get(i);

			Label episode1HighScore = new Label(value.toString(), getSkin());
			table.row();
			table.add(name);
			table.add(episode1HighScore);
		}
		
		// register the back button
		TextButton backButton = new TextButton("Back to main menu", getSkin());
		backButton.addListener(new DefaultActorListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				// game.getSoundManager().play( TyrianSound.CLICK );
				game.setScreen(new MenuScreen(game));
			}
		});
		table.row();
		table.add(backButton).size(250, 60).colspan(2);
		
		game.getHighScoresManager().persist();
	}
	
	

}
