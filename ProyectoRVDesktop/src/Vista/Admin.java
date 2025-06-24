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
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

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
        btdelegar = new javax.swing.JButton();
        cbmanager = new javax.swing.JComboBox<>();
        btver = new javax.swing.JButton();
        lbfecha = new javax.swing.JLabel();
        btverprg = new javax.swing.JButton();
        btverprg1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

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
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbsolicitudes);

        btdelegar.setText("DELEGAR");
        btdelegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdelegarActionPerformed(evt);
            }
        });

        cbmanager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btver.setText("VER IMAGEN");
        btver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btverActionPerformed(evt);
            }
        });

        lbfecha.setFont(new java.awt.Font("Liberation Sans", 3, 14)); // NOI18N

        btverprg.setText("VER PROGRESOS");
        btverprg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btverprgActionPerformed(evt);
            }
        });

        btverprg1.setText("CREAR USER");
        btverprg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btverprg1ActionPerformed(evt);
            }
        });

        jButton1.setText("EXPORTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(cbmanager, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(lbfecha))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btverprg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btdelegar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btverprg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(14, 14, 14)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbmanager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btdelegar)
                        .addGap(18, 18, 18)
                        .addComponent(btver)
                        .addGap(18, 18, 18)
                        .addComponent(btverprg)
                        .addGap(18, 18, 18)
                        .addComponent(btverprg1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbfecha)
                        .addGap(133, 133, 133))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btdelegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdelegarActionPerformed
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
    }//GEN-LAST:event_btdelegarActionPerformed

    private void btverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btverActionPerformed
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
    }//GEN-LAST:event_btverActionPerformed

    private void btverprgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btverprgActionPerformed
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
    }//GEN-LAST:event_btverprgActionPerformed

    private void btverprg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btverprg1ActionPerformed
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
    }//GEN-LAST:event_btverprg1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar reporte PDF");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos PDF", "pdf"));
    fileChooser.setSelectedFile(new File("reporte_solicitudes.pdf"));
    
    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }
    
    final File finalFileToSave = fileChooser.getSelectedFile().getName().toLowerCase().endsWith(".pdf") 
        ? fileChooser.getSelectedFile() 
        : new File(fileChooser.getSelectedFile().getAbsolutePath() + ".pdf");
    
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(new JLabel("Exportando solicitudes a PDF..."));
    panel.add(new JLabel("Click en 'OK' para continuar"));
    panel.add(Box.createVerticalStrut(10));
    
    JOptionPane.showMessageDialog(this, panel, "Exportando", JOptionPane.INFORMATION_MESSAGE);

    final Admin adminFrame = this;
    
    new Thread(() -> {
        try {
            exportarSolicitudesAPDF(finalFileToSave);
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(adminFrame, 
                    "Exportación completada con éxito!", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            });
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(adminFrame, 
                    "Error al exportar: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            });
        }
    }).start();
    }//GEN-LAST:event_jButton1ActionPerformed
private void exportarSolicitudesAPDF(File file) throws Exception {
    List<Map<String, Object>> solicitudes = adminNeg.obtenerTodasSolicitudesConDetalles();
    
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(file));
    document.open();
    
    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

    Paragraph title = new Paragraph("Reporte de Solicitudes", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    title.setSpacingAfter(20);
    document.add(title);
    
    Paragraph fecha = new Paragraph("Generado el: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), normalFont);
    fecha.setAlignment(Element.ALIGN_RIGHT);
    fecha.setSpacingAfter(20);
    document.add(fecha);
    
    int total = solicitudes.size();
    int current = 0;
    
    for (Map<String, Object> solicitud : solicitudes) {
        current++;
        
        Paragraph solicitudTitle = new Paragraph("Solicitud #" + solicitud.get("id") + ": " + solicitud.get("titulo"), subtitleFont);
        solicitudTitle.setSpacingAfter(10);
        document.add(solicitudTitle);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);
        
        addTableCell(table, "ID:", solicitud.get("id").toString(), normalFont);
        addTableCell(table, "Título:", solicitud.get("titulo").toString(), normalFont);
        addTableCell(table, "Descripción:", solicitud.get("descripcion").toString(), normalFont);
        addTableCell(table, "Fecha creación:", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(solicitud.get("fecha_creacion")), normalFont);
        addTableCell(table, "Ciudadano:", solicitud.get("ciudadano").toString(), normalFont);
        addTableCell(table, "Email ciudadano:", solicitud.get("email_ciudadano").toString(), normalFont);
        addTableCell(table, "Encargado:", solicitud.get("encargado").toString(), normalFont);
        addTableCell(table, "Email encargado:", solicitud.get("email_encargado").toString(), normalFont);
        addTableCell(table, "Estado:", solicitud.get("estado").toString(), normalFont);
        
        document.add(table);

        @SuppressWarnings("unchecked")
        List<byte[]> fotos = (List<byte[]>) solicitud.get("fotos");
        if (fotos != null && !fotos.isEmpty()) {
            Paragraph imagenesTitle = new Paragraph("Imágenes adjuntas:", subtitleFont);
            imagenesTitle.setSpacingBefore(15);
            imagenesTitle.setSpacingAfter(10);
            document.add(imagenesTitle);
            
            for (int i = 0; i < fotos.size(); i++) {
                byte[] fotoBytes = fotos.get(i);
                Image image = Image.getInstance(fotoBytes);
                float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                if (image.getWidth() > documentWidth) {
                    image.scaleToFit(documentWidth, document.getPageSize().getHeight());
                }
                
                document.add(image);
                document.add(new Paragraph(" "));
            }
        }
        
        if (current < total) {
            Paragraph separator = new Paragraph("------------------------------------------------------------------");
            separator.setSpacingBefore(20);
            separator.setSpacingAfter(20);
            document.add(separator);
        }
    }
    
    document.close();
}

private void addTableCell(PdfPTable table, String label, String value, Font font) {
    PdfPCell cellLabel = new PdfPCell(new Phrase(label, font));
    cellLabel.setBorder(Rectangle.NO_BORDER);
    cellLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
    
    PdfPCell cellValue = new PdfPCell(new Phrase(value, font));
    cellValue.setBorder(Rectangle.NO_BORDER);
    cellValue.setHorizontalAlignment(Element.ALIGN_LEFT);
    
    table.addCell(cellLabel);
    table.addCell(cellValue);
}
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
    private javax.swing.JButton btdelegar;
    private javax.swing.JButton btver;
    private javax.swing.JButton btverprg;
    private javax.swing.JButton btverprg1;
    private javax.swing.JComboBox<String> cbmanager;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbfecha;
    private javax.swing.JTable tbsolicitudes;
    // End of variables declaration//GEN-END:variables
}
