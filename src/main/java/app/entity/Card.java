package app.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
@Table(name = "cards")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int number;
    @Size(max = 3,message = "Value is required, maximum 255 characters.")
    private String logicStatus;
    @Size(min = 2,max = 30,message = "Name on card is required, maximum 255 characters.")
    private String name;
    @Size(min = 2,max = 30,message = "Second name on card is required, maximum 255 characters.")
    private String secondName;
    @ManyToOne(targetEntity = CardAccount.class,optional = false)
    private CardAccount cardAccount;
}