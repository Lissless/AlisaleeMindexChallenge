package com.mindex.challenge.data;

import java.util.ArrayList;
import java.util.List;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){
    }

    public Employee getEmployee(){
        return this.employee;
    }
    
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public int getNumOfReports(){
        return this.numberOfReports;
    }

    public void setNumOfReports(EmployeeRepository er){
        if(this.employee == null){ this.numberOfReports = -1; }

        ArrayList<Employee> open = new ArrayList<>();
        ArrayList<Employee> closed = new ArrayList<>();
        Employee eval;
        List<Employee> currentReports;

        open.add(this.employee);
        while(open.size() != 0){
            eval = open.get(0);
            open.remove(0);
            if(!closed.contains(eval)){
                currentReports = eval.getDirectReports();
                if(currentReports != null){
                    for( Employee e : currentReports){
                        open.add(0, er.findByEmployeeId(e.getEmployeeId()));
                    }
                }
                closed.add(eval);
            }
        }

        int count = closed.size() - 1; // -1 Account for the self

        this.numberOfReports = count;

    }

}
