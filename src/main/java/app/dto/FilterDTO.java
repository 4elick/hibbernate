package app.dto;

import app.entity.Status;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class FilterDTO {

    private Date birthdateMoreThan;
    private Date birthdateLessThan;
    private Status status;
    private String personalNumber;
}
