// Advanced Algorithms Lab - 16SCS16

// Implement a Monte Carlo algorithm to test the primality of a given integer and determine its performance.

// A primality test is an algorithm for determining whether an input number is prime. 
// A prime number (or a prime) is a natural number greater than 1 
//   that has no positive divisors other than 1 and itself. 

// Topic of debate - 1 is a prime number

// Is a given (odd) positive integer a prime?
// non prime - composite

// Why check primality - one use in cryptography ,  Hashing - table size ( proof why table size of prime is better than a composite number ), 
//   public-key cryptography, which makes use of properties of difficulty of factoring large numbers into their prime factors
//   say factoring a large, 400-digit number into two 200-digit primes 
//   RSA algorithm

// Find mistake here
// Factoring a large, 400-digit Prime number into two 200-digit primes ?
// - - - -

// Brute force application 
//  divisibility by odd numbers from 3 to square root of N 
//  requires roughly 1/2 times square root of N divisions, 
//  for say 200 digit number , 10 power 200 , 
//  it is about 10 power 200/2 computations - 10 power 100

// Now say we have a Multi core , for simplicaity say 10 core processor , each of say 10 G Hz
// So we have 10 x 10 x 10 power 9 , computation capacity per second
// So 10 x 10 x 10 power 9 , 10 power 11 computations per second

// With above computation power how long would 10 power 100 computations be completed
//        10 power 11  computations  in  1 second
// hence  10 power 100 computations  in  ( 10 power 100 / 10 power 11 ) seconds

// 10 power 89 seconds

// Lets convert it to years
// 1 year has 365 days x 24 hours x 60 minutes x 60 seconds = 31536000 seconds
// 31536000 be 3 x 10 power 7
// Now lets be generous again and say its 10 x 10 power 7 = 10 power 8 seconds

// If    10 power 8  seconds  is  1 year 
// Then  10 power 89 seconds  is  ( 10 power 89 / 10 power 8 ) = 10 power 81 years
// roughly 10 power ( 100 - 20 ) , 100 was our number of digits / 2

// 10 followed by 80 zeros years to calculate if a number with 400 digits is prime with brute force approach
// or 1 followed by 81 zeros 

// Now, would you suggest brute force algorithm to test primality ?

// Asymptotically 
// if number is n , then number of digits is log n to base 10
// number of digits m = log n
// Prime number with 400 digits, We factor to two 200 digit primes , 
// minimum division required are 1/2 square root of n , 1/2 ( ( 10 raise to ( 200 / 2 ) )
// 1/2 times 10 raise to ( ( log n ) / 2 )

// So ( approximately ) run time = 10 power ( ( ( log n ) / 2 )- 20 )  years
// Exponential run time !

// Other Solution
// sieve of Eratosthenes

// If it can not happen in our life time , then 

// Any faster solution

// The AKS primality test (also known as Agrawal–Kayal–Saxena primality test and cyclotomic AKS test) 
// Is a deterministic primality-proving algorithm by Manindra Agrawal, Neeraj Kayal, and Nitin Saxena, 
// The algorithm determines whether a number is prime or composite within polynomial time. 
// The authors received the 2006 Gödel Prize and the 2006 Fulkerson Prize for this work.
// Deterministic polynomial-time algorithm for primality testing

// Fermat and Solovay–Strassen test


// Can we use randomization ?
// Now lets see whats - Monte Carlo
// Monte Carlo methods (or Monte Carlo experiments) are a broad class of computational algorithms 
// that rely on repeated random sampling to obtain numerical results. 
// Their essential idea is using randomness to solve problems that might be deterministic in principle. 
// They are often used in physical and mathematical problems and are most useful when it is difficult or impossible to use other approaches. 
// Monte Carlo methods are mainly used in three distinct problem classes:
//  1. optimization, 
//  2. numerical integration, and 
//  3. generating draws from a probability distribution.

// Randomized algorithms - Miller-Rabin algorithm for primality testing of integers

// On other topic - 
// Ford–Fulkerson algorithm (FFA) is a greedy algorithm that computes the maximum flow in a flow network
// Yes Bellman Ford!

// Fermats little theorem 

// A probabilistic primality testing algorithm - What concept is being made 
// Binary number representation in computers , ease of finding powers of 2
// And the theorem
// (Fermat’s Lesser Theorem) If P is prime, and 0 < A < P, then A power (P−1) ≡ 1 (mod P)
// A in above example generally considered as 2 , so 2 power (P−1) ≡ 1 (mod P)

// But - Carmichael numbers.
// are there few numbers that do not satisfy Fermats Little Theorem?

// Mersenne primes

// Chinese Remainder theorem
// We have Euclids Theorem before computers , so is Chinese Remainder Theorem

// The basic structure of randomized primality tests is as follows:

//  1.  Randomly pick a number a.
//  2.  Check some equality (corresponding to the chosen test) involving a and the given number n. 
//      If the equality fails to hold true, then n is a composite number, 
//      a is known as a witness for the compositeness, and the test stops.
//  3.  Repeat from step 1 until the required accuracy is achieved.

//  After one or more iterations, if n is not found to be a composite number, then it can be declared probably prime.

// Then How to decrease probability of making error

// PSEUDOPRIME 

/*

   Then Miller–Rabin primality test
     Input #1: n > 3, an odd integer to be tested for primality;
     Input #2: k, a parameter that determines the accuracy of the test
     Output: composite if n is composite, otherwise probably prime

     write n − 1 as 2 power (r)  . d ; with d odd by factoring powers of 2 from n − 1
     WitnessLoop: repeat k times:
        pick a random integer a in the range [2, n − 2]
        x ← a power d  mod n

        if x = 1 or x = n − 1 then
   	  continue WitnessLoop

        repeat r − 1 times:

          x ← x power 2 mod n

          if x = n − 1 then
           continue WitnessLoop
          else           
           return composite

        else     
           return probably prime

*/

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.math.BigInteger;
// Using BigInt satisfies the large size requirement , but its object vs primitive type
// Performance takes a hit in using BigInt object vs int
// Check out the documentation at http://docs.oracle.com/javase/1.5.0/docs/api/java/math/BigInteger.html

// Pending
// Use BigInt instead of long

public class PrimalityTestWithMonteCarloMethod 
 {

   public static long pow (long n, long d ) // power calculated in big O of log n
    {
      System.out.print("\n In pow method  : n = " + n + " , d = " + d );
      if ( d == 0) 
       { 
        return 1;
       } 
      if ( d == 1)
       {
        return n;
       }

      if ( ( d % 2 ) == 0 )  
       {
        return pow ( n * n, d/2); // even n=(n^2)^d/2
       }
      else        
       {       
        return n * pow ( n * n, d/2); // odd  a=a*(a^2)^ d/2
       }
    } // https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test#Computational_complexity

   public static void main(String[] args) 
    {
      // Would Miller-Rabin algorithm for primality testing of integers be sufficient
      // Monte Carlo Methods / Algorithm / Experiment definition be satisfied by Miller-Rabin algorithm
      
      boolean composite = false ;
      boolean probablyPrime = true ;

      Scanner conin = new Scanner(System.in);

      // Read number
      long n; // long is a signed 64-bit type
      System.out.print("\n Enter Number : ");      
      n = conin.nextLong();

      System.out.println(" Entered Number is = " + n );      
   

      //   Input #1: n > 3, an odd integer to be tested for primality;
      if( (n%2) == 0 || ( n < 3 ) )
       {
         System.out.println(" Entered Number is even or less than 3 ");
         return;
       } 

      //   Input #2: k, a parameter that determines the accuracy of the test
      System.out.print("\n Enter accuracy required for the test = " );
      int k;  
      k = conin.nextInt();

      System.out.println(" Required accuracy = " + k );

      //   write n − 1 as 2r.d with d odd by factoring powers of 2 from n − 1
      long temp = n - 1;
      int r = 0 ;
      long d;

      System.out.println("\n Lets find r and d");
      do
       {
         d = temp / 2 ;

         r++; // Counting how many times are we dividing by 2

         System.out.println(" temp = " + temp + " \t r = " + r + " \t d = " + d );

         temp = d;  

       } while( (d%2) == 0 );   // Untill odd d is found , or loop till even
      // Now why this works?

      System.out.println(" n - 1  = 2 power ( r ) . d , d is odd " + k );
      System.out.println(" " + (n-1) + "  = 2 power ( " + r + " ) . " + d);
  

      for ( int i = 0 ; ( i < k ) ; i++ )
       {
         // System.out.print("\n i " + i );

         // [ 2 , n - 2 ] , open interval , inclusive
         // ThreadLocalRandom.current().nextLong(m, n) (for m ≤ x < n) 
         long a =  ThreadLocalRandom.current().nextLong(2, n-1);
         System.out.print(" \n Random number generated : " + a );

         // Efficient way to find powers or exponent

         a = 2 ; d = 10; 
         // x ← a power d  mod n
         long aPowerD = pow(a,d);
         System.out.print(" \n a Power d = " + a + " Power " + d + " = " + aPowerD + "\n");

         long x = aPowerD % n;      

         if ( ( x == 1 ) || ( x == ( n - 1 ) ) )
          {
            //	  continue WitnessLoop
          }
         else
          {
             composite = true ;
             // To break out of loop - instead of break , setting i to k
             i = k ;
          }  
     
         if ( ! composite )
          {
            // repeat r − 1 times:  

            for ( int j = 0 ; ( j < r ); j++ )
             {
               // x ← x2 mod n
               x = ( x * x ) % n;

                // if x = n − 1 then
                if ( x == ( n-1 ) ) 
                 {
                   //   continue WitnessLoop
                 }  
                else
                 {
                   composite = true;
                   j = r ; // To break out of inner loop   
                   i = k ; // To break out of outer loop
                 } 
             }
          }

       }


      // Output: composite if n is composite, otherwise probably prime

      if ( composite )
       {
         System.out.print(" \n Number is composite , its not prime ");
         // Optimus Prime ? J J Abrams
       }
      else
       {
         System.out.print(" \n Number is possibly prime ");
       }  
    }   

    // What is the run time of the algorithm
    // Can any improvements be made to above algorithm


    // Can you also implement the same with Object Oriented principles ?
    // Above code though a Java code , it is not utilising the full power of Object Orientation

 }

// References - Text books
// Introduction to the design & analysis of algorithms / Anany Levitin
// Introduction to Algorithms , Thomas H. Cormen , Charles E. Leiserson , Ronald L. Rivest
//                              Clifford Stein[For proofs]
// Data Structures and Algorithm Analysis in Java , Mark Allen Weiss
// Java : The Complete Reference, Herbert Schildt

// Coursera - Algorithms I and II , Tim / Tom Rougeden

// Introduction to Algorithm - Text book mentions RSA many time - RSA , 
// RONALD L. RIVEST - R in RSA , Creator of RSA also an author of text book

// a is a witness , Indeed !
// I am awaited at the gates of Valhalla - Nux , Witness me - Mad Max Fury Road 
//  - George Miller
