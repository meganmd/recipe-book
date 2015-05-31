package measurement;

import java.util.HashSet;

/**
 * Amount of an ingredient which consists of a Fraction and a String 
 * representing a unit (such as teaspoon, tablespoon, or cup)
 * @author Megan Daly
 *
 */
public class VolAmount
{
	private Fraction amount;
	private String unit;
	private static HashSet<String> units = new HashSet<String>();
	
	/**
	 * Constructs VolAmount from provided Fraction amount and String unit
	 * @param amount
	 * @param unit
	 */
	public VolAmount(Fraction amount, String unit)
	{
		this.amount = amount;
		setUnit(unit);
	}
	
	/**
	 * Constructs VolAmount by splitting string into a fraction and unit part.
	 * Assumes unit is only one word; will throw error otherwise.
	 * @param string of format "a b/c units", "a units", or "b/c units"
	 */
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
		if (amount.isGreaterThan(1) && unit.charAt(unit.length()-1)=='s')
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
		if (amount.isLessThan(1) || amount.equals(1))
			return unit + "s";
		else
			return unit;
	}
	
	public static HashSet<String> getPossibleUnits()
	{
		return units;
	}
	
	public String toString()
	{
		return amount.toString() + " " + getUnit();
	}	

}