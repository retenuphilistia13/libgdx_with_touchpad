package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class AnimationFrame {

    private TextureRegion textureRegion;

    private Rectangle rectangle;

    public Animation<TextureRegion> animation;

    private Player player;
public void draw(Batch batch,float delta){
    if(!animationPaused){//if not paused
        elapsedTime+=delta;
    }


}
    float elapsedTime;

boolean animationPaused;
public AnimationFrame(Player player){

    rectangle=new Rectangle();

    textureRegion=new TextureRegion();

    animationPaused = false;
    animation = null;
    elapsedTime = 0;



    this.player=player;

}

    public void setTextureRegion(Texture t)
    {
        textureRegion.setRegion(t);
      //  player.setSize( t.getWidth(), t.getHeight() );
        rectangle.setSize( t.getWidth(), t.getHeight() );
    }

    public TextureRegion getTextureRegion(){
        return textureRegion;
    }

    public Animation<TextureRegion> loadTexture(String fileName)
    {
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }


    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames,
                                                           float frameDuration, boolean loop) {
        int fileCount = fileNames.length;

        Array<TextureRegion> textureArray = new Array<TextureRegion>();
        for (int n = 0; n < fileCount; n++) {
            String fileName = fileNames[n];
            Texture texture = new Texture(Gdx.files.internal(fileName));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }
        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, textureArray);
        if (loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        if (animation == null)
            setAnimation(anim);



        return anim;

    }

    public void setAnimation(Animation<TextureRegion> anim) {
        animation = anim;

        TextureRegion tr = animation.getKeyFrame(0);

        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();
//
       player.setSize(w, h);//size of actor
//
        player.setOrigin(w / 2, h / 2);//center of actor

    }

}
