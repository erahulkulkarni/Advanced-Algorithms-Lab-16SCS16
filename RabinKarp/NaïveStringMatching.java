// Advanced Algorithms Lab - 16SCS16

// 4. To solve string matching problem using naïve approach 
// [ Later building on Rabin Carp Algorithm ]

// Naive string matching algorithm also known/called as Brute-Force String Matching

// A brute-force algorithm for the string-matching :
//   Align the pattern against the first m characters of the text and 
//   start matching the corresponding pairs of characters from left to right until either all the m pairs of the characters match 
//	If match - the algorithm can stop
//	Else A mismatching pair is encountered. 
//       Hence shift the pattern one position to the right and resume the character comparisons, starting again with the first character of the pattern and its counterpart in the text. 

/*
   ALGORITHM BruteForceStringMatch(T [0..n − 1], P [0..m − 1])
   //Implements brute-force string matching
   //Input: An array T [0..n − 1] of n characters representing a text and
   //       an array P [0..m − 1] of m characters representing a pattern
   //Output: The index of the first character in the text that starts a 
   //          matching substring or −1 if the search is unsuccessful

     for i ← 0 to n − m do
       j ← 0
       while j < m and P [j ] = T [i + j ] do
         j ← j + 1

       if j = m return i

     return −1
*/


// Can we do better ?

// Yes !
// Rabin-Karp
// Finite automaton
// Knuth-Morris-Pratt

// m is size of shift table
// n is size of string

// Algorithm 		Preprocessing time 		Matching time
//  Naive			0				O( ( n - m + 1) m )
//  Rabin-Karp			Theta(m)			O( ( n - m + 1) m )
//  Finite automaton		O( m |Sigma| )			Theta(n)
//  Knuth-Morris-Pratt		Theta(m)			Theta(n)

// The string-matching algorithms and their preprocessing and matching times
	
import java.util.*;
import java.io.*;

class NaïveStringMatching
 {
   public static void main( String args[] )
   {

     System.out.print("\n We will read text string from a file.\n Please enter file name :  "); 
     Scanner conin = new Scanner(System.in);
     String fileName = conin.next();

     FileReader fin;
     Scanner src;

     // Let text be saved in variable t
     String t = "";

     try
       {
         fin = new FileReader(fileName);
         src = new Scanner(fin);

         // How do you read file as is?
         // Without overlooking newline , tab and continuour spaces
         while ( src.hasNext() )
           {
             t = t + src.next();
           }
       }
     catch(  FileNotFoundException e )
       {
         System.out.print("\n  Exception thrown : " + e ); 
       }

     // System.out.print("\n Read text is =  " + t);
     int n = t.length();
     System.out.print("\n Text length is =  " + n);
     //Input: An array T [0..n − 1] of n characters representing a text

     System.out.print("\n\n Please Enter pattern : ");   
     // Let pattern be saved in variable p
     String p = conin.next();

     // System.out.print("\n Read pattern is =  " + p);
     int m = p.length();
     // an array P [0..m − 1] of m characters representing a pattern
     System.out.print("\n Pattern length is =  " + m);

     if( m < n )
       {
/*
   //Output: The index of the first character in the text that starts a 
   //          matching substring or −1 if the search is unsuccessful

     for i ← 0 to n − m do
       j ← 0
       while j < m and P [j ] = T [i + j ] do
         j ← j + 1

       if j = m return i

     return −1
*/
         boolean matchFound = false;
	 // boolean match[] = new boolean[n];

         for( int i = 0 ; i < ( n - m + 1 ) ; i++ )        
           {
             int j = 0;
             // match[i] = false;

             while ( ( j < m ) && ( p.charAt(j) == t.charAt(i+j) ) )
               {
                 j = j+1;
               }

             if( j == m )
               {
                 // return i
                 matchFound = true;
		 // match[i] = true;
                 System.out.print("\n Match found in text at location =  " + i);
                 return;
               }
           }

	 // Or print directly , as if match is found , then control is returned.
         if( ! matchFound )
           {
             System.out.print("\n Match not found in text of pattern " + p);             
           }

       }
     else
       {
         System.out.print("\n\n Pattern length m, is greater than text length n ");
         System.out.print("\n m > n : ");
         System.out.print("\n " + m + " > " + n);
       }



    
    return;

    // Can you also implement the same with Object Oriented principles ?
    // Above code though a Java code , it is not utilising the full power of Object Orientation

   }
 }

// References - Text books
// Introduction to the design & analysis of algorithms , Anany Levitin
// Introduction to Algorithms , Thomas H. Cormen , Charles E. Leiserson , Ronald L. Rivest
//                              Clifford Stein [ proofs ]
// Data Structures and Algorithm Analysis in Java , Mark Allen Weiss
// And 
// Java : The Complete Reference, Herbert Schildt

// Coursera , https://www.coursera.org/learn/bioinformatics/
// Biology Meets Programming: Bioinformatics for Beginners
//   University of California, San Diego , 
//   Phillip Compeau and Pavel Pevzner


//   The quick brown fox jumps over the lazy dog
