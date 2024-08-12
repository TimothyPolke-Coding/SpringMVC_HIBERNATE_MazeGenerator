package com.timothypolke.puzzle;

import java.io.IOException;
import java.security.SecureRandom;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class Puzzle extends BufferedImage{
	private int xCells=0;
	private int yCells=0;
	private int boundarySize=0;
	private int cellSize=0;
	
	private Graphics2D graphics=null; //graphics
	
	private String solved=null;
	private String unsolved=null;
	
	//BEGIN creation variables
	private Block[][] verticalWalls=null;
	private Block[][] horizontalWalls=null;
	private Block[][] cells=null;
	//END creation variables
	
	//BEGIN solution variables
	private ArrayList<String> directions=null;
	private ArrayList<String> route=null;
	private ArrayList<Block> history=null;
	private int x=0;
	private int y=0;
	//END solution variables
	
	public Puzzle(int xCells, int yCells, int boundarySize, int cellSize, Color foreground, Color background, Color highlight){
		super((xCells*cellSize)+((xCells*boundarySize)+boundarySize),(yCells*cellSize)+((yCells*boundarySize)+boundarySize),BufferedImage.TYPE_INT_RGB);
		
		this.xCells=xCells;
		this.yCells=yCells;
		this.boundarySize=boundarySize;
		this.cellSize=cellSize;
	
		graphics=createGraphics();
		
		create();
		
		solve();
		
		redraw(foreground, background, highlight);
		solved = Base64.getEncoder().encodeToString(processImage());
			
		redraw(foreground, background, background);
		unsolved = Base64.getEncoder().encodeToString(processImage());
	}
	
	public String getSolved(){
		return solved;
	}
	
	public String getUnsolved(){
		return unsolved;
	}
	
	public void create(){
		verticalWalls=new Block[yCells][xCells+1];
		horizontalWalls=new Block[yCells+1][xCells];
		cells=new Block[yCells][xCells];
		
		ArrayList<Block> visitedCells=new ArrayList();

		createVerticalWalls(xCells+1,yCells);
		createHorizontalWalls(xCells,yCells+1);
		createCells();
		
		visitedCells.add(cells[0][0]);
		while(determinePresenceOfClosedCells()==true){
			chooseDirection(visitedCells.get(visitedCells.size()-1).getColumnID(),visitedCells.get(visitedCells.size()-1).getRowID(),visitedCells,populateDirections());
		}	
	}
	
	public void solve(){
		directions=new ArrayList();
		directions.add("N");
		directions.add("S");
		directions.add("W");
		directions.add("E");
		
		history=new ArrayList();
		history.add(cells[0][0]);
		
		route=new ArrayList();

		while(traverseVisited(yCells-1,xCells-1)!=true)
		{
			if(forwardMotion(x, y-1, x, y, 0)==false || forwardMotion(x, y+1, x, y+1, 1)==false || forwardMotion(x-1, y, x, y, 2)==false || forwardMotion(x+1, y, x+1, y, 3)==false){
			}
			else if(backwardMotion(x, y-1, 1)==true || backwardMotion(x, y+1, 0)==true || backwardMotion(x-1, y, 3)==true || backwardMotion(x+1, y, 2)==true){
			}
		}
	}

	public void createVerticalWalls(int xVerticalWalls,int yVerticalWalls){
		for(int iy=0;iy<yVerticalWalls;iy++){
			for(int ix=0;ix<xVerticalWalls;ix++){
				verticalWalls[iy][ix]=new Block(iy,ix,ix*cellSize+ix*boundarySize,iy*cellSize+iy*boundarySize+boundarySize,false);
			}
		}
	}
	
	public void createHorizontalWalls(int xHorizontalWalls,int yHorizontalWalls){
		for(int iy=0;iy<yHorizontalWalls;iy++){
			for(int ix=0;ix<xHorizontalWalls;ix++){
				horizontalWalls[iy][ix]=new Block(iy,ix,ix*cellSize+ix*boundarySize+boundarySize,iy*cellSize+iy*boundarySize,false);
			}
		}
	}
	
	public void createCells(){
		for(int iy=0;iy<yCells;iy++){
			for(int ix=0;ix<xCells;ix++){
				cells[iy][ix]=new Block(iy,ix,ix*cellSize+ix*boundarySize+boundarySize,iy*cellSize+iy*boundarySize+boundarySize,true);
			}
		}
	}
	
	public void chooseDirection(int posX,int posY,ArrayList<Block> visitedCells,ArrayList<Integer> uncheckedDirections){
		Random rand=new SecureRandom();
		int direction=0;
		int xChange=0;
		int yChange=0;
		int xResult=0;
		int yResult=0;
		boolean type=false;
		loop:
		while(uncheckedDirections.size()>0){
			direction=uncheckedDirections.get(rand.nextInt(uncheckedDirections.size()));
			
			if(direction==0){
				xChange=posX;
				yChange=posY-1;
				xResult=posX;
				yResult=posY;
				type=true;
			}
			else if(direction==1){
				xChange=posX;
				yChange=posY+1;
				xResult=posX;
				yResult=posY+1;
				type=true;
			}
			else if(direction==2){
				xChange=posX-1;
				yChange=posY;
				xResult=posX;
				yResult=posY;
				type=false;
			}
			else if(direction==3){
				xChange=posX+1;
				yChange=posY;
				xResult=posX+1;
				yResult=posY;
				type=false;
			}
		
			try	{
				if(checkVisitedStatus(cells[yChange][xChange],visitedCells)==false){
					openWall(xResult,yResult,type);
				}
				visitedCells.add(cells[yChange][xChange]);
				uncheckedDirections.remove(findIndexOfRandom(uncheckedDirections,direction));
				break loop;
			}
			catch(ArrayIndexOutOfBoundsException e){
			}
		}
	}

	public void openWall(int posX,int posY,boolean type){
		if(type==false){
			verticalWalls[posY][posX].setOpen(true);
		}
		if(type==true){
			horizontalWalls[posY][posX].setOpen(true);
		}
	}
	
	public boolean determinePresenceOfClosedCells(){
		boolean present=false;
		loop:
		for(int y=0;y<yCells;y++){
			for(int x=0;x<xCells;x++){
				if(checkClosedWalls(cells[y][x])==true){
					present=true;
					break loop;
				}
			}
		}
		return present;
	}
	
	public boolean checkClosedWalls(Block cell){
		boolean closed=false;
		if(horizontalWalls[cell.getRowID()][cell.getColumnID()].getOpen()==false && horizontalWalls[cell.getRowID()+1][cell.getColumnID()].getOpen()==false && verticalWalls[cell.getRowID()][cell.getColumnID()].getOpen()==false && verticalWalls[cell.getRowID()][cell.getColumnID()+1].getOpen()==false){
			closed=true;
		}
		return closed;
	}
	
	public boolean checkVisitedStatus(Block cell,ArrayList<Block> visitedCells){
		boolean result=false;
		loop:
		for(int i=0;i<visitedCells.size();i++){
			if(visitedCells.get(i).getColumnID()==cell.getColumnID() && visitedCells.get(i).getRowID()==cell.getRowID()){
				result=true;
				break loop;
			}
		}
		return result;
	}
	
	public ArrayList<Integer> populateDirections(){
		ArrayList<Integer> uncheckedDirections=new ArrayList();
		uncheckedDirections.add(0);
		uncheckedDirections.add(1);
		uncheckedDirections.add(2);
		uncheckedDirections.add(3);
		return uncheckedDirections;
	}
	
	public int findIndexOfRandom(ArrayList directions,int randInt){
		int index=0;
		loop:
		for(int i=0;i<directions.size();i++){
			if(directions.get(i).equals(randInt)){
				index=i;
				break loop;
			}
		}
		return index;
	}
	
	public boolean forwardMotion(int x1, int y1, int x2, int y2, int direction){
		boolean failed=true;
		try{
			if (direction == 0 || direction == 1){
				if(traverseVisited(y1,x1)==false && isWallOpen(horizontalWalls[y2][x2])==true){
					route.add(directions.get(direction));
					history.add(cells[y1][x1]);
					x=x1;
					y=y1;
					failed=false;
				}
			}
			else if (direction == 2 || direction == 3){
				if(traverseVisited(y1,x1)==false && isWallOpen(verticalWalls[y2][x2])==true){
					route.add(directions.get(direction));
					history.add(cells[y1][x1]);
					x=x1;
					y=y1;
					failed=false;
				}
			}
		}
		catch(IndexOutOfBoundsException e){
		}
		return failed;
	}
	
	public boolean backwardMotion(int varX, int varY, int direction){
		boolean match=false;
		if(String.valueOf(route.get(route.size()-1)).equals(directions.get(direction))){
			x=varX;
			y=varY;
			route.remove(route.size()-1);
			route.trimToSize();
			match=true;
		}
		return match;
	}
	
	public boolean isWallOpen(Block wall){
		boolean wallOpen=false;
		if(wall.getOpen()==true){
			wallOpen=true;
		}
		return wallOpen;
	}
	
	public boolean traverseVisited(int y,int x){
		boolean found=false;
		loop:
		for(int i=0;i<history.size();i++){
			if(history.get(i).getColumnID()==x && history.get(i).getRowID()==y){
				found=true;
				break loop;
			}
		}
		return found;
	}
	
	public void highlightCell(Color color,int posX,int posY,int type){
		graphics.setColor(color);
		if(type==0){
			graphics.fillRect(horizontalWalls[posY][posX].getStartX(),horizontalWalls[posY][posX].getStartY(),cellSize,boundarySize);
		}
		else if(type==1){
			graphics.fillRect(verticalWalls[posY][posX].getStartX(),verticalWalls[posY][posX].getStartY(),boundarySize,cellSize);
		}
		else if(type==2){
			graphics.fillRect(cells[posY][posX].getStartX(),cells[posY][posX].getStartY(),cellSize,cellSize);
		}
	}
	
	public void redraw(Color foreground,Color background,Color highlight){
		for(int iy=0;iy<yCells+1;iy++){
			for(int ix=0;ix<xCells;ix++){
				if(horizontalWalls[iy][ix].getOpen()==true){
					highlightCell(background,ix,iy,0);
				}
				else if(horizontalWalls[iy][ix].getOpen()==false){
					highlightCell(foreground,ix,iy,0);
				}
			}
		}
		
		for(int iy=0;iy<yCells;iy++){
			for(int ix=0;ix<xCells+1;ix++){
				if(verticalWalls[iy][ix].getOpen()==true){
					highlightCell(background,ix,iy,1);
				}
				else if(verticalWalls[iy][ix].getOpen()==false){
					highlightCell(foreground,ix,iy,1);
				}
			}
		}
		
		int currentx = 0;
		int currenty = 0;
		highlightCell(highlight,currentx,currenty,2);
		for(int i=0;i<route.size();i++){
			if(route.get(i).equals(directions.get(0))){
				currentx = currentx;
				currenty = currenty - 1;
			}
			else if(route.get(i).equals(directions.get(1))){
				currentx = currentx;
				currenty = currenty + 1;
			}
			else if(route.get(i).equals(directions.get(2))){
				currentx = currentx - 1;
				currenty = currenty;
			}
			else if(route.get(i).equals(directions.get(3))){
				currentx = currentx + 1;
				currenty = currenty;
			}
			highlightCell(highlight,currentx,currenty,2);
		}
		
		for(int iy=0;iy<yCells;iy++){
			for(int ix=0;ix<xCells;ix++){
				if(this.getRGB(cells[iy][ix].getStartX(),cells[iy][ix].getStartY())!=highlight.getRGB()){
					highlightCell(background,ix,iy,2);
				}
			}
		}
		
		drawBoundary(foreground);
		
		highlightCell(highlight,0,0,1);
		highlightCell(highlight,xCells,yCells-1,1);
	}
	
	public void drawBoundary(Color color){
		graphics.setColor(color);
		for(int iy=0;iy<=yCells;iy++){
			for(int ix=0;ix<=xCells;ix++){
				graphics.fillRect(ix*cellSize+ix*boundarySize,iy*cellSize+iy*boundarySize,boundarySize,boundarySize);
			}
		}
	}
	
	public byte[] processImage(){
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		byte[] imageInByteArray=null;
		try{
			ImageIO.write(this,"jpg",baos);
			baos.flush();
			imageInByteArray=baos.toByteArray();
			baos.close();
		}
		catch(IOException e){
		}
		return imageInByteArray;
	}
	
	public static String randomize(int length,char[] charactersAllowed){		
		SecureRandom random=new SecureRandom();
		StringBuilder code=new StringBuilder();	
		for(int i=0;i<length;i++){
			code.append(charactersAllowed[random.nextInt(charactersAllowed.length)]);
		}
		return code.toString();
	}
}
