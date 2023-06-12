package interfaces.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import clases.*;
import interfaces.CambiarDatosUsuario;
import interfaces.Inicio;
import sistema.Validaciones;

public class InicioAdmin {
    private JPanel inicioAdmin;
    private JTabbedPane menuAdmin;
    private JPanel administrarUsuarios;
    private JPanel verMensajes;
    private JPanel gestionarReportes;
    private JComboBox cboFiltroUsuarios;
    private JPanel filtroTipoCuenta;
    private JCheckBox ckbFiltroRepartidor;
    private JCheckBox ckbFiltroUsuario;
    private JCheckBox ckbFiltroEstibaje;
    private JCheckBox ckbFiltroConductor;
    private JCheckBox ckbFiltroEjecutivo;
    private JTextField txtFiltroCedula;
    private JPanel filtroCedula;
    private JTextField textField1;
    private JList listaUsuarios;
    private JButton btnBorrarFiltros;
    private JPanel filtroNombre;
    private JPanel agregarUsuario;
    private JTextField txfAgregarNombre;
    private JTextField txfAgregarCedula;
    private JTextField txfAgregarTelefono;
    private JComboBox cboAgregarTipoCuenta;
    private JPasswordField pswAgregarContrasenia;
    private JPasswordField pswAgregarContraseniaV;
    private JButton btnAgregarUsuario;
    private JButton btnAplicarFiltro;
    private JButton eliminarUsuarioButton;
    private JButton modificarUsuarioButton;
    private JPanel filtroCombo;
    private JList listMensajes;
    private JComboBox cboMensajeEmisor;
    private JTextField txfMensajeCedula;
    private JButton btnMensajeBuscar;
    private JLabel lbMensajesDe;
    private JButton btnMensajesTodos;
    private JButton btnMensajesTexto;
    private JTextField txfMensajesTexto;
    private JComboBox cboReportesFiltro;
    private JPanel reportesServicio;
    private JComboBox cboReportesServicioQuien;
    private JButton btnReportesServicioCedula;
    private JTextField txfReporteServicoCedula;
    private JList listReportes;
    private JTextField txfReporteVialCedula;
    private JPanel reportesVial;
    private JButton btnReporteVialFiltrar;

    public InicioAdmin() {

        /** Poner las opciones del combo box de tipo según las opciones existentes de
         *  TipoCuenta */
        for (TipoCuenta tipo : TipoCuenta.values()) {
            if (!tipo.equals(TipoCuenta.ADMINISTRADOR))
                cboAgregarTipoCuenta.addItem(tipo.name());
        }

        /** Inicializar los valores de la lista */
        borrarFiltrosBusqueda();
        actualizarLista(Inicio.sistema.getUsuarios());

        /** Borrar filtros aplicados */
        btnBorrarFiltros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarFiltrosBusqueda();

                /**
                 * Actualizar la lista
                 */
                actualizarLista(Inicio.sistema.getUsuarios());
            }
        });

        /** Actualizar vista según selección del tipo de filtro */
        cboFiltroUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = cboFiltroUsuarios.getSelectedIndex();
                boolean fCeudla = false,
                        fTipo = false,
                        fNombre = false;
                switch (opcion) {
                    /**
                     * Filtro con tipo cuenta
                     */
                    case 0:
                        fTipo = true;
                        break;
                    /**
                     * Filtro con cédula
                     */
                    case 1:
                        fCeudla = true;
                        break;
                    /**
                     * Filtro con Nombre
                     */
                    case 2:
                        fNombre = true;
                        break;
                    /**
                     * Selección inválida
                     */
                    default:
                        JOptionPane.showMessageDialog(null, "Item seleccionado inválido, intente de nuevo");
                }

                filtroCedula.setVisible(fCeudla);
                filtroCedula.setEnabled(fCeudla);
                filtroTipoCuenta.setVisible(fTipo);
                filtroTipoCuenta.setEnabled(fTipo);
                filtroNombre.setVisible(fNombre);
                filtroNombre.setEnabled(fNombre);
                btnBorrarFiltros.setEnabled(true);
                btnAplicarFiltro.setEnabled(true);
            }
        });

        /** Agregar usuario */
        btnAgregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /** Que el tipo seleccionado sea válido */
                if (cboAgregarTipoCuenta.getSelectedIndex() > -1) {
                    String cedula = txfAgregarCedula.getText(),
                            nombre = txfAgregarNombre.getText(),
                            telefono = txfAgregarTelefono.getText(),
                            contrasenia = pswAgregarContrasenia.getText(),
                            contraseniaV = pswAgregarContraseniaV.getText();
                    /** Verifica que ningún campo esté vacio */
                    if (cedula.equals("") || nombre.equals("") || telefono.equals("")
                            || contrasenia.equals("") || contraseniaV.equals("")) {
                        JOptionPane.showMessageDialog(null, "Llene todos los campos correctamente.");
                    } else {
                        try {
                            /** Validación cédula */
                            boolean validacion = Validaciones.cedulaValida(cedula);
                            if (validacion) {
                                /** !!! La validación de si la cédula ya existe se puede poner antes pero ya tengo sueño XD */
                                Validaciones.validarTelefono(telefono);
                                if (contrasenia.equals(contraseniaV)) {
                                    Persona p = new Persona(nombre, cedula, telefono, contrasenia, TipoCuenta.valueOf(cboAgregarTipoCuenta.getSelectedItem().toString()));
                                    if (Inicio.sistema.agregarUsuario(p)) {
                                        JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
                                        actualizarLista(Inicio.sistema.getUsuarios());
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Un usuario con esa cédula ya existe");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La cédula ingresada es inválida.");
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un tipo para el usuario");
                }
            }
        });

        /** Aplicar filtro de busqueda */
        btnAplicarFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = cboFiltroUsuarios.getSelectedIndex();

                switch (opcion) {
                    /**
                     * Filtro con tipo cuenta
                     */
                    case 0:
                        /**
                         * Creación de la lista para el parámetro del filtro de búsqueda
                         */
                        boolean usuario = ckbFiltroUsuario.isSelected(),
                                estibaje = ckbFiltroEstibaje.isSelected(),
                                ejecutivo = ckbFiltroEjecutivo.isSelected(),
                                conductor = ckbFiltroConductor.isSelected(),
                                repartidor = ckbFiltroRepartidor.isSelected();
                        if (usuario || estibaje || ejecutivo || conductor || repartidor) {
                            ArrayList<TipoCuenta> filtro = new ArrayList<>();
                            /**
                             * Se añaden según la selección
                             */
                            if (usuario) {
                                filtro.add(TipoCuenta.USUARIO);
                            }
                            if (estibaje) {
                                filtro.add(TipoCuenta.ESTIBAJE);
                            }
                            if (ejecutivo) {
                                filtro.add(TipoCuenta.EJECUTIVO);
                            }
                            if (repartidor) {
                                filtro.add(TipoCuenta.REPARTIDOR);
                            }
                            if (conductor) {
                                filtro.add(TipoCuenta.CONDUCTOR);
                            }

                            actualizarLista(Inicio.sistema.buscarUsuarioTipo(filtro));
                        } else {
                            JOptionPane.showMessageDialog(null, "Seleccione por lo menos una opción");
                        }

                        break;
                    /**
                     * Filtro con cédula
                     */
                    case 1:
                        String cedula = txtFiltroCedula.getText();
                        try {
                            boolean valida = Validaciones.cedulaValida(cedula);
                            if (valida) {
                                Persona buscado = Inicio.sistema.buscarUsuarioCedula(cedula);
                                if (buscado != null) {
                                    SortedSet<Persona> model = new TreeSet<>();
                                    model.add(buscado);
                                    actualizarLista(model);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un usuario con la cédula ingresada");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La cédula ingresada no es válida");
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }

                        break;
                    /**
                     * Filtro con Nombre
                     */
                    case 2:
                        String nombre = txtFiltroCedula.getText();
                        SortedSet<Persona> busqueda = Inicio.sistema.buscarUsuarioNombre(nombre);
                        if (busqueda.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Ningún usuario cumple con la condición");
                        } else {
                            actualizarLista(busqueda);
                        }
                        break;
                    /**
                     * Selección inválida
                     */
                    default:
                        JOptionPane.showMessageDialog(null, "Item seleccionado inválido, intente de nuevo");
                }
            }
        });

        /** Eliminar el usuario seleccionado
         * !!! Falta comentar*/
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Persona p = (Persona) listaUsuarios.getSelectedValue();
                if (p != null) {
                    int opcion = JOptionPane.showConfirmDialog(null, "Está seguro de que desea eliminar el usuario: \n" +
                            "\tCedula: " + p.getCedula() + "\n" +
                            "\tNombre: " + p.getNombre(), "Confirmación eliminación", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        Inicio.sistema.eliminarUsuario(p);
                        JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.");
                        actualizarLista(Inicio.sistema.getUsuarios());
                    } else {
                        JOptionPane.showMessageDialog(null, "Se canceló la eliminación del usuario");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ningún usuario seleccionado");
                }
            }
        });

        /** !!! Modificar el usuario seleccionado */
        modificarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /** Redirige a una nueva ventana de modificación, pero se le pasa un parámetro
                 *  para que tenga idea de qué elemento modificar*/

                Persona p = (Persona) listaUsuarios.getSelectedValue();
                if (p != null) {
                    JFrame este = (JFrame) SwingUtilities.getWindowAncestor(inicioAdmin);
                    este.setContentPane(new CambiarDatosUsuario(true, p).getPanel());
                    este.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ningún elemento para modificar.");
                }
            }
        });
        btnMensajeBuscar.addActionListener(new ActionListener() {
            /**
             * Filtrar y visualizar mensajes por usuario emisor o receptor
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = cboMensajeEmisor.getSelectedIndex();
                String cedula = txfMensajeCedula.getText();

                try {
                    if (Validaciones.cedulaValida(cedula)) {
                        SortedSet<Mensaje> mostrar = new TreeSet<>();
                        if (opcion == 0) {
                            lbMensajesDe.setText("Mensajes de " + cedula);
                            mostrar = Inicio.sistema.buscarMensajesEmisor(cedula);
                        } else {
                            lbMensajesDe.setText("Mensajes para " + cedula);
                            mostrar = Inicio.sistema.buscarMensajesEmisor(cedula);
                        }
                        DefaultListModel<Mensaje> mensajes = new DefaultListModel<>();
                        for (Mensaje m : mostrar) {
                            mensajes.addElement(m);
                        }
                        listMensajes.setModel(mensajes);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cédula ingresada inválida");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        btnMensajesTodos.addActionListener(new ActionListener() {
            /**
             * Mostrar todos los mensajes
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<Mensaje> mensajes = new DefaultListModel<>();
                for (Mensaje m : Inicio.sistema.getMensajes()) {
                    mensajes.addElement(m);
                }
                listMensajes.setModel(mensajes);
            }
        });
        btnMensajesTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<Mensaje> mensajes = new DefaultListModel<>();
                String buscado = txfMensajesTexto.getText();
                for (Mensaje m : Inicio.sistema.buscarMensajesContenido(buscado)) {
                    mensajes.addElement(m);
                }
                listMensajes.setModel(mensajes);
            }
        });
        cboReportesFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean vial = (cboReportesFiltro.getSelectedIndex() == 0);
                boolean servicio = !vial;

                reportesServicio.setEnabled(servicio);
                reportesServicio.setVisible(servicio);
                reportesVial.setEnabled(vial);
                reportesVial.setVisible(vial);
            }
        });
        btnReporteVialFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txfReporteVialCedula.getText();
                try {
                    if (Validaciones.cedulaValida(cedula)){
                        DefaultListModel<ReporteVia> reportes = new DefaultListModel<>();
                        for (ReporteVia r: Inicio.sistema.buscarReporteVia(cedula)){
                            reportes.addElement(r);
                        }
                        listReportes.setModel(reportes);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cédula ingresad incorecta");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        btnReportesServicioCedula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txfReporteServicoCedula.getText();
                try{
                    if (Validaciones.cedulaValida(cedula)){
                        if (Inicio.sistema.buscarUsuarioCedula(cedula) != null){
                            boolean option = cboReportesServicioQuien.getSelectedIndex() == 0;
                            ArrayList<ReporteServicios> reportes;
                            if (option){
                                reportes = Inicio.sistema.buscarReporteServicioEmisor(cedula);
                            } else {
                                reportes = Inicio.sistema.buscarReporteServicioReceptor(cedula);
                            }
                            DefaultListModel<ReporteServicios> reporteS = new DefaultListModel<>();
                            for (ReporteServicios r: reportes){
                                reporteS.addElement(r);
                            }
                            listReportes.setModel(reporteS);
                        } else {
                            JOptionPane.showMessageDialog(null, "El usuario ingresado no existe");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cédula ingresada inválida");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    /**
     * Método para actualizar la lista
     *
     * @param lista lista con los
     */
    private void actualizarLista(SortedSet<Persona> lista) {
        DefaultListModel<Persona> modeloLista = new DefaultListModel<>();
        modeloLista.addAll(0, lista);
        listaUsuarios.setModel(modeloLista);
    }

    /**
     * Reiniciar los parámetros por defecto de las opciones de busqueda
     */
    private void borrarFiltrosBusqueda() {
        cboFiltroUsuarios.setSelectedIndex(-1);
        filtroCedula.setVisible(false);
        filtroCedula.setEnabled(false);
        filtroTipoCuenta.setVisible(false);
        filtroTipoCuenta.setEnabled(false);
        filtroNombre.setVisible(false);
        filtroNombre.setEnabled(false);
        btnBorrarFiltros.setEnabled(false);
        btnAplicarFiltro.setEnabled(false);
    }

    public JPanel getPanel() {
        return inicioAdmin;
    }
}
