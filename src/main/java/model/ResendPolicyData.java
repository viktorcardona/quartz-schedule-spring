package model;

import java.io.Serializable;

/**
 * Created by viccardo on 9/02/16.
 */
public class ResendPolicyData implements Serializable{

    private String lead;
    private int attemps;

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public int getAttemps() {
        return attemps;
    }

    public void setAttemps(int attemps) {
        this.attemps = attemps;
    }

    @Override
    public String toString() {
        return "ResendPolicyData{" +
                "lead='" + lead + '\'' +
                ", attemps=" + attemps +
                '}';
    }
}
