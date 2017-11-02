import java.io.*;
/**
 * Created by brhee on 1/24/17.
 */
public class PercolationStats {
    double[] time;
    double total;
    double meanTime;
    double devTime;
    double[] prob;
    double meanp;
    double devp;
    public PercolationStats(int n) {
        time = new double[n];
        prob = new double[n];
        total = 0.0;
        meanTime = 0.0;
        devTime = 0.0;
        meanp = 0.0;
        devp = 0.0;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(t);
        String type = args[2];
        if (type.equals("slow")) {
            for (int i = 0; i < t; i++) {
                double counter = 0.0;
                PercolationQuick perc = new PercolationQuick(n);
                Stopwatch watch = new Stopwatch();
                while (!perc.percolates()) {
                    int x = StdRandom.uniform(n);
                    int y = StdRandom.uniform(n);
                    if(!perc.isOpen(n - 1 - x,y)) {
                        perc.open(x , y);
                        counter++;
                    }
                }
                stats.time[i] = watch.elapsedTime();
                stats.prob[i] = counter / (n * n);
            }
            stats.total = StdStats.sum(stats.time);
            stats.meanTime = StdStats.mean(stats.time);
            stats.devTime = StdStats.stddev(stats.time);
            stats.meanp = StdStats.mean(stats.prob);
            stats.devp = StdStats.stddev(stats.prob);
        }
        else if (type.equals("fast")) {
            for (int i = 0; i < t; i++) {
                double counter = 0.0;
                Percolation perc = new Percolation(n);
                Stopwatch watch = new Stopwatch();
                while (!perc.percolates()) {
                    int x = StdRandom.uniform(n);
                    int y = StdRandom.uniform(n);
                    if(!perc.isOpen(n - 1 - x,y)) {
                        perc.open(x , y);
                        counter++;
                    }
                }
                stats.time[i] = watch.elapsedTime();
                stats.prob[i] = counter / (n * n);
            }
            stats.total = StdStats.sum(stats.time);
            stats.meanTime = StdStats.mean(stats.time);
            stats.devTime = StdStats.stddev(stats.time);
            stats.meanp = StdStats.mean(stats.prob);
            stats.devp = StdStats.stddev(stats.prob);
        }
        System.out.println("mean threshold=" + stats.meanp);
        System.out.println("std dev=" + stats.devp);
        System.out.println("time=" + stats.total);
        System.out.println("mean time=" + stats.meanTime);
        System.out.println("stddev time=" + stats.devTime);
        System.out.println();
    }
}
