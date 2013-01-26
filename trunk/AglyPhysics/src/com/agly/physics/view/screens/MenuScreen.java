package com.agly.physics.view.screens;

import com.agly.physics.AglyPhysics;
import com.agly.physics.utils.DefaultActorListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends AbstractScreen {

	public MenuScreen(AglyPhysics game) {
		super(game);
	}
	
	 @Override
	    public void show()
	    {
	        super.show();
	        
	        // retrieve the default table actor
	        Table table = super.getTable();
	        table.add( "- Agly Physics -" ).spaceBottom( 50 );
	        table.row();
	        
	        // register the button "start game"
	        TextButton startGameButton = new TextButton( "Start game", getSkin() );
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
	        
	        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
	        table.row();
	        
	        // register the button "high scores"
	        TextButton highScoresButton = new TextButton( "High Scores", getSkin() );
	        highScoresButton.addListener( new DefaultActorListener() {
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
	                game.setScreen( new HighScoresScreen( game ) );
	            }
	        } );
	        table.add( highScoresButton ).uniform().fill();
	        
	    }

}
