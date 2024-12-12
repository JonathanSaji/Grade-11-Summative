package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HandleAllKeys implements KeyListener {
    public boolean upP,downP,leftP,rightP;
    @Override//dont use
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upP = true;
        }
        if(code == KeyEvent.VK_A){
            leftP = true;
        }
        if(code == KeyEvent.VK_S){
            downP = true;
        }
        if(code == KeyEvent.VK_D){
            rightP = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upP = false;
        }
        if(code == KeyEvent.VK_A){
            leftP = false;
        }
        if(code == KeyEvent.VK_S){
            downP = false;
        }
        if(code == KeyEvent.VK_D){
            rightP = false;
        }
    }
}
