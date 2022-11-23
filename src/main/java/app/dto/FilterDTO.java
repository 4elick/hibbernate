package app.dto;

import app.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class FilterDTO {

    private Date birthdateMoreThan;
    private Date birthdateLessThan;
    private List<Status> status;
    private String personalNumber;
    private String sortBy;
    @NotNull
    private int page;
    @NotNull
    private int size;
}
