package app.dto;

import app.entity.Status;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeDTO {

    public interface Request {

    }

    public interface Response{

    }

    @JsonView(Response.class)
    @Null(groups = {Request.class})
    @NotNull(groups = {Response.class})
    long id;
    @JsonView({Response.class, Request.class})
    @NotNull(groups = {Response.class, Request.class})
    String name;
    @JsonView({Response.class, Request.class})
    @NotNull(groups = {Response.class, Request.class})
    String secondName;
    @JsonView({Response.class, Request.class})
    @NotNull(groups = {Response.class, Request.class})
    String fatherName;
    @JsonView({Request.class})
    @NotNull(groups = {Request.class})
    @Null(groups = {Response.class})
    String personalNumber;
    @JsonView({Response.class, Request.class})
    @NotNull
    Date birthdate;
    @JsonView({Response.class, Request.class})
    @NotNull(groups = {Response.class, Request.class})
    Status status;
    @JsonView({Response.class})
    @NotNull(groups = {Request.class})
    @Null(groups = {Response.class})
    long role_id;
    @JsonView({Response.class, Request.class})
    @NotNull(groups = {Response.class, Request.class})
    String nameRole;
    @JsonView({Request.class})
    @NotNull(groups = {Request.class})
    @Null(groups = {Response.class})
    Date createDate;
    @JsonView({Response.class, Request.class})
    @NotNull(groups = {Response.class, Request.class})
    List<Long> cardAccounts;

}
