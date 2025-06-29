package Vista;

import Negocio.LoginNeg;
import Negocio.ManagerNeg;
import java.awt.Color;
import java.awt.Font;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Manager extends javax.swing.JFrame {
    
    private final ManagerNeg managerNeg = new ManagerNeg();
    private LoginNeg.UsuarioLogin currentUser;
    private Map<String, Integer> statusMap;

    public Manager() {
        this(new LoginNeg.UsuarioLogin(0, "Desconocido", "Manager"));
    }
    public Manager(LoginNeg.UsuarioLogin user) {
        initComponents();
        aplicarEstilos();
        this.currentUser = user;
        setTitle("Panel del Encargado - " + user.nombre);
        setLocationRelativeTo(null);
        cargarDatos();
    }
    private void cargarDatos() {
        DefaultTableModel model = (DefaultTableModel) tbsolicitudes.getModel();
        model.setRowCount(0);
        for (String[] fila : managerNeg.listarReportesAsignados(currentUser.id)) {
            model.addRow(fila);
        }

        cmstatus.removeAllItems();
        statusMap = managerNeg.obtenerEstados();
        for (String nombre : statusMap.keySet()) {
            cmstatus.addItem(nombre);
        }

        int[] progreso = managerNeg.obtenerProgreso(currentUser.id);
        int cerrados = progreso[0], total = progreso[1];
        int porcentaje = total == 0 ? 0 : (cerrados * 100 / total);
        prgcerradas.setValue(porcentaje);
        prgcerradas.setStringPainted(true);
        prgcerradas.setString(porcentaje + "% cerrados");
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsolicitudes = new javax.swing.JTable();
        cmstatus = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnVerImg = new javax.swing.JButton();
        prgcerradas = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel1.setText("SOLICITUDES ENCARGADAS");

        tbsolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Reporte", "Titulo de Reporte", "Reportado Por", "Progreso"
            }
        ));
        jScrollPane1.setViewportView(tbsolicitudes);
        if (tbsolicitudes.getColumnModel().getColumnCount() > 0) {
            tbsolicitudes.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        cmstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnVerImg.setText("VER IMAGEN");
        btnVerImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerImgActionPerformed(evt);
            }
        });

        jLabel2.setText("TU RATIO (CERRADAS)");

        btnCerrarSesion.setText("CERRAR SESIÓN");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reload.png"))); // NOI18N
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVerImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btnCerrarSesion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReload)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(prgcerradas, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addGap(32, 32, 32)
                        .addComponent(btnVerImg)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prgcerradas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185)
                        .addComponent(btnCerrarSesion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    int fila = tbsolicitudes.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una solicitud.");
        return;
    }

    String idStr = tbsolicitudes.getValueAt(fila, 0).toString();
    int reportId = Integer.parseInt(idStr);
    String estado = cmstatus.getSelectedItem().toString();
    int statusId = statusMap.get(estado);

    boolean actualizado = managerNeg.actualizarEstado(reportId, statusId);
    if (actualizado) {
        JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.");
        cargarDatos();
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar el estado.");
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVerImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerImgActionPerformed
    int fila = tbsolicitudes.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una solicitud.");
        return;
    }

    int reportId = Integer.parseInt(tbsolicitudes.getValueAt(fila, 0).toString());
    byte[] imagenBytes = managerNeg.obtenerFotoReporte(reportId);

    if (imagenBytes != null) {
        ImageIcon originalIcon = new ImageIcon(imagenBytes);
        int anchoMax = 500;
        int altoMax = 400;

        float scale = Math.min((float) anchoMax / originalIcon.getIconWidth(), (float) altoMax / originalIcon.getIconHeight());
        int newWidth = Math.round(originalIcon.getIconWidth() * scale);
        int newHeight = Math.round(originalIcon.getIconHeight() * scale);

        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel lbl = new JLabel(scaledIcon);
        JOptionPane.showMessageDialog(this, lbl, "Foto del Reporte", JOptionPane.PLAIN_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No hay imagen disponible.");
    }
    }//GEN-LAST:event_btnVerImgActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose(); 

    // Abrir la ventana de login u otra ventana deseada
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        cargarDatos();
    }//GEN-LAST:event_btnReloadActionPerformed

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
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnVerImg;
    private javax.swing.JComboBox<String> cmstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar prgcerradas;
    private javax.swing.JTable tbsolicitudes;
    // End of variables declaration//GEN-END:variables

    private void aplicarEstilos() {
            // Colores y fuentes
            Color guinda = new Color(128, 0, 32);
            Color rojoClaro = new Color(200, 0, 50);
            Font fuenteNegrita = new Font("Arial", Font.BOLD, 14);
            Font fuenteTitulo = new Font("Arial", Font.BOLD, 22);

            // Fondo del JFrame
            getContentPane().setBackground(guinda);

            // Título
            jLabel1.setForeground(Color.WHITE);
            jLabel1.setFont(fuenteTitulo);

            // Subtítulo o label adicional
            jLabel2.setForeground(Color.WHITE);
            jLabel2.setFont(fuenteNegrita);

            // Botón Actualizar
            btnActualizar.setBackground(rojoClaro);
            btnActualizar.setForeground(Color.WHITE);
            btnActualizar.setFont(fuenteNegrita);
            btnActualizar.setFocusPainted(false);

            // Botón Cerrar Sesión
            btnCerrarSesion.setBackground(rojoClaro);
            btnCerrarSesion.setForeground(Color.WHITE);
            btnCerrarSesion.setFont(fuenteNegrita);
            btnCerrarSesion.setFocusPainted(false);

            // Botón Ver Imagen
            btnVerImg.setBackground(rojoClaro);
            btnVerImg.setForeground(Color.WHITE);
            btnVerImg.setFont(fuenteNegrita);
            btnVerImg.setFocusPainted(false);

            // ComboBox cmstatus
            cmstatus.setBackground(Color.WHITE);
            cmstatus.setForeground(guinda.darker());
            cmstatus.setFont(fuenteNegrita);

            // ProgressBar
            prgcerradas.setBackground(Color.WHITE);
            prgcerradas.setForeground(rojoClaro);
            prgcerradas.setFont(fuenteNegrita);

            // Tabla
            tbsolicitudes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            tbsolicitudes.getTableHeader().setForeground(Color.WHITE);
            tbsolicitudes.getTableHeader().setBackground(new Color(90, 0, 20));
            tbsolicitudes.setFont(new Font("Arial", Font.PLAIN, 13));
            tbsolicitudes.setRowHeight(22);
            
            btnReload.setBackground(rojoClaro);
            btnReload.setForeground(Color.WHITE);
            btnReload.setFocusPainted(false);
    }
}

