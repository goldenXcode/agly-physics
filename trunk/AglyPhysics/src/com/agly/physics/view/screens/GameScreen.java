package com.agly.physics.view.screens;

import com.agly.physics.AglyPhysics;
import com.agly.physics.controller.DeskController;

public class GameScreen extends AbstractScreen{
	
	
	private DeskController controller;
	public GameScreen(AglyPhysics game) {
		super(game);

	}
	
    @Override
    protected boolean isGameScreen()
    {
        return true;
    }
    
    @Override
    public void show()
    {
        super.show();
        

        
        //stage.addActor(click2d);
        
        controller = new DeskController(stage);
        

        
    }
    
    @Override
    public void render(
            float delta ){
		super.render(delta);
		controller.update(delta);
	}

}
