/*
 * Interfaz gráfica del programa
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

/**
 *
 * @author artur
 */
public class Interfaz extends javax.swing.JFrame {

    Alumno alumnos[] = new Alumno[42];
    int tope = 0;
    DefaultTableModel modelo;
    String arregloTabla[] = new String[2];

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.setSize(new Dimension(900, 350));
        this.setLocationRelativeTo(null);
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre/s");
        modelo.addColumn("Numero de lista");
        this.Tabla.setModel(modelo);
    }

    /**
     * Método para guardar los datos del participante
     */
    public void CapturarDatos() {
       if(PantallaNombre.getText().equals("")||PantallaNumero.getText().equals("")){
           JOptionPane.showMessageDialog(null,"Hay un campo vacio","Error",JOptionPane.ERROR_MESSAGE);
        
       }
       else{
            if (tope >= 43) {
            JOptionPane.showMessageDialog(null, "Error. Se ha completado la lista");
        } else {
            alumnos[tope] = new Alumno(PantallaNombre.getText(), Integer.parseInt(PantallaNumero.getText()), false);
            arregloTabla[0] = alumnos[tope].nombre;
            arregloTabla[1] = String.valueOf(alumnos[tope].listNum);
            alumnos[tope].setControl(false);
            modelo.addRow(arregloTabla);
            tope++;
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
                lista[1] = st.nextToken();
                lista[0] = st.nextToken();
                
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
    private void Objetos(){
        for(int i=0;i<Tabla.getRowCount();i++){
        alumnos[tope] = new Alumno(String.valueOf(Tabla.getValueAt(i, 0)), Integer.parseInt(String.valueOf(Tabla.getValueAt(i, 1))), false);        
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
        BotonLimpiar = new javax.swing.JButton();
        BotonBorrar = new javax.swing.JButton();
        Suerte = new javax.swing.JButton();
        PantallaResultado = new javax.swing.JTextField();

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
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RULETA DE LA SUERTE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel2.setText("NOMBRE:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel3.setText("NÚMERO DE LISTA:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        PantallaNombre.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        PantallaNombre.setToolTipText("Espacio para escribir el nombre de un participante.");
        PantallaNombre.setNextFocusableComponent(PantallaNumero);
        PantallaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PantallaNombreKeyTyped(evt);
            }
        });
        getContentPane().add(PantallaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 310, 50));

        PantallaNumero.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        PantallaNumero.setToolTipText("Espacio para escribir el número de lista del participante.");
        PantallaNumero.setNextFocusableComponent(BotonAgregar);
        PantallaNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PantallaNumeroKeyTyped(evt);
            }
        });
        getContentPane().add(PantallaNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 190, 50));

        BotonAgregar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        BotonAgregar.setMnemonic('a');
        BotonAgregar.setText("AGREGAR");
        BotonAgregar.setToolTipText("Agregar participante a la tabla.");
        BotonAgregar.setNextFocusableComponent(PantallaNombre);
        BotonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(BotonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, 40));

        Tabla.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
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

    getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 235, 310, 260));

    BotonCargar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    BotonCargar.setMnemonic('c');
    BotonCargar.setText("Cargar");
    BotonCargar.setToolTipText("Cargar un archivo de su equipo.");
    BotonCargar.setNextFocusableComponent(BotonLimpiar);
    BotonCargar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonCargarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 150, -1));

    BotonGuardar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    BotonGuardar.setMnemonic('g');
    BotonGuardar.setText("Guardar");
    BotonGuardar.setToolTipText("Guardar la tabla en un archivo.");
    BotonGuardar.setNextFocusableComponent(BotonCargar);
    BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonGuardarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 150, -1));

    BotonLimpiar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    BotonLimpiar.setMnemonic('l');
    BotonLimpiar.setText("Limpiar Campos");
    BotonLimpiar.setToolTipText("Limpiar campos de texto.");
    BotonLimpiar.setNextFocusableComponent(BotonBorrar);
    BotonLimpiar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonLimpiarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 150, -1));

    BotonBorrar.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    BotonBorrar.setMnemonic('b');
    BotonBorrar.setText("Borrar Tabla");
    BotonBorrar.setToolTipText("Borrar toda la tabla.");
    BotonBorrar.setNextFocusableComponent(Suerte);
    BotonBorrar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            BotonBorrarActionPerformed(evt);
        }
    });
    getContentPane().add(BotonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 150, -1));

    Suerte.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    Suerte.setMnemonic('p');
    Suerte.setText("¡PROBAR SUERTE!");
    Suerte.setToolTipText("Girar la tómbola.");
    Suerte.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            SuerteActionPerformed(evt);
        }
    });
    getContentPane().add(Suerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 160, 40));

    PantallaResultado.setEditable(false);
    PantallaResultado.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
    PantallaResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    PantallaResultado.setToolTipText("Aquí aparecerá el ganador.");
    PantallaResultado.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            PantallaResultadoActionPerformed(evt);
        }
    });
    getContentPane().add(PantallaResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 180, 60));

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
    private void BotonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiarActionPerformed

        if (PantallaNombre.getText().equals("") && PantallaNumero.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No hay nada que limpiar", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
        } else {
            PantallaNombre.setText("");
            PantallaNumero.setText("");
        }
    }//GEN-LAST:event_BotonLimpiarActionPerformed

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

        }
        tope=0;
    }//GEN-LAST:event_BotonBorrarActionPerformed

    /**
     * Método para permitir únicamente letras en el campo de Nombre
     */
    private void PantallaNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PantallaNombreKeyTyped

        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {

            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo LETRAS");

        }

    }//GEN-LAST:event_PantallaNombreKeyTyped

     /**
     * Método para permitir únicamente números en el campo de Número
     */
    private void PantallaNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PantallaNumeroKeyTyped

        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {

            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo NÚMEROS");

        }

    }//GEN-LAST:event_PantallaNumeroKeyTyped

    private void BorrarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarFilaActionPerformed
        int filas = Tabla.getSelectedRow();
        if (filas >= 0) {
            modelo.removeRow(filas);
            tope--;
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona la fila a eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BorrarFilaActionPerformed

    private void PantallaResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaResultadoActionPerformed
            
    }//GEN-LAST:event_PantallaResultadoActionPerformed

    private void SuerteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuerteActionPerformed
    
         Random aleatorio= new Random();
        boolean bandera=true;// variable de centinela
        boolean repetidos=false;
       int numero=0;
        if(tope==0){
            JOptionPane.showMessageDialog(null, "No hay personas","Error",JOptionPane.ERROR_MESSAGE);
        }else{
         while(bandera==true){//ciclo, hasta que no aparezca un dato que no ha sido seleccionado terminara
          if(repetidos==true){
              JOptionPane.showMessageDialog(null,"Todos han participado", "Error",JOptionPane.ERROR_MESSAGE);
              break;
          }
            
            numero=aleatorio.nextInt(tope);//da un numero al azar, dnde cont es la cantidad de personas regsitradas en el arreglo de objetos
          //posicion es el valor de control de cada persona, en la calse datos[la de arreglo de objetos] para saber si ya participo o no
            
            
           if(alumnos[numero].getControl()==false){
               JOptionPane.showMessageDialog(null,alumnos[numero].getNombre()+" "+alumnos[numero].getListNum(),"¡ FELICIDADES !",JOptionPane.INFORMATION_MESSAGE);
               alumnos[numero].setControl(true);
               break;
           }
           else{
               int cont=0;
              
               for(int i=0;i<tope;i++){
                   if(alumnos[i].getControl()==false){

                       cont++;
                   }
    
               }
               if(cont==0){
                   repetidos=true;
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
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JTextField PantallaNombre;
    private javax.swing.JTextField PantallaNumero;
    private javax.swing.JTextField PantallaResultado;
    private javax.swing.JButton Suerte;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
