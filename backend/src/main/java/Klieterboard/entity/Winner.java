package Klieterboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Winner {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column (nullable = false)
    private int season_year;

    @Column (nullable = false)
    private int season_semester;

    @Column (nullable = false)
    private String username;

    @Column (nullable = false)
    private int score;

}
