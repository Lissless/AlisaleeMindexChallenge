package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {
    
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation create(Compensation comp){
        LOG.debug("Creating compensation [{}]", comp);

        Employee emp = comp.getEmployee();
        String inputID = emp.getEmployeeId();

        if(inputID == null){
            throw new RuntimeException("Invalid Employee Structure: missing ID.");
        }

        emp = employeeRepository.findByEmployeeId(inputID);

        Compensation uniqueCheck = compensationRepository.findCompensationByEmployee(emp);
        if(uniqueCheck != null){
            throw new RuntimeException("A compensation for id: " + inputID + " already exists.");
        }

        comp.setEmployee(emp);

        compensationRepository.insert(comp);

        return comp;
    }

    @Override
    public Compensation read(String id){
        LOG.debug("Reading compensation for ID: [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        Compensation result = compensationRepository.findCompensationByEmployee(employee);

        if (result == null){
            throw new RuntimeException("Employee with id " + id + " does not have an existng compensation.");
        }

        return result;
    }

}
