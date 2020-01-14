package com.ecs.systems;

import com.ecs.Direction;
import com.ecs.EntityManager;
import com.ecs.components.Component;
import com.ecs.components.Movement;
import com.ecs.components.Speed;
import com.ecs.components.Transform;

import java.util.List;

public class MovementSystem implements ESystem {

    @Override
    public void update(EntityManager entityManager) {
        List<Component> components = entityManager.getComponents(Movement.class);

        for(Component component : components) {
            if (((Movement) component).move != null) {

                Direction move = ((Movement) component).move;

                int velocity = 1;
                int qt = ((Movement) component).quantity;

                List<Component> speeds = entityManager.getComponentsTogether(Speed.class, component);
                for (Component speedC : speeds) {
                    velocity = velocity + ((Speed) speedC).velocity;
                }
                if (qt > 0) {
                    velocity = velocity < qt ? velocity : qt;
                }

                List<Component> componentsTogether = entityManager.getComponentsTogether(Transform.class, component);
                for (Component cTransform: componentsTogether) {
                    if (move == Direction.LEFT) {
                        ((Transform) cTransform).x = ((Transform) cTransform).x - velocity;
                    } else {
                        ((Transform) cTransform).x = ((Transform) cTransform).x + velocity;
                    }
                }
                ((Movement) component).quantity = 0;
                ((Movement) component).move = null;
            }
        }
    }
}
