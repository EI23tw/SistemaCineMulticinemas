package vista;
public class VentanaPrincipal extends javax.swing.JFrame {
    private controlador.ControladorCine controlador;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());
    
    public VentanaPrincipal() {
        initComponents(); // 1. Primero dejamos que NetBeans dibuje toda la ventana y la tabla
        this.pack(); // Esto ajusta la ventana al tamaño mínimo necesario según tus elementos
        this.setLocationRelativeTo(null); // Esto centra la ventana en la pantalla al abrirla
        controlador = new controlador.ControladorCine(); // 2. Encendemos el cerebro
        cargarCartelera(); // 3. Ahora sí llenamos la tabla porque ya existe
        cargarPeliculasCombo();
    }
    private void cargarCartelera() {
        // 1. Obtenemos el modelo de datos de la tabla que diseñaste
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaPeliculas.getModel();
        modelo.setRowCount(0); // Limpiamos las filas vacías por defecto

        // 2. Le pedimos al controlador nuestra lista de películas (ordenadas por el Árbol)
        java.util.List<modelo.Pelicula> lista = controlador.obtenerCartelera();

        // 3. Recorremos la lista e insertamos fila por fila en la tabla
        for (modelo.Pelicula p : lista) {
            modelo.addRow(new Object[]{
                p.getTitulo(),
                p.getGenero(),
                p.getHorario(),
                p.getNumeroSala()
            });
        }
    }
    private void cargarPeliculasCombo() {
        // 1. Limpiamos las opciones por defecto ("Item 1", "Item 2", etc.)
        comboPeliculas.removeAllItems(); 
        
        // 2. Le pedimos al controlador nuestra lista de películas
        java.util.List<modelo.Pelicula> lista = controlador.obtenerCartelera();
        
        // 3. Recorremos la lista y agregamos el título de cada película al menú
        for (modelo.Pelicula p : lista) {
            comboPeliculas.addItem(p.getTitulo()); 
        }
    }
    private void dibujarAsientos() {
        // 1. Limpiamos el panel por si ya tenía asientos de otra película
        panelAsientos.removeAll();

        // 2. Obtenemos qué película seleccionó el usuario en el ComboBox
        int index = comboPeliculas.getSelectedIndex();
        if (index == -1) return; // Si no hay selección, no hacemos nada

        // Traemos la película desde nuestro controlador
        java.util.List<modelo.Pelicula> lista = controlador.obtenerCartelera();
        modelo.Pelicula peliSeleccionada = lista.get(index);

        // 3. Le pedimos al controlador la Matriz de Asientos exacta para esa sala
        estructuras.MatrizAsientos matrizSala = controlador.getSala(peliSeleccionada.getNumeroSala());

        // 4. Le decimos al panel que su cuadrícula debe ser del tamaño exacto de la matriz
        panelAsientos.setLayout(new java.awt.GridLayout(matrizSala.getFilas(), matrizSala.getColumnas(), 5, 5));

        // 5. Recorremos la matriz fila por fila y columna por columna
        for (int i = 0; i < matrizSala.getFilas(); i++) {
            for (int j = 0; j < matrizSala.getColumnas(); j++) {
                
                // Extraemos el asiento lógico de la memoria
                modelo.Asiento asientoFisico = matrizSala.getAsiento(i, j);
                
                // Creamos un botón visual con el nombre del asiento (Ej. A1)
                javax.swing.JButton botonAsiento = new javax.swing.JButton(asientoFisico.getNombreAsiento());

                // 6. Pintamos el botón según su estado (Verde = Libre, Rojo = Ocupado)
                if (asientoFisico.isOcupado()) {
                    botonAsiento.setBackground(java.awt.Color.RED);
                    botonAsiento.setForeground(java.awt.Color.WHITE);
                } else {
                    botonAsiento.setBackground(java.awt.Color.GREEN);
                }

                // 7. Le damos funcionalidad al botón al hacerle clic
                botonAsiento.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (asientoFisico.isOcupado()) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Este asiento ya está ocupado.");
                        } else {
                            // Si está libre, pedimos el nombre y mandamos a la Cola
                            String cliente = javax.swing.JOptionPane.showInputDialog("Ingrese nombre del cliente para reservar:");
                            
                            if (cliente != null && !cliente.trim().isEmpty()) {
                                
                                // === AQUÍ INICIA LA NUEVA VALIDACIÓN REGEX ===
                                if (!cliente.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                                    // Si tiene números o símbolos raros, mostramos error y cancelamos
                                    javax.swing.JOptionPane.showMessageDialog(null, 
                                        "Error: El nombre solo debe contener letras y espacios.", 
                                        "Dato Inválido", 
                                        javax.swing.JOptionPane.ERROR_MESSAGE);
                                    return; // Este return detiene el proceso, el asiento NO se reserva
                                }
                                // === AQUÍ TERMINA LA VALIDACIÓN ===

                                asientoFisico.setOcupado(true); // Cambiamos el estado en la matriz
                                // Registramos la reserva en la Cola (precio fijo de $5 para el ejemplo)
                                controlador.registrarReserva(cliente, peliSeleccionada, asientoFisico.getNombreAsiento(), 5.00);
                                javax.swing.JOptionPane.showMessageDialog(null, "¡Ticket enviado a la cola de confirmación!");
                                dibujarAsientos(); // Redibujamos la sala para que el botón se ponga rojo
                            }
                        }
                    }
                });

                // Agregamos el botón terminado a nuestro panel visual
                panelAsientos.add(botonAsiento);
            }
        }

        // 8. Refrescamos el panel para que los botones aparezcan en pantalla
        panelAsientos.revalidate();
        panelAsientos.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPeliculas = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        comboPeliculas = new javax.swing.JComboBox<>();
        panelAsientos = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRecibo = new javax.swing.JTextArea();
        btnAtender = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        comboSalasReporte = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtReporte = new javax.swing.JTextArea();
        btnGenerarReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 153));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setToolTipText("");
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        tablaPeliculas.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        tablaPeliculas.setForeground(new java.awt.Color(0, 0, 204));
        tablaPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título", "Género", "Horario", "Sala"
            }
        ));
        jScrollPane1.setViewportView(tablaPeliculas);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(0, 0, 204));
        btnBuscar.setText("Buscar...");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Cartelera", jPanel1);

        comboPeliculas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboPeliculas.setForeground(new java.awt.Color(0, 153, 51));
        comboPeliculas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboPeliculas.addActionListener(this::comboPeliculasActionPerformed);

        panelAsientos.setLayout(new java.awt.GridLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAsientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(comboPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 677, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(comboPeliculas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 381, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Comprar Entradas", jPanel2);

        txtRecibo.setColumns(20);
        txtRecibo.setRows(5);
        jScrollPane2.setViewportView(txtRecibo);

        btnAtender.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAtender.setForeground(new java.awt.Color(0, 0, 204));
        btnAtender.setText("Atender Siguiente Reserva");
        btnAtender.addActionListener(this::btnAtenderActionPerformed);

        btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(255, 0, 0));
        btnImprimir.setText("Imprimir Recibo");
        btnImprimir.addActionListener(this::btnImprimirActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 449, Short.MAX_VALUE)
                        .addComponent(btnImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtender)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir)
                    .addComponent(btnAtender))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Confirmar / Cola", jPanel3);

        comboSalasReporte.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboSalasReporte.setForeground(new java.awt.Color(0, 153, 51));
        comboSalasReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sala 1", "Sala 2", "Sala 3" }));

        txtReporte.setColumns(20);
        txtReporte.setRows(5);
        jScrollPane3.setViewportView(txtReporte);

        btnGenerarReporte.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGenerarReporte.setForeground(new java.awt.Color(0, 0, 255));
        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.addActionListener(this::btnGenerarReporteActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(comboSalasReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGenerarReporte))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboSalasReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerarReporte)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Reportes", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPeliculasActionPerformed
                dibujarAsientos(); // Cada vez que cambien de película, redibujamos la sala
    }//GEN-LAST:event_comboPeliculasActionPerformed

    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        // 1. Le pedimos al controlador que desencole el siguiente ticket de la estructura
        modelo.Reserva ticketAtendido = controlador.procesarSiguienteCompra();
        
        // 2. Verificamos si efectivamente había alguien esperando en la cola
        if (ticketAtendido != null) {
            // Imprimimos el ticket formateado en nuestra área de texto
            txtRecibo.setText("======================================\n");
            txtRecibo.append("         CINE MULTICINEMAS\n");
            txtRecibo.append("======================================\n");
            txtRecibo.append("TICKET #" + ticketAtendido.getIdReserva() + "\n");
            txtRecibo.append("CLIENTE: " + ticketAtendido.getNombreCliente() + "\n");
            txtRecibo.append("PELÍCULA: " + ticketAtendido.getPelicula().getTitulo() + "\n");
            txtRecibo.append("SALA: " + ticketAtendido.getPelicula().getNumeroSala() + "\n");
            txtRecibo.append("ASIENTO: " + ticketAtendido.getAsientosReservados() + "\n");
            txtRecibo.append("TOTAL PAGADO: $" + ticketAtendido.getTotalPagar() + "0\n");
            txtRecibo.append("======================================\n");
            txtRecibo.append("¡Gracias por su compra!\n");
        } else {
            // Si la estructura cola está vacía, mostramos un mensaje
            txtRecibo.setText("No hay reservas pendientes en la cola.");
            javax.swing.JOptionPane.showMessageDialog(null, "La cola de espera está vacía.");
        }
    }//GEN-LAST:event_btnAtenderActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // 1. Verificamos qué sala seleccionó el usuario en el menú desplegable
        int index = comboSalasReporte.getSelectedIndex();
        if (index == -1) return;

        // Sumamos 1 porque el índice de Java empieza en 0, pero nuestras salas son 1, 2 y 3
        int numeroSala = index + 1;

        // 2. Le pedimos al controlador que recorra la Matriz y haga los cálculos
        String estadisticas = controlador.generarReporteOcupacion(numeroSala);

        // 3. Imprimimos el resultado limpio en nuestra área de texto
        txtReporte.setText(estadisticas);
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            // Guardamos el texto actual del recibo en una variable
            String textoRecibo = txtRecibo.getText();
            
            // Verificamos que no esté vacío Y que no sea el mensaje de error de la cola
            if (textoRecibo.isEmpty() || textoRecibo.equals("No hay reservas pendientes en la cola.")) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "No hay ningún recibo válido para imprimir.", 
                    "Impresión Bloqueada", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return; // Esto aborta la ejecución y evita que se abra la ventana de PDF
            }
            
            // Si pasa la validación, abre la ventana de impresión nativa de Windows/Mac
            boolean impreso = txtRecibo.print();
            
            if (impreso) {
                javax.swing.JOptionPane.showMessageDialog(this, "¡Recibo impreso/exportado con éxito!");
            }
        } catch (java.awt.print.PrinterException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al imprimir: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // 1. Obtenemos lo que el usuario escribió
        String criterio = txtBuscar.getText().trim();
        
        // 2. Preparamos la tabla para actualizarla
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaPeliculas.getModel();
        modelo.setRowCount(0); // Limpiamos la tabla actual
        
        // 3. Decidimos qué lista mostrar
        java.util.List<modelo.Pelicula> listaMostrar;
        
        if (criterio.isEmpty()) {
            // Si el buscador está vacío, mostramos todo el catálogo
            listaMostrar = controlador.obtenerCartelera(); 
        } else {
            // AQUÍ ESTÁ EL CAMBIO: Llamamos al nuevo Buscador Universal
            listaMostrar = controlador.buscarPeliculas(criterio);
        }
        
        // 4. Llenamos la tabla con los resultados
        for (modelo.Pelicula p : listaMostrar) {
            modelo.addRow(new Object[]{
                p.getTitulo(),
                p.getGenero(),
                p.getHorario(),
                p.getNumeroSala()
            });
        }
        
        // 5. Un pequeño aviso si no hay resultados
        if (listaMostrar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron películas para: " + criterio);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JComboBox<String> comboPeliculas;
    private javax.swing.JComboBox<String> comboSalasReporte;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel panelAsientos;
    private javax.swing.JTable tablaPeliculas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtRecibo;
    private javax.swing.JTextArea txtReporte;
    // End of variables declaration//GEN-END:variables
}
