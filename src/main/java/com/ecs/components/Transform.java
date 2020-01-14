package com.ecs.components;

public class Transform implements Component {
    public int x;

    public Transform() {
        x = 0;
    }

    public Transform(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Position x: " + x;
    }
}
