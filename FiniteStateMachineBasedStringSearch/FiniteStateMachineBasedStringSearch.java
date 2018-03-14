// Advanced Algorithms Lab - 16SCS16

// 5. Design and implement Finite State Machine based String search algorithm and
//    compare its performance with Naïve Search technique.

// From here we can build on to
// 6. To solve string matching problem using Robin Karp algorithm and determine its
//    performance.

// Last we checked - Naive , Horspool , Bayer Moore 
//                   Then Rabin-Karp algorithm for String matching 

// Now String matching with finite automata

// Some more Mathematics , Finite Automata Theory

// Finite automaton
// Fixed number of states , transition between states on reading input , Q
// In fixed number of state - One Start state q0 , Final State[s] / Accepting state[s] - A
//                            Both q0 and A are sub set of Q
// Input Set - Finite Set of input characters - called Alphabet , Sigma
// A function - that check present state and input read and informs next state , Delta

// So mathematically , machine M , or Finite Automata M

// M = { Q , q0 , A , Sigma , Delta }

// Regular Expression , Regular Grammar , grep ?

// So if we write a table for function Delta , then how many maximum entries can it have
// in a DFA ?

// also known as Transition Table

// Accept or Reject the string

/*
   Wait this may not be correct

	For each state in Q			OR	For each input symbol in Sigma
	   For each input symbol in Sigma		   For each state in Q
		Only one transition Delta			Only one transition Delta
*/

//            Q * Sigma				OR		Sigma * Q  Entries

// If you are thinking NFA and Eplison NFA , transition can happen to a single or multiple states
//    On reading or Without reading input respectively

// No worries - we have Algorithm that converts NFA and Eplison NFA to DFA

// Now questions - 

// Why are they called Deterministic and Non Deterministic

// Deterministic - for one input there is only ONE next state the machine can be in
//                 There is no or in Deterministic

// Non Deterministic ?


// Which is more powerful : Regular Expression , DFA , NFA or Eplison NFA ?

// The one that exercises regularly is more powerful ?

// Given Eplison NFA , NFA , DFA or Regular Expression

// Eplison NFA →  NFA →  DFA  →  Regular Expression
//    |					 |
//    ↑					 ↓
//    |_______________←__________________|


// Now back -

// Is Program a Finite State Machine ?
// Computer is a Finite State Machine , event driven , so how can we have random number generators ?


// For Cosntant Access time suggested Data Structure will be ?
// Which we can use for transition table

// Automata - A simple machine for processing information—that scans the text string T for all occurrences of the pattern P

// To know current state - or string seen so far : 
// We have to know :
//                  1. Have we already matched the string we're looking for ("someString")?
//		    2. If not, could we possibly be in the middle of a match

//  Assume someString is "nano" , is the pattern P ="nano"

//  Say - Text T is "The wavelength of given wave is 10 nano meter"

//  If we're in the middle of a match, we need to know how much of "nano" we've already seen. 
//  Also, depending on the characters we haven't seen yet, 
//        there may be more than one match that we could be in the middle of -- 
//    for instance if we've just seen "...nan", then we have different matches if the next characters are "o..." 
//     or 
//    if they're "ano...". 

//  But let's be optimistic, and only remember the longest partial match.

//  So we want our states to be partial matches to the pattern. 

//  The possible partial matches to "nano" are "", "n", "na", "nan", 
//     or (the complete match) "nano" itself. 

//  Prefix not Sub strings !
//  There are differences , yes Prefix is subset of Sub Strings
//  Substring with condition : Prefix is substring - which should begin from first character and include consecutive characters

//  In other words, they're just the prefixes of the string. 

//  What are the suffixes?

//  In general, if the pattern has m characters, we need m+1 states; here m=4 and there are five states.

// Now Transition Table ?

// If we've just seen "...nan", and see another character "x", what state should we go to?
// if x is the next character in the match (here "o"), we should go to the next longer prefix (here "nano"). 

// But suppose we see a different character, such as "a"?
// That means that the string so far looks like "...nana"
// So Prefix ! [compare to Horspool now , we shift by 2 is it ?]
// The longest partial match we could be in is just "na"

// So from state "nan", we should draw an arrow labeled "a" to state "na". 
// Note that "na" is a prefix of "nano" (so it's a state) 
// and 
// a suffix of "nana" (so it's a partial match consistent with what we've just seen).


// Prefix ( of pattern ) Suffix ( of Text being matched )

// We have successful match And unsuccessful match

// Roughly speaking - 
// Where we have failed , can we check if it builds up a part/ some part of successful match scenario in prefix part of pattern , hence the text
//      With details of already matched / not matched characters

// Prefix of pattern P == Suffix of text matched so far - will be the state q , 
// Machine M transits to state q
	
// P = "nano" , prefixes are "" "n" "na" "nan" and "nano"
// HENCE the states in Finite Automata

// For exampe text T = "banananano"

// Simulating this on the Text string T = "banananona" to match , 
// we get the sequence of states empty, empty, empty, "n", "na", "nan", "na", "nan", "nano", "nano", "nano"

// How Macine M transitions happen

// No prefix of P matches with "b" of "b anananano", and with next just "a" , second character of "b a nananano" , 
// SO Finite Automata Still in state "" , no match state

// But "n" third character of "ba n ananano" matches a prefix "n" of pattern P " n ano"
// So Now FiniteAutomata moves to STATE "n"


// Next "na" of "ba na nanano" match with "na" of pattern " na no"
// So Finite Automata moves to SATAE "na" from state "n"

// Next "nan" of "ba nan anano" matches with "nan" of pattern "nan o"
// So finite automata moves to state "nan" from state "na"


// Is this right ?
// What has been matches is exactly the satate the Finite Automata M is in !


// But next there is a mismatch "a" in "nan a" 
// Now the question does any of prefix of Pattern P = "nano" : "" , "n" , "na" , "nan" ,  "nano" match with suffix of "nana" that are : "" "a" , "na" , "ana" and "nana"
// Not longest match is "na"
// SO
// We do not go to final state "nano" as there was a mismatch at final character
// BUT we also DO NOT restart the match / go to START state 
// AS
// part of prefix of pattern has matched with suffix of text

// That is just recently some match happened in text which is also present in prefix of pattern
// So next transition will be to state that say we have read this string

// Here is it just "" or "n" or "na" ?
// As in text "na" of "bana na" is also present / and has been matched earlier - 
// And its also present in Pattern P "nano" s "na" , "na" in "nano"
// So transit to state "na" when mismatch happened because of "nana" in text with "nano" of pattern

// So the longest match that is both Prefix of pattern P and Suffix of text T that has been matched

/*
    In general the transition from state+character to state is 
       the longest string that's simultanously a prefix of the original pattern 
       and a suffix of the state+character we've just seen. 

    This is enough to tell us what all the transitions should be. If we're looking for pattern "nano", the transition table would be

    empty is ""


            n       a       o       other
            ---     ---     ---     ---
    empty:  "n"     empty   empty   empty
    "n":    "n"     "na"    empty   empty
    "na":   "nan"   empty   empty   empty
    "nan":  "n"     "na"    "nano"  empty
    "nano": "nano"  "nano"  "nano"  "nano"

    This matches only one occurance of patter P in text T

   
    For instance the entry 
    in row "nan" and column n says that 
    the largest string that's simultaneously BOTH 
	a prefix of "nano" and
	AND
	a suffix of "nan"+n="nann" is simply "n".

    T = "ba nana nano" , "nana" had mismatch at "a" , 
	so Suffix of "nana" : "" , "a" , "na" , "ana" and "nana" that also matches 
	the suffix of P "nano" : "" , "n" , "na" , "nan" and "nano"
    Match are "" and "na"
    But Longest match is "na" , so the transition happens to state "na"

    Why ?

*/

// Run time - Build Transation Table + Transations when n characters of T are checked

// To specify the string-matching automaton corresponding to a given pattern P 
//  P[1 . . m], we first define an auxiliary function sigma called as - Suffix function
// Suffix function

// The function maps Sigma* { 0 , 1 , m } such that 
//     sigma(x) is the length of the longest prefix of P 
//		       that is also a suffix of x


// Now how to build Transition table
// The table size will be pattern length m times number of characters in Sigma
// m * | Sigma |

// | Sigma | represents cardinality of set Sigma , the Alphabet

/* 
    FINITE_AUTOMATON_MATCHER ( Text T ; Delta Transition Table ; Pattern m )
        // So we should have the Delta , Transition table ready
        // Ok lets assume we have it and proceed with algorithm


        n = T.length  // Initialize , length of Text
        q = 0         // Start state
                      // q is state

        for i = 1 to n   // Observe , no two loops : one for text one for pattern
                         // Its only one run over text length 

            q = Delta ( state q , T[i] ); 
                         // Find the state string matching machine would be in
                         //     After reading character T[i]

                         // If the present state is q and character T[i] is being read
                         //     to which state will the machine transit to    

            if q == m    // What does this mean 
                         // Machine is in final state

                print “Pattern occurs with shift” i - m
*/

/*
    Lets complete our Assumption now : 
    
    Compute_Transition_Function ( Patter P , Alphabet Sigma )
        // We will construct a table - P x | Sigma |

        m = P.length ; // Get the length of pattern
        //                             We will have m states
        //                             m x | Sigma | 

        for q = 0 to m     // For each state                

            for each character a in Sigma     // for each characte in Sigma

                k = min ( m + 1 ,  q + 2 )    // Why this , will update on this

                repeat
                    k = k - 1

                until Prefix of pattern P of length k = Suffix with chatacter a
                      longest prefix of P that is also a suffix of x
                        
                      P = S.a
                      Pk = Pq . a
                      // Pq , We have come till here matching some pattern and
                      // in state q , so pattern matched till now plus new a
                      // Pattern matched till now plus new character a
                      // This resembles match , so go to next state 

                // Then 
                Delta ( q , a ) = k

    return Delta
*/

//  Pk
//  Pq



// What if all characters in text T are made up of character in pattern P
// What if few
// What if none

// None is straight forward - shift back to state "" , same as shift by entire length in Horspool algorithm
// Few and All

// Maintain longest match at each state


// INVARIANT ?

// Can we do better ?
// Yes !
// Knuth-Morris-Pratt

// Comparision

// m is size of shift table
// n is size of string

// Algorithm 		Preprocessing time 		Matching time
//  Naive			0				O( ( n - m + 1) m )
//  Rabin-Karp			Theta(m)			O( ( n - m + 1) m )
//  Finite automaton		O( m |Sigma| )			Theta(n)
//  Knuth-Morris-Pratt		Theta(m)			Theta(n)

	
import java.util.Scanner;

class FiniteStateMachineBasedStringSearch
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

/*
    Transition Table

                     n       a       o       other                
           State    ---     ---     ---     ---
    empty:    0    "n"     empty   empty   empty
    "n":      1    "n"     "na"    empty   empty
    "na":     2    "nan"   empty   empty   empty
    "nan":    3    "n"     "na"    "nano"  empty
    "nano":   4    "nano"  "nano"  "nano"  "nano"


                n       a       o       other                
               ---     ---     ---     ---
    empty:  0   1       0       0       0
    "n":    1  "n"     "na"    empty   empty
    "na":   2  "nan"   empty   empty   empty
    "nan":  3  "n"     "na"    "nano"  empty
    "nano": 4  "nano"  "nano"  "nano"  "nano"

Pattern P = "nano"

Sigma = { n , a , o }

m = 3

Prefix and suffix of "nano"
    Prefixes   Sufixes
     -----      ----
     ""          ""
     "n"         "o"
     "na"        "no"
     "nan"       "ano" 
     "nano"      "nano"

Prefix of pattern P and suffix of ""
    Prefixes   Sufixes
     -----      ----
     ""          ""
     "n"
     "na"
     "nan"
     "nano"

Prefix pf pattern P and suffix of "n"
    Prefixes   Sufixes
     -----      ----
     ""          ""
     "n"         "n"
     "na"
     "nan"
     "nano"


Prefix of pattern P and suffix of "na"
    Prefixes   Sufixes
     -----      ----
     ""          ""
     "n"         "a"
     "na"        "na"
     "nan"
     "nano"


Prefix and suffix of "nan"
    Prefixes   Sufixes
     -----      ----
     ""          ""
     "n"         "n"
     "na"        "an"
     "nan"       "nan"


For q = 0 , And for { n , a, o }
    For n
       k = min ( 3 + 1 , 0 + 2 )
       k = 2

       we are in state 0 , and we are matching ""."n"
                                       zero state."n" 
                                                 . here is concatenation

       here "x" is "n"
       longest prefix of P that is also a suffix of x is "n"
       Hence the transition to state 1

       That is Delta( state 0 , "n" ) is "n" = 1
       1 = Delta( state 0 , "n" )       

    For a
       State is still 0 , "" has been matched
       now  
       here "x" is "a"
       longest prefix of P that is also a suffix of x is ""
       Hence the transition to state 1


    For o
*/

//   The quick brown fox jumps over the lazy dog
