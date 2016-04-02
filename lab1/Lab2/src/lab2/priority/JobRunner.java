package lab2.priority;

public class JobRunner {
    public static final int DEFAULT_TIME_SLICE = 20;
            
    public static class Job implements Comparable<Job> {
        
        protected int workTime;
        protected int worked;
        protected int waited;
        
        public Job(int workTime) {
            this.workTime = workTime;
            this.worked = 0;
            this.waited = 0;
        }

        @Override
        public int compareTo(Job other) {
            return Integer.compare(this.workTime, other.workTime);
        }
        
        public boolean isDone() {
            return worked >= workTime;
        }
        
        public int getWorkTime() {
            return workTime;
        }
        
        public int getWait() {
            return waited;
        }
        
        public int getTurnaround() {
            return waited + worked;
        }
        
        public int work(int time) {
            int toWork = Math.min(time, workTime - worked);
            worked += toWork;
            
            return toWork;
        }
        
        public void wait(int time) {
            waited += time;
        }
    }
    
    private JobStorage jobs;            
    private int timeSlice;
    
    public JobRunner(JobStorage storage) {
        this(storage, DEFAULT_TIME_SLICE);
    }
    
    public JobRunner(JobStorage storage, int timeSlice) {
        jobs = storage;
        this.timeSlice = timeSlice;
    }
    
    public void add(Job job) {
        jobs.add(job);
    }
    
    public void process() {
        while(!jobs.isDone()) {
            jobs.work(timeSlice);
        }
    }
    
    public int getTimeSlice() {
        return timeSlice;
    }
    
    public int getTotalTurnaround() {
        int total = 0;
        
        for(Job job : jobs.getDone()) {
            total += job.getTurnaround();
        }
        
        return total;
    }
    
    public int getTotalWait() {
        int total = 0;
        
        for(Job job : jobs.getDone()) {
            total += job.getWait();
        }
        
        return total;
    }
    
    public int count() {
        return jobs.getAll().length + jobs.getDone().length;
    }
}


/*

Job Processing
name=FiFo, avg. turnaround=2564.25, avg. wait=2512.93, time slice=5
name=Round Robin, avg. turnaround=3391.54, avg. wait=3340.22, time slice=5
name=SJF, avg. turnaround=2531.74, avg. wait=2482.1, time slice=5
name=FiFo, avg. turnaround=2564.25, avg. wait=2512.93, time slice=10
name=Round Robin, avg. turnaround=3379.75, avg. wait=3328.43, time slice=10
name=SJF, avg. turnaround=2140.31, avg. wait=2092.41, time slice=10
name=FiFo, avg. turnaround=2564.25, avg. wait=2512.93, time slice=15
name=Round Robin, avg. turnaround=3374.5, avg. wait=3323.18, time slice=15
name=SJF, avg. turnaround=1810.55, avg. wait=1764.61, time slice=15
name=FiFo, avg. turnaround=2564.25, avg. wait=2512.93, time slice=20
name=Round Robin, avg. turnaround=3371.85, avg. wait=3320.53, time slice=20
name=SJF, avg. turnaround=1719.0, avg. wait=1673.72, time slice=20

*/