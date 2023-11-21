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
    public float tamanhoB = 80;
    public float movimentoBarrinha = 0;
    public float transXBola = 0;
    public float transYBola = 0;
    private float margemXdErro;
    private float margemYdErro;
    public final float janela = 1890f;
    public float direitaXBola = tamanho/2, esquerdaXBola = -tamanho/2;
    public float superiorYBola = tamanho/2, inferiorYBola = -tamanho/2;
    public final float velocidadeX = 30f, velocidadeY = 60f;
    public float taxaAttX = 40f , taxaAttY = 30f;
    public float direitaBarrinha = tamanho*3 , esquerdaBarrinha =direitaBarrinha -(tamanho*6);
    public float Ybarrinha = -900 ;
    public final float InicioTriangulo = 100;
    public float triangulo =100;
    public boolean dandoPlay = false;
    public boolean Menu = true;
    public boolean pause = false;
    public boolean fimJogo = false;
    public int mode;
    public final float velDoMovimentarDaBarra = 100;

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

    public void borda(GL2 gl, GLUT glut){//Borda jogo
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
    public void bordaInicio(GL2 gl, GLUT glut){ //Borda tela inicial
        gl.glPushMatrix();
        gl.glColor3f(1,0,1);
        gl.glLineWidth(100f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(1550,500);
        gl.glVertex2f(1550,-150);
        gl.glVertex2f(-1550,-150);
        gl.glVertex2f(-1550,500);
        gl.glEnd();
        gl.glPopMatrix();
    }
    public void sol(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(950, 1050, 0);
        gl.glColor3f(1,1,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }
    public void faixaCentro(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glLineWidth(100f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(10,900);
        gl.glVertex2f(10,-1000);
        gl.glEnd();
        gl.glPopMatrix();
    }
    public void faixaDireita(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glLineWidth(100f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(1200,500);
        gl.glVertex2f(1200,-750);
        gl.glVertex2f(1800,-750);
        gl.glVertex2f(1800,500);
        gl.glEnd();
        gl.glPopMatrix();
    }
    public void faixaEsquerda(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(1,1,1);
        gl.glLineWidth(100f);
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glVertex2f(-1200,500);
        gl.glVertex2f(-1200,-750);
        gl.glVertex2f(-1800,-750);
        gl.glVertex2f(-1800,500);
        gl.glEnd();
        gl.glPopMatrix();
    }
    public void campo(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(0,1,0);
        gl.glLineWidth(100f);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glVertex2f(1900,820);
        gl.glVertex2f(1900,-1000);
        gl.glVertex2f(-1900,-1000);
        gl.glVertex2f(-1900,820);
        gl.glEnd();
        gl.glPopMatrix();
    }
    public void tri(GL2 gl, GLUT glut){
        gl.glPushMatrix();
        gl.glColor3f(0, 1, 0);
        gl.glLineWidth(100f);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(1900,820);
        gl.glVertex2f(1900,-1000);
        gl.glVertex2f(-1900,-1000);
        gl.glVertex2f(-1900,820);
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
    public void bola0(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(transXBola, transYBola, 0);
        gl.glTranslatef(margemXdErro,margemYdErro,0);
        // Aplica os valores convertidos:
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }
    public void vida1(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glTranslatef(-1250, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }
    public void vida2(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glTranslatef(-1050, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }
    public void vida3(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glTranslatef(-850, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }
    public void vida4(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glTranslatef(-650, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }
    public void vida5(GL2 gl,GLUT glut){
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glTranslatef(-450, 965, 0);//mantem a bola no ponto quando começar
        gl.glColor3f(1,0,0);
        glut.glutSolidSphere(tamanho,500,500);
        gl.glPopMatrix();
    }
    public void resetarPosicaoInicialBolinha(){
        transYBola = 0;
        superiorYBola = tamanho / 2;
        inferiorYBola = - tamanho / 2;
        taxaAttY = - taxaAttY;

        transXBola = 0;
        direitaXBola = tamanho / 2;
    }
    public void movimentarBola0(){
        if (dandoPlay && vidas!=0){
            transYBola+= taxaAttY;//inicia a movimentacao da bolinha no eixo y
            superiorYBola =transYBola + tamanho + margemYdErro;//armazena a extremidade Y com base na translacao e tamanho do objeto( /2 porque a bolinha é iniciada no centro da janela )
            inferiorYBola =transYBola - tamanho + margemYdErro;

            transXBola+= taxaAttX;//inicia a movimentacao da bolinha no eixo X
            direitaXBola =transXBola + tamanho + margemXdErro;//armazena a extremidade X
            esquerdaXBola= transXBola - tamanho + margemXdErro;

            if(taxaAttX>= 0){
                float pixeisAteParede = janela - direitaXBola;
                float restoMargemDeErro = pixeisAteParede % taxaAttX;
                if(restoMargemDeErro != 0){
                    margemXdErro += restoMargemDeErro;
                    direitaXBola += restoMargemDeErro;
                    esquerdaXBola = direitaXBola - 100;
                }
            }else{
                float pixeisAteParede = - janela + esquerdaXBola;
                float restoMargemDeErro = pixeisAteParede % taxaAttX;
                if(restoMargemDeErro != 0){
                    margemXdErro -= restoMargemDeErro;
                    direitaXBola -= restoMargemDeErro;
                    esquerdaXBola = direitaXBola - 100;
                }
            }
            if(taxaAttY>=0){
                float pixeisAteParede = janela - superiorYBola;
                float restoMargemDeErro = pixeisAteParede % taxaAttY;
                if(restoMargemDeErro != 0){
                    margemYdErro += restoMargemDeErro;
                    superiorYBola += restoMargemDeErro;
                    inferiorYBola = superiorYBola - 100;
                }
            } else {
                float pixeisAteBarra = (- 800) - inferiorYBola;

                float restoMargemDeErro = pixeisAteBarra % taxaAttY;
                if(restoMargemDeErro != 0){
                    margemYdErro += restoMargemDeErro;
                    inferiorYBola += restoMargemDeErro;
                    superiorYBola = inferiorYBola + 100;
                }
            }
            //verificar colisoes paredes
            if(direitaXBola >= janela ){
                taxaAttX = - taxaAttX;
            } else if(esquerdaXBola <= (-janela)){
                taxaAttX = - taxaAttX;
            }
            //verifica colisões teto/chão
            if(superiorYBola >= janela){
                taxaAttY = - taxaAttY;
            }else if(inferiorYBola <= - janela ){
                vidas-=1;
                //resetando valores iniciaais
                resetarPosicaoInicialBolinha();
            }
        }
    }
    public void movimentacaoDaBarrinha(){
        //verifica colisao no eixo y
        if(direitaXBola >= esquerdaBarrinha && esquerdaXBola <= direitaBarrinha){
            if (inferiorYBola == -700f)
            {
                placar+=50;
                fase = (placar/200)+1;

                //taxa crescente, eixo y
                taxaAttY = velocidadeY + (5 * (fase-1));

                Random ran = new Random();
                int aleatorizaAcressimoX = ran.nextInt(6);
                // bolinha continua o curso do eixo x que estava realizando
                if (taxaAttX<0){
                    taxaAttX = -velocidadeX - (5 * (fase-1));//pode aumentar velocidade a depender da fase
                    taxaAttX -= aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
                } else {
                    taxaAttX = velocidadeX + (5 * (fase - 1));//pode aumentar velocidade a depender da fase
                    taxaAttX += aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
                }
            }else
            if (inferiorYBola <= -800f && superiorYBola >= -850f)// parte superior + margem de erro
            {
                placar+=50;

                fase = (placar/200)+1;

                //taxa crescente, eixo y
                taxaAttY = velocidadeY + (5 * (fase-1));

                Random ran = new Random();
                int aleatorizaAcressimoX = ran.nextInt(6);
                //se bater na lateral a bolinha vai para o lado oposto em relação ao eixo x
                if (taxaAttX < 0){
                    taxaAttX = velocidadeX + (5 * (fase-1) );//pode aumentar velocidade a depender da fase
                    taxaAttX += aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
                } else {
                    taxaAttX = -velocidadeX - (5 * (fase - 1));//pode aumentar velocidade a depender da fase
                    taxaAttX -= aleatorizaAcressimoX;// acressimo aleatorio para evitar repetição de colisão
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
            bordaInicio(gl,glut);
            sol(gl,glut);
            desenhaTexto(gl, 700, 690, Color.BLACK, "Seja bem-vindo ao nosso jogo - PONG!");
            desenhaTexto(gl, 880, 650, Color.BLACK, "REGRAS:");
            desenhaTexto(gl, 230, 620, Color.BLACK, "Para jogar use as teclas da seta esquerda (para andar a esquerda) e a seta direita (para andar a direita) do teclado!");
            desenhaTexto(gl, 720, 590, Color.BLACK, "Para PAUSAR o jogo aperte a tecla P!");
            desenhaTexto(gl, 680, 559, Color.BLACK, "Para começar o jogo aperte a tecla ENTER!");
            desenhaTexto(gl, 720, 530, Color.BLACK, "Para sair do jogo aperte a tecla ESC!");

        }else if(dandoPlay && vidas != 0){
            bola0(gl,glut);
            vida1(gl,glut);
            vida2(gl,glut);
            vida3(gl,glut);
            vida4(gl,glut);
            vida5(gl,glut);
            borda(gl,glut);
            faixaCentro(gl, glut);
            faixaDireita(gl,glut);
            faixaEsquerda(gl,glut);
            campo(gl,glut);
            tri(gl,glut);
            barrinha(gl, glut);
            movimentacaoDaBarrinha();
            desenhaTexto1(gl, 30, 1000, Color.BLACK, "LEVEL " + fase);
            desenhaTexto1(gl, 1600, 1000, Color.BLACK, "SCORE " + placar);

            if (vidas!= 0) {
                bola0(gl, glut);
                movimentarBola0();

                barrinha(gl, glut);
                movimentacaoDaBarrinha();

                if (fase >= 2) {
                    //obstaculo(gl, glut);
                    //colisaoObstaculo();
                }
            }

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
