package controller;

import entity.CardAccount;
import entity.Employee;
import entity.Role;
import entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.EmployeeDataService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class EmployeeController {

    private final EmployeeDataService employeeDataService;

    @GetMapping(path = "/employee",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Employee getEmployee(){
        Role role = new Role();
        role.setNameRole("RABOTNIK");
        role.setCreateDate(new Date());
        role.setEmployees(new ArrayList<Employee>());

        adSomeEmployee("test","test","test","1234",new Date(),Status.ACTIVE,role,new ArrayList<CardAccount>());
        return employeeDataService.findById(1L);
    }


    private void adSomeEmployee(String name, String secondName, String fatherName, String personalNumber, Date birthDate, Status status, Role role, List<CardAccount> cardAccounts){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setFatherName(fatherName);
        employee.setSecondName(secondName);
        employee.setPersonalNumber(personalNumber);
        employee.setBirthdate(birthDate);
        employee.setRole(role);
        employee.setStatus(status);
        employee.setCardAccounts(cardAccounts);
        employee.getRole().getEmployees().add(employee);
        employeeDataService.save(employee);
        employeeDataService.save(employee);
    }
}
