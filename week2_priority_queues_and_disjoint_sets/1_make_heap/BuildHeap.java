import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BuildHeap {
  private int[] data;
  private List<Swap> swaps;

  private FastScanner in;
  private PrintWriter out;

  public static void main(String[] args) throws IOException {
    new BuildHeap().solve();
    //manualTest();
  }

  private static void manualTest() {
    int[] arr = new int[] { 5, 4, 3, 2, 1 };
    BuildHeap heap = new BuildHeap();
    heap.data = arr;
    heap.generateSwaps();

    for (Swap swap : heap.swaps) {
      System.out.println(swap.index1 + " " + swap.index2);
    }
    System.out.println();
    System.out.println(Arrays.toString(heap.data));
    System.out.println();
  }

  private void readData() throws IOException {
    int n = in.nextInt();
    data = new int[n];
    for (int i = 0; i < n; ++i) {
      data[i] = in.nextInt();
    }
  }

  private void writeResponse() {
    out.println(swaps.size());
    for (Swap swap : swaps) {
      out.println(swap.index1 + " " + swap.index2);
    }
  }

  private int getLeftChild(int i) {
    return 2 * i + 1;
  }

  private int getRightChild(int i) {
    return 2 * i + 2;
  }

  private void repairHeap() {
    for (int i = (data.length / 2); i >= 0; i--) {
      siftDown(i);
    }
  }

  private void siftDown(int i) {
    int minIndex = i;
    int left = getLeftChild(i);
    if (left < data.length && data[left] < data[minIndex]) {
      minIndex = left;
    }

    int right = getRightChild(i);
    if (right < data.length && data[right] < data[minIndex]) {
      minIndex = right;
    }

    if (i != minIndex) {
      swap(minIndex, i);
      siftDown(minIndex);
    }
  }

  private void swap(int i, int j) {
    swaps.add(new Swap(j, i));
    int tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
  }

  private void generateSwaps() {
    swaps = new ArrayList<Swap>();
    if (data.length == 0) {
      return;
    }
    this.repairHeap();
  }

  public void solve() throws IOException {
    in = new FastScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    readData();
    generateSwaps();
    writeResponse();
    out.close();
  }

  static class Swap {
    int index1;
    int index2;

    public Swap(int index1, int index2) {
      this.index1 = index1;
      this.index2 = index2;
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
