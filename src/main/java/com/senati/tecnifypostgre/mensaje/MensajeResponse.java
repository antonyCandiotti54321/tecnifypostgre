package com.senati.tecnifypostgre.mensaje;


import lombok.Getter;
import lombok.Setter;
import com.senati.tecnifypostgre.user.UserDTO;

@Getter
@Setter
public class MensajeResponse {
    private Long id;
    private UserDTO remitente;
    private UserDTO receptor;
    private String mensaje;

    public MensajeResponse(Mensaje mensaje) {
        this.id = mensaje.getId();
        this.remitente = new UserDTO(mensaje.getIdRemitente());
        this.receptor = new UserDTO(mensaje.getIdReceptor());
        this.mensaje = mensaje.getMensaje();
    }
}
