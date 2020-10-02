/*
 * Interfaz gráfica del programa
 * Arturo Jacob | Diego Barba | Obed Ramírez   | Diego Aarón
 */
package Tombola;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author artur
 */
public class Interfaz extends javax.swing.JFrame {

    Alumno alumnos[] = new Alumno[42];
    int tope = 0;
    DefaultTableModel modelo;
    DefaultTableModel modelo2;
    String arregloTabla[] = new String[2];
    String arregloTabla2[] = new String[2];

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.setSize(new Dimension(900, 350));
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/icons/ruleta.png")).getImage());
        modelo = new DefaultTableModel();
        modelo.addColumn("Número de lista");
        modelo.addColumn("Nombre/s");
        this.Tabla.setModel(modelo);

        modelo2 = new DefaultTableModel();
        modelo2.addColumn("Núm. lista");
        modelo2.addColumn("Nombre/s");
        this.TablaGanadores.setModel(modelo2);
    }

    /**
     * Método para guardar los datos del participante
     */
    public void CapturarDatos() {
        if (PantallaNombre.getText().equals("") || PantallaNumero.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Hay un campo vacio", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            if (tope >= 43) {
                JOptionPane.showMessageDialog(null, "Error. Se ha completado la lista");
            } else {
                if (repetido() == true) {
                    JOptionPane.showMessageDialog(null, "Ese número ya está en la lista. Elija otro.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    alumnos[tope] = new Alumno(PantallaNombre.getText(), Integer.parseInt(PantallaNumero.getText()), false);
                    arregloTabla[1] = alumnos[tope].nombre;
                    arregloTabla[0] = String.valueOf(alumnos[tope].listNum);
                    alumnos[tope].setControl(false);
                    modelo.addRow(arregloTabla);
                    tope++;
                }
            }
        }
    }//Fin CapturarDatos

    /**
     * Método para guardar los datos de la tabla en un archivo
     */
    protected void guardar() throws IOException {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = fc.showSaveDialog(this);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File archivo = fc.getSelectedFile();
        try {
            FileWriter fw = new FileWriter(archivo);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < Tabla.getRowCount(); i++) {
                for (int j = 0; j < Tabla.getColumnCount(); j++) {
                    pw.print(Tabla.getValueAt(i, j) + ",");
                }
                pw.println();
            }
            pw.close();
            JOptionPane.showMessageDialog(null, "Archivo guardado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//Fin guardar()

    /**
     * Método para cargar un archivo de datos en la tabla
     */
    protected void cargar() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = fc.showSaveDialog(this);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File archivo = fc.getSelectedFile();

        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader bf = new BufferedReader(fr);
            String linea;
            linea = bf.readLine();
            String[] lista = new String[modelo.getColumnCount()];
            while (linea != null) {
                StringTokenizer st = new StringTokenizer(linea, ",");
                lista[0] = st.nextToken();
                lista[1] = st.nextToken();

                modelo.addRow(lista);
                linea = bf.readLine();
            }

            bf.close();
            Objetos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al encontrar el archivo en carpeta del proyecto", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }//Fin cargar

    /**
     *
     */
    private void Objetos() {
        for (int i = 0; i < Tabla.getRowCount(); i++) {
            alumnos[tope] = new Alumno(String.valueOf(Tabla.getValueAt(i, 1)), Integer.parseInt(String.valueOf(Tabla.getValueAt(i, 0))), false);
            tope++;
        }
    }//Fin Objetos

    /**
     * Método para eliminar una fila
     */
    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        int filas = Tabla.getSelectedRow();
        if (filas >= 0) {
            modelo.removeRow(filas);
            tope--;
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona la fila a eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }//Fin popBorrarActionPerformed

    /**
     * Para saber si se repite algún número de la lista
     */
    private boolean repetido() {
        boolean bandera = false;
        for (int i = 0; i < tope; i++) {
            if (alumnos[i].listNum == Integer.parseInt(PantallaNumero.getText())) {
                bandera = true;
                break;
            }
        }
        return bandera;
    }//Fin Metodo repetido

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu2 = new javax.swing.JPopupMenu();
        BorrarFila = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PantallaNombre = new javax.swing.JTextField();
        PantallaNumero = new javax.swing.JTextField();
        BotonAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        BotonCargar = new javax.swing.JButton();
        BotonGuardar = new javax.swing.JButton();
        BotonReiniciar = new javax.swing.JButton();
        BotonBorrar = new javax.swing.JButton();
        Suerte = new javax.swing.JButton();
        PantallaResultado = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaGanadores = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        BorrarFila.setText("Borrar Fila");
        BorrarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarFilaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(BorrarFila);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("La Ruleta De La Suerte");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RULETA DE LA SUERTE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 60));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel2.setText("NOMBRE:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel3.setText("NÚMERO DE LISTA:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        PantallaNombre.setBackground(new java.awt.Color(217, 236, 242));
        PantallaNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PantallaNombre.setForeground(new java.awt.Color(51, 51, 51));
        PantallaNombre.setToolTipText("Espacio para escribir el nombre de un participante.");
        PantallaNombre.setNextFocusableComponent(PantallaNumero);
        PantallaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PantallaNombreKeyTyped(evt);
            }
        });
        getContentPane().add(PantallaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 290, 50));

        PantallaNumero.setBackground(new java.awt.Color(217, 236, 242));
        PantallaNumero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PantallaNumero.setForeground(new java.awt.Color(51, 51, 51));
        PantallaNumero.setToolTipText("Espacio para escribir el número de lista del participante.");
        PantallaNumero.setNextFocusableComponent(BotonAgregar);
        PantallaNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PantallaNumeroKeyTyped(evt);
            }
        });
        getContentPane().add(PantallaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 190, 50));

        BotonAgregar.setBackground(new java.awt.Color(246, 214, 173));
        BotonAgregar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        BotonAgregar.setForeground(new java.awt.Color(51, 51, 51));
        BotonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/agregar-usuario.png"))); // NOI18N
        BotonAgregar.setMnemonic('a');
        BotonAgregar.setText("AGREGAR");
        BotonAgregar.setToolTipText("Agregar participante a la tabla.");
        BotonAgregar.setNextFocusableComponent(PantallaNombre);
        BotonAgregar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/agregar-usuario (1).png"))); // NOI18N
        BotonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 120, 50));

        Tabla.setBackground(new java.awt.Color(217, 236, 242));
        Tabla.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        Tabla.setForeground(new java.awt.Color(51, 51, 51));
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        )
    );
    Tabla.setToolTipText("Lista de participantes.");
    Tabla.setComponentPopupMenu(jPopupMenu2);
    jScrollPane1.setViewportView(Tabla);

    getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 320, 260));

    BotonCargar.setBackground(new java.awt.Color(250, 252, 194));
    BotonCargar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    BotonCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abierto.png"))); // NOI18N
    BotonCargar.setMnemonic('c');
    BotonCargar.setText("Cargar");
    BotonCargar.setToolTipText("Cargar un archivo de su equipo.");
    BotonCargar.setNextFocusableComponent(BotonReiniciar);
    BotonCargar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/abierto (1).png"))); // NOI18N
    BotonCargar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonCargarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 150, -1));

    BotonGuardar.setBackground(new java.awt.Color(204, 246, 200));
    BotonGuardar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    BotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/disquete.png"))); // NOI18N
    BotonGuardar.setMnemonic('g');
    BotonGuardar.setText("Guardar");
    BotonGuardar.setToolTipText("Guardar la tabla en un archivo.");
    BotonGuardar.setNextFocusableComponent(BotonCargar);
    BotonGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/disquete (1).png"))); // NOI18N
    BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonGuardarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 160, -1));

    BotonReiniciar.setBackground(new java.awt.Color(246, 214, 173));
    BotonReiniciar.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
    BotonReiniciar.setForeground(new java.awt.Color(51, 51, 51));
    BotonReiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/restart.png"))); // NOI18N
    BotonReiniciar.setMnemonic('r');
    BotonReiniciar.setText("Reiniciar");
    BotonReiniciar.setToolTipText("Reinicar tómbola.");
    BotonReiniciar.setNextFocusableComponent(BotonBorrar);
    BotonReiniciar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonReiniciarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 250, 60));

    BotonBorrar.setBackground(new java.awt.Color(249, 192, 192));
    BotonBorrar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    BotonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar.png"))); // NOI18N
    BotonBorrar.setMnemonic('b');
    BotonBorrar.setText("Borrar Tabla");
    BotonBorrar.setToolTipText("Borrar toda la tabla.");
    BotonBorrar.setNextFocusableComponent(Suerte);
    BotonBorrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eliminar (1).png"))); // NOI18N
    BotonBorrar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonBorrarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 320, -1));

    Suerte.setBackground(new java.awt.Color(246, 214, 173));
    Suerte.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    Suerte.setForeground(new java.awt.Color(51, 51, 51));
    Suerte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trebol.png"))); // NOI18N
    Suerte.setMnemonic('p');
    Suerte.setText("¡PROBAR SUERTE!");
    Suerte.setToolTipText("Girar la tómbola.");
    Suerte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    Suerte.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trebol (1).png"))); // NOI18N
    Suerte.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            SuerteActionPerformed(evt);
        }
    });
    getContentPane().add(Suerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 190, 40));

    PantallaResultado.setEditable(false);
    PantallaResultado.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
    PantallaResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    PantallaResultado.setToolTipText("Aquí aparecerá el ganador.");
    getContentPane().add(PantallaResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 220, 90));

    TablaGanadores.setBackground(new java.awt.Color(217, 236, 242));
    TablaGanadores.setForeground(new java.awt.Color(51, 51, 51));
    TablaGanadores.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {},
            {},
            {},
            {}
        },
        new String [] {

        }
    ));
    jScrollPane2.setViewportView(TablaGanadores);

    getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 250, 120));

    jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel4.setText("Tabla de ganadores");
    getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 250, -1));

    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/blue.jpg"))); // NOI18N
    getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, -10, -1, 70));

    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/blue.jpg"))); // NOI18N
    getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, -1, 70));

    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nube 1.png"))); // NOI18N
    getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 360, -1, -1));

    jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nube 1.png"))); // NOI18N
    getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

    jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nube 1.png"))); // NOI18N
    getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 90, -1, -1));

    jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nube 1.png"))); // NOI18N
    getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, -1));

    jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/letrero.png"))); // NOI18N
    getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, -1, -1));

    jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ruleta.png"))); // NOI18N
    getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nube 1.png"))); // NOI18N
    getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, -30, -1, -1));

    jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sol.png"))); // NOI18N
    getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

    jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pilar2.png"))); // NOI18N
    getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, -1, -1));

    jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nube 1.png"))); // NOI18N
    getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, -1, -1));

    jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/avion.png"))); // NOI18N
    getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, -1, -1));

    jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/globos 2.png"))); // NOI18N
    getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, -1, -1));

    jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nube 1.png"))); // NOI18N
    getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, -30, -1, -1));

    jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/globos 2.png"))); // NOI18N
    getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, -1, -1));

    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/blue 2.jpg"))); // NOI18N
    getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 50, 930, 540));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para agregar datos a la tabla
     */
    private void BotonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarActionPerformed
        CapturarDatos();
        //Limpiamos campos
        PantallaNombre.setText("");
        PantallaNumero.setText("");
    }//GEN-LAST:event_BotonAgregarActionPerformed

    /**
     * Método para cargar un archivo, y mostrar los datos de este en la tabla
     */
    private void BotonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCargarActionPerformed
        int filas = Tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
        int filas2 = TablaGanadores.getRowCount();
        for (int i = 0; i < filas2; i++) {
            modelo2.removeRow(0);
            alumnos[i].setControl(false);
        }
        tope = 0;
        cargar();
    }//GEN-LAST:event_BotonCargarActionPerformed

    /**
     * Método para guardar un archivo con los datos de la tabla
     */
    private void BotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarActionPerformed
        try {
            guardar();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonGuardarActionPerformed

    /**
     * Método para limpiar los campos de texto
     */
    private void BotonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonReiniciarActionPerformed

        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea borrar todos los datos de la tabla de participantes?", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            int filas = TablaGanadores.getRowCount();
            for (int i = 0; i < filas; i++) {
                modelo2.removeRow(0);
                alumnos[i].setControl(false);
            }

        }

    }//GEN-LAST:event_BotonReiniciarActionPerformed

    /**
     * Método para borrar los datos de la tabla
     */
    private void BotonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBorrarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea borrar todos los datos de la tabla?", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            int filas = Tabla.getRowCount();
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);
            }
            int filas2 = TablaGanadores.getRowCount();
            for (int i = 0; i < filas2; i++) {
                modelo2.removeRow(0);
                alumnos[i].setControl(false);
            }

        }
        tope = 0;
    }//GEN-LAST:event_BotonBorrarActionPerformed

    /**
     * Método para permitir únicamente letras en el campo de Nombre
     */
    private void PantallaNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PantallaNombreKeyTyped

        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {

            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar sólo LETRAS");

        }

    }//GEN-LAST:event_PantallaNombreKeyTyped

    /**
     * Método para permitir únicamente números en el campo de Número
     */
    private void PantallaNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PantallaNumeroKeyTyped

        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {

            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar sólo NÚMEROS");

        }

    }//GEN-LAST:event_PantallaNumeroKeyTyped

    /**
     * Método para borrar una fila
     */
    private void BorrarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarFilaActionPerformed
        int filas = Tabla.getSelectedRow();
        if (filas >= 0) {
            modelo.removeRow(filas);
            tope--;
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona la fila a eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BorrarFilaActionPerformed

    /**
     * Método para juegar a la tómbola
     */
    private void SuerteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuerteActionPerformed

        Random aleatorio = new Random();
        boolean bandera = true;// variable de centinela
        boolean repetidos = false;
        int numero = 0;
        if (tope == 0) {
            JOptionPane.showMessageDialog(null, "No hay personas", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            while (bandera == true) {//ciclo, hasta que no aparezca un dato que no ha sido seleccionado terminara
                if (repetidos == true) {
                    JOptionPane.showMessageDialog(null, "Todos han participado", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }

                numero = aleatorio.nextInt(tope);//da un numero al azar, dnde cont es la cantidad de personas regsitradas en el arreglo de objetos
                //posicion es el valor de control de cada persona, en la calse datos[la de arreglo de objetos] para saber si ya participo o no

                if (alumnos[numero].getControl() == false) {

                    JOptionPane.showMessageDialog(null, alumnos[numero].getNombre() + " " + alumnos[numero].getListNum(), "¡ FELICIDADES !", JOptionPane.INFORMATION_MESSAGE);
                    alumnos[numero].setControl(true);
                    arregloTabla2[1] = alumnos[numero].getNombre();
                    arregloTabla2[0] = String.valueOf(alumnos[numero].getListNum());
                    modelo2.addRow(arregloTabla2);
                    PantallaResultado.setText(alumnos[numero].getNombre());
                    break;
                } else {
                    int cont = 0;

                    for (int i = 0; i < tope; i++) {
                        if (alumnos[i].getControl() == false) {

                            cont++;
                        }

                    }
                    if (cont == 0) {
                        repetidos = true;
                    }
                }

            }
        }
    }//GEN-LAST:event_SuerteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BorrarFila;
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JButton BotonBorrar;
    private javax.swing.JButton BotonCargar;
    private javax.swing.JButton BotonGuardar;
    private javax.swing.JButton BotonReiniciar;
    private javax.swing.JTextField PantallaNombre;
    private javax.swing.JTextField PantallaNumero;
    private javax.swing.JTextField PantallaResultado;
    private javax.swing.JButton Suerte;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable TablaGanadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
