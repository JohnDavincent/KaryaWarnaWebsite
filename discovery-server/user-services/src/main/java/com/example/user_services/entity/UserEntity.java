package com.example.user_services.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private String user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "verify_otp")
    private String verifyOtp;

    @Column(name = "is_account_verified")
    private Boolean isAccountVerified;

    @Column(name = "verify_otp_expired_at")
    private Long verifyOtpExpiredAt;

    @Column(name = "reset_otp")
    private String resetOtp;

    @Column(name = "reset_Otp_expired_at")
    private Long resetOtpExpiredAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id_fk", referencedColumnName = "id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}



