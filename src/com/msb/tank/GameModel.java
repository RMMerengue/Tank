package com.msb.tank;

import com.msb.tank.cor.BulletTankCollider;
import com.msb.tank.cor.Collider;
import com.msb.tank.cor.ColliderChain;
import com.msb.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD,this);
    //Bullet b = new Bullet(300, 300, Dir.DOWN);
//    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    List<GameObject> objects = new ArrayList<>();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) propertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50+i*300, 200, Dir.DOWN, Group.BAD, this));
        }

        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));

    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("Bullets num:" + bullets.size(), 10, 60);
//        g.drawString("Enemy num:" + tanks.size(), 10, 80);
//        g.drawString("explodes num:" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);

            }
        }

//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collidWith(tanks.get(j));
//            }
//        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
