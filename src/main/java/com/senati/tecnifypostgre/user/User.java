package com.senati.tecnifypostgre.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = {"correo"}) })
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PostgreSQL usa este tipo de generación para autoincrementar
    Long id;

    @Column(nullable = false, name = "correo")
    @Email(message = "El correo debe ser valido")
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    private Integer celular;
    private Integer edad;

    @Column(length = 100)
    private String provincia;

    @Column(length = 100)
    private String distrito;

    @Column(length = 255)
    private String ubicacion;

    @Column(length = 100)
    private String especialidad;

    private Integer experiencia;

    @Column(length = 255)
    private String certificaciones;

    @Column(length = 50)
    private String disponibilidad;

    private Boolean vehiculo;
    private Double radio;
    private Double tarifa;

    @DecimalMin(value = "-90.0", message = "Latitud mínima permitida: -90")
    @DecimalMax(value = "90.0", message = "Latitud máxima permitida: 90")
    Double latitud;

    @DecimalMin(value = "-180.0", message = "Longitud mínima permitida: -180")
    @DecimalMax(value = "180.0", message = "Longitud máxima permitida: 180")
    Double longitud;

    @NotNull(message = "El rol es obligatorio")
    @Enumerated(EnumType.STRING)
    Role role;

    LocalDateTime fecha_actualizacion;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Método para actualizar la fecha automáticamente
    @PrePersist
    @PreUpdate
    public void actualizarFecha() {
        this.fecha_actualizacion = LocalDateTime.now();
    }
}
