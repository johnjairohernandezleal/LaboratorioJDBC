
package laboratoriojdbc;

import datos.UsuariosJDBC;
import domain.*;
import java.sql.*;
import java.util.*;

public class LaboratorioJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        UsuariosJDBC  usuariosJDBC = new UsuariosJDBC();
        //prueba insert
        //usuariosJDBC.insert("johnj", "123456");
        //prueba actualizar
        //usuariosJDBC.update(2,"juan", "123");
        //prueba eliminar
       // usuariosJDBC.delete(1);
       // prueba listar
      List<Usuario> usuarios = usuariosJDBC.select();
      for(Usuario usuario : usuarios){
          System.out.println(usuarios);
      }
       
    }
    
}
