package com.msb.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {


    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD,this);
    //Bullet b = new Bullet(300, 300, Dir.DOWN);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) propertyMgr.get("initTankCount"));

        for (int i = 0; i < 5; i++) {
            tanks.add(new Tank(50+i*80, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("Bullets num:" + bullets.size(), 10, 60);
        g.drawString("Enemy num:" + tanks.size(), 10, 80);
        g.drawString("explodes num:" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collidWith(tanks.get(j));
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
