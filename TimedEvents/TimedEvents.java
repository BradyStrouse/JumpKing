package TimedEvents;

import gameFrame.myFrame;

public class TimedEvents {
    public class calculatePhysics implements Runnable {
        myFrame frame;

        public calculatePhysics(myFrame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            frame.calculatePhysics();
        }
    }

    public class repaint implements Runnable {
        myFrame frame;

        public repaint(myFrame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            frame.repaint();
        }
    }
}
