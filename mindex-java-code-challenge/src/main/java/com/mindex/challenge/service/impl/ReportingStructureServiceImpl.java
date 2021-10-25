package com.mindex.challenge.service.impl;


import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id){
        LOG.debug("Creating and reading Reporting Structure for employee with id [{}]", id);
        
        Employee employeee = employeeRepository.findByEmployeeId(id);

        if (employeee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        
        ReportingStructure rs = new ReportingStructure();

        rs.setEmployee(employeee);

        rs.setNumOfReports(employeeRepository);

        if(rs.getNumOfReports() == -1){
            throw new RuntimeException("Reporting Structure Employee was not properly set");
        }

        return rs;
    }
    
}
