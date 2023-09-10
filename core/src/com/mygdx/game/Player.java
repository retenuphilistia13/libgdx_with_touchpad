package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends Actor {

    private TextureRegion textureRegion;

    private Rectangle rectangle;

    //private Animation<TextureRegion> walkAnimation;

    AnimationFrame walkFrame,runFrame;

    private float controlX,controlY;

    private Rotation rotation;

    private Animation animationState;

    float elapsedTime;
    private boolean animationPaused;

    public void setMoveX(float mX){

        controlX=mX*5;
    }

    public void setMoveY(float mY){

        controlY=mY*5;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Color c = getColor(); // used to apply tint color effect
        batch.setColor(c.r, c.g, c.b, c.a);

if(animationState==Animation.IDLE) {
    if (isVisible() && walkFrame.animation != null)
        batch.draw(walkFrame.animation.getKeyFrame(elapsedTime),
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    else if (isVisible()) {
        batch.draw(textureRegion,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}

else if(animationState==Animation.RUN) {
    if (isVisible() && runFrame.animation != null)
        batch.draw(runFrame.animation.getKeyFrame(elapsedTime),
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    else if (isVisible()) {
        batch.draw(textureRegion,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}



    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!animationPaused){//if not paused
            elapsedTime+=delta;
        }

clearActions();//in case

        Action move= Actions.moveBy(controlX,getY());
if(controlX==0){
    animationState=Animation.IDLE;
}
        if (controlX <0) {
            // Flip the player horizontally
            setScaleX(-1);
            // Set the origin at the center of the player
            setOrigin(getWidth() / 2, getHeight() / 2);
            rotation=Rotation.LEFT;
            animationState=Animation.RUN;
        } else if(controlX > 0){
            rotation=Rotation.RIGHT;
            setScaleX(1); // Set it back to the original scale (no flip)

            animationState=Animation.RUN;
        }
        addAction(move);
    }

    public void setAnimationPaused(boolean pause) {
        animationPaused = pause;
    }
    public Player(String fileName){

        super();
        rotation=Rotation.RIGHT;

        animationPaused = false;

        elapsedTime = 0;

        String[] fileNames = new String[1];
        fileNames[0] = fileName;


        walkFrame=new AnimationFrame(this);
        walkFrame.loadAnimationFromFiles(fileNames,1,true);



        controlX=0;
        controlY=0;


    }
    public Player(String[] fileAnimation,String[] runAnimation){

    super();

        rotation=Rotation.RIGHT;

        animationPaused = false;

        animationState=Animation.IDLE;

        elapsedTime = 0;

        runFrame=new AnimationFrame(this);
        runFrame.loadAnimationFromFiles(runAnimation,0.1f,true);


        walkFrame=new AnimationFrame(this);
        walkFrame.loadAnimationFromFiles(fileAnimation,0.1f,true);


        controlX=0;
        controlY=0;


    }



}
