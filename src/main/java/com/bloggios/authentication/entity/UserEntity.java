package com.bloggios.authentication.entity;

import com.bloggios.authentication.enums.Provider;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - authentication-service
 * Package - com.bloggios.authentication.entity
 * Created_on - August 28 - 2024
 * Created_at - 23:09
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "randomUUID")
    @GenericGenerator(name = "randomUUID", strategy = "org.hibernate.id.UUIDGenerator")
    private int userId;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(unique = true, nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String apiVersion;
    private String version;
    private int timesDisabled = 0;

    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "UserEntity", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "RoleEntity", referencedColumnName = "roleId")
    )
    private List<RoleEntity> roles = new ArrayList<>();
}
