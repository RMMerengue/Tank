package com.msb.tank.decorator;

import com.msb.tank.GameModel;
import com.msb.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{
    public RectDecorator(GameObject go){
        super(go);
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(super.go.x, super.go.y, super.go.getWidth(), super.go.getHeight());
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
