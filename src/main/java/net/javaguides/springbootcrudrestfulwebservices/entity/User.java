package net.javaguides.springbootcrudrestfulwebservices.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty
    @NotNull
    @Column(name = "email")
    private String email;
    @NotEmpty
    @NotNull
    @Column
    private String birthdate;
    @NotEmpty
    @NotNull
    @Column
    private String adress;
    @NotEmpty
    @NotNull
    @Column
    @CPF
    private String cpf;


}
