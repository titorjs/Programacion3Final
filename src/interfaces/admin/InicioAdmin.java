package interfaces.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import clases.Persona;
import clases.TipoCuenta;
import interfaces.Inicio;
import sistema.Validaciones;

public class InicioAdmin {
    private JPanel inicioAdmin;
    private JTabbedPane menuAdmin;
    private JPanel administrarUsuarios;
    private JPanel gestionarReportes;
    private JPanel verMensajes;
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
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton btnAgregarUsuario;
    private JButton btnAplicarFiltro;
    private JButton eliminarUsuarioButton;
    private JButton modificarUsuarioButton;

    public InicioAdmin() {

        /** Poner las opciones del combo box de tipo según las opciones existentes de
         *  TipoCuenta */
        for (TipoCuenta tipo: TipoCuenta.values()){
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
                switch (opcion){
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
                if (cboAgregarTipoCuenta.getSelectedIndex() > -1){

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

                switch (opcion){
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
                        if (usuario || estibaje || ejecutivo || conductor || repartidor){
                            ArrayList<TipoCuenta> filtro = new ArrayList<>();
                            /**
                             * Se añaden según la selección
                             */
                            if (usuario){filtro.add(TipoCuenta.USUARIO);}
                            if (estibaje){filtro.add(TipoCuenta.ESTIBAJE);}
                            if (ejecutivo){filtro.add(TipoCuenta.EJECUTIVO);}
                            if (repartidor){filtro.add(TipoCuenta.REPARTIDOR);}
                            if (conductor){filtro.add(TipoCuenta.CONDUCTOR);}

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
                        try{
                            boolean valida = Validaciones.cedulaValida(cedula);
                            if(valida){
                                Persona buscado = Inicio.sistema.buscarUsuarioCedula(cedula);
                                if (buscado != null){
                                    SortedSet<Persona> model = new TreeSet<>();
                                    model.add(buscado);
                                    actualizarLista(model);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe un usuario con la cédula ingresada");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La cédula ingresada no es válida");
                            }
                        } catch (Exception ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }

                        break;
                    /**
                     * Filtro con Nombre
                     */
                    case 2:
                        String nombre = txtFiltroCedula.getText();
                        SortedSet<Persona> busqueda = Inicio.sistema.buscarUsuarioNombre(nombre);
                        if (busqueda.isEmpty()){
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

        /** Eliminar el usuario seleccionado */
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /** Modificar el usuario seleccionado */
        modificarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * Método para actualizar la lista
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
    private void borrarFiltrosBusqueda(){
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
