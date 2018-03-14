// Advanced Algorithms Lab - 16SCS16

// 4. To solve string matching problem using naïve approach and the KMP algorithm.
//    Compare their performance.

// KMP is an improvement over : 5. Design and implement Finite State Machine based 
//                                   String search algorithm 

// Last we checked - Naive , Horspool , Bayer Moore 
//                   Then Rabin-Karp algorithm , Finite State Machine for String matching 

// We are trying to find a pattern P of length m , in a Text T of lenght n
//    m <= n 

// We had covered at length on Finite automaton in Finite State Machine based String Match

// Again the same concept - 

// CAN we make use of what has been matched , to decide where to start the next match IF
//    IF there was a mismatch somewhere down the line while matching P in T
// Horspool , Bayer Moore has shift by constant c , c >= 1 on mis match
//            Pattern match will begin ( from left hand side to right ) after 1 <= shift <= m
// Finite automata based match , did the same by keeping track of matched information as states
// So will KMP , its remembering the state it was in AND and decides next state / shift 
//    IF there was a mismatch
//    AND if there is a mismatch we go to smaller / lower state

// Improvement is in transition table size , 
// SPECIFICALLY Transition on MisMatch
// Finite State automata required m . | Sigma | , KMP will need only size m

// So we had covered some Mathematics , Biology 
// Why leave Philosophy behind - 
// KMP algorithm : Do not forget how you have come to present being , remember the past
//                                                    Present match , earlier matches  
//                                                    Present state , earlier states
//           Use this to decide how much to shift and where in pattern begin the new match
  

// If we are having Pattern P = "nano"
// Text T = "banananobano"

// Overlap of two strings x and y to be the longest word 
//         that's a suffix of x and a prefix of y. 

// Here the overlap of "nan" and "nano" is just "n". 
// Its not the overlap of all of x or y, so it's not "nan".


// Nan , rotti , paratha , phulka ? Though good , we are not going for semantics here.

 
// In general the value of i we want to skip to is the one corresponding to the largest overlap with the current match: 

//    Length of match so far  -  Length of longest Proper prefix that is also a suffix

//         State q            -   (say) K

//    Now will this overlap , first k charactes of pattern P with k characters in Text T ?


// The difference between KMP and the automata 
//     KMP has only two arrows out of each circle in Automata , 
//     In Finite Automata based string matching it is ?
//     One one per character in Alphapet

//     We can simulate KMP just like any other automaton, 

//     by placing a marker on the start state (j=0) and 
//        moving it around the arrows. 

//     Whenever we get a matching character in T[] we move on to the next character of the text. This is the main routine

//     But whenever we get a mismatch , we will refer Transition Table
//         we look at the same character in the next step,
//         except for the case of a mismatch in the state j=0

//     Same character ? A Scooby Do expression ?

//     Its where mis match occured
//         And again except for the case of a mismatch in the state j=0 


// All it says is - if there was mismatch in State 0 , shift pattern by one and check again
//                  IF IF automata was in other state than Initial State , 
//                       then shift will depend on how much has been matched , 
//                       AND 
//                       How much of it can be aligned in Text after we shift


//  There should be a transition from j=m to some smaller value of j, 
//        which should happen on any character
//        If we want to find all occurrences of the pattern, we should be able to find an occurrence even if it overlaps another one. 
//        ALL OCCURANCES

/*
    How many times does pattern P ="CC" occur in Text T = "GATCCAGATCCCCATAC"

    Back to biology again , text T is a Nucleotide sequence

    Why are we doing this - we are finding the most frequent 2-mer of "GATCCAGATCCCCATAC".
    "CC" is a 2-mer
    Frequent k-mer in a Nucleotide sequence build up Origin of Replication or Ori

    What is Ori - This is the start state for cell / DNA replication
    Yes Cell / DNA replication can also be considered as a Finite State Machine

    Why Find Ori - This can be done computationaly - hence saving up valuable time of biologist

    How Biologist find Ori - Biologist knock off parts of DNA and check if cell is replicating

    Its an Ori if after knocking off a part of DNA does not initiate replication

    Remember Nucleotide sequence can be in Millions , in case of Human Beings its Billions

*/

//    Now back

//    If the pattern were "nana", we should find both occurrences of it in the text "nanana". 

//    The transition from j=m should go to the next longest position that can match, 
//    which is simply 
//          j=overlap(pattern,pattern). 
//    In this case overlap("nano","nano") is empty 
//    All suffixes of "nano" use the letter "o", and no prefix does so we go to j=0.


//    What can the shift be in terms of length of pattern , m and overlap j ?


// So now we come to point - Difference between Prefix and Proper Prefix
// So also Suffix and Proper Suffix

/*
Prefix and suffix  of "nano"
And Proper Prefix , Suffix

                           Proper     Proper
    Prefixes   Sufixes    Prefixes   Sufixes
     -----      ----       -----      ----
     ""          ""         ""          ""
     "n"         "o"        "n"         "o"
     "na"        "no"       "na"        "no"
     "nan"       "ano"      "nan"       "ano" 
     "nano"      "nano"
*/

//  In other words, knowing that Pq is length of longest Prefix of TOfSPlusQ ,
//    Where we want the longest proper prefix Pk of Pq that is also a suffix of TOfSPlusQ

//  Shift will be length of Pattern P - length of longest proper prefix Pk of Pq that is also a suffix of TOfSPlusQ

//  Is this shift correct ?

//  Why this shift - 
//           If some overlap - we do not start the match from the first character in Pattern
//              There is overlap , lets make use of this known information 
//                and 
//              then start check for match from immediate next character in OVERLAP


// Suppose we're looking for pattern "nano" in text "banananobano".
// P = "nano"
// m = 4
// T = "banananobano"
// n = 12

/*
      Shift Table , name as Pi [ 1 , . .  , m ]
                                 To what state should we shift on mismatch
   
      IT IS USED ONLY ON MISMATCH

      And transition on MATCH ?
      This happens in KMP algorithm , and KMP main routine refers to the Transition Table
           ONLY if there is a mis match
        
      ( Pi , Can we expect Andriod version P to be called as Pie )
      

      Matched
      Pattern    On
              Mismatch
        
    j           Pi
   ---         -----
    0 ""         0       No Match , shift pattern by 1 , state j = 0,
                         [ Next character is "n" , then go to state j = 1 ]

    1 "n"        0       "n" has been matched , but no "a" ahead , go to state j = 0
                         [ if "a" is ahead , go to state j = 2 , which happen in KMP ]

    2 "na"       0       "na" matched so far , but no "n" matched next 

    3 "nan"      2

    4 "nano"     0
 

ARE WE WRONG WITH FOLLOWING : 
                              ?
   
    1  if mismatch occurs after "n" has been matched , that is j was 1

         - - - - n "Not a" - - - 
                 n    a    n o
      
       Length of longest PROPER prefix of "n" that is also a Suffix of text matched, 
       So far Text matched is "n"
       Length of longest proper prefix of "n" that is also a Suffix of "n" = 0

       Proper prefix of "n" is   ""
       Suffix of "n" are "" and  "n"

       So We are back at square one ,
       Shift the pattern by 1 , state is 0 

       Shift by =           1             -                 0
                  How much matched so far - longest proper prefix that is also a suffix 


    2  Text matched so far "na"

         - - - - n a "Not n" - - -
                 n a    n    o
    
       Length of longest PROPER prefix of "na" that is also a Suffix of text matched, 
       so far Text matched is "n a"

       Proper prefix of "na" is     "" and "n"
       Suffix of "n a" are          "" , "a" and "na"

       No Match.

       So We are back at square one , Shift the pattern by 2 , state is 0 

       Length of longest proper prefix of "na" that is also a Suffix of "n a" = 0
 
       Hence state 0 , or j = 0

       IS SHIFT Right ?

       Shift by =           2             -                 0
                  How much matched so far - longest proper prefix that is also a suffix 

       Shift = 2

    3  Text matched so far "nan"

         - - - - n a n "Not O" - - -
                 n a n    o
    
       Length of longest PROPER prefix of "nan" that is also a Suffix of text matched, 
       so far Text matched is "n a n"

       Proper prefix of "nan" is     "" , "n" and "na"
       Suffix of "n a n" are         "" , "n" , "an" and "nan"

       Shift the pattern by ? 
       j from 3 to 1 , State is 1 , j = 1

       Because "n" has been matched.

       Length of longest proper prefix of "nan" that is also a Suffix of "n a n" = 1
 
       Hence state 1 , or j = 1

       Now do you compare "n" , not required . As we know it has been matched , present
          And shift of pattern is such that "n" in pattern P ALIGNS with "n" in Text T
       Then what should be compared - 
          the next character to "n" , begin the match from "a" , earlier the character was definitely not "o" , BUT it can be "a" 


       Shift by =           3             -                 1
                  How much matched so far - longest proper prefix that is also a suffix 

       Shift = 2


    4  Text matched so far "nano"

         - - - - n a n o - - -
                 n a n o
    
       Length of longest PROPER prefix of "nano" that is also a Suffix of text matched, 
       so far Text matched is "n a n o"

       We are in final state , But what if we want to find multiple occurances and ALSO
          ALSO overlapping occurances 

       Proper prefix of "nano" is     "" , "n" , "na" and "nan" 
       Suffix of "n a n o" are        "" , "o" , "no" , "ano" and "nano"

       Shift the pattern by ? 
       j from 4 to 0 , State is 0 , j = 0

       Length of longest proper prefix of "nano" that is also a Suffix of "n a n o" = 0
 
       Hence state 0 , or j = 0


       Shift by =           4             -                 0
                  How much matched so far - longest proper prefix that is also a suffix 

       Shift = 4


// 

// Hence Linear Run Time. 


// Revision of Horspool ?

// We have already found a longest prefix that matches with suffix in Finite State Automata String match
// Now its Proper Prefix
// And transition is NOT on specific character in Alphabeth
// So shift by this length , such that the first of some length characters of such shift will be matching , and continue with next match

// Can I call it a shift table or a transition table
// Or both represent the same concept in the end


// Find longest PROPER Prefix of Pattern P [ Right before mismatch has occured ]
// That is also a SUFFIX of Pattern P      [ Right before mismatch has occured ]

// Length of pattern
// What has been matched so far , state q 

// 

// INVARIANT ?
// 

// Comparision

// m is size of shift table
// n is size of string

// Algorithm 		Preprocessing time 		Matching time
//  Naive			0				O( ( n - m + 1) m )
//  Rabin-Karp			Theta(m)			O( ( n - m + 1) m )
//  Finite automaton		O( m |Sigma| )			Theta(n)
//  Knuth-Morris-Pratt		Theta(m)			Theta(n)

// Oracle
// Back

	
import java.util.Scanner;

class KnuthMorrisPratt
 {
   public static void main( String args[] )
   {

     // If number of characters - 8 bit - a shift table of length 256 will be save [ In C programming? , sufficient for ASCII characters ]
     // Now if text is Latin , Hindi ?
     // In Java - character , or char is saved as 2 bytes , 16 Bits - shift table size will be ?
     // 
     // 65536 ?

    // Python has a key value pair
    // Called Dictionary ?

    // Could you use enumerate for states

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
// An Introduction to Formal Languages and Automata - Peter Linz

// ICS 161 -- Dept. Information & Computer Science -- UC Irvine

//   The quick brown fox jumps over the lazy dog


/*
		void computeLPSArray(String pat, int M, int[] lps)
		{
			// length of the previous longest prefix suffix
			int len = 0;
			int i = 1;
			lps[0] = 0;  // lps[0] is always 0

			// the loop calculates lps[i] for i = 1 to M-1
			while (i < M)
			{
				if (pat[i] == pat[len])                         
				{
					len++;
					lps[i] = len;
					i++;
				}
				else  // (pat[i] != pat[len])
				{
					// This is tricky. Consider the example.
					// AAACAAAA and i = 7. The idea is similar 
					// to search step.
					if (len != 0)
					{
						len = lps[len-1];

						// Also, note that we do not increment
						// i here
					}
					else  // if (len == 0)
					{
						lps[i] = len;
						i++;
					}
				}
			}
		}

   Longest Proper Prefix that is also a Suffix , lps

   Let P ="nano" , m = 4 , len = 0 

   For i = 0 , lps is 0 , lps[0] = 0

   For i = 1
            i < m
                  
            "n" , proper prefix "" , suffix "" and "n" , so lps[1] = 0


*/
