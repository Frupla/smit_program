public class Complex {
	private double real;
	private double imaginary;
	private double magnitude;
	private double phase;
	public Complex(){
		real = 0;
		imaginary = 0;
		magnitude = 0;
		phase = 0;
	}
	public Complex(double mag, double ang){
		magnitude = mag;
		phase = ang*(Math.PI/180.0);
		real = magnitude*Math.cos(phase);
		imaginary = magnitude*Math.sin(phase);
	}	
	private void updateMagAndPhase(){
		magnitude = getMagnitude();
		phase = getPhase();
	}

	public void setRealAndImaginary(double re, double im){
		real = re;
		imaginary = im;
		magnitude = Math.sqrt(re*re+im*im);
		phase = Math.atan2(im,re);
	}

	public void setMagnitude(double mag){
		magnitude = mag;
		real = magnitude*Math.cos(phase);
		imaginary = magnitude*Math.sin(phase);
	}

	public double getReal(){
		return real;
	}
	public double getImaginary(){
		return imaginary;
	}
	public double getMagnitude(){
		return magnitude;
	}
	public double getPhase(){
		return (180.0/Math.PI)*phase;
	}
	public Complex(Complex z){
		magnitude = z.getMagnitude();
		phase = z.getPhase();
		real = getReal();
		imaginary = getImaginary();
	}

	public Complex plus(Complex other){
		return new Complex(real + other.getReal(),imaginary + other.getImaginary());
	}
	public Complex times(Complex other){
		return new Complex(magnitude*(other.magnitude),phase + (other.phase));
	}
	public Complex times(double other){
		return new Complex(magnitude*other,getPhase());
	}
	public String toString(){
		return real + "+" + imaginary + "i";
	}

}
