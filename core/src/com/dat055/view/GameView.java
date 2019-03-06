package com.dat055.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dat055.controller.GameController.Mode;
import com.dat055.model.GameModel;
import com.dat055.model.map.GameMap;

/**
 * Renders everything that is used in game.
 * @author Karl Strålman
 * @version 2019-02-21
 */
public class GameView extends View{
    private ShapeRenderer renderer;
    private Mode mode;
    private float rotation = 0;

    private boolean debug = false;
    private String debugString;

    public GameView(GameModel gameModel) {
        this.model = gameModel;
        renderer = new ShapeRenderer();
    }

    public void render(PolygonSpriteBatch batch) {
        GameMap map1 = ((GameModel)model).getGameMap1();
        GameMap map2 = ((GameModel)model).getGameMap2();
        batch.setProjectionMatrix(((GameModel)model).getCam().combined);    // Set camera for batch

        map1.render(batch, rotation);
        map2.render(batch, rotation - 180);

        // Debug
        if(debug) {
            batch.end();
            renderer.setProjectionMatrix(((GameModel)model).getCam().combined);

            // Map specific debug tec
            if(mode == Mode.FRONT) {
                map1.drawBoundingBoxes(renderer);
                batch.begin();
                map1.drawEntityText(((GameModel)model).getFont(), batch);
            } else {
                map2.drawBoundingBoxes(renderer);
                batch.begin();
                map2.drawEntityText(((GameModel)model).getFont(), batch);
            }

            //Debug text for whole map
            BitmapFont font = ((GameModel)model).getFont();
            Camera cam = ((GameModel)model).getCam();
            font.setColor(Color.WHITE);
            font.draw(batch, debugString,
                    cam.position.x - Gdx.graphics.getWidth()/2,
                    cam.position.y+Gdx.graphics.getHeight()/2);
            batch.setProjectionMatrix(cam.combined);
        }
    }

    public float getRotation() { return rotation; }

    public void setDebugString(String debugString) { this.debugString = debugString; }
    public void setRotation(float rotation) { this.rotation = rotation;}
    public void setDebug(boolean debug) { this.debug = debug; }
    public void setMode(Mode mode) { this.mode = mode; }
}
