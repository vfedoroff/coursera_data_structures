import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;
    private PriorityQueue<Worker> minPriQueue;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
        //test();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        minPriQueue = new PriorityQueue<>(numWorkers);
        for (int i = 0; i < numWorkers; i++) {
            minPriQueue.add(new Worker(i));
        }
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            Worker bestWorker = minPriQueue.poll();
            assignedWorker[i] = bestWorker.index;
            startTime[i] = bestWorker.currentFinishTime;
            bestWorker.currentFinishTime += duration;
            minPriQueue.add(bestWorker);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }


    private static void test() {
        int numWorkers = 2;
        int[] jobs = new int[]{1, 2, 3, 4, 5};
        JobQueue jobQueue = new JobQueue();
        jobQueue.jobs = jobs;
        jobQueue.numWorkers = numWorkers;
        jobQueue.assignJobs();

        for (int i = 0; i < jobs.length; ++i) {
            System.out.println(jobQueue.assignedWorker[i] + " " + jobQueue.startTime[i]);
        }
        System.out.println();

        numWorkers = 4;
        jobs = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        jobQueue = new JobQueue();
        jobQueue.jobs = jobs;
        jobQueue.numWorkers = numWorkers;
        jobQueue.assignJobs();

        for (int i = 0; i < jobs.length; ++i) {
            System.out.println(jobQueue.assignedWorker[i] + " " + jobQueue.startTime[i]);
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

    static class Worker implements Comparable<Worker> {
        int index;
        long currentFinishTime;

        public Worker(int index) {
            this.index = index;
        }

        /**
         * Sort based on the finishTime and index
         *
         * @param w
         * @return
         */
        @Override
        public int compareTo(Worker w) {
            if (currentFinishTime < w.currentFinishTime) {
                return -1;
            } else if (currentFinishTime == w.currentFinishTime) {
                if (index < w.index) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (currentFinishTime > w.currentFinishTime) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "" + index;
        }
    }
}
