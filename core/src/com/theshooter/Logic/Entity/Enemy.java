package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class Enemy extends BreakableEntity {
    private Rectangle target;
    private Map map;
    private int velocity;


    public Enemy(int x, int y, int w, int h, int hp, int velocity, Rectangle player, Map map){
        super(x, y, w, h, hp, Depth.ENEMY, false);
        target = player;
        this.velocity = velocity;
        this.map = map;
    }

    public Enemy(int x, int y, int w, int h, int velocity, Rectangle player, Map map){
        this(x, y, w, h, 1, velocity, player, map );
    }

    public Enemy(int x, int y, int hp, int velocity, Rectangle player, Map map){
        this(x, y, 50, 50, hp, velocity, player, map );
    }

    public Enemy(int x, int y, int velocity, Rectangle player, Map map){
        this(x, y, 50, 50, 1, velocity, player, map );
    }

    @Override
    public void update() {
        super.update();
        if (!isBroken()) {
            float dx = target.getX() - getX();
            float dy = target.getY() - getY();
            double len = Math.hypot(dx, dy);
            dx /= len;
            dy /= len;

            int changeX = (int) (dx * Gdx.graphics.getDeltaTime() * velocity);
            int changeY = (int) (dy * Gdx.graphics.getDeltaTime() * velocity);
            setX(getX() + changeX);
            setY(getY() + changeY);
            if (!map.isAllowed(super.getRectangle())) {
                setX(getX() - changeX);
                setY(getY() - changeY);
            }
        }
    }
}