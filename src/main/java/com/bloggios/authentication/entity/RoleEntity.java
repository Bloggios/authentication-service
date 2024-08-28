package com.bloggios.authentication.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - authentication-service
 * Package - com.bloggios.authentication.entity
 * Created_on - August 28 - 2024
 * Created_at - 23:29
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "role")
public class RoleEntity {

    @Id
    private String roleId;
    private String name;
}
