import java.util.*;
import java.io.*;

public class tree_height {
	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int root;
		ArrayList<ArrayList<Integer>> children;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			children = new ArrayList<ArrayList<Integer>>(n);
			for (int i = 0; i < n; i++)
				children.add(new ArrayList<Integer>());
			for (int i = 0; i < n; i++) {

				int parent = in.nextInt();
				if (parent == -1)
					root = i;
				else {

					children.get(parent).add(i);
				}
			}
		}

		int compute(int root) {
			int max = 0;
			for (int i = 0; i < children.get(root).size(); i++) {
				max = Math.max(max, compute(children.get(root).get(i)));
			}
			return max + 1;
		}

		int computeHeight() {
			return compute(root);
		}
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_height().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
