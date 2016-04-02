package lab2.priority;

import lab2.priority.JobRunner.*;

public class RRJobStore extends FiFoJobStore {
    @Override
    public void work(int time) {
//        if(lastRemoved == null) {
//            lastRemoved = jobs.remove();
//        }
//        
        do {
            Job job = jobs.getFirst();
            
            int spent = job.work(time);
            
            reportWork(time);
            reportWait(spent, job);
            
            if(job.isDone()) {
                done.add(jobs.remove());
            }
            
            time -= spent;
        } while(time > 0 && jobs.size() > 0);
    }
}
