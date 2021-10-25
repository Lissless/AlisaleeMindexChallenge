package com.mindex.challenge.data;

import java.util.Date;

import com.mindex.challenge.data.Employee;

public class Compensation {
    Employee employee;
    Double salary;
    Date effectiveDate;

    public Compensation(){
    }

    public Employee getEmployee(){
        return this.employee;
    }

    public void setEmployee(Employee emp){
        this.employee = emp;
    }

    public Double getSalary(){
        return this.salary;
    }

    public void setSalary(Double val){
        this.salary = val;
    }

    public Date getEffectiveDate(){
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date d){
        this.effectiveDate = d;
    }

}
