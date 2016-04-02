package lab2.priority;

import java.util.LinkedList;
import lab2.priority.JobRunner.*;

public class FiFoJobStore extends JobStorage {
    protected LinkedList<Job> jobs;
    protected LinkedList<Job> done;
    private Job lastRemoved = null;

    public FiFoJobStore() {
        this.jobs = new LinkedList<>();
        this.done = new LinkedList<>();
    }
        
    @Override
    public void add(Job job) {
        super.add(job);
        jobs.add(job);
    }
    
    @Override
    public JobRunner.Job[] getAll() {
        Job[] result = new Job[jobs.size()];
        return jobs.toArray(result);
    }
    
    @Override
    public JobRunner.Job[] getDone() {
        Job[] result = new Job[done.size()];
        return done.toArray(result);
    }
    
    @Override
    public void work(int time) {
        if(lastRemoved == null) {
            lastRemoved = jobs.remove();
        }
        
        while(time > 0 && lastRemoved != null) {
            int spent = lastRemoved.work(time);;
            
            time -= spent;
            reportWork(spent);
            reportWait(spent, lastRemoved);
            
            if(lastRemoved.isDone()) {
                done.add(lastRemoved);
                lastRemoved = null;
            }
        }
    }
    
}
