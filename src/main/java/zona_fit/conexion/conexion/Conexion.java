package zona_fit.conexion.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var baseDatos = "nombre-db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "tu_usuario";
        var password = "tu_password";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,password);
        }catch (Exception e){
            System.out.println("ERROR al conectarse a la base de datos " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion != null){
            System.out.println("Conexion exitosa:" + conexion);
        }else {
            System.out.println("Error al intentar conectarse a la base de datos...");
        }
    }
}

