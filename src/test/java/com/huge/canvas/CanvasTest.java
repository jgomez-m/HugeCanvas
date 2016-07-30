
package com.huge.canvas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests all public methods
 * Canvas Test Class 
 * @author Julian Gomez
 */
@RunWith(MockitoJUnitRunner.class)
public class CanvasTest{
    
    private Canvas canvas;
    
    public CanvasTest() {
    }
    
   
    @Before
    public void setUp(){
        canvas = new Canvas(20, 4);
    }
    
    @Test
    public void testDrawLineSuccess(){
        int[] points = new int[] { 1,2,6,2 };
        
        //Then
        assertTrue(canvas.drawLine(points));
        points = new int[] {6,3,6,4};
        assertTrue(canvas.drawLine(points));
    }
    
    @Test
    public void testDrawLineFail(){
        int[] points = points = new int[] {6,3,5,4};
        assertFalse(canvas.drawLine(points));
        points = new int[] {1,1,3,3};
        assertFalse(canvas.drawLine(points));
    }
    
    @Test
    public void testDrawRectangleSuccess(){
        int[] points = new int[] {16,1,20,3};
        assertTrue(canvas.drawRectangle(points));
        points = new int[] {19,2,20,3};
        assertTrue(canvas.drawRectangle(points));
    }
    
    @Test
    public void testDrawRectangleFail(){
        int[] points = new int[] {16,1,21,3};
        assertFalse(canvas.drawRectangle(points));
        points = new int[] {16,1,16,3};
        assertFalse(canvas.drawRectangle(points));
        points = new int[] {0,1,21,3};
        assertFalse(canvas.drawRectangle(points));
        points = new int[] {16,1,11,3};
        assertFalse(canvas.drawRectangle(points));
        points = new int[] {21,1,11,3};
        assertFalse(canvas.drawRectangle(points));
    }
    
    @Test
    public void testBucketFill(){
        int x = 10, y = 3;
        assertTrue(canvas.bucketFill(x, y, 'o'));
        assertFalse(canvas.bucketFill(x, y, 'o'));
        assertFalse(canvas.bucketFill(0, 0, '0'));
    }
    
    @Test
    public void testPrintCanvas(){
        assertNotNull(canvas.printCanvas());
        int[] points = new int[] { 1,2,6,2 };
        assertTrue(canvas.drawLine(points));
        points = new int[] {6,3,6,4};
        assertTrue(canvas.drawLine(points));
        //Test
        assertNotNull(canvas.printCanvas());
    }
    
    @Test
    public void testIsPositionDrawn(){
        assertFalse(CanvasUtil.isPositionDrawn(canvas.getCanvas(), 0, 0));
        canvas.drawLine(new int[] {5,1,10,1 });
        for(int i=5; i<=10; i++){
            assertTrue(CanvasUtil.isPositionDrawn(canvas.getCanvas(), i, 1));
        }
        assertFalse(CanvasUtil.isPositionDrawn(canvas.getCanvas(), 11, 1));
        canvas.drawLine(new int [] {11,1,11,1});
        assertTrue(CanvasUtil.isPositionDrawn(canvas.getCanvas(), 11, 1));
        
        assertFalse(CanvasUtil.isPositionDrawn(canvas.getCanvas(), 20, 4));
        canvas.drawLine(new int [] {20,4,20,4});
        assertTrue(CanvasUtil.isPositionDrawn(canvas.getCanvas(), 20, 4));
    }
    
    @Test
    public void testIsOutOfRange(){
        assertTrue(CanvasUtil.isOutOfRange(0, 4, canvas.getSizeX(), canvas.getSizeY()));
        assertTrue(CanvasUtil.isOutOfRange(20, 0, canvas.getSizeX(), canvas.getSizeY()));
        assertFalse(CanvasUtil.isOutOfRange(20, 4, canvas.getSizeX(), canvas.getSizeY()));
        assertTrue(CanvasUtil.isOutOfRange(20, 5, canvas.getSizeX(), canvas.getSizeY()));
        assertTrue(CanvasUtil.isOutOfRange(21, 4, canvas.getSizeX(), canvas.getSizeY()));
    }
}
