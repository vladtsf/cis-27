package lab2.priority;

import java.util.NoSuchElementException;
import lab2.priority.JobRunner.*;

public class RRJobStore extends FiFoJobStore {
    @Override
    public void work(int time) {
        try {
            Job job = jobs.remove();
            int spent = job.work(time);
            
            reportWork(spent);
            reportWait(spent, job);
            
            (job.isDone() ? done : jobs).add(job);
        } catch(NoSuchElementException e) {
            // all done
        }
    }
}
