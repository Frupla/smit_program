import java.awt.*;
import java.util.*;
import java.io.*;

public class smit{
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		plot circle = new plot();
		Complex mouse;
		double oh = 0, no = 0;
		boolean done = false;
		double x = 0;
		double y = 0;
		double r = 0;
		int i = 0;
		int j = 0;

		Color[] colorArray = {
			StdDraw.BLUE,
		    StdDraw.RED,
		    StdDraw.GREEN,
		    StdDraw.YELLOW,
		    StdDraw.MAGENTA,
		    StdDraw.ORANGE,
		    StdDraw.CYAN
		};

		StdDraw.setCanvasSize(520,520);
		StdDraw.setXscale(-2, 2);
        StdDraw.setYscale(-2, 2);
        StdDraw.setPenRadius(0.0025);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.arc(1,-1,1,90,180);
        StdDraw.arc(1, 1,1,180,-90);
        StdDraw.line(0,1,0,-1);
        StdDraw.line(1,0,-1,0);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(0,0,1);

        while(true){
        	if(StdDraw.mousePressed()){
        			oh = StdDraw.mouseX();
        			no = StdDraw.mouseY();
        			mouse = new Complex(Math.sqrt(oh*oh+no*no),Math.atan2(no,oh)*(180.0/Math.PI));
       				Color old = StdDraw.getPenColor();
        			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        			drawTextBox(0,1.2,"C: " + round100(mouse.getMagnitude()) + "∠" + round100(mouse.getPhase()));
        			StdDraw.circle(mouse.getReal(),mouse.getImaginary(),0.01);
        			StdDraw.setPenColor(old);
        	}
        	if(!done){
	        	System.out.println("What do you want to do?\n1: Draw circle\n2: Draw point\n3: Choose points (no way back)");
	        	switch(console.nextInt()){
	        		case 1:
		        		System.out.println("Magnitude:");
		        		x = console.nextDouble();
		        		System.out.println("Phase:");
		        		y = console.nextDouble();
		        		System.out.println("Radius:");
		        		r = console.nextDouble();
		        		circle = new plot(x,y,r,colorArray[i]);
		        		drawCircle(circle);
		        		drawTextBox(-1.8,-1.2-i*0.1,"C: " + round100(circle.getMagnitude()) + "∠" + round100(circle.getPhase()) + ",  R: " + round100(circle.r));
		        		i++;
		        		if(i >= colorArray.length){
		        			i = 0;
		        		}
		        		break;
	        		case 2:
	        			System.out.println("Magnitude:");
		        		x = console.nextDouble();
		        		System.out.println("Phase:");
		        		y = console.nextDouble();
		        		circle = new plot(x,y,0.01,colorArray[colorArray.length-1-j]);
		        		drawCircle(circle);
		        		drawTextBox(0,-1.2-j*0.1,"C: " + round100(circle.getMagnitude()) + "∠" + round100(circle.getPhase()));
		        		j++;
		        		if(j >= colorArray.length){
		        			j = 0;
		        		}
		        		break;
	        		case 3:
	        			done = true;
	        			break;	
	        		default:
		        		break;
	        	}
	    	}
		}
	}
        


	public static void drawTextBox(double x, double y, String s){
		double halfwidth = (s.length()*0.03);
		double halfheight = 0.035;
		Color old = StdDraw.getPenColor();
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(x+2*halfwidth,y,2*halfwidth,2*halfheight);
		StdDraw.setPenColor(old);        		
		StdDraw.textLeft(x,y,s);
	}

	public static double round100(double x){
    		return Math.round(100.0*x)/100.0;
    }
	
	public static void drawCircle(plot thisGuy){
		Complex z = thisGuy.number;
		double x = z.getReal();
		double y = z.getImaginary();
		
		StdDraw.setPenColor(thisGuy.c);
        StdDraw.circle(x,y,(thisGuy.r));
	}

}