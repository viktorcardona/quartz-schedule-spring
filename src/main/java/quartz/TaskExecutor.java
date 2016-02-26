package quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import listener.ApplicationContextProvider;

/**
 * Created by viccardo on 4/02/16.
 */
@Component
public class TaskExecutor implements Job {

    private final static Logger LOGGER = Logger.getLogger(TaskExecutor.class);

    static final String ATTRIBUTE_BEAN_TASK_EXECUTOR= "BEAN_EXECUTOR";
    static final String ATTRIBUTE_INPUT_TASK= "INPUT_DATA";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap dataMap = context.getMergedJobDataMap();
            String beanId = dataMap.get(ATTRIBUTE_BEAN_TASK_EXECUTOR).toString();
            Task task = (Task) ApplicationContextProvider.getApplicationContext().getBean(beanId);
            task.execute(dataMap.get(ATTRIBUTE_INPUT_TASK));
        }catch(Exception exc){
            LOGGER.error("Execution error on scheduled task.",exc);
            throw new JobExecutionException(exc);
        }
    }

}
