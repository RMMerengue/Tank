package com.msb.tank.abstractfactory;

import com.msb.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank{
    int x, y;
    Dir dir = Dir.DOWN;
    private  static final int SPEED = Integer.parseInt((String) propertyMgr.get("tankSpeed"));
    private boolean living = true;
    private boolean moving  = true;
    TankFrame tf = null;
    private Random random = new Random();
    Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.goodTankD.getWidth(),
            HEIGHT = ResourceMgr.goodTankD.getHeight();
    public Rectangle rect = new Rectangle();

    FireStrategy fs;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group == Group.GOOD) {
            String goodFSName = (String)propertyMgr.get("goodFS");
            try {
                fs  = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            String badFSName = (String)propertyMgr.get("badFS");
            try {
                fs  = (FireStrategy) Class.forName(badFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        if(!living) tf.tanks.remove(this);
        Color c = g.getColor();
        g.setColor(group==Group.GOOD ? Color.GREEN : Color.BLUE);
        g.fillRect(x, y, 40, 40);
        g.setColor(c);
        move();
//        x += 10;
//        y += 10;

    }

    private void move() {
        if(!moving) return;
        switch(dir) {
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }

        if(this.group==Group.BAD&&random.nextInt(10)>8) {
            this.fire();
        }
        if(this.group==Group.BAD&&random.nextInt(100)>90){
            randomDir();
        }
        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(x < 2) x = 2;
        if(y < 28) y = 28;
        if(x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2) x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 2;
        if(y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2) y = TankFrame.GAME_HEIGHT- RectTank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            this.tf.gf.createBullet(bX, bY, dir, this.group, this.tf);

        }


        if(this.group==Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public void die() {
        this.living = false;
    }
}

