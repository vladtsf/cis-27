package lab2;

import java.util.LinkedList;

public class NaturalMergeSort extends SortAlgorithm {
    public static Comparable[] aux;
    
    public static void sort(LinkedList<Comparable> a) {
        int N = a.size();
        aux = new Comparable[N];
        assert isSorted(a, 0, N - 1);
        
        sort(a, 0, N);
    }
    
    private static void sort(LinkedList<Comparable> a, int lo, int hi) {
        int N = a.size();
        
        int mid = seq(a, lo, hi);
        // lhi = local high        
        int lhi = seq(a, mid + 1, hi);
        
        if(lhi >= N) return;
        
        // merging two sequences
        merge(a, lo, mid, lhi);
        
        // merging recursively sort the rest
        sort(a, lhi + 1, hi);
        
        // merge left and right sides       
        merge(a, lo, lhi, hi - 1);
        
    }
    
    private static int seq(LinkedList<Comparable> a, int lo, int hi) {
        int i;
        
        for(i = lo; i < hi - 1 && less(a.get(i), a.get(i+1));) i++;
        
        return i;
    }
    
    private static void merge(LinkedList<Comparable> a, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a.get(k); 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              
                a.set(k,aux[j++]);
            else if (j > hi)               
                a.set(k,aux[i++]);
            else if (less(aux[j], aux[i])) 
                a.set(k,aux[j++]);
            else                           
                a.set(k,aux[i++]);
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }
}
