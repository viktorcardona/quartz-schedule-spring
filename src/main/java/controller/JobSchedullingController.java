package controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.JobSchedullingData;
import service.JobSchedullingService;

/**
 * Created by viccardo on 9/02/16.
 */
@RestController
public class JobSchedullingController {

    private final static Logger LOGGER = Logger.getLogger(JobSchedullingController.class);

    @Autowired
    private JobSchedullingService jobSchedullingService;

    @RequestMapping("/schedule")
    public JobSchedullingData schedule(@RequestParam(value="minutes", defaultValue="1") int minutes) {
        LOGGER.info("JobSchedullingController.schedule.minutes:"+minutes);
        JobSchedullingData data = new JobSchedullingData();
        data.setDate(new Date());
        data.setMinutes(minutes);
        jobSchedullingService.scheduleResendPolicyTask(minutes);
        return data;
    }

}
