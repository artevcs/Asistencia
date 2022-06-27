import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BotonDetalle extends JButton {

    Color BLANCO = new Color(255, 255, 255);
    Color AZUL_CIELO = new Color(218, 238, 243);
    Color GRIS2 = new Color(200, 200, 200);
    Color GRIS1 = new Color(64, 64, 64);

    Border borde = BorderFactory.createLineBorder(GRIS2, 1);
    EmptyBorder padding = new EmptyBorder(0,4,0,4);
    CompoundBorder bordeCombuesto = BorderFactory.createCompoundBorder(borde, padding);

    public BotonDetalle(int tipo, Dimension dimension){
        super("...");
        this.setBorder(bordeCombuesto);
        this.setOpaque(true);
        if (tipo == 0 )
            this.setBackground(BLANCO);
        else
            this.setBackground(AZUL_CIELO);
        this.setForeground(GRIS1);
        this.setPreferredSize(dimension);
    }

}
