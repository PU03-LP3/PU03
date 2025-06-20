// Autor: ""

package ModelsDAO;

import ModelsDAO.*;
import Conexion.conexion;
import Models.User;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDAO {

//Obtiene el usuario mediante el email
    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id, full_name, email, password, role, created_at FROM users WHERE email = ?";
        try (Connection conn = conexion.obtener();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getTimestamp("created_at")
                );
            }
        }
        return null;
    }

    
//Verifica si el email y contraseña ingresados corresponden a un usuario registrado 
 //y la Contrasena en  ingresada por el usuario.
//return Objeto User si las credenciales son válidas, o null si no lo son.
    
    public User login(String email, String password) throws SQLException, ClassNotFoundException {
        User user = getUserByEmail(email);
        if (user != null && checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

//Verifica si la contraseña ingresada  coincide con la contraseña almacenada (hash).

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        try {
            // Crea una instancia del algoritmo de hash SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Aplica el algoritmo SHA-256 a la contraseña ingresada (en bytes)
            byte[] hashBytes = md.digest(rawPassword.getBytes());
             // Construye el hash en formato hexadecimal como texto
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                // Convierte cada byte en un valor hexadecimal de 2 dígitos y lo agrega al StringBuilder
                sb.append(String.format("%02x", b));
            }
              // Obtiene la cadena final del hash generado
            String hashedInput = sb.toString();
            // Compara el hash generado con el hash almacenado en la base de datos
            return hashedInput.equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            // En caso de que el algoritmo "SHA-256" no esté disponible, imprime el errors
            e.printStackTrace();
            return false;
        }
    }

  //Crea un nuevo usuario en la base de datos
    public boolean createUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO users (full_name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexion.obtener();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
