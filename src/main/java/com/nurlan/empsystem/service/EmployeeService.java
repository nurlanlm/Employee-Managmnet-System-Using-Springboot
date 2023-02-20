package com.nurlan.empsystem.service;
import com.nurlan.empsystem.dto.EmployeeDto;
import java.util.List;


public interface EmployeeService  {

    void saveEmployee(EmployeeDto employeeDto);
    void deleteEmployee(Long id);
    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();

}
