package lab2;

import java.util.LinkedList;

public class NaturalMergeSort extends SortAlgorithm {
    public static Comparable[] aux;
    
    public static void sort(LinkedList<Comparable> a) {
        int N = a.size();
        aux = new Comparable[N];
        
        int lo, mid, lhi;
        
        while(!isSorted(a)) {
            lo = 0;
            
            do {
                mid = seq(a, lo, N - 1);
                lhi = seq(a, mid + 1, N - 1);
                
                if(lhi >= N) {
                    break;
                }
                
                merge(a, lo, mid, lhi);
                lo = lhi + 1;
            } while(true);
        }
    }
    
    private static int seq(LinkedList<Comparable> a, int lo, int hi) {
        int i = lo;
        
        while(i < hi - 1) {            
            if(less(a.get(i+1), a.get(i))) {
                return i;
            }
            
            i++;
        }
        
        return i;        
    }
    
    private static void merge(LinkedList<Comparable> a, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        a.subList(lo, hi + 1).toArray(aux);

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a.set(k,aux[j++-lo]);
            else if (j > hi)               a.set(k,aux[i++-lo]);
            else if (less(aux[j-lo], aux[i-lo])) a.set(k,aux[j++-lo]);
            else                           a.set(k,aux[i++-lo]);
        }
        
        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
        
        // DEBUG, see results below        
//        System.out.println(lo + " " + mid + " " + hi + ": " + a.toString());
    }
}

/*
Natural Merge Sort
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
Sorting 10^4
BUILD SUCCESSFUL (total time: 2 seconds)
*/


/*

DEBUG
l m h  [list]
0 1 2: [2, 3, 4, 1, 7, 5, 8, 9, 0, 6]
3 4 7: [2, 3, 4, 1, 5, 7, 8, 9, 0, 6]
8 8 9: [2, 3, 4, 1, 5, 7, 8, 9, 0, 6]
0 2 7: [1, 2, 3, 4, 5, 7, 8, 9, 0, 6]
8 8 9: [1, 2, 3, 4, 5, 7, 8, 9, 0, 6]
0 7 8: [0, 1, 2, 3, 4, 5, 7, 8, 9, 6]
0 8 9: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

*/