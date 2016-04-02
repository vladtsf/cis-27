package lab2.priority;

import java.util.LinkedList;
import lab2.priority.JobRunner.Job;

public class SJFJobStorage extends JobStorage {
    private LinkedList<Object> done;
    private JobPQ jobs = new JobPQ();
    
    public SJFJobStorage() {
        this.done = new LinkedList<>();
    }
    
    @Override
    public void add(Job job) {
        super.add(job);
        jobs.insert(job);
    }
    
    @Override
    public void work(int time) {
        do {
            Job job = jobs.max();
            
            int spent = job.work(time);
            
            reportWork(time);
            reportWait(spent, job);
            
            if(job.isDone()) {
                done.add(jobs.delMax());
            }
            
            time -= spent;
        } while(time > 0 && jobs.size() > 0);
    }

    @Override
    public Job[] getAll() {
        return jobs.getAll();
    }

    @Override
    public JobRunner.Job[] getDone() {
        Job[] result = new Job[done.size()];
        return done.toArray(result);
    }
    
}
