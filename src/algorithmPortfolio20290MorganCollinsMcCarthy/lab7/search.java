package algorithmPortfolio20290MorganCollinsMcCarthy.lab7;

public class search {

	public boolean search(String text, String pat) {
		for (int i = 0; i < text.length() - pat.length(); i++) { //string search
			int j = 0;
			while ((j < pat.length()) && (text.charAt(i + j) == pat.charAt(j))) {
				j++; //here we increase the found size while the next char is equal
			}
			if (j == pat.length()) { //true when the string is found
				return true;
			}
		}

		return false;
	}

	public boolean KMPSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[]
		// array)
		computeLPSArray(pat, M, lps);

		int x = 0;
		while (x < N) {
			if (pat.charAt(j) == txt.charAt(x)) {
				x++;
				j++;
			}
			if (j == M) {
				return true;
			} else if (x < N && pat.charAt(j) != txt.charAt(x)) {
				if (j != 0)
					j = lps[j - 1];
				else
					x++;
			}
		}
		return false;
	}

	void computeLPSArray(String pat, int M, int lps[]) {
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else // (pat[i] != pat[len])
			{
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar
				// to search step.
				if (len != 0) {
					len = lps[len - 1];

					// Also, note that we do not increment
					// i here
				} else // if (len == 0)
				{
					lps[i] = len;
					i++;
				}
			}
		}
	}
}
