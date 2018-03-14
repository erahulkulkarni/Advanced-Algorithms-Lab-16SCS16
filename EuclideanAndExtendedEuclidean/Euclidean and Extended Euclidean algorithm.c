// Advanced Algorithms Lab - 16SCS16

#include<stdio.h>

// 1. Program to calculate greatest common divisor (GCD) using Euclid's Algorithm
// Greatest common divisor (GCD) of two numbers is the largest number that divides both of them without leaving a remainder

// Extended Euclidean algorithm is an extension to the Euclidean algorithm, 
// which computes, besides the greatest common divisor of integers m and n, 
// the coefficients of Bézout's identity, that is integers x and y such that

// mx + ny = gcd ( m , n )


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

int euclidsAlgorithm(int m , int n )
 {
   int r = 0;

   char temp; 
   
   printf("\n Euclid's Algorithm \n");
   printf("\n r = %d \t m = %d \t n = %d \n", r, m, n);

   while( n!=0 )
    {
      r = m % n;
      m = n;
      n = r;
      printf("\n r = %d \t m = %d \t n = %d \n", r, m, n);

      printf("\n Hit Enter to continue : ");
      scanf("%c",&temp);
    }

   printf("\n Algorithm complete \n GCD to be returned GCD = %d", m );
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

int extendedEuclidsAlgorithm( int m , int n , int *x, int *y )
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

   char temp; 

   printf("\n\n Extended Euclid's Algorithm \n");

   printf("\n What is to be done in loop : \n ");
   printf("\n rNext  =  rPrevious - quotient * remainder;");
   printf("\n sNext  =  sPrevious - quotient * sPresent;");
   printf("\n tNext  =  tPrevious - quotient * tPresent;");        

   printf("\n\n Value in While loop \nQuotient \t\t Remainder \t\t s \t\t\t t \n");

   while ( rPresent != 0 )
    {
      quotient  = rPrevious / rPresent ;
      remainder = rPrevious % rPresent ;            

      rNext  =  rPrevious - quotient * remainder;  // This provided
				// 0 <= rNext < absolute value of rPresent
      sNext  =  sPrevious - quotient * sPresent;  
      tNext  =  tPrevious - quotient * tPresent;  
 
      // print the values
      printf("\n %d / %d = %d \t ", rPrevious , rPresent , quotient);
      printf(" %d mod %d = %d \t ", rPrevious , rPresent , remainder);
      printf(" %d - ( %d * %d ) = %d \t ", sPrevious , quotient , sPresent , sNext);
      printf(" %d - ( %d * %d ) = %d \t ", tPrevious , quotient , tPresent , tNext);

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
      
      printf("\n Hit Enter to continue : ");
      scanf("%c",&temp);
    }

   *x  =  sPrevious ;
   *y  =  tPrevious ;
   return rPrevious;
 }


int main()
 {

   int m;
   int n;
   int x;
   int y;

   int gcd=0;

   printf("\n Please enter two non negative integers : ");
   scanf("%d%d",&m,&n);

   if( ( m > 0 ) && ( n > 0 ) )
    {
     printf("\n Entered number[s] is[are] non negative");
     printf("\n m = %d \t and n = %d", m, n);

     // call Euclids Algorithm
     gcd=euclidsAlgorithm(m,n);
     printf("\n In main() \n GCD = %d \n ", gcd);
     printf("\n So, Euclid's algorithm, What's the run time in Asymptotic Notation?");

     
     // Now call Extended Euclids Algorithm
     //   ( not the mobile call but function call ) 
     gcd=extendedEuclidsAlgorithm( m , n , &x, &y );

     printf("\n In main() \n GCD = %d \n ", gcd);
     printf("\n The coefficients of Bézout's identity ");
     printf("\n That is integers x and y such that mx + ny = gcd ( m , n )\n ");
     printf("\n m = %d \t x = %d", m, x );
     printf("\n n = %d \t y = %d", n, y );
     printf("\n ( m * x ) + ( n * y ) = gcd ( m , n ) \n");
     printf("\n ( %d * %d ) + ( %d * %d ) = gcd ( %d , %d ) \n", m, x, n, y, m, n );
     printf("\n Now, Extended Euclid's algorithm, What's the run time in Asymptotic Notation?");

    }
   else
    {
     printf("\n Entered number[s] is[are] negative\n");
    }
   
   return (0);
 }
