package Klieterboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column( nullable = false, unique = true)
    private String kilterId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String name;

    @Column
    private Integer score;

    @Column
    private Integer grade;

}
