import java.awt.*;
import java.util.*;
import java.io.*;

public class smit{
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		plot circle = new plot();
		Complex mouse;
		Color old;
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
		    StdDraw.ORANGE,
		    StdDraw.MAGENTA,
		    StdDraw.YELLOW,
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
        StdDraw.circle(0.5,0,0.5);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(0,0,1);

        while(true){
        	if(StdDraw.mousePressed()){
        			oh = StdDraw.mouseX();
        			no = StdDraw.mouseY();
        			mouse = new Complex(Math.sqrt(oh*oh+no*no),Math.atan2(no,oh)*(180.0/Math.PI));
       				old = StdDraw.getPenColor();
        			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        			drawTextBox(0,1.2,"C: " + round100(mouse.getMagnitude()) + "∠" + round100(mouse.getPhase()));
        			StdDraw.circle(mouse.getReal(),mouse.getImaginary(),0.01);
        			StdDraw.setPenColor(old);
        	}
        	if(!done){
	        	System.out.println("What do you want to do?\n1: Draw circle\n2: Draw point\n3. Draw a line\n4: Find Wavelengths towards generator\n5: Choose points (no way back)");
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
	        			System.out.println("Real:");
		        		x = console.nextDouble();
		        		System.out.println("Imaginary:");
		        		y = console.nextDouble();
		        		old = StdDraw.getPenColor();
		        		line(x,y);
		        		StdDraw.setPenColor(old);
	        			break;
	        		case 4:
	        			System.out.println("Imaginary:");
		        		y = console.nextDouble();
		        		wavelength2generator(y);
	        			break;	
	        		case 5:
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

	public static double U(double r, double x){
		return (r*r - 1 + x*x)/((r+1)*(r+1) + x*x);
	}

	public static double V(double r, double x){
		return (2*x)/((r+1)*(r+1) + x*x);
	}

	public static void line(double r, double x){
		Complex gamma1 = new Complex();
		Complex gamma2 = new Complex();
		plot circle = new plot();
		double u = U(r,x);
		double v = V(r,x);
		double x1 = Math.sqrt(r*(r*r+x*x-2*r+1))/r;
		double x2 = -Math.sqrt(r*(r*r+x*x-2*r+1))/r;
		double u1 = U(1,x1);
		double v1 = V(1,x1);
		double u2 = U(1,x2);
		double v2 = V(1,x2);
		double xf = 0;
		double uf = 0;
		double vf = 0;
		gamma1.setRealAndImaginary(u,v);


		if(Math.atan2(v1,u1) > Math.atan2(v2,u2)){
			xf = x2;
			uf = u2;
			vf = v2;
		}else{
			xf = x1;
			uf = u1;
			vf = v1;
		}		        		
		gamma2.setRealAndImaginary(uf,vf);
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.arc(0,0,gamma1.getMagnitude(),gamma1.getPhase(),gamma2.getPhase());


		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.arc(0,0,1.2,gamma1.getPhase(),gamma2.getPhase());

		Complex forDrawing1 = new Complex(1.2, gamma1.getPhase());
		Complex forDrawing2 = new Complex(1.2, gamma2.getPhase());

		dashedLine(forDrawing1);
		dashedLine(forDrawing2);

		drawTextBox((forDrawing2.times(1.0416)).getReal(),(forDrawing2.times(1.0416)).getImaginary(), "L = " + round100((gamma2.getPhase()-gamma1.getPhase())/720.0) + "λ");
		circle = new plot(gamma1.getMagnitude(),gamma1.getPhase(),0.01,StdDraw.BLUE);
		drawCircle(circle);
		drawTextBox(1.1,0.1,"z = " + round100(r) + (x > 0 ? " + j" + round100(x) : " - j" + round100(-x)));
		circle = new plot(gamma2.getMagnitude(),gamma2.getPhase(),0.01,StdDraw.RED);
		drawCircle(circle);
		drawTextBox(1.1,-0.1, "z = " + 1 + (xf > 0 ? " + j" + round100(xf) : " - j" + round100(-xf)));
		
	}

	public static void dashedLine(Complex point){
		int n = 0;
		double spacing = 0.05;
		double length = 0.01; 
		Complex start = new Complex();
		Complex stop = new Complex();
		Color old = StdDraw.getPenColor();
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		while(spacing*n <= point.getMagnitude()){
			start = new Complex(n*spacing, point.getPhase());
			stop = new Complex(n*spacing + length, point.getPhase());
			StdDraw.line(start.getReal(),start.getImaginary(),stop.getReal(),stop.getImaginary());
			n++;
		}
		StdDraw.setPenColor(old);

	}

	public static void wavelength2generator(double x){
		Complex gamma = new Complex();
		double u = U(0,x);
		double v = V(0,x);
		Complex forPoint = new Complex();
		plot point;

		gamma.setRealAndImaginary(u,v);
		dashedLine(gamma.times(1.2));

		drawTextBox(gamma.times(1.25).getReal(),gamma.times(1.25).getImaginary(), "L = " + round100((180.0-gamma.getPhase())/720.0) + "λ");

		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.arc(0,0,1.2,gamma.getPhase(),180);
		double a = 0.01;
		int i = 0;
			
		while(1-i*a > 0){
			forPoint.setRealAndImaginary(U(1-a*i,x),V(1-a*i,x));
			point = new plot(forPoint.getMagnitude(),forPoint.getPhase(),0.002,StdDraw.LIGHT_GRAY);
			drawCircle(point);
			i++;
		}

		forPoint.setRealAndImaginary(U(1,x),V(1,x));
		point = new plot(forPoint.getMagnitude(),forPoint.getPhase(),0.01,StdDraw.RED);
		drawCircle(point);
		

		
	}
}