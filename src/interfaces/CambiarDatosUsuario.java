package interfaces;

import clases.Persona;
import clases.TipoCuenta;
import interfaces.admin.InicioAdmin;
import sistema.Validaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarDatosUsuario {
    private JPanel cambiarDatosUsuario;
    private JTextField txfNombre;
    private JTextField txfCedula;
    private JTextField txfTelefono;
    private JPanel panTipoCuenta;
    private JComboBox cboTipoCuenta;
    private JPasswordField pswContrasenia;
    private JPasswordField pswContraseniaV;
    private JButton btnModificarInfo;
    private JButton btnCancelar;

    public CambiarDatosUsuario(boolean esAdmin, Persona persona) {

        /** Incializar combo box menos admin */

        for(TipoCuenta tipo: TipoCuenta.values()){
            if (!tipo.equals(TipoCuenta.ADMINISTRADOR))
                cboTipoCuenta.addItem(tipo.name());
        }

        /** El admin puede editar el nombre y tipo, el interfaces.usuario no */
        txfNombre.setEditable(esAdmin);
        cboTipoCuenta.setEnabled(esAdmin);

        /** Inicializar los valores */
        txfNombre.setText(persona.getNombre());
        txfCedula.setText(persona.getCedula());
        txfTelefono.setText(persona.getTelefono());
        cboTipoCuenta.setSelectedItem(persona.getTipo());


        /** Modificar la información de la persona
         * !!! falta comentar*/
        btnModificarInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String telefono = txfTelefono.getText();
                try{
                    Validaciones.validarTelefono(telefono);
                    String nombre = txfNombre.getText();
                    String contrasenia = pswContrasenia.getText();
                    String contraseniaV = pswContraseniaV.getText();
                    boolean validar = !(nombre.equals("") || cboTipoCuenta.getSelectedIndex() < 0);
                    if (validar){
                        persona.setNombre(nombre);
                        persona.setTelefono(telefono);
                        persona.setTipo(TipoCuenta.valueOf(cboTipoCuenta.getSelectedItem().toString()));

                        if (contraseniaV.equals(contrasenia) && !contraseniaV.equals("")){
                            persona.setContrasenia(contrasenia);
                        }

                        redirigirInicio(esAdmin);

                    } else {
                        JOptionPane.showMessageDialog(null, "Revise que todos los campos estén llenos.");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        /** Cancelar y redirigir a la página correspondiente */
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirigirInicio(esAdmin);
            }
        });
    }

    private void redirigirInicio(boolean esAdmin){
        JPanel panel;
        if (esAdmin){
            panel = new InicioAdmin().getPanel();

        } else {
            /** !!! Se debe agregar para que navegue al inicio del interfaces.usuario y borrar lo que está ahorita */
            panel = null;
        }
        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cambiarDatosUsuario);
        este.setContentPane(panel);
        este.revalidate();
    }
    public JPanel getPanel(){
        return cambiarDatosUsuario;
    }
}
