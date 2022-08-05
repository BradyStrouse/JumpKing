package Character;

import java.lang.management.GarbageCollectorMXBean;

public class movement {
    double horizontal_speed = 10;
    double vertical_speed = 10;
    double gravity = 1;
    Character chara;

    public movement(Character chara) {
        this.chara = chara;
    }

    public void setVertical(double vertical_speed) {
        this.vertical_speed = vertical_speed;
    }

    public void setHorizontal(double horizontal_speed) {
        this.horizontal_speed = horizontal_speed;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getVertical() {
        return vertical_speed;
    }

    public double getHorizontal() {
        return horizontal_speed;
    }

    public double getGravity() {
        return gravity;
    }
}