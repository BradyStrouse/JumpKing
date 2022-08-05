import Character.Character_test;

public class TimedEvents {
    public class calculatePhysics implements Runnable {
        public calculatePhysics() {

        }

        @Override
        public void run() {

        }
    }

    public class repaint implements Runnable {
        Character_test frame;

        public repaint(Character_test frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            frame.repaint();
        }
    }
}
