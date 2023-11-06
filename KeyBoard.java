import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import static java.lang.System.exit;

public class KeyBoard implements KeyListener {
    private Cena cena;
    private Object exit;
//espaço - 32 ENTER - 13
    public KeyBoard(Cena cena) {
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            exit(0);

        switch (e.getKeyChar()) {
            case 'r':
                System.out.println("chamou Rotacao");
                cena.ang += 45.0f;
                break;

            case 'p':
                System.out.println("chamou Pause");
                break;

            case '1':
                cena.op = 1;
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
