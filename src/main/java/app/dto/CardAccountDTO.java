package app.dto;

import app.entity.Card;
import app.entity.Employee;
import app.entity.Status;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class CardAccountDTO {

    @JsonView(Response.class)
    @Null(groups = {Request.class})
    @NotNull(groups = {Response.class})
    private long id;
    @JsonView(Request.class)
    @NotNull(groups = {Response.class, Request.class})
    private String accountNumber;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private String value;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private Status status;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private EmployeeDTO employee;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private List<Long> cards = new ArrayList<>();

}
