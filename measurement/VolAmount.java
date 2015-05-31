package measurement;

import java.util.HashSet;

public class VolAmount
{
	private Fraction amount;
	private String unit;
	private HashSet<String> units = new HashSet<String>();
	
	public VolAmount(Fraction amount, String unit)
	{
		this.amount = amount;
		setUnit(unit);
	}
	
	public VolAmount(String string)
	{
		String[] pieces = string.split(" ");
		if (pieces.length == 2)
		{
			amount = new Fraction(pieces[0]);
			setUnit(pieces[1]);	
		}
		else if (pieces.length == 3)
		{
			new Fraction(pieces[0] + " " + pieces[1]);
			setUnit(pieces[2]);
		}
		else
			throw new IllegalArgumentException("String must be in format " +
								"'a b/c units', 'a units', or 'b/c units'");
	}
	
	private void setUnit(String unit)
	{
		if (unit.charAt(unit.length()-1)=='s')
			this.unit = (unit.substring(0, unit.length()-1)).toLowerCase();
		else
			this.unit = unit.toLowerCase();
		units.add(unit.toLowerCase());
	}
	
	public double getAmount()
	{
		return amount.toDouble();
	}
	
	public String getUnit()
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