package com.msb.tank.cor;

import com.msb.tank.Bullet;
import com.msb.tank.Explode;
import com.msb.tank.GameObject;
import com.msb.tank.Tank;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if(b.group==t.getGroup()) return false;

            if (b.rect.intersects(t.rect)){
                t.die();
                b.die();
                int eX = t.getX() +Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY = t.getY() +Tank.HEIGHT/2 - Explode.HEIGHT/2;

                b.gm.add(new Explode(eX, eY, b.gm));
                return true;
            }
            return false;
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2, o1);
        }else{
            return true;
        }
    }
}
