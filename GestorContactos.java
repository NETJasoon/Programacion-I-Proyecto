import java.sql.Connection;

public class GestorContactos {

    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS contactos (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nombre TEXT NOT NULL," +
                     "telefono TEXT NOT NULL," +
                     "correo TEXT NOT NULL)";
        try (Connection conn = ConexionSQLite.conectar();
             java.sql.Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (java.sql.SQLException e) {
            System.out.println("Error creando tabla: " + e.getMessage());
        }
    }

    public void agregarContacto(Contacto c) {
        String sql = "INSERT INTO contactos(nombre, telefono, correo) VALUES (?, ?, ?)";
        try (Connection conn = ConexionSQLite.conectar();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getTelefono());
            pstmt.setString(3, c.getCorreo());
            pstmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            System.out.println("Error al agregar contacto: " + e.getMessage());
        }
    }

    public void mostrarContactos() {
        String sql = "SELECT * FROM contactos";
        try (Connection conn = ConexionSQLite.conectar();
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("-------------------------");
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Error al mostrar contactos: " + e.getMessage());
        }
    }

    public void buscarContactoJOption(String nombreBuscado) {
        String sql = "SELECT * FROM contactos WHERE nombre LIKE ?";
        try (Connection conn = ConexionSQLite.conectar();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombreBuscado + "%");
            java.sql.ResultSet rs = pstmt.executeQuery();

            StringBuilder resultado = new StringBuilder();
            while (rs.next()) {
                resultado.append("ID: ").append(rs.getInt("id")).append("\n")
                         .append("Nombre: ").append(rs.getString("nombre")).append("\n")
                         .append("Teléfono: ").append(rs.getString("telefono")).append("\n")
                         .append("Correo: ").append(rs.getString("correo")).append("\n")
                         .append("-------------------------\n");
            }

            if (resultado.length() == 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Contacto no encontrado.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, resultado.toString());
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Error al buscar contacto: " + e.getMessage());
        }
    }

    public void eliminarContactoJOption(String nombreEliminar) {
        String sql = "DELETE FROM contactos WHERE nombre = ?";
        try (Connection conn = ConexionSQLite.conectar();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreEliminar);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "Contacto eliminado exitosamente.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No se encontró el contacto.");
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Error al eliminar contacto: " + e.getMessage());
        }
    }
}
