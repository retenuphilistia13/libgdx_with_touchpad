package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {



	Stage stage;
	Controller touchpad;
	Skin skin;

	GameState gameState;

	Player player;
	@Override
	public void create () {

		gameState=GameState.LOADING;
		// Load the skin
		Skin skin = new Skin(Gdx.files.internal("assets//awesome_skin.json"));

		stage=new Stage();

		  touchpad =new Controller(5,skin);

		touchpad.getColor().a = 0.4f;

		String[] walkFrame=new String[15];

		String[] runFrame=new String[15];


		int count=1,i=0;


		for( i=0,count=1;i<15;i++,  count++){
			walkFrame[i]="boy/directory/idle/Idle ("+Integer.toString(count)+").png";

		}


		for( i=0,count=1;i<15;i++,count++){
			runFrame[i]="boy/directory/run/Run ("+Integer.toString(count)+").png";

		}


		 player =new Player(walkFrame,runFrame);


// To enable the touchpad, call:

		//player.setTextureRegion(new Texture(Gdx.files.internal("assets/boy/directory/idle/Idle (1).png")));
		player.setWidth(100); // Set the width of the actor
		player.setHeight(100); // Set the height of the actor

		stage.addActor(player);
		stage.addActor(touchpad);



		Gdx.input.setInputProcessor(stage);


		gameState=GameState.START_GAME;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 0.8f);

		float dt =Gdx.graphics.getDeltaTime();

		update(dt);

	stage.draw();
	}


	public void update(float dt){
		float knobX = touchpad.getKnobX();
		float knobY = touchpad.getKnobY();
		//System.out.println("originx"+controller.getKnobPercentX()+" prigin y"+controller.getKnobPercentY());

		player.act(dt);

player.setMoveX(touchpad.getKnobPercentX());
		player.setMoveY(touchpad.getKnobPercentY());

//		System.out.println("knob X"+knobX+"   knop Y:"+knobY);
// Use knobX and knobY to control your game character or perform other actions

	}
	
	@Override
	public void dispose () {

		stage.dispose();
		skin.dispose();
	}
}
