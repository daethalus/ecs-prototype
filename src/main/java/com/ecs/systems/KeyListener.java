package com.ecs.systems;

import com.ecs.Direction;
import com.ecs.EntityManager;
import com.ecs.components.Camera;
import com.ecs.components.Component;
import com.ecs.components.Movement;

import java.util.List;
import java.util.Random;

public class KeyListener implements ESystem {

    private Random random = new Random();

    @Override
    public void update(EntityManager entityManager) {
        //Simulating a key pressed =)
        int value = random.nextInt(200);
        Direction direction = null;
        if (value == 50) {
            direction = Direction.LEFT;
        } else if (value == 100) {
            direction = Direction.RIGHT;
        }
        if (direction != null) {
            List<Component> components = entityManager.getComponents(Camera.class);
            for(Component component : components) {
                List<Component> c = entityManager.getComponentsTogether(Movement.class, component);
                for (Component cm : c) {
                    ((Movement) cm).move = direction;
                }
            }
        }
    }
}
