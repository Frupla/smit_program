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
		real = getReal();
		imaginary = getImaginary();
	}
	private void updateMagAndPhase(){
		magnitude = getMagnitude();
		phase = getPhase();
	}
	public double getReal(){
		return magnitude*Math.cos(phase);
	}
	public double getImaginary(){
		return magnitude*Math.sin(phase);
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
		return new Complex(real * other.getReal() - imaginary * other.getImaginary(),imaginary * other.getReal() + real * other.getImaginary());
	}
	public String toString(){
		return real + "+" + imaginary + "i";
	}

}
