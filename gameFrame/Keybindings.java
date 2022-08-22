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
    Action crouch;
    Action downAction;
    Action startJumpTimer;
    Action jump;
    Action upAction;

    myFrame frame;
    Character chara;
    JPanel pane;
    public Keybindings(Character chara, JPanel pane){
        this.chara = chara;
        leftAction    = new LeftAction(chara);
        rightAction   = new RightAction(chara);
        crouch        = new crouch(chara);
        jump          = new jump(chara);
        upAction      = new UpAction(chara);
        
        //movement with arrow keys
        pane.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "jump");
        pane.getActionMap().put("jump", jump);

        pane.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        pane.getActionMap().put("moveLeft", leftAction);

        pane.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        pane.getActionMap().put("moveRight", rightAction);

        pane.getInputMap().put(KeyStroke.getKeyStroke("UP"), "nextLevel");
        pane.getActionMap().put("nextLevel", upAction);
        //pane.getActionMap().put(null, standingStill);
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

class RightAction extends AbstractAction{
    Character chara;
    public RightAction(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
       // System.out.println("trying to move");
        chara.stepRight();
    }
}

class crouch extends AbstractAction{
    Character chara;
    public crouch(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        chara.chargeJump();
    }
}
// class letGo extends AbstractAction{
//     Character chara;

// }
class jump extends AbstractAction{
    Character chara;
    public jump(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        chara.jump();
    }
}

class UpAction extends AbstractAction{
    Character chara;
    public UpAction(Character chara) {
        this.chara = chara;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
       // System.out.println("trying to move");
       
    }
}
