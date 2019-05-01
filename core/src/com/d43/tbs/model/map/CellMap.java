package com.d43.tbs.model.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.d43.tbs.control.MapHandler;
import com.d43.tbs.model.GameObject;
import com.d43.tbs.model.unit.Unit;

public class CellMap extends GameObject{
	
	private Cell[][] map;
	private int rows, cols;
	
	private Array<Unit> choosingUnits;
	
	private TextureAtlas textureAtlas;
	
	public CellMap(TextureAtlas textureAtlas, TextureRegion textureRegion, float x, float y, float width, float height) {
		super(textureRegion, x, y, width, height);
		
		this.textureAtlas = textureAtlas;
		
		this.choosingUnits = new Array<Unit>();
	}
	
	public void initCells(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		map = new Cell[this.rows][this.cols];
		
		Array<TextureRegion> regions = new Array<TextureRegion>(); 
		regions.add(this.textureAtlas.findRegion("cell"));
		regions.add(this.textureAtlas.findRegion("cellMouseOn"));
		regions.add(this.textureAtlas.findRegion("cellMouseOnBlocked"));
		
		int multiplier = 80;
		
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++) {
//				Cell cell = new Cell(regions, (-8 + i*0.99f)*multiplier, (-3.5f + j*0.6f)*multiplier, 1f * multiplier, 0.625f * multiplier);
				Cell cell;
//				if(j % 2 == 0)
//					cell = new Cell(regions, (-8 + i*0.99f)*multiplier+multiplier/2, (-3.5f + j*0.6f)*multiplier, 1f * multiplier, 0.625f * multiplier);
//				else 
//					cell = new Cell(regions, (-8 + i*0.99f)*multiplier, (-3.5f + j*0.6f)*multiplier, 1f * multiplier, 0.625f * multiplier);
//				cell = new Cell(regions, (-8 + i*0.99f)*multiplier, (-4.3f + j*0.6f)*multiplier, 1f * multiplier, 0.625f * multiplier);
//				cell = new Cell(regions, (-8 + i*1f)*multiplier, (-4.3f + j*0.625f)*multiplier, 1f * multiplier, 0.625f * multiplier);
//				cell = new Cell(regions, (-8 + i*1f)*multiplier, (-4.5f + j*0.625f)*multiplier, 1f * multiplier, 0.625f * multiplier);
				cell = new Cell(regions, (-8 + i*1f)*multiplier, (-4.4f + j*0.625f)*multiplier, 1f * multiplier, 0.625f * multiplier);
				map[i][j] = cell;
			}
	}
	
	public void addChoosingUnit(Unit unit) {
		this.choosingUnits.add(unit);
	}
	
	public void reDrawCells() {
		
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getCols() {
		return this.cols;
	}
	
	public void setMapHandler(MapHandler mapHandler) {
		for(int i = 0; i < this.rows; i++)
			for(int j = 0; j < this.cols; j++)
				map[i][j].setMapHandler(mapHandler);
	}
	
	public void placeUnits(Array<Unit> units) {
//		for(int i = 0; i < this.cols; i++)
//			for(int j = 0; j < this.rows; j++)
//				cells[i][j].getController().setUnitOn(-1);
		
//		for(int i = 0; i < units.size; i++)
//			units.get(i).getCell().getController().setUnitOn(units.get(i).getId());
				
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
//		for(int i = 0; i < this.rows; i++)
//			for(int j = 0; j < this.cols; j++)
//				map[i][j].draw(batch);
		
		for(int i = 0; i < this.rows; i++)
			for(int j = this.cols-1; j >= 0; j--)
				map[i][j].draw(batch);
	}
	
	public Cell[][] getCells() {
		return map;
	}
	
	public Cell getCell(int x, int y) {
		return map[x][y];
	}
	
//	public Cell findCell(Vector2 location) {
//		for(int i = 0; i < this.rows; i++)
//			for(int j = 0; j < this.cols; j++)
//				if(cells[i][j].getController().isBelong(location.x, location.y))
//					return cells[i][j];
//		return null;
//	}
//	
//	public String findCellString(Vector2 location) {
//		for(int i = 0; i < this.rows; i++)
//			for(int j = 0; j < this.cols; j++)
//				if(cells[i][j].getController().isBelong(location.x, location.y))
//					return new String(Integer.toString(i)+" : "+Integer.toString(j));
//		return "";
//	}
	
	public boolean cellExist(Polygon bounds) {
		for(int i = 0; i < this.rows; i++)
			for(int j = 0; j < this.cols; j++)
				if(map[i][j].getBounds() == bounds)
					return true;
		return false;
	}
	
	public Cell findCell(Polygon bounds) {
		for(int i = 0; i < this.rows; i++)
			for(int j = 0; j < this.cols; j++)
				if(map[i][j].getBounds() == bounds)
					return map[i][j];
		return null;
	}
	
	public Vector2 findCellCoord(Polygon bounds) {
		for(int i = 0; i < this.rows; i++)
			for(int j = 0; j < this.cols; j++)
				if(map[i][j].getBounds() == bounds)
					return new Vector2(i, j);
		return null;
	}
	
	public String findCellString(Polygon bounds) {
		for(int i = 0; i < this.rows; i++)
			for(int j = 0; j < this.cols; j++)
				if(map[i][j].getBounds() == bounds)
					return new String(Integer.toString(i) + " : " + Integer.toString(j));
		return ":";
	}
	
	public void returnMap() {
		String str = "";
		for(int i = this.cols-1; i >= 0; i--) {
			for(int j = 0; j < this.rows; j++) {
				str += map[j][i].getUnit() != null ? Integer.toString(map[j][i].getUnit().getId()) : "*";
			}
			Gdx.app.log("tag", str);
			str = "";
		}
	}
}
