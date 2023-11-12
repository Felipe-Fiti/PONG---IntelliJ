import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import java.awt.*;

import static java.lang.System.exit;

public class KeyBoard implements KeyListener{
    private Cena cena;
    private Object exit;
    //espaço - 32 ENTER - 13
    public KeyBoard(Cena cena){
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e){
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            exit(0);

        switch (e.getKeyChar()){
            case 80://TECLA P - Pause/Stop do jogo
                cena.pause =! cena.pause;
                cena.Menu = false;
                cena.jogo =! cena.pause;
                break;
            case 13://Tecla ENTER - Start Jogo
                cena.jogo = true;
                cena.Menu = false;
                break;
            case 149://seta esquerda
                cena.movimentoBarrinha-= 50;
                cena.direitaBarrinha -= 50;
                cena.esquerdaBarrinha =cena.direitaBarrinha -(cena.tamanho*6);
                break;
            case 151://seta direita
                cena.movimentoBarrinha+= 50;
                cena.direitaBarrinha += 50;
                cena.esquerdaBarrinha =cena.direitaBarrinha -(cena.tamanho*6);
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){}
}
