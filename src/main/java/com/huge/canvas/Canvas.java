package com.huge.canvas;

import java.util.Arrays;

/**
 * Defines all services needed to implement a Canvas Application
 * @author Julian Gomez <madknight@gmail.com>
 */
public class Canvas {

    private static final char LIMIT_HOR_CHAR = '-';
    private static final char LIMIT_VER_CHAR = '|';
    private static final char LINECHAR = 'x';

    private int sizeX;
    private int sizeY;
    private char[][] canvas;

    public Canvas() { 
    }

    /**
     * Returns the width of the Canvas
     * @return 
     */
    public int getSizeX() {
        return sizeX;
    }
    
    /**
     * Returns the height of the Canvas
     * @return 
     */
    public int getSizeY() {
        return sizeY;
    }

    public char[][] getCanvas() {
        return canvas;
    }
    /**
     * Constructor
     * @param x canvas width
     * @param y canvas height
     */
    public Canvas(int x, int y){
        sizeX = x; sizeY = y;
        canvas = new char[x+1][y+1];
        for(int i=0; i< x+1; i++){
            Arrays.fill(canvas[i], ' ');
        }
    }
    
    /**
     * Draws a line on Canvas (Horizontal or Vertical)
     * @param points [x1, y1, x2, y2]
     * @return true if the line could be drawn
     */
    
    public boolean drawLine(int[] points){
        
        if(CanvasUtil.isOutOfRange(points[0], points[1], sizeX, sizeY) || 
                CanvasUtil.isOutOfRange(points[2], points[3], sizeX, sizeY)) {
            return false;
        }
        //If line is horizontal
        if(points[1] == points[3]) { 
            for(int i= points[0]; i<= points[2]; i++) {
                canvas[i][points[1]] = LINECHAR;
            }
        // Vertical Line
        } else if(points[0] == points[2]) { 
            for(int j = points[1]; j<= points[3]; j++) {
                canvas[points[0]][j] = LINECHAR;
            }
        } else {
            return false;
        }
        return true;
    }
    
    
    /**
     * Draw a rectangle on the Canvas
     * @param points [x1, y1, x2, y2]
     * @return true if the rectangle could be drawn
     */
    public boolean drawRectangle(int[] points){
        if (points[1] >= points[3] || points[0] >= points[2] 
            || CanvasUtil.isOutOfRange(points[0], points[1], sizeX,sizeY)
            || CanvasUtil.isOutOfRange(points[2], points[3], sizeX,sizeY)) 
        {
            return false;
        }
        drawLine(new int[] {points[0], points[1], points[0], points[3]} ); /* Left */
        drawLine(new int[] {points[0], points[1], points[2], points[1]} ); /* Top */
        drawLine(new int[] {points[2], points[1], points[2], points[3]} ); /* Right */
        drawLine(new int[] {points[0], points[3], points[2], points[3]} ); /* Bottom */
        return true;
    }
    
    /**
     * Fill the entire area connected to x,y with colour 'colour'
     * @param x X-dimendion
     * @param y Y-dimension
     * @param colour Character with it will be filled
     * @return if bucket fill could be drawn
     */
    public boolean bucketFill(int x, int y, char colour){
        if(CanvasUtil.isOutOfRange(x,y,sizeX,sizeY) || 
                CanvasUtil.isPositionDrawn(canvas,x,y)) {
            return false;
        }
        canvas[x][y] = colour;
        bucketFill(x+1, y, colour);
        bucketFill(x-1, y, colour);
        bucketFill(x, y+1, colour);
        bucketFill(x, y-1, colour);
        return true;
    }
    
    /**
     * Creates a string representation of the canvas drawing
     * @return string
     */
    public String printCanvas() {
        StringBuilder canvasStr = new StringBuilder();
        for (int y = 0; y <= getSizeY()+1 ; y++) {
            for (int x = 0; x <= getSizeX()+1 ; x++) {
                if (y == 0 || y == getSizeY()+1) {
                    canvasStr.append(LIMIT_HOR_CHAR);
                } else if (x == 0 || x == getSizeX()+1) {
                    canvasStr.append(LIMIT_VER_CHAR);
                } else if (!CanvasUtil.isOutOfRange(x, y, sizeX, sizeY) && 
                        CanvasUtil.isPositionDrawn(canvas, x, y)) {
                    canvasStr.append(canvas[x][y]);
                } else {
                    canvasStr.append(" ");
                }
            }
            canvasStr.append("\n");
        }
        return canvasStr.toString();
    }
}
