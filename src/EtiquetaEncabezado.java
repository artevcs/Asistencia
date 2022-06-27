import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EtiquetaEncabezado extends JLabel {
    Color GRIS3 = new Color(217, 217, 217);
    Color GRIS2 = new Color(200, 200, 200);
    Color GRIS1 = new Color(64, 64, 64);

    public EtiquetaEncabezado(String texto, int alineacion, Dimension dimension){
        super(texto, alineacion);
        Border borde = BorderFactory.createLineBorder(GRIS2, 1);
        EmptyBorder padding = new EmptyBorder(0,4,0,4);
        CompoundBorder bordeCompuesto = BorderFactory.createCompoundBorder(borde, padding);
        this.setBorder(bordeCompuesto);
        this.setOpaque(true);
        this.setBackground(GRIS3);
        this.setForeground(GRIS1);
        this.setPreferredSize(dimension);
    }
}
