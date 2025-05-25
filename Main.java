import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        GestorContactos gestor = new GestorContactos();
        gestor.crearTabla();

        String opcionStr;

        do {
            opcionStr = JOptionPane.showInputDialog(
                null,
                "--- Gestor de Contactos ---\n" +
                "1. Agregar contacto\n" +
                "2. Mostrar contactos\n" +
                "3. Buscar contacto\n" +
                "4. Eliminar contacto\n" +
                "5. Salir\n" +
                "Seleccione una opción:"
            );

            if (opcionStr == null) break;

            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                continue;
            }

            switch (opcion) {
                case 1:
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    String telefono = JOptionPane.showInputDialog("Teléfono:");
                    String correo = JOptionPane.showInputDialog("Correo electrónico:");
                    gestor.agregarContacto(new Contacto(nombre, telefono, correo));
                    break;
                case 2:
                    gestor.mostrarContactos();
                    break;
                case 3:
                    String buscar = JOptionPane.showInputDialog("Nombre a buscar:");
                    gestor.buscarContactoJOption(buscar);
                    break;
                case 4:
                    String eliminar = JOptionPane.showInputDialog("Nombre a eliminar:");
                    gestor.eliminarContactoJOption(eliminar);
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (!"5".equals(opcionStr));
    }
}

// java -cp ".;lib/sqlite-jdbc-3.49.1.0.jar" Main