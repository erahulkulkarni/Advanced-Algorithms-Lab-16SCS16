// Advanced Algorithms Lab - 16SCS16

// 6. To solve string matching problem using Rabin Karp algorithm and determine its
//    performance.

// [ Later on we will check  from the same domain - ]
// 5. Design and implement Finite State Machine based String search algorithm and
//    compare its performance with Naïve Search technique.
// 4. To solve string matching problem using naïve approach and the KMP algorithm.
//    Compare their performance.

// Lest go with order - naïve , The Rabin-Karp algorithm , String matching with finite automata and then The Knuth-Morris-Pratt algorithm


// Its Rabin not Robin
// Rabin Karp Algorithm

// And yes Miller Rabin
//		  Rabin Karp
//	 Michael O. Rabin
// Turing Award winner

// [ Allan Turing and Michael Rabin - same Doctoral advisor - Alonzo Church ]	


// A string is a sequence of characters from an alphabet. 
// Strings of particular interest are text strings, which comprise letters, numbers, and
//   special characters; 
//   bit strings, which comprise zeros and ones; and gene sequences,
//   which can be modeled by strings of characters from the four-character alphabet { A ,
//   C , G , T }

// Problem — searching for a given word in a text has attracted special attention
//           Called as string matching


// Last we dealt with primality , some Mathematics

// Now some Mathematics and Biology and back to Mathematics

// Why is string matching important

// Applications [ Few with respect to Biology ] - 

// What a Computer Science graduate can contribute to Biology ?

// Real life applications - we are able to pin point gene - possibly the cause of disorder
// Which protein is not being generated , Immunotherapy for Cancer [ , still debated knock off gene that have been known to create disorders for IVF ]

// How do you find genes - genetic data - nucleotides sequence - Using Sequencer

// Genetic data in Human beings - 46 chromosomes - 23 pairs - [informally call it] Genome

// Its sequences of { A ,T , C , G } nucleotides

// Genome data - has millions of base pairs , building - finding Genome for a particular person it self takes days to weeks using Sequencer and Software which output Genetic Information

// Sequencer only reads base pairs in lengths of 10s - 20s nucleotides at a time ( out of millions of nucleotides ) and its not in specific order
// [ also making use of PCR - Polymerase Chain Reaction ]

// Now out of these 20 length details - build up the Genome consisitng sequence of millions of nucleotides 
// This may go for days , weeks , years : based on algorithm you implementation for string / pattern match

// This is reverse of String Matching where Present[available in text] Pattenrs are given - goal is to find text 
// 10 - 20 length nucleotides are patterns , text is Genome

// Million nucleotide sequence - 10 power 6 to build out of given 10 - 20 length nucleotide 


// Vaugely , one more problem
// Finding Origin of Replication Problem:
//  Input: A DNA string Genome.
//  Output: The location of ori in Genome.

// Origin of replication of Genome , in Bacteria ( E Coli ) with in Genome data of -  5 million nucleotides

// Vibrio Cholerae - Origin of Replication - 500 nucleotides

// 3 Billion Nucleotide in human genome

// Better defined problem
// Find frequent occuring patterns ( k-mer of specific length - d ) out of Million size sequence data


// Hence one major application - Bioinformatics

// GNU's grep - uses Boyer–Moore
// GREP is - ?


// Now back to string matching


// Naive string matching algorithm also known/called as Brute-Force String Matching


// Why study Brute force - 
// Say you are interviewing a Brute froce Algorithm - Its HR round
// Interviewer - What are your strength and weakness?
// Brute force Algorithm - Characteristic strength is simplicity but weakness is inferior efficiency


// given a string of n characters called the text and a string of m characters (m ≤ n) called the pattern, find a substring of the text that matches the pattern

// If matches other than the first one need to be found, a string-matching algorithm
//   can simply continue working until the entire text is exhausted.

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
// Run time analysis
// Worst case - on every m th comparision mismatch may occur , hence m comparisions are done ( n - m + 1) times
// O( ( n - m + 1) m )
// O(nm) class
// Theta run time ?


// Can we do better ?


// Yes , lets see Space vs time trade off
// More efficient algorithms for string searching - 
// The most widely known of them—by R. Boyer and J. Moore along with its simplification suggested by R. Horspool.


// Generic layout of faster string matching algorithm - 
// Idea - Preprocess the pattern to get some information about it, 
//   store this information in a table, and then use this information during an actual
//   search for the pattern in a given text. 

//   This is exactly the idea behind the two best-known algorithms of this type: 
//   Knuth-Morris-Pratt algorithm and 
//   Boyer-Moore algorithm


// Shift [ not constant amount like 1 in Naive algorithm ] such that possibility on next immediated match is not over looked


// Generate Shift table [ the space component of Space vs Time trade Off ]


// Idea - Shift by ( not 1 but by some ) amount , where probably you will get a match
// How much you shift varies 

//  Compare left to right Or Right to left , hence Two different algorithms


// Simplified version of the Boyer-Moore algorithm suggested by R. Horspool 


// Pattern matched from right to left to text ( from left to right )
// Two cases - 	Last character in pattern has mismatched with text
// 		After few successful match of characters of pattern with text a mismatch has occured


// Horspool’s Algorithm
// Lets just go through example
/*
	Searching for the pattern BARBER in some text

		s0 ... c ... sn−1
		   B A R B E R

		c here means character c


	Case 1 : If there are no c’s in the pattern — e.g., 
	c is letter S in our example— we can safely shift the pattern by its entire length (if we shift less, some character of the pattern would be aligned against the text’s character c that is known not to be in the pattern) :

		s0 ... ... ... S ... ... ... sn−1
                                 S != R
		     B A R B E R
				B A R B E R

	What if multiple R in pattern , still the same shift - Why ?


	Case 2 : If there are occurrences of character c in the pattern but it is not the last one there — e.g., 
	c is letter B in our example — the shift should align the rightmost occurrence of c in the pattern with the c in the text:
		s0 ... ... ... B ... ... ... sn−1
                                 B != R , B is present in pattern , so shift such that B in 						  text matches with last B of pattern
		     B A R B E R
			 B A R B E R


	Case 3 : If c happens to be the last character in the pattern but there are no c’s
among its other m − 1 characters —e.g., 
	c is letter R in our example—the situation is similar to that of Case 1 and the pattern should be shifted by the entire pattern’s length m:
		s0 ... ... ... M   E  R ... ... ... sn−1
			       !=  =  =
 			 L E A D   E  R
		 			L E A D E R

					Though E is present in pattern , but M is not in pattern , so again Case 1
					Again - M is not present in pattern , moving pattern by three units right may match E of pattern with E of text , but there will be mismatch at M of text again

Hence following is not adviced -
		s0 ... ... ... M   E  R ... ... ... sn−1
			       !=  =  =
 			 L E A D   E  R
		 	       L   E  A  D  E  R , shift is not adviced , though E == E , ( assume there is match of D E and R also ) - but M of text and L or pattern

				So Shift by 3 or the length of pattern ?
				Can you write proof - does this work or not?


	Case 4 : Finally, if c happens to be the last character in the pattern and there
are other c’s among its first m − 1 characters —e.g., 
	c is letter R in our example—
	the situation is similar to that of Case 2 and the rightmost occurrence of c among
the first m − 1 characters in the pattern should be aligned with the text’s c:
	[ Why not shift by entire patter length ]
		s0 ... ... ... A  R ... ... ... sn−1
			      !=  =
	      	    R E O R D  E  R
                         R  E  O  R D E R

Right-to-left character comparisons can lead to farther shifts of the pattern than the shifts by only one position always made by the brute-force algorithm.

No other information about the text in which eventual searching will be done is required

// Runtime  Average-case complexity of O(n) 
// 	    Worst case has O(nm)

Horspool’s algorithm is obviously faster on average than the brute-force algorithm. 
In fact, as mentioned, it is often at least as efficient as its more sophisticated predecessor discovered by R. Boyer and J. Moore.


// What is an average case ?
   Example of sorting
   10 unique number 
   Problem - Given n numbers , arrange in non decreasing [ or non increasing ] order

	10 unique numbers - How many permutations of size 10
	First position can be filled in 10 ways , second in 9 , third in 8 . . Last position in 1 way
	So 10! permutations

	Now if you calculate time taken / comparisions made for each of the permutation to sort - Apply the algorithm 10! times and calculate the run times / comparisions
	Let them be t1 , t2 , . . . , tOf10F run times

	Now Average case = Sum of all such run time / 10!
			 = ( t1 + t2 + . . + tOf10F ) / 10!
	
*/

/*

// Boyer-Moore Algorithm
	If the first comparison of the rightmost character in the pattern with the corresponding character c in the text fails, the algorithm does exactly the same thing as Horspool’s algorithm ( Case 1 and 2 )

	But where does it differ - ( Case 3 and 4 )
	after some positive number k (0 < k < m) of the pattern’s characters are matched successfully before a mismatch is encountered - Shift differs

	Boyer-Moore algorithm determines the shift size by considering two quantities

	1. Bad-symbol shift - The first one is guided by the text’s character c that caused a mismatch with its counterpart in the pattern. Accordingly, it is called the bad-symbol shift.
	2. Good-suffix shift - The second type of shift is guided by a successful match of the last k > 0 characters of the pattern. Accordingly, we call this type of shift the good-suffix shift

// Run time - When searching for the first occurrence of the pattern, the worst-case efficiency of the Boyer-Moore algorithm is known to be linear

	Worst-case running time of O ( n + m ) only if the pattern does not appear in the text (as in original paper) 

	Upper bound of 3n comparisons in the worst case

	When the pattern does occur in the text, running time of the original algorithm is O(nm) in the worst case. 
	But as mentioned earlier : the worst-case efficiency of the Boyer-Moore algorithm is known to be linear - How ?

	Inclusion of the Galil rule results in linear runtime across all cases.

*/

//        Boyer-Moore Algorithm
//              Moore - Melay machines in Logic Design ?
// Bellman Ford Moore Shimbel Algorithm
// Moore , Same ?


// Can we do better ?
// Yes !

// Finite automaton
// Knuth-Morris-Pratt

// Number Comparision
// String comparision
// Character Comparision


// Horners Rule [ similar line fast Fourier transform (FFT) ]




// The Rabin-Karp algorithm
// Application - detecting plagiarism , two-dimensional pattern matching



// A string-matching algorithm
// Using hashing to find any one of a set of pattern strings in a text
// In simple - find Hash value of pattern 
// One step at a time - 
//     find hash value of text [same length of pattern , sliding right one at a time ]
// Only If , only if hash values are equal then compare string and pattern

// 


// Which hash function to use ?
// Compute faster , can earlier results be reused?


// Comparision

// m is size of shift table
// n is size of string

// Algorithm 		Preprocessing time 		Matching time
//  Naive			0				O( ( n - m + 1) m )
//  Rabin-Karp			Theta(m)			O( ( n - m + 1) m )
//  Finite automaton		O( m |Sigma| )			Theta(n)
//  Knuth-Morris-Pratt		Theta(m)			Theta(n)

// The string-matching algorithms and their preprocessing and matching times


// Knutt Morris Pratt
// Donald Knutt - The Art of Programming , LaTeX

	
import java.util.Scanner;

class RabinKarp
 {
   public static void main( String args[] )
   {

     // If number of characters - 8 bit - a shift table of length 256 will be save [ In C programming? , sufficient for ASCII characters ]
     // Now if text is Latin , Hindi ?
     // In Java - character , or char is saved as 2 bytes , 16 Bits - shift table size will be ?
     // 
     // 65536 ?

     // Advantage of array - constant access time : O(1)
     // Drawback - insert , delete (and shift to fill the vacant space) is O(n)

    
    return;


    // Can you also implement the same with Object Oriented principles ?
    // Above code though a Java code , it is not utilising the full power of Object Orientation

   }
 }

// R. M. Karp and M. O. Rabin, “Efficient Randomized Pattern-Matching Algorithms,” Aiken Computer Laboratory Report TR-31-81, Harvard University, Cambridge, Mass., 1981

// Concentrate on input enhancement , 
// Is this the same input enhancement as used in Machine Learning ?


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
