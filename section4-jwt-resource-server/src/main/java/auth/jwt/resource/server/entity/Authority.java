package auth.jwt.resource.server.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Suleyman Yildirim
 */
@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;
}
