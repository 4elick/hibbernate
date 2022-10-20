package app.mapping;

import app.dao.CardAccountsCrudRepository;
import app.dto.EmployeeDTO;
import app.entity.CardAccount;
import app.entity.Employee;
import app.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@RequiredArgsConstructor
@Service
public class MappingEntity {
    private final CardAccountsCrudRepository cardAccountsCrudRepository;

    public EmployeeDTO convertToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSecondName(employee.getSecondName());
        employeeDTO.setFatherName(employee.getFatherName());
        employeeDTO.setBirthdate(employee.getBirthdate());
        employeeDTO.setStatus(employee.getStatus());
        employeeDTO.setRole_id(employee.getRole().getId());
        employeeDTO.setNameRole(employee.getRole().getNameRole());
        employeeDTO.setCardsAccounts(new ArrayList<>());
        for(CardAccount cardAccount : employee.getCardAccounts()){
            employeeDTO.getCardsAccounts().add(cardAccount.getId());
        }
        return employeeDTO;
    }

    public Employee convertToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSecondName(employeeDTO.getSecondName());
        employee.setFatherName(employeeDTO.getFatherName());
        employee.setBirthdate(employeeDTO.getBirthdate());
        employee.setStatus(employeeDTO.getStatus());
        employee.setPersonalNumber(employeeDTO.getPersonalNumber());

        employee.setCardAccounts(new ArrayList<>());
        for (long id:employeeDTO.getCardsAccounts()){
            if(cardAccountsCrudRepository.existsById(id)){
                employee.getCardAccounts().add(cardAccountsCrudRepository.findById(id).get());
            }
        }

        Role role = new Role();
        role.setId(employeeDTO.getRole_id());
        role.setNameRole(employeeDTO.getNameRole());
        role.setCreateDate(employeeDTO.getCreateDate());
        role.setEmployees(new ArrayList<>());
        role.getEmployees().add(employee);

        return employee;

    }
}
