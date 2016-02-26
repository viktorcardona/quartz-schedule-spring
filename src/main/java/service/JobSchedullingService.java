package service;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ResendPolicyData;
import quartz.TaskScheduler;

/**
 * Created by viccardo on 9/02/16.
 */
@Service
public class JobSchedullingService {

    private final static Logger LOGGER = Logger.getLogger(JobSchedullingService.class);

    @Autowired
    private TaskScheduler taskScheduler;

    public void scheduleResendPolicyTask(int minutes){

        try {

            Calendar taskCalendar = Calendar.getInstance();
            taskCalendar.add(Calendar.MINUTE, minutes);
            Date startTime = taskCalendar.getTime();

            String name = "resend"+System.currentTimeMillis();
            String group = "policies";


            ResendPolicyData data = new ResendPolicyData();
            data.setAttemps(7);
            data.setLead("{lead: this is my lead}");

            String beanExecutor = "taskExecutorResendPolicy";


            taskScheduler.scheduleTask(name, group, startTime, beanExecutor, data);
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

}
