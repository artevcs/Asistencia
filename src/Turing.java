import java.sql.*;

public class Turing {
    VentanaPrincipal ventanaPrincipal;
    MenuPrincipal menuPrincipal;
    TablaEmpresas tablaEmpresas;

    public static void main(String[] args) throws Exception {
        Turing programaTuring = new Turing();
        programaTuring.iniciar();
    }

    private void iniciar() throws Exception {
        ventanaPrincipal = new VentanaPrincipal();

        menuPrincipal = new MenuPrincipal();
        ventanaPrincipal.setJMenuBar(menuPrincipal);

        tablaEmpresas = new TablaEmpresas();
        ventanaPrincipal.getContentPane().add(tablaEmpresas);

        ventanaPrincipal.setVisible(true);
    }

    public static Connection getConexion() throws  Exception{
        Connection conexion = DriverManager.getConnection("jdbc:h2:file:./db/db", "huron", "orion");
        return conexion;
    }
}
