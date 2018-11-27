/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2projeto;

import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.media.opengl.GL2;

public class Game {

    private Player p1;
    private Player p2;
    private Bola b;

    private boolean fimDeJogo;
    private boolean gol;
    private int golsP1;
    private int golsP2;
    private int aux;
    private Random r;

    public Game() {
        golsP1 = 0;
        golsP2 = 0;
        aux = 0;
    }

    public void initGame() {
        
        r = new Random();
        
        double inc = r.nextDouble();
        
        if(inc < 0.02){
            inc = 0.02;
        }
        else if(inc > 0.05){
            inc = 0.05;
        }
        
        p1 = new Player(2, 2);
        p2 = new Player(9, 2);
        b = new Bola(5, 2);

        if (aux == 0) {
            b.setIncX(inc);
        } else if (aux == 1) {
            b.setIncX(inc * -1);
        }
        
        b.setIncY(0.02);
        
        fimDeJogo = false;
        gol = false;
    }

    public void update(GL2 gl, GLUT glut) {
        if (gol) {
            initGame();
        } else {
            ajustarCamera(gl);
            desenhaTabuleiro(gl, glut);
            Draw(gl, glut);
            Move();
            colisaoBolaPlayer();
            colisaoParede();
            verificaGol();
            updatePontuacao();
        }
    }

    private void ajustarCamera(GL2 gl) {
        gl.glLoadIdentity();
        gl.glTranslated(-2.75, -1, -5);//-2.75 -1 -5
        gl.glRotated(-30, 1, 0, 0);
    }

    private void desenhaTabuleiro(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(150, 75, 0);
        gl.glScaled(5, 5, 0);
        gl.glTranslated(0.55, 0.3, 0);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
    }
    
    private void Draw(GL2 gl, GLUT glut){
        p1.Draw(gl, glut);
        p2.Draw(gl, glut);
        b.Draw(gl, glut);
    }

    
    private void colisaoBolaPlayer() {
        if (p1.getX() + 0.5 > b.getX() && b.getX() >= 2 && b.getY() < p1.getY() + 1 && b.getY() > p1.getY()-0.5) {
            b.setIncX(b.getIncX()*-1);
        }
        if (p2.getX() - 0.5 < b.getX() && b.getX() <= 9 && b.getY() < p2.getY() + 1 && b.getY() > p2.getY()-0.5) {
            b.setIncX(b.getIncX()*-1);
        }
    }

    public void colisaoParede() {
        b.WallCollision();
        p1.WallCollision();
        p2.WallCollision();
    }

    public void setPlayerActions(boolean upleft, boolean downleft, boolean upright, boolean downright, boolean reiniciar) {

        if (upleft && !p1.isTopWall()) {
            p1.setIncY(0.06);
        } else if (downleft && !p1.isUnderWall()) {
            p1.setIncY(-0.06);
        } else {
            p1.setIncY(0);
        }

        if (upright && !p2.isTopWall()) {
            p2.setIncY(0.06);
        } else if (downright && !p2.isUnderWall()) {
            p2.setIncY(-0.06);
        } else {
            p2.setIncY(0);
        }
    }

    public void Move() {
        b.Move();
        p1.Move();
        p2.Move();
    }

    public void updatePontuacao() {
        TextRenderer text = new TextRenderer(new Font("Verdana", Font.BOLD, 30));
        text.beginRendering(700, 700);
        text.setColor(Color.WHITE);
        text.draw("Player 1 vs Player 2", 225, 650);
        text.draw(golsP1 + " : " + golsP2, 325, 600);
        text.endRendering();
    }

    private void verificaGol() {
        if (b.getX() < 0) {
            gol = true;
            golsP2++;
            aux = 0;
        }
        if (b.getX() > 11) {
            gol = true;
            golsP1++;
            aux = 1;
        }
    }
}
