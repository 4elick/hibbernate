package app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Table(name = "cardAccounts")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class CardAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2,max = 30,message = "accountNumber is required, maximum 30 characters.")
    private String accountNumber;
    @Size(max = 3,message = "Value is required, maximum 3 characters.")
    private String value;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(targetEntity = Employee.class,optional = false)
    private Employee employee;
    @OneToMany(targetEntity = Card.class,mappedBy = "cardAccount")
    private List<Card> cards = new ArrayList<Card>();
}
