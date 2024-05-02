package tech.vault.server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "tb_roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    @Getter
    public enum Values {

        ADMIN(1L),
        BASIC(2L);

        long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }

    }
}
