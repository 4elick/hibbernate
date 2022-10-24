package app.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class CardDTO {

    @JsonView(Request.class)
    @Null(groups = {Request.class})
    @NotNull(groups = {Response.class})
    private long id;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private int number;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private String logicStatus;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private String name;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private String secondName;
    @JsonView({Response.class,Request.class})
    @NotNull(groups = {Response.class, Request.class})
    private CardAccountDTO cardAccountDTO;

}
