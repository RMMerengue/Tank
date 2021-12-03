package com.msb.tank;

import java.awt.*;

public class Explode extends GameObject{
    private static final int SPEED = 10;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth(),
                      HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;

    public int step;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if(step>=ResourceMgr.explodes.length){
            GameModel.getInstance().remove(this);
        }
    }
}
