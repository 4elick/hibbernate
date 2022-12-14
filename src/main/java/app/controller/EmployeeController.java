package app.controller;

import app.dto.EmployeeDTO;
import app.dto.Request;
import app.dto.Response;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import app.service.EmployeeDataService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeDataService employeeDataService;
    @JsonView(Response.class)
    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EmployeeDTO getEmployee(@PathVariable long id){
        return employeeDataService.findById(id);
    }

    @JsonView(Response.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<EmployeeDTO> getEmployees(){
        return employeeDataService.findAll();
    }
    @JsonView(Request.class)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody EmployeeDTO employee){
        employeeDataService.save(employee);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id){
        employeeDataService.delete(id);
    }

    @JsonView(Request.class)
    @PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateEmployee(@PathVariable long id,@RequestBody EmployeeDTO employee){
        employeeDataService.updateEmployee(id,employee);
    }

    /*private void adSomeEmployee(String name, String secondName, String fatherName, String personalNumber, Date birthDate, Status status, Role role, List<CardAccount> cardAccounts){
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
    }*/
}
