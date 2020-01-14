package com.ecs.systems;

import com.ecs.EntityManager;
import com.ecs.components.LifePoints;
import com.ecs.components.Transform;

public class EntityRendererSystem implements ESystem {

    @Override
    public void update(EntityManager entityManager) {
        if ((System.currentTimeMillis() % 50) == 0) {
            entityManager.getEntities().stream().forEach(entity -> {
                StringBuilder sb = new StringBuilder();
                sb.append("name: ").append(entity.name).append(", ");
                sb.append(" Transform : ").append(entity.getComponents(Transform.class).stream().findFirst().orElse(null));
                sb.append(" LifePoints: ").append(entity.getComponents(LifePoints.class).stream().findFirst().orElse(null));
                System.out.println(sb.toString());
            });
            System.out.println("-------------------------------");
        }
    }
}
