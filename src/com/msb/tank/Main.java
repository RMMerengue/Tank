package com.msb.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws Exception{
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String) propertyMgr.get("initTankCount"));

        for (int i = 0; i < 5; i++) {
            tf.tanks.add(new Tank(50+i*80, 200, Dir.DOWN, Group.BAD, tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
