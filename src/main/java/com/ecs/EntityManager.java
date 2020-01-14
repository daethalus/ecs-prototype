package com.ecs;

import com.ecs.components.Component;
import com.ecs.entities.Entity;
import com.ecs.systems.ESystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager {

    public List<Entity> entities = new ArrayList<>();
    public List<ESystem> systems = new ArrayList<>();
    public List<Component> components = new ArrayList<>();

    public Entity createEntity() {
        Entity entity = new Entity(this);
        entities.add(entity);
        return entity;
    }

    public void removeEntity(Entity entity) {
        components.removeAll(entity.getComponents());
        entities.remove(entity);
    }

    public void addSystem(ESystem system) {
        systems.add(system);
    }

    public void update() {
        for (ESystem system: systems) {
            try {
                system.update(this);
            } catch (Exception ex) {
                System.out.println("ERROWWWWWWWWWWWWWWWWW");
                ex.printStackTrace();
            }

        }
    }

    public List<Entity> getEntities(){
        return entities;
    }

    public List<Component> getComponentsTogether(Class type, Component component) {
        final List<Component> result = new ArrayList<>();
        entities.forEach(entity -> {
            if (entity.containsComponent(component)) {
                result.addAll(entity.getComponents(type));
            }
        });
        return result;
    }

    public List<Entity> getEntitiesWithComponent(Component component) {
        return entities.stream().filter(entity -> entity.containsComponent(component)).collect(Collectors.toList());
    }

    public List<Component> getComponents(Class type) {
        return components.stream().filter(type::isInstance).collect(Collectors.toList());
    }

    public void destroyComponent(Component component) {
        components.remove(component);
        entities.forEach(entity -> entity.removeComponent(component));
    }

    public void componentAdded(Component component) {
        components.add(component);
    }
}
