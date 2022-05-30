package com.frughtlupes.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;
import com.frughtlupes.system.*;

public class MainWindow extends JFrame {
		
    public MainWindow() {
        super("Test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(16*(16*2),15*(16*2));
        setResizable(false);
        GameScreen gc = new GameScreen();
        add(gc);
        gc.init();
        setVisible(true);
        while(true) {
        	 try {
        		 gc.update();
        		 //System.out.println("update");
                 Thread.sleep(1000/15);
             } catch(InterruptedException e){
                 e.printStackTrace();                 
             }
        }
    }
    
}
