package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.ArrayList;

public class Snake {
    private List<Cell> snake;
    private Texture img;
    private int direction;
    private Food food;

    public Snake(Food food) {
        snake = new ArrayList<Cell>();
        img = new Texture("gear.png");
        for (int i = 0; i < 5; i++)
            snake.add(new Cell(new Vector2(160 - i * 32, 32 * 9), img));
        Gdx.graphics.setTitle("Snake: " + snake.size());
        direction = Input.Keys.RIGHT;
        this.food = food;
    }

    public void render(SpriteBatch batch) {
        for (Cell cell : snake)
            cell.render(batch);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = Input.Keys.DOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = Input.Keys.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = Input.Keys.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = Input.Keys.RIGHT;
        }
        float x = snake.get(0).getPosition().x;
        float y = snake.get(0).getPosition().y;
        switch (direction) {
            case Input.Keys.UP: y += img.getHeight();
                if (y == Gdx.graphics.getHeight())
                    y = 0;
                break;
            case Input.Keys.DOWN: y -= img.getHeight();
                if (y < 0)
                    y = Gdx.graphics.getHeight() - img.getHeight();
                break;
            case Input.Keys.RIGHT: x += img.getWidth();
                if (x == Gdx.graphics.getWidth())
                    x = 0;
                break;
            case Input.Keys.LEFT: x -= img.getWidth();
                if (x < 0)
                    x = Gdx.graphics.getWidth() - img.getWidth();
                break;
        }
        snake.add(0, new Cell(new Vector2(x, y), img));
        if (food.isFood(x, y)) {
            food.reset();
            Gdx.graphics.setTitle("Snake: " + snake.size());
        } else
            snake.remove(snake.size() - 1);
    }
}