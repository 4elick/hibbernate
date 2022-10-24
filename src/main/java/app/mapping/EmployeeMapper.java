package app.mapping;

import app.dao.CardAccountsCrudRepository;
import app.dto.EmployeeDTO;
import app.entity.CardAccount;
import app.entity.Employee;
import app.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class EmployeeMapper {
    private final CardAccountsCrudRepository cardAccountsCrudRepository;

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSecondName(employee.getSecondName());
        employeeDTO.setFatherName(employee.getFatherName());
        employeeDTO.setPersonalNumber(employee.getPersonalNumber());
        employeeDTO.setBirthdate(employee.getBirthdate());
        employeeDTO.setStatus(employee.getStatus());
        employeeDTO.setRole_id(employee.getRole().getId());
        employeeDTO.setNameRole(employee.getRole().getNameRole());
        employeeDTO.setCreateDate(employee.getRole().getCreateDate());
        employeeDTO.setCardAccounts(new ArrayList<>());
        if (!employee.getCardAccounts().isEmpty()) {
            for (CardAccount cardAccount : employee.getCardAccounts()) {
                employeeDTO.getCardAccounts().add(cardAccount.getId());
            }
        }
        return employeeDTO;
    }

    public Employee convertFromDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSecondName(employeeDTO.getSecondName());
        employee.setFatherName(employeeDTO.getFatherName());
        employee.setBirthdate(employeeDTO.getBirthdate());
        employee.setStatus(employeeDTO.getStatus());
        employee.setPersonalNumber(employeeDTO.getPersonalNumber());

        employee.setCardAccounts(new ArrayList<>());
        if (!employeeDTO.getCardAccounts().isEmpty()) {
            for (long id : employeeDTO.getCardAccounts()) {

                employee.getCardAccounts().add(cardAccountsCrudRepository.findById(id).get());

            }
        }

        Role role = new Role();
        role.setId(employeeDTO.getRole_id());
        role.setNameRole(employeeDTO.getNameRole());
        role.setCreateDate(employeeDTO.getCreateDate());
        role.setEmployees(new ArrayList<>());
        role.getEmployees().add(employee);
        employee.setRole(role);

        return employee;

    }
}
