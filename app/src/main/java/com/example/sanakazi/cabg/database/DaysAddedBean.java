package com.example.sanakazi.cabg.database;

/**
 * Created by SanaKazi on 12/1/2016.
 */
public class DaysAddedBean {
    String id,gradientValue,daysAdded,status;


    public DaysAddedBean(String id, String gradientValue, String daysAdded) {
        this.id = id;
        this.gradientValue = gradientValue;
        this.daysAdded = daysAdded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGradientValue() {
        return gradientValue;
    }

    public void setGradientValue(String gradientValue) {
        this.gradientValue = gradientValue;
    }

    public String getDaysAdded() {
        return daysAdded;
    }

    public void setDaysAdded(String daysAdded) {
        this.daysAdded = daysAdded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
