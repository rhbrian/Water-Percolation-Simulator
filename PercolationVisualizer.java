import java.io.*;
/**
 * Created by brhee on 1/24/17.
 */
public class PercolationVisualizer {
    public static void main(String[] args) throws IOException {
        PrintStream ps = System.out;
        File file = new File("visualMatrix.txt");

        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);

        FileOutputStream filestream = new FileOutputStream(file);
        PrintStream fps = new PrintStream(filestream);

        System.setOut(ps);
        System.out.println(n);
		System.out.println();
		System.setOut(fps);
        StdOut.println(n);
        StdOut.println();
     	
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            perc.open(p , q);
         	System.setOut(ps);
            perc.print(perc.grid);
            System.out.println();
            System.setOut(fps);
            perc.print(perc.grid);
            System.out.println();
        }
    }
}

