/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2projeto;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;

/**
 *
 * @author jrram
 */
public class Player {

    private double x, y;
    private double incX, incY;
    private boolean underWall;
    private boolean topWall;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        incX = 0;
        incY = 0;
        underWall = false;
        topWall = false;
    }

    public void Draw(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glScaled(0.5, 0.5, 0.5);
        gl.glTranslated(x,y, 0.5);
        gl.glColor3f((float) 0.10, (float) 0.4, (float) 0.16);
        glut.glutSolidCube((float) 0.5);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glScaled(0.5, 0.5, 0.5);
        gl.glTranslated(x,y+0.5, 0.5);
        gl.glColor3f((float) 0.17, (float) 0.14, (float) 0.56);
        glut.glutSolidCube((float) 0.5);
        gl.glPopMatrix();
    }

    public void Move() {
        y += incY;
    }

    public void WallCollision() {
        topWall = y >= 7;

        underWall = y < -1.8;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isUnderWall() {
        return underWall;
    }

    public void setUnderWall(boolean underWall) {
        this.underWall = underWall;
    }

    public boolean isTopWall() {
        return topWall;
    }

    public void setTopWall(boolean topWall) {
        this.topWall = topWall;
    }

}
