package com.senati.tecnifypostgre.mensaje;

import com.senati.tecnifypostgre.user.User;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PostgreSQL usa auto-incremento
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_remitente", nullable = false)  // Relación con la entidad User (remitente)
    private User idRemitente;

    @ManyToOne
    @JoinColumn(name = "id_receptor", nullable = false)  // Relación con la entidad User (receptor)
    private User idReceptor;

    private String mensaje;  // Contenido del mensaje
}
