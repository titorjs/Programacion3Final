package interfaces.usuario;

import clases.Envio;
import clases.Mensaje;
import clases.Persona;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class cancelEntrega {
    private JTextField txtIdCancelar;
    private JButton btnContinuarCancel;
    private JButton regresarButton;
    private JPanel cancelEntrega;
    private JComboBox cboEnvCancel;
    private Persona user;
    private SortedSet<Mensaje> multas;


    public cancelEntrega(Persona p) {
        multas=new TreeSet<>();
        user=p;
        actId();
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cancelEntrega);
                este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
                este.revalidate();
            }
        });


        btnContinuarCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Envio> enviosPersona= Inicio.sistema.enviosUsuario(user);
                if (enviosPersona.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No cuenta con envios a eliminar, porfavor revise y vuelva a intentarlo");
                    JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cancelEntrega);
                    este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
                    este.revalidate();
                }else {
                    // Se peude usar opcional si se quiere ingresar por el teclado el id
                    //int Id= Integer.parseInt(txtIdCancelar.getText());
                    int cboId=Integer.parseInt(cboEnvCancel.getSelectedItem().toString());
                    if(Inicio.sistema.buscarEnvio(cboId)!=null){
                        Envio en= Inicio.sistema.buscarEnvio(cboId);
                        if(en.getEstado()==0){
                            int opcion=JOptionPane.showConfirmDialog(null,"Está seguro que desea cancelar este envio\n" + "\t Receptor:  "+ en.getReceptor().getNombre()+"\n"+
                                    "\t Descripción:  "+ en.getPaquete().getDetalle(), "Confirmación de cancelación",JOptionPane.YES_NO_OPTION);
                            if(opcion==0){
                                Inicio.sistema.removerEnvio(en);
                                JOptionPane.showMessageDialog(null,"El envio ha sido cancelado");
                            }else {
                                JOptionPane.showMessageDialog(null,"El envio no se ha cancelado");
                            }
                        }else {
                            if(en.getEstado()==4||en.getEstado()==5){
                                JOptionPane.showMessageDialog(null, "Este envio no se puede cancelar, por el estado que se encuentra");
                            }else {
                                int opcion=JOptionPane.showConfirmDialog(null,"Está seguro que desea cancelar este envio\n" + "\t Receptor:  "+ en.getReceptor().getNombre()+"\n"+
                                        "\t Descripción:  "+ en.getPaquete().getDetalle()+"\n Si cancela el envio se le generara una multa a pagar en oficina", "Confirmación de cancelación",JOptionPane.YES_NO_OPTION);
                                if(opcion==0){
                                    Inicio.sistema.removerEnvio(en);
                                    Mensaje m = new Mensaje(Inicio.sistema.buscarUsuarioCedula("0000000000"), p, "Ha sido multad@ con 5 dolares, porfavor hacercarse a pagar a la oficina", LocalDateTime.now());
                                    multas.add(m);
                                    JOptionPane.showMessageDialog(null,"El envio ha sido cancelado"+m.getMensaje());
                                }else {
                                    JOptionPane.showMessageDialog(null,"El envio no se ha cancelado");
                                }
                            }
                        }

                    }else {
                        JOptionPane.showMessageDialog(null, "El envio no se a encontrado, porfavor vuelva a intentarlo");
                        txtIdCancelar.setText("");
                    }
                }

            }
        });
    }


    /**
     * Get del panel
     */
    public JPanel getCancelEntrega() {
        return cancelEntrega;
    }

    public void actId(){
        ArrayList<Envio> envios = Inicio.sistema.enviosUsuario(user);
        for (Envio e: envios){
            int id=e.getId();
            cboEnvCancel.addItem(id);
        }
    }
}
