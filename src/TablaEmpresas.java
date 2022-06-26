import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TablaEmpresas extends JPanel {
    Color BLANCO = new Color(255, 255, 255);
    Color AZUL_CIELO = new Color(218, 238, 243);
    Color GRIS4 = new Color(242, 242, 242);
    Color GRIS3 = new Color(217, 217, 217);
    Color GRIS2 = new Color(200, 200, 200);
    Color GRIS1 = new Color(64, 64, 64);

    int MARGEN_SUPERIOR = 20;
    int MARGEN_IZQUIERDO = 20;
    int ALTO_FILA = 22;
    int SEPARACION = 2;

    ArrayList<Columna> columnas = new ArrayList<>();

    Border borde = BorderFactory.createLineBorder(GRIS2, 1);
    EmptyBorder padding = new EmptyBorder(0,4,0,4);
    CompoundBorder bordeCombuesto = BorderFactory.createCompoundBorder(borde, padding);

    SpringLayout layOut = new SpringLayout();

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
        columnas.add(new Columna(22, "", JLabel.CENTER));

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
        int fila = 1;
        while(resultado.next()) {
            agregarFila(fila, resultado.getString("id_empresa"), resultado.getString("nombre"));
            fila++;
        }
        conexion.close();
    }

    private void agregarFila(int fila, String id, String nombre){
        int tipo;
        if((fila % 2 ) == 0)
            tipo = 0;
        else
           tipo = 1;

        agregarEtiquetaContenido(fila, 0, id, tipo);
        agregarEtiquetaContenido(fila, 1, nombre, tipo);
        agregarBotonContenido(fila, 2, id, "...", tipo);
    }

    private void agregarEtiquetaContenido(int fila, int columna, String texto, int tipo){
        JLabel etiqueta = new JLabel(texto, columnas.get(columna).alineacion);
        etiqueta.setBorder(bordeCombuesto);
        etiqueta.setOpaque(true);
        if (tipo == 0 )
            etiqueta.setBackground(AZUL_CIELO);
        else
            etiqueta.setBackground(BLANCO);
        etiqueta.setForeground(GRIS1);
        etiqueta.setPreferredSize(new Dimension(columnas.get(columna).ancho, ALTO_FILA));
        layOut.putConstraint(SpringLayout.WEST, etiqueta, margenIzquierdo(columna), SpringLayout.WEST, this);
        layOut.putConstraint(SpringLayout.NORTH, etiqueta, margenSuperior(fila), SpringLayout.NORTH, this);
        this.add(etiqueta);
    }

    private void agregarBotonContenido(int fila, int columna, String id, String texto, int tipo){
        JButton boton = new JButton(texto);
        boton.setBorder(bordeCombuesto);
        boton.setOpaque(true);
        if (tipo == 0 )
            boton.setBackground(AZUL_CIELO);
        else
            boton.setBackground(BLANCO);
        boton.setForeground(GRIS1);
        boton.setPreferredSize(new Dimension(columnas.get(columna).ancho, ALTO_FILA));
        layOut.putConstraint(SpringLayout.WEST, boton, margenIzquierdo(columna), SpringLayout.WEST, this);
        layOut.putConstraint(SpringLayout.NORTH, boton, margenSuperior(fila), SpringLayout.NORTH, this);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clic " + id);
            }
        });
        this.add(boton);
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