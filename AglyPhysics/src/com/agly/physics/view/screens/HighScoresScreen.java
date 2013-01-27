package com.agly.physics.view.screens;

import java.util.List;

import com.agly.physics.AglyPhysics;
import com.agly.physics.domain.HighScores;
import com.agly.physics.domain.Score;
import com.agly.physics.utils.DefaultActorListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class HighScoresScreen extends AbstractScreen {
	
	private Integer scoreValue;
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

		final HighScores highScores = game.getHighScoresManager()
				.retrieveHighScores();
		
		if (scoreValue != null &&
				highScores.getScorePosition(scoreValue) != -1)
		{
			TextInputListener listener = new TextInputListener() {
			
				@Override
				public void input(String text) {
					highScores.addScore(text, scoreValue);
					drawTable(highScores);
				}	
			
				@Override
				public void canceled() {
					
				}
			};
		
			Gdx.input.getTextInput(listener, "High Score!", "your name");
		}
		
		drawTable(highScores);

		

	}
	
	private void drawTable(final HighScores highScores) {
		
		// retrieve the default table actor
		Table table = super.getTable();
		
		//reseting the table if it isn't empty
		table.reset();
		table.defaults().spaceBottom(30);
		table.add("High scores").colspan(2);
		
		List<Score> results = highScores.getHighScores();
		for (int i = 0; i < highScores.getCapacity(); i++) {
			
			String name = results.get(i).getName();
			Integer value = results.get(i).getValue();
			

			Label curHighScore = new Label(value.toString(), getSkin());
			table.row();
			table.add(name);
			table.add(curHighScore);
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
        table.add( backButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        
		
        // register the button "start game"
        TextButton startGameButton = new TextButton( "PLay Again", getSkin() );
        startGameButton.addListener( new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                super.touchUp( event, x, y, pointer, button );
                //game.getSoundManager().play( TyrianSound.CLICK );
                game.setScreen( new GameScreen( game ) );
            }
        } );
        
        table.add( startGameButton ).uniform().fill();
	}
	
	

}
