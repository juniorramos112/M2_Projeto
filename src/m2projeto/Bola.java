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
public class Bola {

    private double x, y;
    private double incX, incY;

    public Bola(double x, double y) {
        this.x = x;
        this.y = y;
        incX = 0;
        incY = 0;
    }

    public void Draw(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glScaled(0.5, 0.5, 0.5);
        gl.glTranslated(x, y, 0.5);
        gl.glColor3f((float) 2, 0, (float)2);
        glut.glutSolidSphere((float) 0.2, 10, 10);
        gl.glPopMatrix();
    }

    public void Move() {
        x += incX;
        y += incY;
    }

    public void WallCollision() {
        if (y > 7.5 || y < -1.7) {
            incY *= -1;
        }
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
}
