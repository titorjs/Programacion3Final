package interfaces.chat;

import clases.Mensaje;
import clases.Persona;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class Chat {
    private JPanel chatUsuarios;
    private JTextArea txtAreaInfoUsuario;
    private JTextArea textAreaChat;
    private JButton btnRegresar;
    private JButton btnEnviarMensaje;
    private JTextField txtMensaje;
    private JScrollPane JScrollChat;
public Chat(Persona p1, Persona p2) {

    txtAreaInfoUsuario.setText(p2.toString());
    String resultado = "";
    for (Mensaje m :Inicio.sistema.buscarChatEntre(p1.getCedula(),p2.getCedula())){
        resultado += m.toString() +"\n";
    }
    textAreaChat.setText(resultado);

    JScrollChat.setHorizontalScrollBarPolicy(JScrollChat.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JScrollChat.setVerticalScrollBarPolicy(JScrollChat.VERTICAL_SCROLLBAR_AS_NEEDED);
    btnEnviarMensaje.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nuevoMensaje = txtMensaje.getText();

            JOptionPane.showMessageDialog(null,"Enviado");
            txtMensaje.setText("");
            Inicio.sistema.agregarMensaje(new Mensaje(p1,p2,nuevoMensaje, LocalDateTime.now()));
            /**Agregar un metodo en Sistema para poder agregar los mensajes al TreeSet<> mensajes*/
            String resultado = "";
            for (Mensaje m :Inicio.sistema.buscarChatEntre(p1.getCedula(),p2.getCedula())){
                resultado += m +"\n";
            }
            textAreaChat.setText(resultado);
            JScrollChat.setHorizontalScrollBarPolicy(JScrollChat.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JScrollChat.setVerticalScrollBarPolicy(JScrollChat.VERTICAL_SCROLLBAR_AS_NEEDED);

        }
    });
    btnRegresar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame este = (JFrame) SwingUtilities.getWindowAncestor(chatUsuarios);
            este.setContentPane(new BuscarChats(p1).getBuscarChat());
            este.revalidate();
        }
    });
}
    public JPanel getChatUsuarios() {
        return chatUsuarios;
    }

}
