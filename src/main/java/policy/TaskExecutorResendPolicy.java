package policy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import model.ResendPolicyData;
import quartz.Task;

/**
 * Created by viccardo on 4/02/16.
 */
@Component
public class TaskExecutorResendPolicy implements Task<ResendPolicyData> {

    private final static Logger LOGGER = Logger.getLogger(TaskExecutorResendPolicy.class);

    @Override
    public void execute(ResendPolicyData data) {
        LOGGER.info("TaskExecutorResendPolicy:::execution...");
        LOGGER.info("TaskExecutorResendPolicy.data:"+data);
    }

}
