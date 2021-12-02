package com.msb.tank.abstractfactory;

import com.msb.tank.Audio;
import com.msb.tank.ResourceMgr;
import com.msb.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode{
    public static int WIDTH = ResourceMgr.explodes[0].getWidth(),
            HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;

    TankFrame tf = null;

    public int step;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        //g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10*step, 10*step);
        step++;

        if(step>=10){
            tf.explodes.remove(this);
        }
        g.setColor(c);
    }
}
