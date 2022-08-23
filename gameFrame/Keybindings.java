package gameFrame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Character.Character;
public class Keybindings {
    Action leftAction;
    Action rightAction;
    Action Stop;
    Action crouch;
    Action downAction;
    Action startJumpTimer;
    Action jump;
    Action charge;
    Action upAction;

    myFrame frame;
    Character chara;
    JPanel pane;
    public Keybindings(Character chara, JPanel pane){
        this.chara = chara;
        leftAction    = new LeftAction(chara);
        rightAction   = new RightAction(chara);
        charge        = new Charge(chara);
        jump          = new jump(chara);
        upAction      = new UpAction(chara);
        Stop          = new Stop(chara);
        //movement with arrow keys
        pane.getInputMap().put(KeyStroke.getKeyStroke("pressed SPACE"), "charge");
        pane.getActionMap().put("charge", charge);

        pane.getInputMap().put(KeyStroke.getKeyStroke("released SPACE"), "jump");
        pane.getActionMap().put("jump", jump);

        pane.getInputMap().put(KeyStroke.getKeyStroke("pressed LEFT"), "moveLeft");
        pane.getActionMap().put("moveLeft", leftAction);

        pane.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "StopLeft");
        pane.getActionMap().put("StopLeft", Stop);

        
        pane.getInputMap().put(KeyStroke.getKeyStroke("pressed RIGHT"), "moveRight");
        pane.getActionMap().put("moveRight", rightAction);
        
        pane.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "StopRight");
        pane.getActionMap().put("StopRight", Stop);

        pane.getInputMap().put(KeyStroke.getKeyStroke("UP"), "nextLevel");
        pane.getActionMap().put("nextLevel", upAction);
    }
}
class LeftAction extends AbstractAction{
    Character chara;
    public LeftAction(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        chara.stepLeft();
    }
}
class Stop extends AbstractAction{
    Character chara;
    public Stop(Character chara){
        this.chara = chara;
    }
    @Override
    public void actionPerformed(ActionEvent arg0){
        chara.setx_vel(0);
    }
}
class RightAction extends AbstractAction{
    Character chara;
    public RightAction(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        chara.stepRight();
    }
}
// class letGo extends AbstractAction{
//     Character chara;

// }
class Charge extends AbstractAction{
    Character chara;
    public Charge(Character chara){
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        chara.startCharging();
    }
}
class jump extends AbstractAction{
    Character chara;
    public jump(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        chara.stopCharging();
    }
}

class UpAction extends AbstractAction{
    Character chara;
    public UpAction(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
       chara.jump();
    }
}
