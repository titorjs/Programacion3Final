package interfaces.chat;

import clases.Mensaje;
import clases.Persona;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;


public class BuscarChats {
    private JPanel buscarChat;
    private JTextField txtBuscarChat;
    private JList listChats;
    private JButton btnBuscarChat;
    private JButton btnRegresar;
    private JButton btnChatear;
    private JButton btnTotalChats;
    private JScrollPane JScrollJlist;

    DefaultListModel<Mensaje> dlm =new DefaultListModel<>();

    public BuscarChats(Persona per) {
        /**
         * Se inicia llenando el Jlist con los chats del usario
         * */
        llenarListaTotal(Inicio.sistema.buscarMensajesReceptor(per.getCedula()), Inicio.sistema.buscarMensajesEmisor(per.getCedula()));
        btnBuscarChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receptor=txtBuscarChat.getText();
                /**Valdia que la cedula este en el sistema*/
                if (Inicio.sistema.buscarUsuarioCedula(receptor)!=null){
                    /**Comprueba si existe un chat con ese usario y si no existe, le da la opcion de
                     * crear un nuevo chat con ese usario
                     */
                    boolean existechat = llenarLista(Inicio.sistema.buscarChatEntre(per.getCedula(), receptor));
                    if ( existechat== false) {
                        int opt = JOptionPane.showOptionDialog(null, "No tienes un chat con ese usuario. ¿Deseas crear uno?","Crear chat",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "Sí");
                        if (opt == JOptionPane.YES_OPTION) {
                            Persona nuevochat = Inicio.sistema.buscarUsuarioCedula(receptor);
                            JFrame este = (JFrame) SwingUtilities.getWindowAncestor(buscarChat);
                            este.setContentPane(new Chat(per, nuevochat).getChatUsuarios());
                            este.revalidate();
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"No existe un usuario con esa cedula");
                }
            }
        });
        /**
         * Este botón sirve para dirigirnos al chat seleccionado en el Jlist
         * */
        btnChatear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**Valida que se haya seleccionado un chat en el Jlist*/
                Mensaje chatSeleccionado= (Mensaje) listChats.getSelectedValue();
                if (chatSeleccionado==null){
                    JOptionPane.showMessageDialog(null,"Debes seleccionar un chat");
                }
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(buscarChat);
                este.setContentPane(new Chat(per,chatSeleccionado.getEmisor()).getChatUsuarios());
                este.revalidate();
            }
        });
        /**
         * Este boton sirve para volver a colocar todos los chat del usuario en el Jlist
         * */
        btnTotalChats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarListaTotal(Inicio.sistema.buscarMensajesReceptor(per.getCedula()), Inicio.sistema.buscarMensajesEmisor(per.getCedula()));
            }
        });
    }

    /**
     * Este metodo sirve para imprimir en el Jlist todos los chats que tiene el usario
     * Aparte de eso se implemento el JScrollPane para poder ver lo del Jlist
     * */
    public void llenarListaTotal(SortedSet<Mensaje> listaRecibidos, SortedSet<Mensaje> listaEnviados){
        dlm.removeAllElements();
        List<Mensaje> todosMensajes=new ArrayList<>();
        boolean mensajereciente=false;

        for (Mensaje m:listaRecibidos){
            Persona p1=m.getEmisor();
            for (Mensaje aux:listaRecibidos){
                Persona auxp=aux.getEmisor();
                if (p1.getCedula().equals(auxp)){
                    if (m.getEnviado().compareTo(aux.getEnviado())<0){
                        m=aux;
                    }
                }
            }
            todosMensajes.add(m);
        }

        for (Mensaje m:listaEnviados){
            Persona p1=m.getReceptor();
            for (Mensaje aux:listaEnviados){
                Persona auxp=aux.getReceptor();
                if (p1.getCedula().equals(auxp)){
                    if (m.getEnviado().compareTo(aux.getEnviado())<0){
                        m=aux;
                    }
                }
            }
            todosMensajes.add(m);
        }

        for (Mensaje m:todosMensajes){
            Persona p1=m.getEmisor();
            Persona p2=m.getReceptor();
            for (Mensaje aux:todosMensajes){
                Persona auxp1=aux.getEmisor();
                Persona auxp2=aux.getReceptor();
                boolean caso1=p1.getCedula().equals(auxp1) && p2.getCedula().equals(auxp2);
                boolean caso2=p1.getCedula().equals(auxp2) && p2.getCedula().equals(auxp1);
                if (caso1 || caso2){
                    if (m.getEnviado().compareTo(aux.getEnviado())>0){
                        m=aux;
                    }
                }
            }
            dlm.addElement(m);
        }
        listChats.setModel(dlm);
        JScrollJlist.setHorizontalScrollBarPolicy(JScrollJlist.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollJlist.setVerticalScrollBarPolicy(JScrollJlist.VERTICAL_SCROLLBAR_AS_NEEDED);
    }


    /**
     * Este metodo sirve para imprimir en el Jlist el ultimo mensaje del chat que tiene el usario
     * con la cedula que se ingreso.
     * Aparte de eso se implemento el JScrollPane para poder ver lo del Jlist
     * Para completar el metodo es boolean para poder crear un nuevo chat si
     * no se encontro un chat con el usuario ingresado
     * */
    public boolean llenarLista(SortedSet<Mensaje> lista){
        dlm.removeAllElements();
        if (!lista.isEmpty()){
            dlm.addElement(lista.last());
            listChats.setModel(dlm);
            JScrollJlist.setHorizontalScrollBarPolicy(JScrollJlist.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JScrollJlist.setVerticalScrollBarPolicy(JScrollJlist.VERTICAL_SCROLLBAR_AS_NEEDED);
            return true;
        }
        return false;
    }

    public JPanel getBuscarChat() {
        return buscarChat;
    }
}