package demo.utils.com.myapplication.entity;

import java.io.Serializable;

/**
 * 记账 的bean
 * Created by sts on 2018/1/4.
 */

public class CostBean implements Serializable {
    public String costTitle;
    public String costDate;
    public String costMoney;

    @Override
    public String toString() {
        return "CostBean{" +
                "costTitle='" + costTitle + '\'' +
                ", costDate='" + costDate + '\'' +
                ", costMoney='" + costMoney + '\'' +
                '}';
    }

    public String getCostTitle() {
        return costTitle;
    }

    public void setCostTitle(String costTitle) {
        this.costTitle = costTitle;
    }

    public String getCostDate() {
        return costDate;
    }

    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }
}
