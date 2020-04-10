import java.awt.*;
import java.util.*;
	
public class plot{
	public Complex number; 
	public double r;
	public Color c;
	public plot(){
		r = 0;
		c = StdDraw.BLACK;
	}
	public plot(double i, double j, double l, Color k){
		number = new Complex(i,j);
		r = l;
		c = k;
	}

	public double getMagnitude(){
		return number.getMagnitude();
	}

	public double getPhase(){
		return number.getPhase();
	}

}