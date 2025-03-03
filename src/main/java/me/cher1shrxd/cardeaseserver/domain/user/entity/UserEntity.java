package me.cher1shrxd.cardeaseserver.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.cher1shrxd.cardeaseserver.domain.card.entity.CardEntity;
import me.cher1shrxd.cardeaseserver.domain.user.enums.UserRole;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String job;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CardEntity card;

    @Column(nullable = false)
    private UserRole role;
}
