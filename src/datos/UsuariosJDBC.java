
package datos;

import domain.*;
import java.sql.*;
import java.util.*;

public class UsuariosJDBC {
      /**
     * nos apoyamos de la llave primaria autoincrementable de mysql
     * por lo que se omite este campo de id_personas
     * se utiliza unprepareStatement, por lo que podemos
     * utilizar paramteros(signos de ?)
     * los cuales posteriormente sera sustituidos por el parametro respectivo
     */
    
    private final String SQL_INSERT ="insert into usuario (usuario,password) values (?,?)";
     private final String SQL_UPDATE ="update usuario set usuario=?,password=? where id_usuario =? ";
      private final String SQL_DELETE ="delete from usuario where id_usuario = ?";
       private final String SQL_SELECT ="select id_usuario,usuario,password from usuario order by id_usuario";
       
       /**
        * metodo para insertar
        */
       
       public int insert(String usuario, String password){
           Connection conn = null;
           PreparedStatement stmt = null;
           ResultSet rs = null; // no se utiliza en este metodo
           
           int rows = 0; // contador de registros afectados
           
           try {
               conn = Conexion.getConnection();
               stmt = conn.prepareStatement(SQL_INSERT);
               int index = 1; // varable index se utiliza como contador de columnas
               stmt.setString(index++, usuario);//parametro1 => ?
               stmt.setString(index++, password);// parametro2=> ?
               System.out.println(" Ejecutando query:" + SQL_INSERT);
               rows = stmt.executeUpdate();// numero de registros afectados
               System.out.println("Registros afectados:" + rows);
           } catch (SQLException e) {
               System.out.println("Error al Insertar Registro");
               e.printStackTrace();
           }finally{
               Conexion.close(stmt);
               Conexion.close(conn);
           }
           return rows;
       }
       
       public int update(int id_usuario,String usuario, String password){
           Connection conn = null;
           PreparedStatement stmt = null;
           int rows = 0;
           try {
               conn = Conexion.getConnection();
               stmt = conn.prepareStatement(SQL_UPDATE);
               int index = 1;
               stmt.setString(index++, usuario);
               stmt.setString(index++, password);
               stmt.setInt(index, id_usuario);
               rows = stmt.executeUpdate();
               System.out.println("Registros Actualizados :" + rows);
           } catch (SQLException e) {
               System.out.println("error al actulizar registro");
               e.printStackTrace();
           } finally{
               
               Conexion.close(stmt);
               Conexion.close(conn);
           }
           return rows;
           
       }
       
       public int delete(int id_usuario){
           Connection conn = null;
           PreparedStatement stmt = null;
           int rows = 0;
           
           try {
               conn = Conexion.getConnection();
               stmt = conn.prepareStatement(SQL_DELETE);
               System.out.println("sql a ejecutar " + SQL_DELETE);
               stmt.setInt(1,id_usuario);
               rows = stmt.executeUpdate();
               System.out.println("registros eliminados :" + rows);
           } catch (SQLException e) {
               System.out.println("error al intentar eliminar un registro");
               e.printStackTrace();
           }finally{
               Conexion.close(stmt);
               Conexion.close(conn);
           }
           return  rows;
       }
       
       public List<Usuario> select(){
           
           Connection conn = null;
           ResultSet rs = null;
           PreparedStatement stmt = null;
           
           Usuario usuario1 = null;
           
           List<Usuario> usuarios = new ArrayList<Usuario>();
           try {
               conn = Conexion.getConnection();
               stmt = conn.prepareStatement(SQL_SELECT);
               rs = stmt.executeQuery();
               
               while (rs.next()) {
                int id_usuario = rs.getInt(1);
                String usuario = rs.getString(2);
                String password = rs.getString(3);
                
                usuario1 = new Usuario();
                
                usuario1.setId_usuario(id_usuario);
                usuario1.setUsuario(usuario);
                usuario1.setPassword(password);
                
                //agregamos a la lsita
                usuarios.add(usuario1);
                   
               }
           } catch (SQLException e) {
               System.out.println("eror al listar usuarios");
               e.printStackTrace();
           }finally{
               Conexion.close(rs);
               Conexion.close(stmt);
               Conexion.close(conn);
           }
           return usuarios;
       }
       
    
}
