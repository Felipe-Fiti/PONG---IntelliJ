import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

public class Cena implements GLEventListener{
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private TextRenderer textRenderer;
    //dados da esfera
    public int placar = 0;
    public int vidas = 5;
    public int fase = 1;
    public boolean Menu = true;
    public boolean jogo = false;
    public boolean pause = false;
    public boolean fimJogo = false;
    public float ang;
    public int op;
    //Preenchimento
    public int mode;


    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        GL2 gl = drawable.getGL().getGL2();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;

        reset();

        textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 20));
        //Habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    public void reset(){

        //dados da esfera
        placar = 0;
        vidas = 5;
        fase = 15;

        //preenchimento
        mode = GL2.GL_FILL;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        //objeto para desenho 3D
        GLUT glut = new GLUT();

        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(1, 1, 1, 1);
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); //ler a matriz identidade


        //Modo de desenho - os parametros s�o constantes inteiras
        //int modo =  GL2.GL_FILL; ou GL2.GL_LINE ou GL2.GL_POINT
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);

        desenhaTexto(gl, 10, 1060, Color.BLACK, "Jogo: PONG");
        desenhaTexto(gl, 10, 1040, Color.BLACK, "Placar: " + placar);
        desenhaTexto(gl, 10, 1020, Color.BLACK, "Vidas: " + vidas);

        desenhaTexto(gl, 730, 690, Color.BLACK ,"Seja bem-vindo ao nosso jogo - PONG!");
        desenhaTexto(gl, 880, 650, Color.BLACK ,"REGRAS:");
        desenhaTexto(gl, 600, 620, Color.BLACK ,"Para jogar use somente as teclas da seta esquerda e direita do teclado!");
        desenhaTexto(gl, 760, 590, Color.BLACK ,"Para PAUSAR o jogo aperte a tecla p!");
        desenhaTexto(gl, 775, 558, Color.BLACK ,"Para começar o jogo aperte ENTER!");

        gl.glFlush();
    }

    public void desenhaTexto(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase){
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        //Retorna a largura e altura da janela
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer.setColor(cor);
        textRenderer.draw(frase, xPosicao, yPosicao);
        textRenderer.endRendering();
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();

        //ativa a matriz de projecao
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //ler a matriz identidade

        //projecao ortogonal sem a correcao do aspecto
        gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //ler a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }
    @Override
    public void dispose(GLAutoDrawable drawable) {}
}
