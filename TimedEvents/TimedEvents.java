package TimedEvents;

import Character.Character_test;

public class TimedEvents {
    public class calculatePhysics implements Runnable {
        Character_test frame;

        public calculatePhysics(Character_test frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            System.out.println("calucating");
            frame.calculatePhysics();
        }
    }

    public class repaint implements Runnable {
        Character_test frame;

        public repaint(Character_test frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            System.out.println("repainting");
            frame.repaint();
        }
    }
}
