package com.msb.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir = Dir.STAY;
    private  static final int SPEED = 10;

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public Tank(int x, int y, Dir dir){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
//        x += 10;
//        y += 10;
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
            case STAY:
                break;
        }
    }
}

