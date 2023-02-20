package com.nurlan.empsystem.impl;

import com.nurlan.empsystem.dto.EmployeeDto;
import com.nurlan.empsystem.entity.Employee;
import com.nurlan.empsystem.exception.EmployeeNotFoundException;
import com.nurlan.empsystem.repository.EmployeeRepository;
import com.nurlan.empsystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(user -> modelMapper.map(user, EmployeeDto.class)).collect(Collectors.toList());

    }

    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found.ID: " + id));
        return modelMapper.map(employee, EmployeeDto.class);
    }


     public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
