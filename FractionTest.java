import java.util.Arrays;

public class FractionTest
{
	public static void main(String[] args)
	{	
	
		Fraction[] fracs = new Fraction[] {				//half
								new Fraction(1,2),		//basic
								new Fraction("1/2"),	//basic string
								new Fraction(2,4),		//reduce
								new Fraction("2/4"),	//string reduce
								new Fraction(-1,-2),	//cancel -
								new Fraction("-1/-2")	//string cancel -
							};
		
		for (Fraction frac : fracs)
		{
			assert(frac.getNumerator() == 1);
			assert(frac.getDenominator() == 2);
			assert(frac.toDouble() == .5);
			assert(frac.toImproperString() == "1/2");
			assert(frac.toString() == "1/2");
			assert(Arrays.equals(frac.toImproperArray(), new int[] {1,2}));
			assert(Arrays.equals(frac.toMixedArray(), new int[]{0,1,2}));
		}
		
		fracs = new Fraction[]{							// -half
					new Fraction(-1,2), 	// -num
					new Fraction("-1/2"),	// -num string
					new Fraction(1,-2),		// -denom
					new Fraction("1/-2"),	// -denom string
					new Fraction(-2,4),		// -num reduce
					new Fraction("-2/4"), 	// -num string reduce
					new Fraction(2, -4),	// -denom reduce
					new Fraction("2/-4"),	// -denom string reduce
				};
		
		for (Fraction frac : fracs)
		{
			assert(frac.getNumerator() == -1);
			assert(frac.getDenominator() == 2);
			assert(frac.toDouble() == -.5);
			assert(frac.toImproperString() == "-1/2");
			assert(frac.toString() == "-1/2");
			assert(Arrays.equals(frac.toImproperArray(),new int[]{-1,2}));
			assert(Arrays.equals(frac.toMixedArray(), new int[]{0,-1,2}));
		}
		
		
		
		fracs = new Fraction[]{							// 4/3
					new Fraction(4,3), 		// basic improper
					new Fraction("4/3"),	// string improper
					new Fraction("1 1/3"), 	// string mixed improper
					new Fraction(8, 6),		// reduce improper
					new Fraction("8/6"),	// string reduce improper
					new Fraction("1 2/6"),	// string reduce mixed improper
				};
				
		for (Fraction frac : fracs)
		{
			assert(frac.getNumerator() == 4);
			assert(frac.getDenominator() == 3);
			assert(frac.toDouble() == 1.3333333333333333);
			assert(frac.toImproperString() == "4/3");
			assert(frac.toString() == "1 1/3");
			assert(Arrays.equals(frac.toImproperArray(), new int[] {4,3}));
			assert(Arrays.equals(frac.toMixedArray(), new int[]{1,1,3}));
		}	
		
		
		assert(new Fraction(0, 8).toString() == "0/1");
		System.out.println("4/2 = " + new Fraction(4,2).toString());
		System.out.println("4/2 = " + Arrays.toString(new Fraction(4,2).toMixedArray()));
		System.out.println("-4/3 = " + new Fraction(-4,3).toString());
		System.out.println(".125 = " + new Fraction(.125).toString());
		System.out.println(".3333333333333333 =" + 
							new Fraction(.3333333333333333).toString());
		
	}
	
}