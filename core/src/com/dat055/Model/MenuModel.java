package com.dat055.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.dat055.Model.Menu.Menu;

import java.util.HashMap;


public class MenuModel extends Model {
    private HashMap<String, Menu> menus;
    private String currentMenu;
    private Stage stage;

    public MenuModel() {
        stage = new Stage();
        menus = new HashMap<String, Menu>();
    }

    public void includeMenu(String label, Menu menu) {
        menus.put(label, menu);
    }

    public void swapMenu(String menu) {
        stage.clear();
        if (menus.get(menu).getBg() != null)
            stage.addActor(new Image(menus.get(menu).getBg()));
        stage.addActor(menus.get(menu).getTable());
        currentMenu = menu;
    }

    public Stage getStage() {
        return stage;
    }
}
