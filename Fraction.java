import java.lang.Integer;
import java.lang.Math;
import java.lang.IllegalArgumentException;
import java.lang.ArithmeticException;
import java.util.zip.DataFormatException;

/**
* Fraction math without floating point errors! Yay! 
* @author Megan Daly
*/
public class Fraction
{	
	private int numerator, denominator;

	/**
	* Constructor declares numerator and denominator and reduces fraction. 
	* Normalizes sign. Throws
	* ArithmeticException if denominator is 0.
	* @param numerator is already an integer
	* @param denominator is already an integer
	*/
	public Fraction(int numerator, int denominator)
	{
		if(denominator != 0)
		{
			this.numerator = numerator;
			this.denominator = denominator;
			reduce();
			normalizeSign();
		}
		else
		{
			throw new ArithmeticException("No dividing by 0");
		}
	}
	
	/**
	* Constructor for when one wants to use a whole number as a fraction.
	* @param wholeNumber is an int
	*/
	public Fraction(int wholeNumber)
	{
		numerator = wholeNumber;
		denominator = 1;
	}
	
	/**
	* Constructor takes a string of format "a/b" or "a b/c" where a, b, c 
	* are ints. Will throw ArithmeticException if dividing by 0 
	* and IllegalArgumentException if not in correct format.
	* Reduces fraction. Normalizes sign.
	* @param string is the input string of form "a/b" or "a b/c"
	*/
	public Fraction(String string)
	{
		String[] pieces = string.split(" ");
		if (pieces.length == 1)
		{
			int[] frac = makeNumDenum(pieces[0]);
			numerator = frac[0];
			denominator = frac[1];
		}
		else if (pieces.length == 2)
		{
			int wholes = Integer.parseInt(pieces[0]);
			int[] frac = makeNumDenum(pieces[1]);
			if (wholes >= 0)
				numerator = wholes * frac[1] + frac[0];
			else
				numerator = wholes * frac[1] - frac[0];
			denominator = frac[1];
		}
		else 
		{
			throw new IllegalArgumentException("Fraction is not in correct format" + 
			" of:    a b/c    or    b/c");
		}
		reduce();
		normalizeSign();
	}
	/**
	* Constructor takes a double for a parameter but does not play nicely with long 
	* decimals, which tend to create large numerators and denominators, which mean
	* mathematical functions do not work correctly. Avoid when possible. 
	* @param d is a double to be converted to fraction.
	*/
	public Fraction(double d)
	{
		numerator = 0;
		denominator = 1;
		int i =0;
		while (Math.abs(d)>0 && i<9)
		{
			while (d >= 1)
			{
				d-=1;
				numerator++;
			}
			d *= 10;
			numerator *= 10;
			denominator *= 10;
			i++;
		}
		reduce();
		normalizeSign();
	}
	
	/**
	* Utilized by Fraction(String string) to convert a string of format "a/b" to 
	* an int[] of numerator and denominator. Throws ArithmeticException if dividing
	* by 0 and DataFormatException if not in correct format.
	* @param string is of form "a/b"
	* @return returns new int[] of length 2 where the first element is the
	* numerator and the second the denominator
	*/
	private int[] makeNumDenum(String string)
	{
		String[] pieces = string.split("/");
		if (pieces.length == 2)
		{
			if(Integer.parseInt(pieces[1]) != 0)
			{
				return(new int[]{Integer.parseInt(pieces[0]), 
						Integer.parseInt(pieces[1])});
			}
			else
			{
				throw new ArithmeticException("No dividing by 0");
			}
		}
		else 
		{
			throw new IllegalArgumentException("Fraction is not in correct format" + 
			" of:    a b/c    or    b/c");
		}
	}
	
	/**
	* @return numerator as int
	*/
	public int getNumerator()
	{
		return numerator;
	}
		
	/**
	* @return denominator as int
	*/
	public int getDenominator()
	{
		return denominator;
	}
	
	/**
	* @return double of numerator / double of denominator
	*/
	public double toDouble()
	{
		return (new Integer(numerator).doubleValue())/
				(new Integer(denominator).doubleValue());
	}
	
	/**
	* @return fraction as string "a/b" where a is numerator and b is denominator
	* If fraction > 1, will be improper fraction.
	*/
	public String toImproperString()
	{
		return numerator + "/" + denominator;
	}
	
	/**
	* @return fraction as string "a/b" or "a b/c" not improper fraction
	*/
	public String toString()
	{
		int[] mixed = toMixedArray();
		if (mixed[1] == 0)
			return Integer.toString(mixed[0]);
		else if (mixed[0] == 0)
			return(mixed[1] + "/" + mixed[2]);
		else
			return(mixed[0] + " " + Math.abs(mixed[1]) + "/" + mixed[2]);
	}
	
	/**
	* @return int[] of numerator and denominator, can be improper fraction
	*/
	public int[] toImproperArray()
	{
		return(new int[]{numerator, denominator});
	}
	
	/**
	* @return int[] of whole number, numerator, and denominator; if less than 1 
	* whole number is 0 
	*/
	public int[] toMixedArray()
	{
		int absNum = Math.abs(numerator);
		int  sign;
		if (numerator >= 0)
			sign = 1;
		else
			sign =-1;
		int wholes = 0;
		while (absNum>=denominator)
		{
			wholes++;
			absNum -= denominator;
		}
		return(new int[]{ wholes * sign, numerator * sign, denominator});
	}
	
	/**
	* reduces fraction to lowest terms; called by constructors.
	*/
	private void reduce()
	{
		boolean changed = false;
		for(int i = 2;
			i <= Math.min(Math.abs(numerator), Math.abs(denominator)) && changed==false;
			i++)
		{
			if(numerator % i == 0 && denominator % i == 0
				&& Math.abs(numerator) > 1 && Math.abs(denominator) > 1)
			{
				numerator /= i;
				denominator /= i;
				changed = true;
			}
			if (changed)
				reduce();
		}
	}
	
	/**
	* Makes both numerator and denominator positive if both have the same sign;
	* Makes numerator negative and denominator positive if signs differ.
	* Effect: sign always carried on numerator.
	*/
	private void normalizeSign()
	{
		if (numerator < 0 && denominator < 0)
		{
			numerator = Math.abs(numerator);
			denominator = Math.abs(denominator);
		}
		else if (numerator * denominator < 0 ) // One is negative, not both
		{
			numerator = (-1) * Math.abs(numerator);
			denominator = Math.abs(denominator);
		}
	}
	
	/**
	* If fraction is a whole number, (denominator is 1), returns int.
	* Else, throws DataFormatException
	*/
	public int toInt() throws DataFormatException
	{
		if (denominator == 1)
			return numerator;
		else 
			throw new DataFormatException("Not a whole number. Use roundToInt()" +
											" or toDouble() instead");
	}
	
	/**
	* Rounds up if remaining fraction is >= 1/2.
	* @return rounded int. 
	*/
	public int roundToInt()
	{
		 int[] mixedArray = toMixedArray();
		 if (new Fraction(mixedArray[1],mixedArray[2]).isGreaterThan(new Fraction(1/2)))
		 	return mixedArray[0] + 1;
		 else
		 	return mixedArray[0];
	}
	
	/**
	* @return product of two fractions
	*/
	public static Fraction multiply(Fraction a, Fraction b)
	{
		return new Fraction(a.getNumerator()*b.getNumerator(),
							a.getDenominator()*b.getDenominator());
	}
	
	/**
	* @return sum of two fractions
	*/
	public static Fraction add(Fraction a, Fraction b)
	{
		int n = a.getNumerator()*b.getDenominator() + b.getNumerator()*a.getDenominator();
		int d = a.getDenominator()*b.getDenominator();
		return new Fraction(n, d);
	}
	
	/**
	* @return quotient of two fractions
	*/
	public static Fraction divide(Fraction a, Fraction b)
	{
		return multiply(a, b.reciprocal());
	}
	
	/**
	* @return difference of two fractions
	*/
	public static Fraction subtract(Fraction a, Fraction b)
	{
		return add(a, b.negate());
	}
	
	/**
	* @param fraction to be inverted
	* @return inverted fraction
	*/
	public static Fraction reciprocal(Fraction frac)
	{
		return new Fraction(frac.getDenominator(), frac.getNumerator());
	}
	
	/**
	* Does not change fraction operated on; simply returns its reciprocal.
	* @return inverted fraction
	*/
	public Fraction reciprocal()
	{
		return new Fraction(denominator, numerator);
	}
	
	/**
	* @return fraction * -1
	*/
	public static Fraction negate(Fraction frac)
	{
		return new Fraction(frac.getNumerator()*-1,frac.getDenominator());
	}
	
	/**
	* Does not change fraction operated upon
	* @return fraction *-1
	*/
	public Fraction negate()
	{
		return new Fraction(numerator*-1, denominator);
	}
	
	/**
	* @return true if fractions are equal, false if not
	*/
	public boolean equals(Fraction frac)
	{
		if(numerator == frac.getNumerator() && denominator == frac.getDenominator())
			return true;
		else
			return false;
	}
	
	/**
	* @param i is int to be checked
	* @return true if equals(new Fraction(i))
	*/
	public boolean equals(int i)
	{
		Fraction frac =  new Fraction(i);
		return equals(frac);
	}
	
	/**
	* Imprecise because of rounding errors when converting to double.
	* @param d is double to be checked
	* @return true if toDouble() = d.
	*/
	public boolean equals(double d)
	{
		double f = toDouble();
		if (f == d)
			return true;
		else
			return false;
	}
	
	/**
	* @param new fraction to be checked against
	* @return true if fraction operated on is greater than paramater fraction
	*/
	public boolean isGreaterThan(Fraction frac)
	{
		if (numerator*frac.getDenominator() > denominator * frac.getNumerator())
			return true;
		else
			return false;
	}
	
	/**
	* @param i is int to be checked against
	* @return true if isGreaterThan(new Fraction(i))
	*/
	public boolean isGreaterThan(int i)
	{
		Fraction frac = new Fraction(i);
		return isGreaterThan(frac);
	}
	
	/**
	* Imprecise because of rounding errors when converting to double.
	* @param d is double to be checked
	* @return true if toDouble() > d.
	*/
	public boolean isGreaterThan(double d)
	{
		double f = toDouble();
		if (f>d)
			return true;
		else
			return false;
	}
	
	/**
	* @param new fraction to be checked against
	* @return true if fraction operated on is less than paramater fraction
	*/
	public boolean isLessThan(Fraction frac)
	{
		if (numerator*frac.getDenominator() < denominator * frac.getNumerator())
			return true;
		else
			return false;
	}
	
	/**
	* @param i is int to be checked against
	* @return true if isLessThan(new Fraction(i))
	*/
	public boolean isLessThan(int i)
	{
		Fraction frac = new Fraction(i);
		return isLessThan(frac);
	}
	
	
	/**
	* Imprecise because of rounding errors when converting to double.
	* @param d is double to be checked
	* @return true if toDouble() < d.
	*/
	public boolean isLessThan(double d)
	{
		double f = toDouble();
		if (f<d)
			return true;
		else
			return false;
	}
}