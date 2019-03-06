package com.dat055.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ObjectMap;
import com.dat055.model.menu.Menu;

import java.util.HashMap;


public class MenuModel extends Model {
    private ObjectMap<String, Menu> menus;
    private String currentMenu;
    private Stage stage;

    public MenuModel() {
        musicBank = new ObjectMap();
        stage = new Stage();
        menus = new ObjectMap<String, Menu>();
        initMusic();
    }

    @Override
    public void initMusic() {
        musicBank.put("title", loadMusic("title.mp3"));
    }

    @Override
    public void playMusic(String ost) {
       Music music = musicBank.get(ost);
       if (ost.equals("title"))
           music.setVolume(0.3f);
       music.play();
    }

    public void includeMenu(String label, Menu menu) {
        menus.put(label, menu);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * This method swaps to the current {@link Menu} to the selected one
     * @param menu A string that is used as a key to the hashmap of  menus
     */
    public void swapMenu(String menu) {
        stage.clear();
        Menu next = menus.get(menu);
        next.updateTable();
        if(next.getBg() != null) {
            next.getBg().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stage.addActor(next.getBg());
        }
        Table tbl = next.getTable();
        tbl.setFillParent(true);
        stage.addActor(tbl);
        currentMenu = menu;
    }

    /**
     * Updates the current {@link Menu}, then acts the stage.
     */
    public void update() {
        if(menus.get(currentMenu).isUpdatable())
            menus.get(currentMenu).updateTable();
        stage.act();
    }

    /**
     * A get-method for the stage
     * @return Returns the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * A method that gets the name of the current {@link Menu}.
     * @return The current {@link Menu} as a string.
     */
    public String getCurrentMenu() { return currentMenu; }
}
