// autor: ""

package ModelsDAO;

import ModelsDAO.*;
import Conexion.conexion;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReportStatusDAO {

    //este mapa guarda los estados con su id como clave y su nombre como valor
    private Map<Integer, String> statusMap = new HashMap<>();

    // uando se crea el DAO se carga automaticamente los estados
    public ReportStatusDAO() throws SQLException, ClassNotFoundException {
        loadStatus();
    }

    //carga los estados desde la base de datos y los guarda en el mapa statusMap
    private void loadStatus() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id, status_name FROM report_status";

        try (Connection conn = conexion.obtener();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // recoremos los resultados y los agregamos al mapa
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("status_name");
                statusMap.put(id, name);
            }
        }
    }
    

    //devuelve el nombre del estado segun su id, o "Desconocido" si no existe
    public String getStatusName(int statusId) {
        return statusMap.getOrDefault(statusId, "Desconocido");
    }
}
