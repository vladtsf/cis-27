package lab2;

import java.util.LinkedList;
import java.util.Random;

// Helpers from the book
public class SortAlgorithm {
    private static Random random;    // pseudo-random number generator
    private static long seed;        // pseudo-random number generator seed
    
    static {
        // this is how the seed was set in Java 1.4
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }
    
    protected static int uniform(int n) {
        if (n <= 0) throw new IllegalArgumentException("Parameter N must be positive");
        return random.nextInt(n);
    }
    
    protected static int uniform(int a, int b) {
        if (b <= a) throw new IllegalArgumentException("Invalid range");
        if ((long) b - a >= Integer.MAX_VALUE) throw new IllegalArgumentException("Invalid range");
        return a + uniform(b - a);
    }
    
    protected static void shuffle(Object[] a) {
        if (a == null) throw new NullPointerException("argument array is null");
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n-i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    
    // is v < w ?
    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    protected static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    protected static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    
    protected static boolean isSorted(LinkedList<Comparable> a) {
        return isSorted(a, 0, a.size() - 1);
    }
    
    protected static boolean isSorted(LinkedList<Comparable> a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a.get(i), a.get(i-1))) return false;
        return true;
    }

    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
}
