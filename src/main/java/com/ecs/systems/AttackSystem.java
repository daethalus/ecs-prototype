package com.ecs.systems;

import com.ecs.EntityManager;
import com.ecs.components.Attack;
import com.ecs.components.LifePoints;
import com.ecs.components.Transform;

public class AttackSystem implements ESystem {

    @Override
    public void update(EntityManager entityManager) {
        if ((System.currentTimeMillis() % 50) == 0) {
            entityManager.getComponents(Attack.class).stream().forEach(component -> {
                final Transform tEnemy = (Transform) entityManager.getComponentsTogether(Transform.class, component).stream().findFirst().orElse(null);

                entityManager.getComponents(LifePoints.class).stream().forEach(component1 -> {
                    final Transform hero = (Transform) entityManager.getComponentsTogether(Transform.class, component1).stream().findFirst().orElse(null);
                    if (tEnemy.x == hero.x) {
                        ((LifePoints) component1).life--;
                        System.out.println("ATTACKED");
                    }
                });
            });
        }
    }
}
