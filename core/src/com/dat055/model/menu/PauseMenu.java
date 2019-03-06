package com.dat055.model.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.dat055.controller.GameController;
import com.dat055.controller.MenuController;

public class PauseMenu extends Menu {
    public boolean mute;
    private TextButton resume, settings, menu;
    public PauseMenu(MenuController ctrl) {
        super(ctrl);
        createTable();
    }

    @Override
    public void createTable() {
        resume = createButton("Resume game");
        settings = createButton("Settings");
        menu = createButton("Exit to main menu");

        addListeners();

        layoutTable(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/18);
    }
    //todo: needs documentation
    public void setMute(boolean foo) {mute = foo;}

    private void layoutTable(int butX, int butY) {
        int padS = butY/2;

        Table table = super.table;
        table.setSize(controller.getWidth(),controller.getWidth());

        table.setPosition(0,0);

        table.add(resume).width(butX).height(butY).padBottom(padS).expandX().row();
        table.add(settings).width(butX).height(butY).padBottom(padS).row();
        table.add(menu).width(butX).height(butY).padBottom(padS).row();
    }

    private void addListeners() {
        resume.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                controller.togglePause();
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                resume.setStyle(hoverStyle);
                super.enter(event,x,y,pointer,fromActor);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                resume.setStyle(txtBtnStyle);
                super.enter(event,x,y,pointer,toActor);
            }
        });

        settings.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                controller.swapMenu("Settings");
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settings.setStyle(hoverStyle);
                super.enter(event,x,y,pointer,fromActor);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                settings.setStyle(txtBtnStyle);
                super.enter(event,x,y,pointer,toActor);
            }
        });

        menu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                controller.swapMenu("Main");
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menu.setStyle(hoverStyle);
                super.enter(event,x,y,pointer,fromActor);
            }

            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                menu.setStyle(txtBtnStyle);
                controller.getCtrl().getModel().stopMusic();
                controller.playMusic();
                super.enter(event,x,y,pointer,toActor);
            }
        });
    }
}