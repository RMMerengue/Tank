package com.msb.tank.abstractfactory;

import com.msb.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collidWith(BaseTank Tank);
}
