package Vista;

import Negocio.AdminNeg;
import Negocio.LoginNeg;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Admin extends javax.swing.JFrame {
    private final AdminNeg adminNeg = new AdminNeg();
    private Map<String, Integer> managerMap;
    public Admin() {
        this(new LoginNeg.UsuarioLogin(0, "Desconocido", "ADMIN"));
    }
    public Admin(LoginNeg.UsuarioLogin user) {
        initComponents();
        setTitle("Panel del Administrador - " + user.nombre);
        setLocationRelativeTo(null);
        cargarDatos();
        iniciarReloj();
    }
    private void cargarDatos() {
    DefaultTableModel model = (DefaultTableModel) tbsolicitudes.getModel();
    model.setRowCount(0);
    for (String[] fila : adminNeg.listarSolicitudes()) {
        model.addRow(fila);
        }
        cbmanager.removeAllItems();
        managerMap = adminNeg.obtenerManagers();
        for (String nombre : managerMap.keySet()) {
            cbmanager.addItem(nombre);
        }

    }
    
    private void iniciarReloj() {
        new javax.swing.Timer(1000, e -> {
            String fechaHora = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                    .format(new java.util.Date());
            lbfecha.setText(fechaHora);
        }).start();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsolicitudes = new javax.swing.JTable();
        btnDelegar = new javax.swing.JButton();
        cbmanager = new javax.swing.JComboBox<>();
        btnVerImagen = new javax.swing.JButton();
        lbfecha = new javax.swing.JLabel();
        btnVerPrg = new javax.swing.JButton();
        btnCrearUsuario = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 3, 18)); // NOI18N
        jLabel1.setText("TODAS LAS SOLICITUDES");

        tbsolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id Reporte", "Titulo de Reporte", "Reportador Por", "Asignado a"
            }
        ));
        jScrollPane1.setViewportView(tbsolicitudes);
        if (tbsolicitudes.getColumnModel().getColumnCount() > 0) {
            tbsolicitudes.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        btnDelegar.setText("DELEGAR");
        btnDelegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelegarActionPerformed(evt);
            }
        });

        cbmanager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnVerImagen.setText("VER IMAGEN");
        btnVerImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerImagenActionPerformed(evt);
            }
        });

        lbfecha.setFont(new java.awt.Font("Liberation Sans", 3, 14)); // NOI18N

        btnVerPrg.setText("VER PROGRESOS");
        btnVerPrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPrgActionPerformed(evt);
            }
        });

        btnCrearUsuario.setText("CREAR USUARIO");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        btnCerrarSesion.setText("CERRAR SESIÓN");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbmanager, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnVerPrg, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                            .addComponent(btnVerImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnDelegar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnCrearUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(14, 14, 14))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCerrarSesion)
                                    .addComponent(lbfecha)))))
                    .addComponent(jLabel1))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbmanager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelegar)
                        .addGap(33, 33, 33)
                        .addComponent(btnVerImagen)
                        .addGap(30, 30, 30)
                        .addComponent(btnVerPrg)
                        .addGap(26, 26, 26)
                        .addComponent(btnCrearUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbfecha)
                        .addGap(104, 104, 104)
                        .addComponent(btnCerrarSesion))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDelegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelegarActionPerformed
        int fila = tbsolicitudes.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una solicitud.");
        return;
    }

    String idStr = tbsolicitudes.getValueAt(fila, 0).toString();
    String managerNombre = cbmanager.getSelectedItem().toString();

    int reportId = Integer.parseInt(idStr);
    int managerId = managerMap.get(managerNombre);

    if (adminNeg.delegarReporte(reportId, managerId)) {
        JOptionPane.showMessageDialog(this, "Delegado correctamente.");
        cargarDatos();
    } else {
        JOptionPane.showMessageDialog(this, "Error al delegar.");
    }
    }//GEN-LAST:event_btnDelegarActionPerformed

    private void btnVerImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerImagenActionPerformed
    int fila = tbsolicitudes.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una solicitud.");
        return;
    }

    int reportId = Integer.parseInt(tbsolicitudes.getValueAt(fila, 0).toString());
    byte[] imagenBytes = adminNeg.obtenerFotoReporte(reportId);

    if (imagenBytes != null) {
        ImageIcon originalIcon = new ImageIcon(imagenBytes);
        int anchoMax = 700;
        int altoMax = 600;

        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        float scale = Math.min((float) anchoMax / originalWidth, (float) altoMax / originalHeight);
        int newWidth = Math.round(originalWidth * scale);
        int newHeight = Math.round(originalHeight * scale);

        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel lbl = new JLabel(scaledIcon);
        JOptionPane.showMessageDialog(this, lbl, "Foto del Reporte", JOptionPane.PLAIN_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No hay imagen disponible.");
    }
    }//GEN-LAST:event_btnVerImagenActionPerformed

    private void btnVerPrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPrgActionPerformed
List<String[]> progresos = adminNeg.obtenerProgresoManagers();
    if (progresos.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No hay managers con reportes asignados.");
        return;
    }

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    for (String[] fila : progresos) {
        String nombre = fila[0];
        int porcentaje = Integer.parseInt(fila[1]);

        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(porcentaje);
        barra.setStringPainted(true);
        barra.setString(porcentaje + "%");

        JLabel label = new JLabel(nombre);
        JPanel filaPanel = new JPanel();
        filaPanel.setLayout(new BoxLayout(filaPanel, BoxLayout.Y_AXIS));
        filaPanel.add(label);
        filaPanel.add(barra);

        panel.add(filaPanel);
        panel.add(Box.createVerticalStrut(10));
    }

    JOptionPane.showMessageDialog(this, panel, "Progreso por Encargado", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_btnVerPrgActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
 JTextField txtNombre = new JTextField();
    JTextField txtEmail = new JTextField();
    JPasswordField txtPassword = new JPasswordField();
    JComboBox<String> cbRol = new JComboBox<>(new String[]{"ADMIN", "MANAGER", "CITIZEN"});

    JPanel panel = new JPanel(new java.awt.GridLayout(0, 1));
    panel.add(new JLabel("Nombre completo:"));
    panel.add(txtNombre);
    panel.add(new JLabel("Email:"));
    panel.add(txtEmail);
    panel.add(new JLabel("Contraseña:"));
    panel.add(txtPassword);
    panel.add(new JLabel("Rol:"));
    panel.add(cbRol);

    int result = JOptionPane.showConfirmDialog(this, panel, "Registrar nuevo usuario",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String rol = (String) cbRol.getSelectedItem();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        boolean creado = adminNeg.crearUsuario(nombre, email, password, rol);
        if (creado) {
            JOptionPane.showMessageDialog(this, "Usuario creado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el usuario.");
        }
    }
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose(); 
    // Abrir la ventana de login u otra ventana deseada
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed
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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnDelegar;
    private javax.swing.JButton btnVerImagen;
    private javax.swing.JButton btnVerPrg;
    private javax.swing.JComboBox<String> cbmanager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbfecha;
    private javax.swing.JTable tbsolicitudes;
    // End of variables declaration//GEN-END:variables
}
