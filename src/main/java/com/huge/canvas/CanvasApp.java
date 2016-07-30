package com.huge.canvas;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Canvas Application
 * @author Julian Gomez <madknight@gmail.com>
 */
public class CanvasApp {
    
    private static final String[] COMMANDS = new String[] {"C", "L", "R", "B", "Q"};
    
    private static Canvas canvas; 
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equals("Q")){
            System.out.println("enter command: \"C\", \"L\", \"R\", \"B\", \"Q\" ");
            input = sc.nextLine();
            System.out.println(process(input));
        }
    }

    private static String process(String input) {
        
        String[] args = input.trim().split(" ");
        StringBuilder output = new StringBuilder();
        try {
            if (Arrays.asList(COMMANDS).contains(args[0]) ) {
                if (args[0].equals("Q")) {
                    output.append("Good Luck !");
                } else if (args[0].equals("C")) {
                    int width = Integer.parseInt(args[1]);
                    int height = Integer.parseInt(args[2]);
                    canvas = new Canvas(width, height);
                    output.append(canvas.printCanvas());
                } else if (canvas != null) {
                    int x1 = Integer.parseInt(args[1]);
                    int y1 = Integer.parseInt(args[2]);
                    boolean result=false;
                    switch (args[0]) {
                        case "L":
                            {
                                int x2 = Integer.parseInt(args[3]);
                                int y2 = Integer.parseInt(args[4]);
                                result = canvas.drawLine(new int[] {x1, y1, x2, y2} );
                                break;
                            }
                        case "R":
                            {
                                int x2 = Integer.parseInt(args[3]);
                                int y2 = Integer.parseInt(args[4]);
                                result = canvas.drawRectangle(new int[] {x1, y1, x2, y2});
                                break;
                            }
                        case "B":
                            result = canvas.bucketFill(x1, y1, args[3].charAt(0));
                            break;
                    }
                    output.append(canvas.printCanvas());
                    if(!result){
                        output.append(input+ " is Invalid Input\n");
                    }
                } else {
                    output.append("Create a canvas first ( 'C' x y )");
                }
            } else {
                output.append("Command ").append(args[0]).append(" is not available");
            }
        } catch (Exception ex){
            System.err.println("Command Invalid, Try Again");
        }
        output.append("\n");
        return output.toString();
    }
    
}
