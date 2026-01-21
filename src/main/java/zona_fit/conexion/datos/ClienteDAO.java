package zona_fit.conexion.datos;

import zona_fit.conexion.dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> listaDeClientes = new ArrayList<>();
        Connection con = getConexion();
        var sql = "select * from cliente order by id";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                var cliente  = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                listaDeClientes.add(cliente);
            }
        }catch (Exception e){
            System.out.printf("Error en la consulta " + e.getCause());
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return listaDeClientes;
    }
    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        Connection con = getConexion();
        var sql = "select * from cliente where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error con la consulta " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion " + e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public boolean actualizarCliente(Cliente cliente) {
        int filasAfectadas;
        Connection con = getConexion();
        var sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            ps.setInt(4,cliente.getId());
            filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }catch (Exception e){
            System.out.printf("Error en la consulta " + e.getCause());
            e.printStackTrace();

        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        Connection con = getConexion();
        int filasAfectadas;
        var sql = "Insert into cliente (nombre,apellido,membresia) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }catch (Exception e){
            System.out.println("Error al agregar cliente: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        Connection con = getConexion();
        int filasAfectadas;
        var sql = "delete from cliente where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }catch (Exception e){
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }
}
