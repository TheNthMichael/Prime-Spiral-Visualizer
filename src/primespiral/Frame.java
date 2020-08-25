/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primespiral;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author miryn
 */
public class Frame extends JFrame {
    //Data
    private int screenX;
    private int screenY;
    private int canvasX;
    private int canvasY;
    private Canvas canvas;
    
    public Frame(int init_sx,  int init_sy, Canvas c) {
        super();        
        screenX = init_sx;
        screenY = init_sy;
        canvasX = screenX;
        canvasY = screenY;
        this.setTitle("Prime Spiral");
        this.setBounds(0, 0, canvasX, canvasY);
        this.setVisible(true);
        this.setFocusable(true);
        this.addMouseMotionListener(canvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = c;
        setLayout(null);
        add(canvas);
    }
    //Getters/Setters
    public Canvas getCanvas() {
        return canvas;
    }
    //Public Methods
    @Override
    public void paint(Graphics g) {
        super.paint(g);        
        g.dispose();
    }
    
    //Private Methods
}
