package com.ecs.components;

public class LifePoints implements Component {

    public int life;

    public LifePoints(int life) {
        this.life = life;
    }

    @Override
    public String toString() {
        return String.valueOf(life);
    }
}
