using System;

namespace KnuthMorrisPratt
{
	// C# program for implementation of Knuth Morris Pratt pattern
	// searching algorithm

	class KMP_String_Matching
	{
		public void KMPSearch(String pat, String txt)
		{			
			
			int M = pat.Length;
			int N = txt.Length;

			// create lps[] that will hold the longest
			// prefix suffix values for pattern
			int[] lps = new int[M];
			int j = 0;  // index for pat[]

			// Preprocess the pattern (calculate lps[]
			// array)
			computeLPSArray(pat,M,lps);

			int i = 0;  // index for txt[]
			while (i < N)
			{				
				if (pat[j] == txt[i])
				{
					j++;
					i++;
				}
				if (j == M)
				{
					Console.WriteLine("Found pattern "+ "at index " + (i-j));
					j = lps[j-1];
				}

				// mismatch after j matches
				else if (i < N && pat[j] != txt[i])
				{
					// Do not match lps[0..lps[j-1]] characters,
					// they will match anyway
					if (j != 0)
						j = lps[j-1];
					else
						i = i+1;
				}
			}
		}

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
			
	}

	class MainClass
	{
		// Driver program to test above function
		public static void Main(String[] args)
		{
			String txt = "ABABDABACDABABCABAB";
			String pat = "ABABCABAB";
			new KMP_String_Matching().KMPSearch(pat,txt);
		}
	}
}
