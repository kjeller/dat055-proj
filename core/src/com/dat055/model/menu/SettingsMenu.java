package com.dat055.model.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.dat055.controller.MenuController;
import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * This Class is responsible for creating the settings menu.
 *
 * @author Pontus Johansson
 * @version 2019-03-07
 */
public class SettingsMenu extends Menu {
    private MenuController controller;
    private TextButton apply ,save ,back;
    private Label resolutionX,resolutionY, fullscreen, mute, sound;
    private TextField resFieldX,resFieldY,fulField,musField,soundField;
    private String resSettingX,resSettingY,fulSetting,musSetting,soundSetting;
    private int resX,resY,fulInt,mutInt;
    private Map<String,String> settingsMap;

    /**
     * The constructor for the settingsMenu
     * @param ctrl a menucontroller
     */
    public SettingsMenu(MenuController ctrl) {
        super(ctrl, false, "UI/Delta.jpg");

        settingsMap = new TreeMap<String, String>() {};

        initConfig();
        controller = ctrl;
        createTable();
    }

    /**
     * overrides the method
     * Creates the buttons and text
     */
    @Override
    protected void createTable() {
        Table table = new Table();

        table.setWidth(controller.getWidth());
        table.setHeight(controller.getHeight());
        table.align(Align.center | Align.top);

        table.setPosition(0, 0);

        //creates the Buttons in the menu
        save = createButton("Save");
        apply = createButton("Apply");
        back = createButton("Back");

        //gets settings from the config file
        resSettingX = settingsMap.get("resolutionX");
        resSettingY = settingsMap.get("resolutionY");
        fulSetting = settingsMap.get("fullscreen");
        musSetting = settingsMap.get("mute");
        soundSetting = settingsMap.get("muteeffects");

        //The inputFields
        resFieldX = createTextField(resSettingX);
        resFieldY = createTextField(resSettingY);
        fulField = createTextField(fulSetting);
        musField = createTextField(musSetting);
        soundField = createTextField(soundSetting);

        //The texts in the menu
        resolutionX = new Label("Screen width",lblStyle);
        resolutionY = new Label("Screen height",lblStyle);
        fullscreen = new Label("Fullscreen",lblStyle);
        mute = new Label("Mute music",lblStyle);
        sound = new Label("Mute effects",lblStyle);

        addListeners();

        // creates the table
        table.add(back).width(150).height(40);
        table.add(save).width(170).height(40);
        table.add(apply).width(170).height(40).row();
        table.add();
        table.add(resolutionX).padTop(20).height(40).padBottom(10);
        table.add(resFieldX).padTop(20).padBottom(10).row();
        table.add();
        table.add(resolutionY).padTop(20).height(40).padBottom(10);
        table.add(resFieldY).padTop(20).padBottom(10).row();
        table.add();
        table.add(fullscreen).height(40).padBottom(10).padTop(20);
        table.add(fulField).padTop(20).padBottom(10).row();
        table.add();
        table.add(mute).height(40).padBottom(10).padTop(20);
        table.add(musField).padTop(20).padBottom(10).row();
        table.add();
        table.add(sound).padTop(20).height(40).padBottom(10);
        table.add(soundField).padTop(20).padBottom(10).row();

        super.table = table;
    }

    /**
     *Method that makes the string from the config.txt into int
     * @param strn name of the config
     * @return the specified config in int
     */
    private int parseInt(String strn){
        return Integer.parseInt(settingsMap.get(strn));
    }

    /**
     * Adds listeners for the buttons
     */
    private void addListeners() {
        apply.addListener(new ClickListener() {
            /**
             * Overrides the method so that the button changes to the style when the pointer is above it.
             */
            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                apply.setStyle(hoverStyle);
                super.enter(event,x,y,pointer,fromActor);
            }

            /**
             * Overrides the method so that the button changes to its original style when the pointer leaves.
             */
            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                apply.setStyle(txtBtnStyle);
                super.enter(event,x,y,pointer,toActor);
            }

            /**
             * Override of method
             * gets the new settings from inputfields and applies them
             */
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                settingsMap.put("resolutionX", resFieldX.getText());
                settingsMap.put("resolutionY", resFieldY.getText());
                settingsMap.put("fullscreen",fulField.getText());
                settingsMap.put("mute",musField.getText());
                resX = parseInt("resolutionX");
                resY = parseInt("resolutionY");
                fulInt = parseInt("fullscreen");
                mutInt = parseInt("mute");

                if (inputSanitizer() &&mutInt==1){
                    controller.setMute(true);
                    controller.playMusic();
                }
                else if(inputSanitizer()&& mutInt==0){
                    controller.setMute(false);
                    controller.playMusic();
                }

                if (inputSanitizer()&&fulInt==1){
                    Gdx.graphics.setWindowedMode(resX,resY);
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    controller.resize(resX,resY);
                }
                else if (inputSanitizer()&&fulInt==0){
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    Gdx.graphics.setWindowedMode(resX, resY);
                    controller.resize(resX, resY);
                }
                else
                    System.out.println("You can't do that Dave");
            }
        });
        save.addListener(new ClickListener() {
            /**
             * Overrides the method so that the button changes to the style when the pointer is above it.
             */
            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                save.setStyle(hoverStyle);
                super.enter(event,x,y,pointer,fromActor);
            }

            /**
             * Overrides the method so that the button changes to its original style when the pointer leaves.
             */
            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                save.setStyle(txtBtnStyle);
                super.enter(event,x,y,pointer,toActor);
            }

            /**
             * overrides the method and saves the inputFields to the config.txt
             */
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                settingsMap.put("resolutionX", resFieldX.getText());
                settingsMap.put("resolutionY", resFieldY.getText());
                settingsMap.put("fullscreen", fulField.getText());
                settingsMap.put("mute", musField.getText());
                settingsMap.put("soundeffects", soundField.getText());

                if (inputSanitizer()) {
                    try {
                        ConfigIO.save(settingsMap, "config.txt");
                    } catch (IOException e) {
                        System.out.println("Something Hideous Intentionally Transpired");
                    }
                }
                else
                    System.out.println("You can't do that Dave");
            }
        });
        back.addListener(new ClickListener() {
            /**
             * Overrides the method so that the button changes to the style when the pointer is above it.
             */
            @Override
            public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
                back.setStyle(hoverStyle);
                super.enter(event,x,y,pointer,fromActor);
            }

            /**
             * Overrides the method so that the button changes to its original style when the pointer leaves.
             */
            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
                back.setStyle(txtBtnStyle);
                super.enter(event,x,y,pointer,toActor);
            }
            /**
             * overrides the method, swaps to main menu
             */
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                controller.swapMenu("Main");
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    /**
     * initialises the config file
     */
    private void initConfig(){
        try {
            settingsMap = ConfigIO.load("config.txt");
        }
        catch(IOException e){
            System.out.println("OH,Sugar Honey Ice Tea");
        }
    }

    /**
     * makes sure that inputs are correct values
     * @return true if sanitizers are all true
     */
    private boolean inputSanitizer(){
        return (resSanitizerX() && resSanitizerY() && fulSanitizer() && musicSanitizer() && soundSanitizer());
    }

    /**
     * sanitises the height inputs
     */
    private boolean resSanitizerX(){
        if( settingsMap.put("resolutionX", resFieldX.getText()).equals( "1280") ) //1280x720
            return true;
        else if ( settingsMap.put("resolutionX", resFieldX.getText()).equals("1920"))//1920x1080
            return true;
        else if (settingsMap.put("resolutionX", resFieldX.getText()).equals("1366")) //1366x768
            return true;
        else if ( settingsMap.put("resolutionX", resFieldX.getText()).equals("1600")) //1600x900
            return true;
        return false;
    }

    /**
     * Sanitises the width input
     */
    private boolean resSanitizerY(){
        if( settingsMap.put("resolutionY", resFieldY.getText()).equals("720") ) //1280x720
            return true;
        else if ( settingsMap.put("resolutionY", resFieldY.getText()).equals("1080"))//1920x1080
            return true;
        else if (settingsMap.put("resolutionY", resFieldY.getText()).equals("768")) //1366x768
            return true;
        else if ( settingsMap.put("resolutionY", resFieldY.getText()).equals("900")) //1600x900
            return true;
        return false;
    }

    /**
     * makes sure that fullscreen input is 1 or 0
     */
    private boolean fulSanitizer(){
        if (settingsMap.put("fullscreen", fulField.getText()).equals("1"))
            return true;
        else if (settingsMap.put("fullscreen", fulField.getText()).equals("0"))
            return true;
        return false;
    }

    /**
     * makes sure that mute is 1 or 0
     */
    private boolean musicSanitizer(){
        if(settingsMap.put("mute", musField.getText()).equals("1"))
            return true;
        else if( settingsMap.put("mute", musField.getText()).equals("0"))
            return true;
        return false;
    }

    /**
     * makes sure that muteeffects is 1 or 0
     */
    private boolean soundSanitizer(){
        if (settingsMap.put("muteeffects", soundField.getText()).equals("1"))
            return true;
        else if (settingsMap.put("muteeffects", soundField.getText()).equals("0"))
            return true;
        return false;
    }
}
