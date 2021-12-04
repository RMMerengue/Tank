package com.msb.tank;

import com.msb.tank.cor.BulletTankCollider;
import com.msb.tank.cor.Collider;
import com.msb.tank.cor.ColliderChain;
import com.msb.tank.cor.TankTankCollider;
import com.msb.tank.decorator.RectDecorator;
import com.msb.tank.decorator.TailDecorator;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }
    Tank myTank;
    public static GameModel getInstance(){
        return INSTANCE;
    }


    //Bullet b = new Bullet(300, 300, Dir.DOWN);
//    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    List<GameObject> objects = new ArrayList<>();

    private GameModel() {}

    private void init() {
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);
        new RectDecorator(myTank);
        new TailDecorator(myTank);

        int initTankCount = Integer.parseInt((String) propertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50+i*300, 200, Dir.DOWN, Group.BAD));
        }

        new Wall(150, 150, 200, 50);
        new Wall(550, 150, 200, 50);
        new Wall(300, 300, 50, 200);
        new Wall(550, 300, 50, 200);
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

    public void save() {
        File f = new File("E:\\Learning\\Java_Lessons\\Tank\\tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(){
        File f = new File("E:\\Learning\\Java_Lessons\\Tank\\tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            myTank = (Tank)ois.readObject();
            objects = (List) ois.readObject();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
