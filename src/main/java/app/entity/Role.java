package app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
@Table(name = "roles")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2,max = 20,message = "nameRole is required, maximum 20 characters.")
    private String nameRole;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    /*@JsonIdentityReference(alwaysAsId = true)*/
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<Employee>();

    public Role(String nameRole,Date date){
        this.nameRole = nameRole;
        this.createDate = date;
    }

}
