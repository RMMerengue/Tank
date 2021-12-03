package com.msb.tank;

import com.msb.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
    int oldx, oldy;
    public Dir dir = Dir.DOWN;
    private  static final int SPEED = Integer.parseInt((String) propertyMgr.get("tankSpeed"));
    private boolean living = true;
    private boolean moving;

    private Random random = new Random();
    public Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.goodTankD.getWidth(),
            HEIGHT = ResourceMgr.goodTankD.getHeight();
    public Rectangle rect = new Rectangle();

    FireStrategy fs;

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

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


    public Tank(int x, int y, Dir dir, Group group){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group == Group.GOOD) {
            moving = false;
            String goodFSName = (String)propertyMgr.get("goodFS");
            try {
                fs  = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            moving = true;
            String badFSName = (String)propertyMgr.get("badFS");
            try {
                fs  = (FireStrategy) Class.forName(badFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if(!living) GameModel.getInstance().remove(this);
        switch(dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
        }
        move();
//        x += 10;
//        y += 10;

    }

    public void back(){
        x = oldx;
        y = oldy;
    }

    private void move() {
        oldx = x;
        oldy = y;

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
        if(x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if(y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2) y = TankFrame.GAME_HEIGHT- Tank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    public void fire() {
        fs.fire(this);

    }

    public void die() {
        this.living = false;
    }

    public void stop(){
        moving = false;
    }

    @Override
    public int getWidth() {
        return HEIGHT;
    }

    @Override
    public int getHeight() {
        return WIDTH;
    }
}

