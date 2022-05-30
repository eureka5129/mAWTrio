package com.frughtlupes.system.terrain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Terrain {

    private Tile[][] tiles = null;
    
    private int x;
    
    public Terrain(String jsonPath){
    	x = 0;
    	try {
			StringBuffer sb = new StringBuffer();
			InputStream in = ClassLoader.getSystemResourceAsStream("data/maps/" + jsonPath);
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			br.close();
			in.close();
			String json = sb.toString();
			JSONObject jsonObj = new JSONObject(json);
			int aryWidth = jsonObj.getInt("width");
			int aryHeight = jsonObj.getInt("height");
			System.out.println(aryHeight + "x" + aryWidth + "loading...");
			tiles = new Tile[aryHeight][aryWidth];
			for(int i = 0; i < aryHeight; i++) {
				for(int j = 0; j < aryWidth; j++) {
					String tileId = jsonObj.getJSONArray("tiles").getJSONArray(i).getString(j);
					String tileSkin = jsonObj.getJSONObject("keys").getString(tileId);
					tiles[i][j] = new Tile(tileSkin);
					System.out.println(":" + i + "," + j);
					System.out.println(tiles[i][j].getSkin());
				}
			}
			System.out.println("LOADED!!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
   
    public void update(int addX, Graphics2D G2D) {
    	this.x += addX;
		Graphics2D g2d = G2D;
		g2d.setBackground(new Color(146, 148, 255));
		int firstTile = (int) Math.floor(x/32);
		int XDefer = x % 32;
		for(int i = 0; i < 15; i++) {
			for(int j = firstTile; j < (firstTile+17); j++) {
				if(tiles.length > i) {
					if(tiles[i].length > j) {
						g2d.drawImage(getImage(tiles[i][j].getSkin()), null, ((j-firstTile)*32)-XDefer, ( (14-tiles.length)*32+4 + i*32 ) );
					}
				}
			}
		}
	}
    
	private BufferedImage getImage(String str) {
    	InputStream in = ClassLoader.getSystemResourceAsStream("images/tiles/" + str);
		try {
			BufferedImage image = ImageIO.read(in);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
