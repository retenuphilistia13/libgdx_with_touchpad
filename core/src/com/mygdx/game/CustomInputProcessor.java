package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class CustomInputProcessor extends InputAdapter {
    private Touchpad touchpad;
    private boolean isEnabled = true; // Control the touchpad's enabled/disabled state

    public CustomInputProcessor(Touchpad touchpad) {
        this.touchpad = touchpad;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isEnabled) {
            return super.touchDragged(screenX, screenY, pointer);
        }
        return false; // Ignore touchpad input when it's disabled
    }
}
