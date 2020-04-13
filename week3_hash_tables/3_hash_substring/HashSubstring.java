import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static int multiplier = 256;
    private static long prime = 101;

    private static List<Integer> getOccurrences(Data input) {
        String pat = input.pattern;
        String txt = input.text;
        int m = pat.length(), n = txt.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        long hpattern = 0; // hash for pattern
        long hs = 0; // hash for string
        int i, j;
        long h = 1; 
        // The value of h would be "pow(d, M-1)%q" 
        for (i = 0; i < m-1; i++) {
            h = (h*multiplier)%prime;
        }
        // Calculate the hash value of pattern and first window of text
        for (i = 0; i < m; i++) 
        { 
            hpattern = (multiplier * hpattern + pat.charAt(i)) % prime;
            hs = (multiplier * hs  + txt.charAt(i)) % prime;
        } 
        // Slide the pattern over text one by one 
        for (i = 0; i <= n-m; i++) 
        {
            // Check the hash values of current window of text 
            // and pattern. If the hash values match then only 
            // check for characters on by one 
            if ( hpattern == hs ) 
            { 
                /* Check for characters one by one */
                for (j = 0; j < m; j++) 
                { 
                    if (txt.charAt(i+j) != pat.charAt(j)) 
                        break; 
                } 
      
                // if p == t and pat[0...m-1] = txt[i, i+1, ...i+m-1] 
                if (j == m) 
                    occurrences.add(i);
            }
            // Calculate hash value for next window of text: Remove 
            // leading digit, add trailing digit 
            if ( i < n-m ) 
            { 
                hs = (multiplier*(hs - txt.charAt(i)*h) + txt.charAt(i+m)) % prime;
                // We might get negative value of t, converting it 
                // to positive 
                if (hs < 0) 
                hs = (hs + prime); 
            } 
      
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
