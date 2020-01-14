package com.ecs.systems;

import com.ecs.Direction;
import com.ecs.EntityManager;
import com.ecs.components.*;

import java.util.List;

public class EnemySystem implements ESystem {

    @Override
    public void update(EntityManager entityManager) {
        if ((System.currentTimeMillis() % 50) == 0) {

            List<Component> components = entityManager.getComponents(Enemy.class);

            final Transform heroPosition = (Transform) entityManager.
                    getComponentsTogether(
                            Transform.class,
                            entityManager.getComponents(Hero.class).stream().findFirst().orElse(null)
                    ).stream().findFirst().orElse(null);

            if (components != null) {
                components.stream().forEach(component -> {
                    try {
                        final Transform transform = (Transform) entityManager.getComponentsTogether(Transform.class, component).stream().findFirst().orElse(null);
                        final Movement movement = (Movement) entityManager.getComponentsTogether(Movement.class, component).stream().findFirst().orElse(null);

                        entityManager.getComponents(Hero.class).stream().forEach(hero -> {
                            if (heroPosition.x > transform.x) {
                                movement.move = Direction.RIGHT;
                                movement.quantity = heroPosition.x - transform.x;
                            } else if (heroPosition.x < transform.x) {
                                movement.move = Direction.LEFT;
                                movement.quantity = transform.x - heroPosition.x;
                            }
                        });
                    } catch (Exception ex) {
                        System.out.println("don't mess with the enemies, please");
                    }
                });
            }
        }
    }
}
