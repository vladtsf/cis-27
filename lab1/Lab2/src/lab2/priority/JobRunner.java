package lab2.priority;

public class JobRunner {
    public static final int TIME_SLICE = 20;
            
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
    
    public JobRunner(JobStorage storage) {
        jobs = storage;
    }
    
    public void add(Job job) {
        jobs.add(job);
    }
    
    public void process() {
        while(!jobs.isDone()) {
            jobs.work(TIME_SLICE);
        }
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
