import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraDeMenus extends JMenuBar {
    Turing aplicacion;

    public BarraDeMenus(Turing aplicacion){
        super();
        this.aplicacion = aplicacion;
        JMenu menuDatos = new JMenu("Datos");
        this.add(menuDatos);
        JMenuItem menuEmpresas = new JMenuItem("Empresas de seguridad");
        menuDatos.add(menuEmpresas);
        JMenuItem menuCerrarEmpresas = new JMenuItem("Cerrar panel empresas");
        menuDatos.add(menuCerrarEmpresas);

        menuEmpresas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    aplicacion.mostrarPanelEmpresas();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        menuCerrarEmpresas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacion.cerrarPanelActual();
            }
        });
    }
}
