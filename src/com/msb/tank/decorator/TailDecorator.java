package com.msb.tank.decorator;

import com.msb.tank.GameModel;
import com.msb.tank.GameObject;
import com.msb.tank.TankFrame;

import java.awt.*;

public class TailDecorator extends GODecorator{
    public TailDecorator(GameObject go){
        super(go);
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(super.go.x + super.go.getWidth()/2, super.go.y,
                   super.go.x + super.go.getWidth()/2, 0);
        g.drawLine(super.go.x + super.go.getWidth(), super.go.y + super.go.getHeight()/2,
                       TankFrame.GAME_WIDTH, super.go.y + super.go.getHeight()/2);
        g.drawLine(super.go.x+super.go.getWidth()/2, super.go.y + super.go.getHeight(),
                super.go.x+super.go.getWidth()/2, TankFrame.GAME_HEIGHT);
        g.drawLine(super.go.x, super.go.y+super.go.getHeight()/2,
                0, super.go.y+super.go.getHeight()/2);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
