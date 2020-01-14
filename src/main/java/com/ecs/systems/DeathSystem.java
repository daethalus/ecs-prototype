package com.ecs.systems;

import com.ecs.EntityManager;
import com.ecs.components.Component;
import com.ecs.components.LifePoints;
import com.ecs.entities.Entity;

import java.util.List;

public class DeathSystem implements ESystem {

    @Override
    public void update(EntityManager entityManager) {
        List<Component> components = entityManager.getComponents(LifePoints.class);
        for (Component component : components) {
            if (((LifePoints) component).life <= 0){
                entityManager.getEntitiesWithComponent(component).forEach(Entity::destroy);
            }
        }
    }
}
