package interfaces.chat;

import clases.Mensaje;
import clases.Persona;
import clases.TipoCuenta;
import interfaces.Inicio;
import interfaces.conductor.InicioConductor;
import interfaces.estibaje.InicioEstibaje;
import interfaces.repartidor.InicioRepartidor;
import interfaces.usuario.UsuarioInicio;
import sistema.Validaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


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
                if (receptor.equals("")){
                    JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                }else{
                    try {
                        /** Validación cédula */
                        boolean validacion = Validaciones.cedulaValida(receptor);
                        if (validacion) {
                            /** !!! La validación de si la cédula ya existe  */
                            /**Valida que la cedula este en el sistema*/
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
                                        este.setContentPane(new Chat(per,nuevochat).getChatUsuarios());
                                        este.revalidate();
                                    }
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"No existe un usuario con esa cedula");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La cédula ingresada es inválida.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
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
                if (chatSeleccionado.getEmisor().getCedula().compareTo(per.getCedula()) == 0){
                    JFrame este = (JFrame) SwingUtilities.getWindowAncestor(buscarChat);
                    este.setContentPane(new Chat(per,chatSeleccionado.getReceptor()).getChatUsuarios());
                    este.revalidate();
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
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoCuenta tipo = per.getTipo();
                JPanel panel;
                //Redirigir según el tipo de cuenta
                switch (tipo){
                    case USUARIO:
                        panel = new UsuarioInicio(per).getUsuarioInicio();
                        break;
                    case REPARTIDOR:
                        panel = new InicioRepartidor(per).getPanel();
                        break;
                    case ESTIBAJE:
                        panel = new InicioEstibaje(per).getPanel();
                        break;
                    case CONDUCTOR:
                        panel = new InicioConductor(per).getPanel();
                        break;
                    default:
                        panel = new Inicio().getPanel();
                        JOptionPane.showMessageDialog(null, "Error inesperado, será redirigido al inicio");
                }
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(buscarChat);
                este.setContentPane(panel);
                este.revalidate();

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
        SortedSet<Mensaje> aux= new TreeSet<>();
        todosMensajes.clear();
        boolean existemensaje=false;
        for (Mensaje m:listaRecibidos){
            aux.clear();
            Persona emisor =m.getEmisor();
            Persona actual =m.getReceptor();
            aux.addAll(Inicio.sistema.buscarChatEntre(emisor.getCedula(),actual.getCedula()));
            for (Mensaje x:todosMensajes){
                Persona p1=x.getEmisor();
                Persona p2=x.getReceptor();
                if ((p1.equals(aux.last().getEmisor()) && p2.equals(aux.last().getReceptor())) || (p1.equals(aux.last().getReceptor()) && p2.equals(aux.last().getEmisor()))){
                    existemensaje=true;
                    break;
                }
                existemensaje=false;
            }
            if (existemensaje==false){
                todosMensajes.add(aux.last());
            }
        }
        for (Mensaje m:listaEnviados){
            aux.clear();
            Persona actual =m.getEmisor();
            Persona receptor =m.getReceptor();
            aux.addAll(Inicio.sistema.buscarChatEntre(receptor.getCedula(),actual.getCedula()));
            for (Mensaje x:todosMensajes){
                Persona p1=x.getEmisor();
                Persona p2=x.getReceptor();
                if ((p1.equals(aux.last().getEmisor()) && p2.equals(aux.last().getReceptor())) || (p1.equals(aux.last().getReceptor()) && p2.equals(aux.last().getEmisor()))){
                    existemensaje=true;
                    break;
                }
                existemensaje=false;
            }
            if (existemensaje==false){
                todosMensajes.add(aux.last());
            }
        }

        for (Mensaje m:todosMensajes){
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