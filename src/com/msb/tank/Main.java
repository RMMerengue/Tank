package com.msb.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws Exception{
        TankFrame tf = new TankFrame();

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
