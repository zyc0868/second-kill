package com.example.demo.domain.entity;

import com.example.demo.domain.common.EntityDO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserDO implements EntityDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "userName" , nullable = false)
    private String userName;

    @Column(name = "phone" , unique = true, nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "loginCount", nullable = false)
    private int loginCount;

    @Column(name = "lastLoginDate",nullable = false)
    private Date lastLoginDate;

    @Column(name = "address")
    private String address;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmtCreate", nullable = false, updatable = false)
    private Date gmtCreate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gmtModified", nullable = false)
    private Date gmtModified;

    @Override
    public String getTableName() {
        return "user";
    }
}
