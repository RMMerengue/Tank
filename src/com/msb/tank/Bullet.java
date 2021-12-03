package com.msb.tank;

import java.awt.*;

public class Bullet extends GameObject{
    private static final int SPEED = Integer.parseInt((String) propertyMgr.get("bulletSpeed"));
    public static int WIDTH = ResourceMgr.bulletD.getWidth(),
                      HEIGHT = ResourceMgr.bulletD.getHeight();

    private Dir dir;

    private boolean living = true;

    public Group group = Group.BAD;

    public Rectangle rect = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if(!living) {
            GameModel.getInstance().remove(this);
        }

        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
        }

        move();
    }

    private void move() {
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

        rect.x = this.x;
        rect.y = this.y;

        if(x<0 || y<0 ||x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) living = false;
    }

    public boolean collidWith(Tank tank) {
        if(this.group==tank.getGroup()) return false;

        if (rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() +Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() +Tank.HEIGHT/2 - Explode.HEIGHT/2;

            new Explode(eX, eY);
            return true;
        }
        return false;
    }

    public void die() {
        this.living = false;
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
