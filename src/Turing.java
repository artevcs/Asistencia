import javax.swing.*;
import java.sql.*;

public class Turing {

    VentanaPrincipal ventanaPrincipal;
    BarraDeMenus barraDeMenus;
    JPanel panelActual;
    TablaEmpresas tablaEmpresas;

    public static void main(String[] args) throws Exception {
        Turing programaTuring = new Turing();
        programaTuring.iniciar();
    }

    private void iniciar(){
        ventanaPrincipal = new VentanaPrincipal();

        barraDeMenus = new BarraDeMenus(this);
        ventanaPrincipal.setJMenuBar(barraDeMenus);
        ventanaPrincipal.setVisible(true);
    }

    public static Connection getConexion() throws  Exception{
        Connection conexion = DriverManager.getConnection("jdbc:h2:file:./db/db", "huron", "orion");
        return conexion;
    }

    public void mostrarPanelEmpresas() throws Exception {
        tablaEmpresas = new TablaEmpresas();
        cerrarPanelActual();
        panelActual = tablaEmpresas;
        ventanaPrincipal.getContentPane().add(tablaEmpresas);
        ventanaPrincipal.actualizar();
    }

    public void cerrarPanelActual(){
        if (panelActual != null)
            ventanaPrincipal.remove(panelActual);
        ventanaPrincipal.actualizar();
    }
}
