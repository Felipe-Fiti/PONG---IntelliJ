import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

public class Renderer {
    private static GLWindow window = null;
    public static int screenWidth = 1920;
    public static int screenHeight = 1080;

    private static GLWindow window1 = null;
    public static int screenWidth1 = 600;
    public static int screenHeight1 = 600;

    //Cria a janela de rendeziracao do JOGL
    public static void init(){
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        window = GLWindow.create(caps);
        window.setSize(screenWidth, screenHeight);
        //window.setResizable(false);

        Cena cena = new Cena();

        window.addGLEventListener(cena); //adiciona a Cena a Janela
        //Habilita o teclado : cena
        window.addKeyListener(new KeyBoard(cena));

        //window.requestFocus();
        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start(); //inicia o loop de animacao

        //encerrar a aplicacao adequadamente
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                animator.stop();
                System.exit(0);
            }
        });

        //window.setFullscreen(true);
        window.setVisible(true);
    }

    public static void pagina(){
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        window1 = GLWindow.create(caps);
        window1.setSize(screenWidth1, screenHeight1);
        //window.setResizable(false);

        Cena cena = new Cena();

        window1.addGLEventListener(cena); //adiciona a Cena a Janela
        //Habilita o teclado : cena
        window1.addKeyListener(new KeyBoard(cena));

        //window.requestFocus();
        FPSAnimator animator = new FPSAnimator(window1, 60);
        animator.start(); //inicia o loop de animacao

        //encerrar a aplicacao adequadamente
        window1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                animator.stop();
                System.exit(0);
            }
        });

        //window.setFullscreen(true);
        window1.setVisible(true);
    }

    public static void main(String[] args) {
        init();
    }
}
