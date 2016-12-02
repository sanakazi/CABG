package com.example.sanakazi.cabg.database;

/**
 * Created by SanaKazi on 12/1/2016.
 */
public class CabjBean {
    String cabg_id,age,current_OM_CABG,current_OM_CABGAVR,current_PS_CABG,current_PS_CABGAVR,current_RF_CABG, current_RF_CABGAVR,
            future_OM_CABGAVR,future_PS_CABGAVR,future_RF_CABGAVR,status;


//all values
    public CabjBean(String cabg_id, String age, String current_OM_CABG, String current_OM_CABGAVR, String current_PS_CABG,
                    String current_PS_CABGAVR, String current_RF_CABG, String current_RF_CABGAVR, String future_OM_CABGAVR,
                    String future_PS_CABGAVR, String future_RF_CABGAVR, String status) {
        this.cabg_id = cabg_id;
        this.age = age;
        this.current_OM_CABG = current_OM_CABG;
        this.current_OM_CABGAVR = current_OM_CABGAVR;
        this.current_PS_CABG = current_PS_CABG;
        this.current_PS_CABGAVR = current_PS_CABGAVR;
        this.current_RF_CABG = current_RF_CABG;
        this.current_RF_CABGAVR = current_RF_CABGAVR;
        this.future_OM_CABGAVR = future_OM_CABGAVR;
        this.future_PS_CABGAVR = future_PS_CABGAVR;
        this.future_RF_CABGAVR = future_RF_CABGAVR;
        this.status = status;
    }

    //future age values
    public CabjBean(String cabg_id,String age, String future_OM_CABGAVR, String future_PS_CABGAVR, String future_RF_CABGAVR, String status) {
        this.cabg_id = cabg_id;
        this.future_OM_CABGAVR = future_OM_CABGAVR;
        this.future_PS_CABGAVR = future_PS_CABGAVR;
        this.future_RF_CABGAVR = future_RF_CABGAVR;
        this.status = status;
        this.age = age;
    }

    //current age values
    public CabjBean(String cabg_id, String age, String current_OM_CABG, String current_OM_CABGAVR, String current_PS_CABG, String current_PS_CABGAVR, String current_RF_CABG, String current_RF_CABGAVR, String status) {
        this.cabg_id = cabg_id;
        this.age = age;
        this.current_OM_CABG = current_OM_CABG;
        this.current_OM_CABGAVR = current_OM_CABGAVR;
        this.current_PS_CABG = current_PS_CABG;
        this.current_PS_CABGAVR = current_PS_CABGAVR;
        this.current_RF_CABG = current_RF_CABG;
        this.current_RF_CABGAVR = current_RF_CABGAVR;
        this.status = status;
    }

    public String getCabg_id() {
        return cabg_id;
    }

    public void setCabg_id(String cabg_id) {
        this.cabg_id = cabg_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCurrent_OM_CABG() {
        return current_OM_CABG;
    }

    public void setCurrent_OM_CABG(String current_OM_CABG) {
        this.current_OM_CABG = current_OM_CABG;
    }

    public String getCurrent_OM_CABGAVR() {
        return current_OM_CABGAVR;
    }

    public void setCurrent_OM_CABGAVR(String current_OM_CABGAVR) {
        this.current_OM_CABGAVR = current_OM_CABGAVR;
    }

    public String getCurrent_PS_CABG() {
        return current_PS_CABG;
    }

    public void setCurrent_PS_CABG(String current_PS_CABG) {
        this.current_PS_CABG = current_PS_CABG;
    }

    public String getCurrent_PS_CABGAVR() {
        return current_PS_CABGAVR;
    }

    public void setCurrent_PS_CABGAVR(String current_PS_CABGAVR) {
        this.current_PS_CABGAVR = current_PS_CABGAVR;
    }

    public String getCurrent_RF_CABG() {
        return current_RF_CABG;
    }

    public void setCurrent_RF_CABG(String current_RF_CABG) {
        this.current_RF_CABG = current_RF_CABG;
    }

    public String getCurrent_RF_CABGAVR() {
        return current_RF_CABGAVR;
    }

    public void setCurrent_RF_CABGAVR(String current_RF_CABGAVR) {
        this.current_RF_CABGAVR = current_RF_CABGAVR;
    }

    public String getFuture_OM_CABGAVR() {
        return future_OM_CABGAVR;
    }

    public void setFuture_OM_CABGAVR(String future_OM_CABGAVR) {
        this.future_OM_CABGAVR = future_OM_CABGAVR;
    }

    public String getFuture_PS_CABGAVR() {
        return future_PS_CABGAVR;
    }

    public void setFuture_PS_CABGAVR(String future_PS_CABGAVR) {
        this.future_PS_CABGAVR = future_PS_CABGAVR;
    }

    public String getFuture_RF_CABGAVR() {
        return future_RF_CABGAVR;
    }

    public void setFuture_RF_CABGAVR(String future_RF_CABGAVR) {
        this.future_RF_CABGAVR = future_RF_CABGAVR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
