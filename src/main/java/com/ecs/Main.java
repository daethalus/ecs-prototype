package com.ecs;

import com.ecs.components.*;
import com.ecs.entities.Entity;
import com.ecs.systems.*;

public class Main {

    public static void main(String[] args) {

        EntityManager entityManager = new EntityManager();
        entityManager.addSystem(new MovementSystem());
        entityManager.addSystem(new KeyListener());
        entityManager.addSystem(new EntityRendererSystem());
        entityManager.addSystem(new EnemySystem());
        entityManager.addSystem(new AttackSystem());
        entityManager.addSystem(new DeathSystem());
        entityManager.addSystem(new RemoveEntitySystem());

        Entity entity = entityManager.createEntity();
        entity.name = "Hero";
        entity.addComponent(new Transform());
        entity.addComponent(new Camera());
        entity.addComponent(new Movement());
        entity.addComponent(new Hero());
        entity.addComponent(new LifePoints(20));
        entity.addComponent(new Speed(15));

        for (int x = 1; x<=10; x++ ) {
            Entity enemy = entityManager.createEntity();
            enemy.name = "Enemy " + x;
            enemy.addComponent(new Transform(x % 2 == 0 ? (x +3): -(x - 3)));
            enemy.addComponent(new Movement());
            enemy.addComponent(new Enemy());
            enemy.addComponent(new Attack());
            enemy.addComponent(new Speed(x));
        }


        while(true) {
            entityManager.update();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
