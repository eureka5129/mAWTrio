package com.frughtlupes.system;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import com.frughtlupes.system.terrain.Terrain;

public class GameScreen extends JComponent{
	
	private Terrain terrain;
	
	public void init() {
		terrain = new Terrain("map.json");
	}
	
	public void update() {
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		terrain.update(2, g2d);
	}
}
