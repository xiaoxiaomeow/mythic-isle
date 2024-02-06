package com.bluebear.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bluebear.Game;
import com.bluebear.ui.popups.Popup;
import com.bluebear.ui.resolution.ResolutionManager;

public class ScreenWithPopups extends ScreenAdapter {
    public Game game;
    protected final Stage stage;
    private final Stack mainStack;
    public final Image background;
    public final Table mainTable;
    public ScreenWithPopups (Game game) {
        this.game = game;

        Viewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        mainStack = new Stack();
        background = new Image();
        mainTable = new Table();

        mainStack.setFillParent(true);
        mainStack.add(background);

        mainTable.align(Align.bottomLeft);
        mainTable.setFillParent(true);
        mainStack.add(mainTable);

        stage.addActor(mainStack);

        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown (InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    if (closeTop()) {
                        return true;
                    } else {
                        return escOnEmptyStack();
                    }
                }
                return false;
            }
        });
    }

    public boolean escOnEmptyStack () {
        return false;
    }

    public void popup (Popup popup) {
        popup.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.1f)));
        mainStack.addActor(popup);
    }
    public void close (Popup popup) {
        popup.requestClose();
    }
    public boolean closeTop () {
        Actor actor = mainStack.getChild(mainStack.getChildren().size - 1);
        if (actor instanceof Popup) {
            close((Popup) actor);
            return true;
        }
        return false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        ResolutionManager.updateUIElements();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    @Override
    public void resume() {
        super.resume();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void pause() {
        super.pause();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
    }

}
