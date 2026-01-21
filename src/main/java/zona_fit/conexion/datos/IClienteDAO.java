package zona_fit.conexion.datos;

import zona_fit.conexion.dominio.Cliente;

import java.util.List;

public interface IClienteDAO {
    List<Cliente> listarClientes();
    boolean buscarClientePorId(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
