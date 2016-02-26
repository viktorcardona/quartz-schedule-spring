package quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by viccardo on 3/02/16.
 */
@Component
public class TaskScheduler {

    private final static Logger LOGGER = Logger.getLogger(TaskScheduler.class);

    @Autowired
    private Scheduler scheduler;

    private static final String JOB_GROUP_PREFIX = "helios.";
    private static final String JOB_GROUP_SUFFIX = ".trigger";
    private static final String JOB_NAME_SUFFIX  = ".trigger";
    private static final String JOB_DEFAULT_GROUP = "helios.default";

    public void scheduleTask(String name, String group, Date startTime, String beanExecutor, Object data)
            throws Exception {

        group = (group != null) ? (JOB_GROUP_PREFIX + group) : JOB_DEFAULT_GROUP;

        JobDetail jobDetail = newJob(TaskExecutor.class).withIdentity(name, group).build();

        jobDetail.getJobDataMap().put(TaskExecutor.ATTRIBUTE_BEAN_TASK_EXECUTOR, beanExecutor);
        jobDetail.getJobDataMap().put(TaskExecutor.ATTRIBUTE_INPUT_TASK, data);

        Long runInterval = 0l;
        Integer repeatCount = 0;

        Trigger trigger = newTrigger().withIdentity(name + JOB_NAME_SUFFIX, jobDetail.getKey().getGroup() + JOB_GROUP_SUFFIX)
                .forJob(jobDetail).startAt(startTime).withSchedule(simpleSchedule().withIntervalInMilliseconds(runInterval).withRepeatCount(repeatCount)).
                        build();

        scheduleJob(jobDetail, trigger);
    }

    private void scheduleJob(JobDetail jobDetail, Trigger trigger) throws Exception {
        try {

            if (scheduler.checkExists(jobDetail.getKey())) {
                for (Trigger jobTrigger : scheduler.getTriggersOfJob(jobDetail.getKey())) {
                    scheduler.unscheduleJob(jobTrigger.getKey());
                }
            }

            scheduler.scheduleJob(jobDetail, trigger);
        }
        catch (SchedulerException se) {
            LOGGER.error("Error schedulling a job.", se);
            throw new Exception("Error schedulling a job.", se);
        }
    }
}
