
package com.kpf.admistrativo.controller;

import com.kpf.admistrativo.dao.UsuarioDAO;
import com.kpf.admistrativo.exception.DAOException;
import com.kpf.admistrativo.models.Usuario;
import com.kpf.admistrativo.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioController implements UsuarioDAO {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public Usuario validarUsuario(Usuario usuario) {
        String sql = "SELECT PRIMERNOMBRE,APELLIDOPATERNO FROM USUARIO"
                    + "WHERE USUARIO = ? AND CLAVE = ?";
        try {
             cn = Conexion.conexionBD();
             ps = cn.prepareStatement(sql);
             
             ps.setString(1, usuario.getUsuario());
             ps.setString(2, usuario.getClave());
             
             rs = ps.executeQuery();
             
             while(rs.next())
             {
                 return usuario;
             }
             
             
        } catch (Exception e) {
            System.out.println("Error :" + e);
        }
        
       return null;
    }

   
    
    
    
}
