import java.io.*;
/**
 * Created by brhee on 1/24/17.
 */
public class Percolation {
    int[][] grid;
    int size;
    WeightedQuickUnionUF uf;
    public Percolation(int n) {
        size = n;
        grid = new int[n][n];
        uf = new WeightedQuickUnionUF(n*n);
    }

    public int getArrNum(int x, int y) {
        return size * x + y;
    }

    public void open(int x, int y) {
        if (size - x - 1 == 0) {
            grid[size - x - 1][y] = 2;
        }
        else {grid[size - x - 1][y] = 1;}

        if (size - 1 - x + 1 < size && isOpen(size - 1 - x + 1 , y)) {
            if (!uf.connected(getArrNum(size - x - 1 , y), getArrNum(size - x - 1 + 1 , y))) {
                uf.union(getArrNum(size - x - 1 , y), getArrNum(size - x - 1 + 1 , y));
            }
            if (grid[size - x + 1 - 1][y] == 2) {
                grid[size - x - 1][y] = 2;
            }
        }
        if (size - 1 - x - 1 >= 0 && isOpen(size - 1 - x - 1 , y)) {
            if (!uf.connected(getArrNum(size - x - 1 , y), getArrNum(size - x - 1 - 1 , y))) {
                uf.union(getArrNum(size - x - 1 , y), getArrNum(size - x - 1 - 1 , y));
            }
            if (grid[size - x - 1 - 1][y] == 2) {
                grid[size - x - 1][y] = 2;
            }
        }
        if (y + 1 < size && isOpen(size - 1 - x , y + 1)) {
            if (!uf.connected(getArrNum(size - x - 1 , y) , getArrNum(size - x - 1 , y + 1))) {
                uf.union(getArrNum(size - x - 1, y) , getArrNum(size - x - 1 , y + 1));
            }
            if (grid[size - x - 1][y + 1] == 2) {
                grid[size - x - 1][y] = 2;
            }
        }
        if (y - 1 >= 0 && isOpen(size - 1 - x , y - 1)) {
            if (!uf.connected(getArrNum(size - x - 1 , y) , getArrNum(size - x - 1 , y - 1))) {
                uf.union(getArrNum(size - x - 1 , y) , getArrNum(size - x - 1, y - 1));
            }
            if (grid[size - x - 1][y - 1] == 2) {
                grid[size - x - 1][y] = 2;
            }
        }
        if (grid[size - x - 1][y] == 2) {
            for (int i = 0; i < size * size; i++) {
                if (uf.connected(getArrNum(size - x - 1 , y) , i) && i != getArrNum(size - x - 1 , y)) {
                    grid[i / size][i % size] = 2;
                }
            }
        }
    }

    public boolean isOpen(int x, int y) {
        if (grid[x][y] == 1 || grid[x][y] == 2) return true;
        else return false;
    }

    public boolean isFull(int x, int y) {
        if (grid[size - x - 1][y] == 0) return false;
        if (grid[size - x - 1][y] == 2) return true;
        for (int i = 0; i < size; i++) {
            if (uf.connected(getArrNum(size - x - 1, y) , getArrNum(0 , i )) /*&& isOpen(size - 1 , i)*/) {
                return true;
            }
        }
        return false;
    }
 
    public boolean percolates() {
        for (int y = 0; y < size; y++) {
            if (grid[size - 1][y] == 2) return true;
        }
        return false;
    }

 	public void print(int[][] m) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                System.out.print(m[x][y] + " ");
            }
            System.out.println();
        }
    }
 
    public static void main(String[] args) throws IOException {
     	int n = StdIn.readInt();
        Percolation perc = new Percolation(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            perc.open(p , q);
        }
        
        if (perc.percolates()) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
