package app.dto;

import app.entity.Employee;
import app.entity.Status;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    long id;
    String name;
    String secondName;
    String fatherName;
    @JsonIgnore
    String personalNumber;
    Date birthdate;
    Status status;
    long role_id;
    String nameRole;
    @JsonIgnore
    Date createDate;
    List<Long> cardsAccounts;

}
