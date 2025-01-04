package me.cher1shrxd.cardeaseserver.domain.card.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.cher1shrxd.cardeaseserver.domain.user.entity.UserEntity;

import java.util.UUID;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String background;

    @Column(nullable = false)
    private String logo;
}
