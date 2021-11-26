package com.msb.tank;

import java.awt.*;

public class Explode {
    private static final int SPEED = 10;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth(),
                      HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;

    private boolean living = true;
    TankFrame tf = null;

    public int step;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if(step>=ResourceMgr.explodes.length){
            step = 0;
        }
    }
}
