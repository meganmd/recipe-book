import java.util.ArrayList;
import java.lang.Integer;

public class Ingredient
{
	//private static int CaloriesPerOz;
	//private double OzPerTsp;
	
	
	private static ArrayList<String> types = new ArrayList<String>();
	private String type;
	
	private VolAmount amount;
	
	public Ingredient(VolAmount amount, String type)
	{
		this.amount = amount;
	}
	
	public Ingredient(String string)
	{
		String[] pieces = string.Split(" ");
		String amount = "";
		try
		{
			int i = Integer.parseInt(amount[0]);
			try 
	
	}
	
	public setAmount(VolAmount amount)
	{
		this.amount = amount;
	}
	
	public VolAmount getAmount()
	{
		return amount;
	}
	
	public String toString()
	{
		return amount.toString() + type;
	}
}