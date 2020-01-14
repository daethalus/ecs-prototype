package com.ecs.systems;

import com.ecs.EntityManager;
import com.ecs.entities.Entity;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveEntitySystem implements ESystem {

    @Override
    public void update(EntityManager entityManager) {
        List<Entity> toRemove = entityManager.getEntities().stream().filter(entity -> entity.destroyed).collect(Collectors.toList());
        toRemove.forEach(entityManager::removeEntity);
    }
}
