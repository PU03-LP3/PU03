package Negocio;

import Conexion.conexion;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginNeg {
    
    public static class UsuarioLogin {
        public int id;
        public String nombre;
        public String rol;

        public UsuarioLogin(int id, String nombre, String rol) {
            this.id = id;
            this.nombre = nombre;
            this.rol = rol;
        }
    }

    public UsuarioLogin autenticar(String email, String password) {
        try (Connection cn = conexion.obtener()) {
            String sql = "SELECT id, full_name, role, password FROM users WHERE email = ?";
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String storedHash = rs.getString("password");

                        if (checkPassword(password, storedHash)) {
                            int id = rs.getInt("id");
                            String nombre = rs.getString("full_name");
                            String rol = rs.getString("role");
                            return new UsuarioLogin(id, nombre, rol);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        String hashedInput = hashSHA256(rawPassword);
        return hashedInput != null && hashedInput.equalsIgnoreCase(hashedPassword);
    }

    private String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
