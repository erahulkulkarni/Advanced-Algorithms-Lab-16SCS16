using System;

// 1. Program to calculate greatest common divisor (GCD) using Euclid's Algorithm
// Greatest common divisor (GCD) of two numbers is the largest number that divides both of them without leaving a remainder

// Extended Euclidean algorithm is an extension to the Euclidean algorithm, 
// which computes, besides the greatest common divisor of integers m and n, 
// the coefficients of Bézout's identity, that is integers x and y such that

// mx + ny = gcd ( m , n )

namespace EuclidExtendedEuclid
{
	class MainClass
	{

		// ALGORITHM Euclid(m, n)
		//   Computes gcd(m, n) by Euclid’s algorithm
		//   Input: Two nonnegative, not-both-zero integers m and n
		//   Output: Greatest common divisor of m and n
		//   while n != 0 
		//     do
		//       r ← m mod n
		//       m ← n
		//       n ← r
		//     done 
		//   return m

		public static int euclidsAlgorithm(int m , int n )
		{
			int r = 0; 

			Console.WriteLine("\n Euclid's Algorithm \n");
			Console.WriteLine("\n r = {0} \t m = {1} \t n = {2} \n", r, m, n);

			while( n!=0 )
			{
				r = m % n;
				m = n;
				n = r;
				Console.WriteLine("\n r = {0} \t m = {1} \t n = {2} \n", r, m, n);

				Console.WriteLine("\n Hit Enter to continue : ");
				Console.ReadLine ();
			}

			Console.WriteLine("\n Algorithm complete \n GCD to be returned GCD = {0}", m );
			return m;
		}

		// Extended Euclid's Algorithm

		// ALGORITHM Extended Euclids Algorithm(m, n)
		//   Computes gcd(m, n) , Bézout coefficients x and y such than mx+ny=gcd(m,n)
		//   Input: Two nonnegative, not-both-zero integers m and n
		//   Output: Greatest common divisor of m and n and Bézout coefficients
		//   
		//     rPrevious  ← m ; // this is r0
		//     rPresent   ← n ; // this is r1   
		//     sPrevious  ← 1 ; // this is s0
		//     sPresent   ← 0 ; // this is s1   
		//     tPrevious  ← 0 ; // this is t0
		//     tPresent   ← 1 ; // this is t1
		//     	
		//     do until present remainder rPresent != 0 
		//        rNext  ←  rPrevious - quotient * rPresent  // This provided
		//				// 0 <= rNext < absolute value of rPresent
		//        sNext  ←  sPrevious - quotient * sPresent  
		//        tNext  ←  tPrevious - quotient * tPresent  
		//     done 
		//
		//     // gcd  ←  rPrevious 
		//     // x  ←  sPrevious 
		//     // y  ←  tPrevious 
		//   return gcd, x, y

		public static int extendedEuclidsAlgorithm( int m , int n , ref int x, ref int y )
		{
			int rPrevious = m ; // this is r0
			int rPresent  = n ; // this is r1   
			int sPrevious = 1 ; // this is s0
			int sPresent  = 0 ; // this is s1   
			int tPrevious = 0 ; // this is t0
			int tPresent  = 1 ; // this is t1

			int rNext =0;
			int sNext =0;
			int tNext =0;

			int quotient;
			int remainder;      // Or Reminder!


			Console.WriteLine("\n\n Extended Euclid's Algorithm \n");

			Console.WriteLine("\n What is to be done in loop : \n ");
			Console.WriteLine("\n rNext  =  rPrevious - quotient * remainder;");
			Console.WriteLine("\n sNext  =  sPrevious - quotient * sPresent;");
			Console.WriteLine("\n tNext  =  tPrevious - quotient * tPresent;");        

			Console.WriteLine("\n\n Value in While loop \nQuotient \t\t Remainder \t\t s \t\t\t t \n");

			while ( rPresent != 0 )
			{
				quotient  = rPrevious / rPresent ;
				remainder = rPrevious % rPresent ;            

				rNext  =  rPrevious - quotient * remainder;  // This provided
				// 0 <= rNext < absolute value of rPresent
				sNext  =  sPrevious - quotient * sPresent;  
				tNext  =  tPrevious - quotient * tPresent;  

				// print the values
				Console.WriteLine("\n {0} / {1} = {2} \t ", rPrevious , rPresent , quotient);			
				Console.Write(" {0} mod {1} = {2} \t ", rPrevious , rPresent , remainder);
				Console.Write(" {0} - ( {1} * {2} ) = {3} \t ", sPrevious , quotient , sPresent , sNext);
				Console.Write(" {0} - ( {1} * {2} ) = {3} \t ", tPrevious , quotient , tPresent , tNext);

				// Now update values for next ireartion
				// rNext , rPresent , nPrevious
				//         rPresent assign to rPrevious , again we are preparing for next iteration
				//         rNext assign to remainder , similarly assign s and t
				// Can we change the order of assignment

				rPrevious = rPresent;
				rPresent = remainder;          // Its remainder and not rNext

				sPrevious = sPresent;
				sPresent = sNext;

				tPrevious = tPresent;
				tPresent = tNext;

				Console.WriteLine("\n Hit Enter to continue : ");
				Console.ReadLine ();
			}

			x  =  sPrevious ;
			y  =  tPrevious ;

			return rPrevious;
		}			

		public static void Main (string[] args)
		{
			int m = 0;
			int n = 0;
			int x = 0;
			int y = 0;

			int gcd=0;

			Console.WriteLine("\n Please enter two non negative integers : ");
			m = int.Parse (Console.ReadLine ());
			n = int.Parse (Console.ReadLine ());

			if( ( m > 0 ) && ( n > 0 ) )
			{
				Console.WriteLine("\n Entered number[s] is[are] non negative");
				Console.WriteLine("\n m = {0} \t and n = {1}", m, n);

				// call Euclids Algorithm
				gcd=euclidsAlgorithm(m,n);
				Console.WriteLine("\n In main() \n GCD = {0} \n ", gcd);
				Console.WriteLine("\n So, Euclid's algorithm, What's the run time in Asymptotic Notation?");


				// Now call Extended Euclids Algorithm
				//   ( not the mobile call but function call ) 
				gcd=extendedEuclidsAlgorithm( m , n , ref x, ref y );

				Console.WriteLine("\n In main() \n GCD = {0} \n ", gcd);
				Console.WriteLine("\n The coefficients of Bézout's identity ");
				Console.WriteLine("\n That is integers x and y such that mx + ny = gcd ( m , n )\n ");
				Console.WriteLine("\n m = {0} \t x = {1}", m, x );
				Console.WriteLine("\n n = {0} \t y = {1}", n, y );
				Console.WriteLine("\n ( m * x ) + ( n * y ) = gcd ( m , n ) \n");
				Console.WriteLine("\n ( {0} * {1} ) + ( {2} * {3} ) = gcd ( {4} , {5} ) \n", m, x, n, y, m, n );
				Console.WriteLine("\n Now, Extended Euclid's algorithm, What's the run time in Asymptotic Notation?");

			}
			else
			{
				Console.WriteLine("\n Entered number[s] is[are] negative\n");
			}					
		}
	}
}
