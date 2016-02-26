package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by viccardo on 9/02/16.
 */
public class JobSchedullingData implements Serializable{

    private int minutes;
    private Date date;

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
