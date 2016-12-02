package lab2;

public class ImprovedQuickSort extends SortAlgorithm {

    private ImprovedQuickSort() { }
    private static int M = 30;
    
    public static void setM(int m) {
        M = m;
    }

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // adopted from the book and reworked to support the cutoff to Insertion Sort   
    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi - lo < M) {
            insertionSort(a, lo, hi);
            return;
        }
        
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }
    
    // adopted from the book and rewritten to work with subarrays 
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for(int i = lo + 1; i <= hi; i++) {
            for(int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // taken from the book and slightly modified    
    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = getPivot(a, lo, hi);
        while (true) { 

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    
    // I chose to go with any random element     
    private static Comparable getPivot(Comparable[] a, int lo, int hi) {
        return a[uniform(lo, hi)];
    }
}


/*

Quick Sort
[a, b, b, b, c, d, d, e, e, e, g, g, h, i, i, i, i, j, j, j, k, k, l, l, l, l, m, m, k, m, m, m, m, n, o, o, p, p, q, q, r, s, s, t, t, p, t, t, u, v, v, v, w, w, w, w, z, z, z, z]

*/