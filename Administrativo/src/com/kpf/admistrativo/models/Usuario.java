
package com.kpf.admistrativo.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    private String codUsuario;
    private String usuario;
    private String clave;
    private String primerNombreUsuario;
    private String segundoNombreUsuario;
    private String apellidoPaternoUsuario;
    private String apellidoMaternoUsuario;
   
}
