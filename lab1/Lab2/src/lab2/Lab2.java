package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import lab2.priority.FiFoJobStore;
import lab2.priority.JobRunner;
import lab2.priority.RRJobStore;
import lab2.priority.SJFJobStorage;

public class Lab2 extends SortAlgorithm {

    public static void main(String[] args) {
        System.out.println("Natural Merge Sort");
        LinkedList<Comparable> li = new LinkedList<>(Arrays.asList(3, 4, 2, 1, 7, 5, 8, 9, 0, 6));
        NaturalMergeSort.sort(li);
        System.out.println(li.toString());
        
//        System.out.println("Sorting 10^4");
//        LinkedList<Comparable> ll2 = new LinkedList<>(Arrays.asList(generateArrayOfSize(10000)));
//        NaturalMergeSort.sort(ll2);
        
//        System.out.println("Quick Sort");
//        String[] arr = new String[] {"t", "l", "m", "k", "l", "s", "e", "c", "i", "g", "i", "o", "w", "z", "i", "m", "v", "v", "r", "p", "e", "t", "q", "m", "a", "d", "b", "d", "m", "w", "w", "t", "p", "u", "n", "i", "w", "b", "k", "k", "z", "v", "l", "m", "l", "q", "z", "j", "g", "o", "b", "t", "p", "e", "z", "j", "j", "h", "m", "s"};
//        ImprovedQuickSort.sort(arr);
//        System.out.println(Arrays.toString(arr));
////        calculateFastestM();
//
//
//        // job processing
//        System.out.println("\nJob Processing");
//        int[] jobWaits = generateJobWaits(100);
//        for(int i = 5; i <= 20; i+=5) {
//            testRunners(jobWaits, i);
//        }
    }
    
    private static void testRunners(int[] jobWaits, int timeSlice) {
        JobRunner fifo = new JobRunner(new FiFoJobStore(), timeSlice);
        JobRunner rr = new JobRunner(new RRJobStore(), timeSlice);
        JobRunner sjf = new JobRunner(new SJFJobStorage(), timeSlice);
        
        fillRunnerWithJobs(fifo, jobWaits);
        fillRunnerWithJobs(rr, jobWaits);
        fillRunnerWithJobs(sjf, jobWaits);
        
        fifo.process();
        rr.process();
        sjf.process();
        
        printRunnerStats(fifo, "FiFo");
        printRunnerStats(rr, "Round Robin");
        printRunnerStats(sjf, "SJF");
    }
    
    private static void fillRunnerWithJobs(JobRunner runner, int[] waits) {
        for(int i = 0; i < waits.length; i++) {
            runner.add(new JobRunner.Job(waits[i]));
        }
    }
    
    private static int[] generateJobWaits(int n) {
        int[] waits = new int[n];
        
        for(int i = 0; i < n; i++) {
            waits[i] = uniform(1, 100);
        }
        
        return waits;
    }
    
    private static void printRunnerStats(JobRunner runner, String name) {
        System.out.println(
            "name=" + name
            + ", avg. turnaround=" + (double) runner.getTotalTurnaround() / runner.count()
            + ", avg. wait=" + (double) runner.getTotalWait()/ runner.count()
            + ", time slice=" + runner.getTimeSlice()
        );
    }
    
    // prints data pastable to Excel    
    private static void calculateFastestM() {
        long startTime, elapsedTime;
        
        String[][] arrays = new String[][] {
            generateArrayOfSize((int) Math.pow(10, 3)),
            generateArrayOfSize((int) Math.pow(10, 4)),
            generateArrayOfSize((int) Math.pow(10, 5)),
            generateArrayOfSize((int) Math.pow(10, 6)),
        };
        
        String[] results = new String[32];
        
        results[0] = "\tN=10^3\tN=10^4\tN=10^5\tN=10^6";
        
        for(int s = 0; s < 4; s++) {
            String[] arr = arrays[s];
            
            for(int m = 0; m <= 30; m++) {
                if(s == 0) {
                    results[m+1] = Integer.toString(m);
                }
                
                ImprovedQuickSort.setM(m);
                shuffle(arr);

                startTime = System.currentTimeMillis();
                ImprovedQuickSort.sort(arr);
                elapsedTime = System.currentTimeMillis() - startTime;
                results[m+1] += "\t" + Long.toString(elapsedTime);
                System.out.print(".");
            }
        }
        
        System.out.println("\n" + String.join("\n", results));
    }
    
    private static String[] generateArrayOfSize(int n) {
        String[] arr = new String[n];
        
        for(int i = n - 1; i >= 0; i--) {
            arr[i] = new String(Character.toChars(uniform(90, 130)));
        }
        
        return arr;
    }
    
}
