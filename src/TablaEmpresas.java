import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TablaEmpresas extends JPanel {
    Color GRIS4 = new Color(242, 242, 242);
    Color GRIS3 = new Color(217, 217, 217);
    Color GRIS2 = new Color(190, 190, 190);
    Color GRIS1 = new Color(64, 64, 64);

    int MARGEN_SUPERIOR = 20;
    int MARGEN_IZQUIERDO = 20;
    int ALTO_FILA = 22;
    int SEPARACION = 2;

    ArrayList<Columna> columnas = new ArrayList<>();
    ArrayList<Fila> filas = new ArrayList<>();

    Border borde = BorderFactory.createLineBorder(GRIS2, 1);
    EmptyBorder padding = new EmptyBorder(0,4,0,4);
    CompoundBorder bordeCombuesto = BorderFactory.createCompoundBorder(borde, padding);

    SpringLayout layOut = new SpringLayout();;

    public TablaEmpresas() throws Exception {
        super();
        this.setLayout(layOut);
        this.setBackground(GRIS4);
        this.setSize(new Dimension(1024,768));

        agregarTituloDeColumnas();
        rellenarTabla();
    }

    private void agregarTituloDeColumnas(){
        columnas.add(new Columna(50, "Id", JLabel.CENTER));
        columnas.add(new Columna(250, "Nombre", JLabel.LEFT));

        for (Columna columna: columnas) {
            crearEtiquetaEncabezado(columna);
        }
    }

    private void crearEtiquetaEncabezado(Columna columna){
        JLabel etiqueta = new JLabel(columna.titulo, columna.alineacion);
        etiqueta.setBorder(bordeCombuesto);
        etiqueta.setOpaque(true);
        etiqueta.setBackground(GRIS3);
        etiqueta.setForeground(GRIS1);
        etiqueta.setPreferredSize(new Dimension(columna.ancho, ALTO_FILA));
        int indice = columnas.indexOf(columna);
        layOut.putConstraint(SpringLayout.WEST, etiqueta, margenIzquierdo(indice), SpringLayout.WEST, this);
        layOut.putConstraint(SpringLayout.NORTH, etiqueta, margenSuperior(0), SpringLayout.NORTH, this);
        this.add(etiqueta);
    }

    private void rellenarTabla() throws Exception {
        Connection conexion = Turing.getConexion();
        Statement sentencia = conexion.createStatement();
        String consulta = "SELECT * FROM Empresas;";
        ResultSet resultado = sentencia.executeQuery(consulta);
        while(resultado.next()) {
            System.out.println(resultado.getString("id_empresa") + " | " + resultado.getString("nombre"));
        }
        conexion.close();
    }

    private void agregarFila(int indice, String id, String nombre, int tipo){

    }

    private int margenIzquierdo(int indiceColumna){
        int margen = MARGEN_IZQUIERDO;
        int indiceColumnaActual = 0;
        while (indiceColumna > 0 && indiceColumnaActual < indiceColumna){
            margen = margen + columnas.get(indiceColumnaActual).ancho + SEPARACION;
            indiceColumnaActual++;
        }
        return margen;
    }

    private int margenSuperior(int fila){
        return MARGEN_SUPERIOR + (fila * (ALTO_FILA + SEPARACION));
    }
}

class Fila {

    int tipo;
    int indice;
    JLabel etiquetaId;
    JLabel etiquetaNombre;

    public Fila(int indice, int tipo){
        this.indice = indice;
        this.tipo = tipo;
    }
}