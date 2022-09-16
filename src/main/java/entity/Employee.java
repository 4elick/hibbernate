package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2,max = 255,message = "Name is required, maximum 255 characters.")
    private String name;
    @Size(min = 2,max = 255,message = "SecondName is required, maximum 255 characters.")
    private String secondName;
    @Size(min = 2,max = 255,message = "FatherName is required, maximum 255 characters.")
    private String fatherName;
    @Size(min = 2,max = 12,message = "PersonalNumber is required, maximum 12 characters.")
    private String personalNumber;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(targetEntity = Role.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(targetEntity = CardAccount.class,mappedBy = "employee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CardAccount> cardAccounts = new ArrayList<CardAccount>();



}
