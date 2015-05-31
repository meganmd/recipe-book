package measurement;
public enum VolUnit
{
	TEASPOON
	{
		public double tspIn()
		{
			return 1.0;
		}
		public String toString()
		{
			return "teaspoon";
		}
	},
	TABLESPOON
	{
		public double tspIn()
		{
			return 3.0;
		}
		public String toString()
		{
			return "tablespoon";
		}
	},
	CUP
	{
		public double tspIn()
		{
			return 48.0;
		}
		public String toString()
		{
			return "cup";
		}
	};
}