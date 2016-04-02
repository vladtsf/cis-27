package lab2.priority;

import lab2.priority.JobRunner.*;

public abstract class JobStorage {
    protected int work = 0;
    protected int worked = 0;
    
    public void add(Job job) {
        work += job.getWorkTime();
    }
    
    public boolean isDone() {
        return worked >= work;
    }
    
    public abstract void work(int time);
    
    public void reportWork(int time) {
        worked += time;
    }
    
    protected void reportWait(int time, Job worker) {
        for(Job job : getAll()) {
            if(job != worker && !job.isDone()) {
                job.wait(time);
            }
        }
    }
    
    public abstract Job[] getAll();
    public abstract Job[] getDone();
}
