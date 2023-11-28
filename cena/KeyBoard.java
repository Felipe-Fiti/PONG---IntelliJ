package cena;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import java.awt.*;
import static java.lang.System.exit;

public class KeyBoard implements KeyListener{
    private Cena cena;
    private Object exit;
    public KeyBoard(Cena cena){
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e){
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            exit(0);
        switch (e.getKeyChar()) {

        }

        switch (e.getKeyCode()){
            case 80://TECLA P - Pause/Stop do jogo
                cena.pause =! cena.pause;
                cena.Menu = false;
                cena.dandoPlay =! cena.pause;
                break;
            case 13://Tecla ENTER - Start Jogo
                cena.dandoPlay = true;
                cena.Menu = false;
                break;
            case 149://seta esquerda
                if (cena.movimentoBarrinha > - cena.janela + 300) {
                    cena.movimentoBarrinha -= cena.velDoMovimentarDaBarra;
                    cena.direitaBarrinha -= cena.velDoMovimentarDaBarra;
                    cena.esquerdaBarrinha = cena.velDoMovimentarDaBarra - 300;
                }
                break;
            case 151://seta direita
                if (cena.movimentoBarrinha < cena.janela - 300) {
                    cena.movimentoBarrinha += cena.velDoMovimentarDaBarra;
                    cena.direitaBarrinha += cena.velDoMovimentarDaBarra;
                    cena.esquerdaBarrinha = cena.velDoMovimentarDaBarra - 300;
                }
                break;
            case 32://Barra de Espaço
                if (cena.fimDoJogo){
                    cena.restartDoJogo();
                } else{
                    cena.dandoPlay=!cena.dandoPlay;
                    cena.Menu=false;
                    cena.pause=!cena.dandoPlay;
                }
        }
    }
    @Override
    public void keyReleased(KeyEvent e){}
}
