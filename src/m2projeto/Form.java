package m2projeto;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class Form
        implements GLEventListener, KeyListener {

    GLU glu = new GLU();
    GLUT glut = new GLUT();
    GL2 gl;
    
    private boolean upleft;
    private boolean downleft;
    private boolean upright;
    private boolean downright;
    private boolean reiniciar;

    Game game = new Game();

    public static void main(String args[]) {
        new Form();
    }

    public Form() {
        GLJPanel canvas = new GLJPanel();
        canvas.addGLEventListener(this);

        JFrame frame = new JFrame("");
        frame.setSize(700, 700);
        frame.getContentPane().add(canvas);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                }).start();
            }
        });

        frame.addKeyListener(this);
    }

    public void init(GLAutoDrawable glAuto) {
        Animator a = new Animator(glAuto);
        a.start();
        
        game.initGame();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
    }
    @Override
    public void display(GLAutoDrawable glAuto) {
        GL2 gl = glAuto.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        game.update(gl, glut);
        game.setPlayerActions(upleft, downleft, upright, downright, reiniciar);
    }

    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) {
        GL2 gl = gLAutoDrawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 1, 30);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0, 0, -5);
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {

    }

    public void dispose(GLAutoDrawable glad) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upright = true;
        }

        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            downright = true;
        }

        if (ke.getKeyCode() == KeyEvent.VK_Q) {
            upleft = true;
        }

        if (ke.getKeyCode() == KeyEvent.VK_A) {
            downleft = true;
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_R) {
            reiniciar = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upright = false;
        }

        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            downright = false;
        }

        if (ke.getKeyCode() == KeyEvent.VK_Q) {
            upleft = false;
        }

        if (ke.getKeyCode() == KeyEvent.VK_A) {
            downleft = false;
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_R) {
            reiniciar = false;
        }
    }

}
