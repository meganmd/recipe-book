import java.lang.Math;

public class VolAmount
{
	private Fraction amount;
	private String unit;
	
	public VolAmount(int numerator, int denominator String unit)
	{
		this.amount = new Fraction(numerator, denominator);
		this.unit = unit;
	}
	
	public double amount()
	{
		return amount.toDouble();
	}
	
	public String unit()
	{
		return unit;
	}
	
	public String toString()
	{
		String string = amount.toString() + " " + unit;
		if (amount.toDouble()>1.0)
			return string + "s";
		else
			return string;
	}	


	/* private double numberOfTeaspoons;
	
	public VolAmount(double amount, VolUnit unit)
	{
		numberOfTeaspoons = amount*unit.tspIn();
	}
	
	public String toString()
	{
		VolUnit unit;
		if (numberOfTeaspoons < VolUnit.TEASPOON.tspIn())
		{
			unit = VolUnit.TEASPOON;
		}
		else if (numberOfTeaspoons < VolUnit.TABLESPOON.tspIn())
		{
			unit = VolUnit.TABLESPOON;
		}
		else
		{
			unit = VolUnit.CUP;
		}
		
		double numberOfUnits = numberOfTeaspoons/unit.tspIn();
		String string = toFrac(numberOfUnits) + " " + VolUnit.toString;
		if (numberOfUnits > 1.0)
		{
			string += s;
		}
		return string;
	}
	
	private String toFrac(double number)
	{
		int wholes = 0;
		while (number >= 1.0)
		{
			number -=1;
			wholes++;
		}
		
	}
	*/
}