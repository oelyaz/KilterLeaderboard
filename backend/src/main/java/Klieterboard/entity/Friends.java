package Klieterboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (nullable = false, unique = true)
    private Integer id;

    @Column (nullable = false, unique = true)
    private String username;


    public Friends(String username) {
        this.username = username;
    }

    /**
     * Compares the username of this object to the parameter object.
     * Only works, if the parameter is either a {@code Friends} or a {@code User}.
     * @param friend the object to be compared
     * @return If the parameter object and this object have the same username.
     */
    @Override
    public boolean equals(Object friend) {
        if(friend instanceof Friends) return this.username.equals(((Friends)friend).getUsername());
        if(friend instanceof User) return this.username.equals(((User)friend).getUsername());
        return false;
    }
}
