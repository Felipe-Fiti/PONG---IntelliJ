import com.jogamp.opengl.GL;
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
    private TextRenderer textRenderer1;
    public int placar = 0; //25 pontos
    public int vidas = 5;
    public int fase = 1;
    public float tamanho = 85;
    public float tamanhoB = 100;
    public float movimentoBarrinha;
    public float transXBola = 0;
    public float transYBola = 0;
    public final float janela = 1000f;

    public float direitaXBola = tamanho/2, esquerdaXBola = -tamanho/2;
    public float superiorYBola = tamanho/2, inferiorYBola = -tamanho/2;
    public final float velocidadeX = 20f, velocidadeY = 15f;
    public float taxaX =20f , taxaY =15f;
    public float direitaBarrinha = tamanho*3 , esquerdaBarrinha =direitaBarrinha -(tamanho*6);
    public float Ybarrinha = -900 ;
    public final float InicioTriangulo = 100;
    public float triangulo =100;
    public boolean easterEgg = true;
    public boolean Menu = true;
    public boolean jogo = false;
    public boolean pause = false;
    public boolean fimJogo = false;
    public int mode;


    @Override
    public void init(GLAutoDrawable drawable){
        GL2 gl = drawable.getGL().getGL2();
        //SRU
        xMin = - 1920;
        xMax = 1920;
        yMin = - 1080;
        yMax = 1080;
        zMin = - 1000;
        zMax = 1000;

        reset();

        textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 25));
        textRenderer1 = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 35));
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

    public void borda(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(0,0,0);
        gl.glLineWidth(100f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(1880,820);
        gl.glVertex2f(1880,-1020);
        gl.glVertex2f(-1880,-1020);
        gl.glVertex2f(-1880,820);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public void faixa(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(1,0,1);
        gl.glLineWidth(100f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(1000,820);
        gl.glVertex2f(1000,-1020);
        gl.glVertex2f(-1000,-1020);
        gl.glVertex2f(-1000,820);
        gl.glEnd();
        gl.glPopMatrix();
    }


    public void campo(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(0,1,0);
        gl.glLineWidth(100f);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glVertex2f(1000,820);
        gl.glVertex2f(1000,-1020);
        gl.glVertex2f(-1000,-1020);
        gl.glVertex2f(-1000,820);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public void barrinha(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0,-900,0);
        gl.glTranslatef(movimentoBarrinha,0,0);
        float x = (float) -(tamanhoB*2);
        for (int i = 0; i < 6 ; i++) {
            gl.glPushMatrix();
            gl.glTranslatef(x,0,0);
            gl.glColor3f(0,1,1);
            glut.glutSolidCube(tamanhoB);
            gl.glPopMatrix();
            x+=tamanhoB;
        }
        gl.glPopMatrix();
    }
    public void bola(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(20, 10, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }

    public void bola1(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(-1250, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }

    public void bola2(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(-1050, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }

    public void bola3(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(-850, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }

    public void bola4(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(-650, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }

    public void bola5(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(-450, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }

    public void resetarPosicaoInicialBolinha(){
        transYBola = 0;
        superiorYBola = tamanho / 2;
        inferiorYBola = - tamanho / 2;
        taxaY = - taxaY;

        transXBola = 0;
        direitaXBola = tamanho / 2;
    }
    public void movimentarBarra(){
        //verifica a colisão da barra com a parede
        System.out.println(taxaY);


        if (movimentoBarrinha+ tamanho*3 >= janela){
            movimentoBarrinha = janela - tamanho*3;
            direitaBarrinha = janela;
        }else if(movimentoBarrinha- tamanho*3 <= - janela){
            movimentoBarrinha = - janela + tamanho*3;
            direitaBarrinha = - janela + (tamanho*6);
        }
        //verifica colisao no eixo y
        if (inferiorYBola <= Ybarrinha+(tamanho) && inferiorYBola >= Ybarrinha+(tamanho/2))// parte superior + margem de erro
        {
            //verificar colisão com a parte superior da barrra
            if(direitaXBola >= esquerdaBarrinha && direitaXBola <= direitaBarrinha){
                placar+=50;

                fase = (placar/200)+1;

                //taxa crescente, eixo y
                taxaY = velocidadeY + (5 * (fase-1));

                Random ran = new Random();
                int aleatorizaAcressimoX = ran.nextInt(6);
                // bolinha continua o curso do eixo x que estava realizando
                if (taxaX<0){
                    taxaX = -velocidadeX - (5 * (fase-1));//pode aumentar velocidade a depender da fase
                    taxaX -= aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
                } else {
                    taxaX = velocidadeX + (5 * (fase - 1));//pode aumentar velocidade a depender da fase
                    taxaX += aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
                }
            }
        }else if (inferiorYBola <= Ybarrinha+(tamanho) && inferiorYBola >= Ybarrinha-(tamanho)){
            //verificar colisão com a parte lateral da barrra
            if(direitaXBola >= esquerdaBarrinha && direitaXBola <= direitaBarrinha)
            {
                placar+=50;

                fase = (placar/200)+1;

                //taxa crescente, eixo y
                taxaY = velocidadeY + (5 * (fase-1));

                Random ran = new Random();
                int aleatorizaAcressimoX = ran.nextInt(6);
                //se bater na lateral a bolinha vai para o lado oposto em relação ao eixo x
                if (taxaX < 0){
                    taxaX = velocidadeX + (5 * (fase-1) );//pode aumentar velocidade a depender da fase
                    taxaX += aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
                } else {
                    taxaX = -velocidadeX - (5 * (fase - 1));//pode aumentar velocidade a depender da fase
                    taxaX -= aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
                }
            }
        }
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
            desenhaTexto(gl, 700, 690, Color.BLACK, "Seja bem-vindo ao nosso jogo - PONG!");
            desenhaTexto(gl, 880, 650, Color.BLACK, "REGRAS:");
            desenhaTexto(gl, 230, 620, Color.BLACK, "Para jogar use as teclas da seta esquerda (para andar a esquerda) e a seta direita (para andar a direita) do teclado!");
            desenhaTexto(gl, 720, 590, Color.BLACK, "Para PAUSAR o jogo aperte a tecla P!");
            desenhaTexto(gl, 680, 559, Color.BLACK, "Para começar o jogo aperte a tecla ENTER!");
            desenhaTexto(gl, 720, 530, Color.BLACK, "Para sair do jogo aperte a tecla ESC!");

        }else if(jogo){
            bola(gl,glut);
            bola1(gl,glut);
            bola2(gl,glut);
            bola3(gl,glut);
            bola4(gl,glut);
            bola5(gl,glut);
            borda(gl,glut);
            faixa(gl, glut);
            campo(gl,glut);
            barrinha(gl, glut);
            movimentarBarra();
            desenhaTexto1(gl, 30, 1000, Color.BLACK, "LEVEL " + fase);
            desenhaTexto1(gl, 1600, 1000, Color.BLACK, "SCORE " + placar);
        }else if(pause){
            desenhaTexto(gl, 800, 700, Color.BLACK, "O jogo está Pausado!");
            desenhaTexto(gl, 710, 650, Color.BLACK, "Aperte a letra P para continuar o jogo!");
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

    public void desenhaTexto1(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase){
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        //Retorna a largura e altura da janela
        textRenderer1.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer1.setColor(cor);
        textRenderer1.draw(frase, xPosicao, yPosicao);
        textRenderer1.endRendering();
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
