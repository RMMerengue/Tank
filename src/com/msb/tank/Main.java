package com.msb.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws Exception{
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String) propertyMgr.get("initTankCount"));

        for (int i = 0; i < 5; i++) {
            tf.tanks.add(tf.gf.createTank(50+i*100, 200, Dir.DOWN, Group.BAD, tf));
        }

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
