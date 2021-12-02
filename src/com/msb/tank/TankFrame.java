package com.msb.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    GameModel gm = new GameModel();

    static final int GAME_WIDTH = Integer.parseInt((String) propertyMgr.get("gameWidth")),
                     GAME_HEIGHT = Integer.parseInt((String) propertyMgr.get("gameHeight"));


    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);

    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);

    }


    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    gm.getMyTank().fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();
//            x += 20;
//            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bL&&!bU&&!bR&&!bD) gm.getMyTank().setMoving(false);
            else{
                gm.getMyTank().setMoving(true);
                if(bL) gm.getMyTank().setDir(Dir.LEFT);
                if(bU) gm.getMyTank().setDir(Dir.UP);
                if(bR) gm.getMyTank().setDir(Dir.RIGHT);
                if(bD) gm.getMyTank().setDir(Dir.DOWN);
            }
        }
    }
}
