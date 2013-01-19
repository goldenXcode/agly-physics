package com.agly.physics.view.screens;

import com.agly.physics.AglyPhysics;
import com.agly.physics.controller.DeskController;
import com.agly.physics.model.Desk;
import com.agly.physics.view.RenderHelper;

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
        
        controller = new DeskController(stage);
        

        
    }
    
    @Override
    public void render(
            float delta ){
    	
		super.render(delta);
		controller.update(delta);

		if (RenderHelper.isDebug() == true) {
		RenderHelper.box2dRenderer.render(
				Desk.world, DeskController.stage.getCamera().combined);
		}
	}

}
