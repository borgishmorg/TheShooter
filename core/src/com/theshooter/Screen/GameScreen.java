package com.theshooter.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.theshooter.Game;
import com.theshooter.Player;

public class GameScreen implements Screen {

    final private Game game;
    private ScreenObjectArray screenObjects;

    private Texture floor;
    private Texture flyingFloor;
    private Texture box;

    private Player player;
    private PlayerScreenObject playerScreen;

    private OrthographicCamera camera;

    public GameScreen(Game game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        camera.translate(-960, -540);

        floor = new Texture("floor/floor2.png");
        flyingFloor = new Texture("floor/flyingfloor.png");
        box = new Texture("box.png");

        player = new Player(new Texture("body.png"), new Texture("legs.png"), 20, 20, 50, 50);
        playerScreen = new PlayerScreenObject(player);

        screenObjects = new ScreenObjectArray();

        screenObjects.add(playerScreen);

        for (int i = -100; i < 100; i++)
            for (int j = -100; j < 100; j++)
                screenObjects.add(new ScreenObject(floor, i*50, j*50, 50, 50, Depth.FLOOR));

        for (int i = 20; i > 10; i--)
            for (int j = 10; j > -10; j--)
                screenObjects.add(new ScreenObject(box, i*50, j*50, 50, 50, Depth.THINGS));

        for (int i = 15; i > 10; i -= 2)
            for (int j = 10; j > -10; j -= 3)
                screenObjects.add(new ScreenObject(flyingFloor, i*50, j*50, 50, 50, Depth.WALLS));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.3f, 0.3f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        screenObjects.draw(game.batch);

        game.batch.end();

        int dx = 0, dy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dx += 1000;
            //player.move(0, (int)(1000 * Gdx.graphics.getDeltaTime()));
            //camera.translate(playerScreen.getScreenX(), playerScreen.getScreenY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dx -= 1000;
            //player.move(0, (int)(1000 * Gdx.graphics.getDeltaTime()));
            //camera.translate(playerScreen.getScreenX(), playerScreen.getScreenY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dy -= 1000;
            //player.move(0, (int)(1000 * Gdx.graphics.getDeltaTime()));
            //camera.translate(playerScreen.getScreenX(), playerScreen.getScreenY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))  {
            dy += 1000;
            //player.move(0, (int)(1000 * Gdx.graphics.getDeltaTime()));
            //camera.translate(playerScreen.getScreenX(), playerScreen.getScreenY());
        }
        dx *= Gdx.graphics.getDeltaTime();
        dy *= Gdx.graphics.getDeltaTime();
        player.move(dx, dy);
        camera.translate(dx - dy, (dx + dy)/2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        box.dispose();

        flyingFloor.dispose();
        floor.dispose();

        screenObjects.clear();
    }
}
