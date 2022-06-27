import javax.swing.*;
import java.awt.*;

public class AmeEmpresas extends JPanel {

    Color GRIS4 = new Color(242, 242, 242);
    SpringLayout layOut = new SpringLayout();

    public AmeEmpresas(){
        this.setLayout(layOut);
        this.setBackground(GRIS4);
        this.setSize(new Dimension(1024,768));
    }
}
