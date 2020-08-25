/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primespiral;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.math.BigInteger;
import java.util.*;
import javax.swing.JPanel;

/**
 *
 * @author miryn
 */
public class Canvas extends JPanel implements MouseMotionListener {
    //Data
    private int canvasX, canvasY;
    ArrayList<BigInteger> primes;
    ArrayList<Point> polarPoints;
    Point origin;
    int size;
    double dist;
    double prev_dist;
    
    
    public Canvas(int init_x, int init_y, ArrayList<BigInteger> primes) {
        canvasX = init_x;
        canvasY = init_y;
        this.primes = primes;
        this.polarPoints = new ArrayList<>();
        origin = new Point(init_x/2, init_y/2);
        dist = 1;
        prev_dist = 1;
        Point p;
        int x, y;
        for(BigInteger b : primes) {
            p = getPolarCoordinates(b.intValue());
            polarPoints.add(p);
            System.out.println("x: " + p.x + " y: " + p.y);
        }
        size = 4;
 
        //Canvas Stuff
        addMouseMotionListener(this);
        setLayout(null);
        setBounds(0, 0, canvasX, canvasY);
        setFocusable(true);
        setVisible(true);
        setRequestFocusEnabled(true);
        requestFocus();
        //addKeyListener(this);
    }
    //Getters/Setters
    public void setCanvasX(int newSize) {
        if(newSize >= 0)
            canvasX = newSize;        
    }
    public int getCanvasX() {
        return canvasX;
    }
    public void setCanvasY(int newSize) {
        if(newSize >= 0)
            canvasY = newSize;
    }
    public int getCanvasY() {
        return canvasY;
    }
    //Public Methods
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(40, 40, 40));
        g2d.fillRect(0, 0, canvasX, canvasY);
        g2d.setColor( new Color(52, 224, 187));
        for(Point p : polarPoints) {
            g2d.fillOval(p.x, p.y, size, size);
        }
    
        g.dispose();
    }
    //Private Methods
    
    //Event Handler -> Throw into another class?

    private Point getPolarCoordinates(int r) {
        int x = (int)(r*(Math.cos(r)) - (Math.sin(r)));
        int y = (int)((r*Math.sin(r)) - (Math.cos(r)));
        x /= dist;
        y /= dist;
        x += origin.x;
        y += origin.y;
        return new Point(x,y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        dist = Math.sqrt( (x-origin.x)*(x-origin.x) + (y-origin.y)*(y-origin.y) );
        
        if(dist < 1)
            dist = 1;
        if(prev_dist < 1)
            prev_dist = 1;
        System.out.println("DIST: " + dist);
        polarPoints = new ArrayList<>();
        Point p;
        size = (int)map((long)dist, 1920, 0, 1, 5);
        
        for(BigInteger b : primes) {
            p = getPolarCoordinates(b.intValue());
            polarPoints.add(p);
        }
        prev_dist = dist;
        this.repaint();
    }
    
    long map(long x, long in_min, long in_max, long out_min, long out_max) {
        x = in_max - x;
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }


}