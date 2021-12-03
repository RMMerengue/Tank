package com.msb.tank.cor;

import com.msb.tank.Bullet;
import com.msb.tank.GameObject;
import com.msb.tank.Tank;
import com.msb.tank.Wall;

public class TanktWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

            if (t.rect.intersects(w.rect)){
                t.back();
            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
            return collide(o2, o1);
        }
        return true;

    }
}
