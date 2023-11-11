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
    public int placar = 0;
    public int vidas = 5;
    public int fase = 1;
    public float tamanho = 50;
    public float movimentoBarrinha;
    public float transXBola = 0;
    public float transYBola = 0;
    public boolean easterEgg = true;
    public boolean Menu = true;
    public boolean jogo = false;
    public boolean pause = false;
    public boolean fimJogo = false;
    public float ang;
    public int op;
    public int mode;


    @Override
    public void init(GLAutoDrawable drawable){
        GL2 gl = drawable.getGL().getGL2();
        //SRU
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;

        reset();

        textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 20));
        //Profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    public void reset(){
        placar = 0;
        vidas = 5;
        fase = 1;
        mode = GL2.GL_FILL;
    }

    public void continuarJogo(){
        placar = 0;
        vidas = 5;
        fase = 1;
    }

    public void barrinha(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0,-900,0);
        gl.glTranslatef(movimentoBarrinha,0,0);
        float x = (float) -(tamanho*2.5);
        for (int i = 0; i < 6 ; i++) {
            gl.glPushMatrix();
            gl.glTranslatef(x,0,0);
            gl.glColor3f(0,0,1);
            glut.glutSolidCube(tamanho);
            gl.glPopMatrix();
            x+=tamanho;
        }
        gl.glPopMatrix();
    }
    public void bola(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(-10, -10, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,1,1);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }


    @Override
    public void display(GLAutoDrawable drawable){
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();
        //cor da janela - Branco
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        if(Menu){
            desenhaTexto(gl, 730, 690, Color.BLACK, "Seja bem-vindo ao nosso jogo - PONG!");
            desenhaTexto(gl, 880, 650, Color.BLACK, "REGRAS:");
            desenhaTexto(gl, 400, 620, Color.BLACK, "Para jogar use as teclas da seta esquerda (para andar a esquerda) e a seta direita (para andar a direita) do teclado!");
            desenhaTexto(gl, 760, 590, Color.BLACK, "Para PAUSAR o jogo aperte a tecla P!");
            desenhaTexto(gl, 730, 559, Color.BLACK, "Para começar o jogo aperte a tecla ENTER!");
            desenhaTexto(gl, 760, 530, Color.BLACK, "Para sair do jogo aperte a tecla ESC!");

        }else if(jogo){
            desenhaTexto(gl, 10, 1060, Color.BLACK, "Jogo: PONG");
            desenhaTexto(gl, 10, 1035, Color.BLACK, "Fase: " + fase);
            desenhaTexto(gl, 10, 1010, Color.BLACK, "Placar: " + placar);
            desenhaTexto(gl, 10, 985, Color.BLACK, "Vidas: " + vidas);

        }else if(pause){
            desenhaTexto(gl, 800, 700, Color.BLACK, "O jogo está Pausado!");
            desenhaTexto(gl, 710, 650, Color.BLACK, "Aperte a letra P para continuar o jogo!");
        }else if(easterEgg){
            desenhaTexto(gl, 750, 700, Color.BLACK, "Você encontrou um easter Egg!");
            desenhaTexto(gl, 710, 670, Color.BLACK, "O que está fazendo aqui? Volte ao Jogo!");
        }

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
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
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
    public void dispose(GLAutoDrawable drawable){}
}
