import javax.swing.*;

public class MenuPrincipal extends JMenuBar {
    public MenuPrincipal(){
        super();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuReportes = new JMenu("Reportes");

        this.add(menuArchivo);
        this.add(menuReportes);
    }
}
