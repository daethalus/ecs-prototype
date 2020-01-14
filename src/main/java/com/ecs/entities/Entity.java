package com.ecs.entities;

import com.ecs.EntityManager;
import com.ecs.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Entity {

    private static long idCounter = 0;

    public long id;
    private EntityManager entityManager;
    public String name;
    public boolean destroyed = false;

    private List<Component> components;

    public List<Component> getComponents() {
        return components;
    }

    public Entity(EntityManager entityManager) {
        id = idCounter++;
        components = new ArrayList<>();
        this.entityManager = entityManager;
    }

    public void addComponent(Component component) {
        components.add(component);
        entityManager.componentAdded(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public List<Component> getComponents(Class type) {
        return components.stream().filter(type::isInstance).collect(Collectors.toList());
    }

    public boolean containsComponent(Component component) {
        return components.contains(component);
    }

    public void destroy() {
        destroyed = true;
    }
}
