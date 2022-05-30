package com.example.appBiaf;

import com.google.android.material.snackbar.Snackbar;


import pojos.Configuracion;
import pojos.Participante;
import pojos.Usuario;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.ArrayList;

public class Cliente extends Thread {

    String opcion=""; // 1 insertar usurio
    Usuario usuario;
    Participante participante;
    Configuracion configuracion;
    ArrayList<Participante> listaParticipantes;

    public Cliente() {
    }

    public ArrayList<Participante> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setListaParticipantes(ArrayList<Participante> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void run() {
        try {
            String equiposervidor = "192.168.0.26";
            String equiposervidor2 = "10.190.34.80";
            int puertoServidor = 30500;
            /** Se abre un socket en el cliente, y dicho socket establece
             "una conexión con el socket del servidor si tuado en el puerto 30500 de la 127.0.0.1" */
            Socket socketCliente = new Socket(equiposervidor, puertoServidor);

            //Preparo el envio de datos
            OutputStream os = socketCliente.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            //opciones que solo mandan un usuario par insertrlo o eliminarlo
            if (opcion.equals("1") || opcion.equals("2")){
                dos.writeUTF(opcion);
                InsertaryEliminar(socketCliente,usuario);

            //opciones para recuperar un participante
            } else if (opcion.equals("3")){
                dos.writeUTF(opcion);
                mostrarParticipante(socketCliente, participante);

            //opciones para buscr usuarios, ya sea solo por nombre o por nombre y contraseña
            } else if (opcion.equals("4") || opcion.equals("5")) {
                dos.writeUTF(opcion);
                IniciarSesion(socketCliente, usuario);

            // opcion para listar participantes
            }else if (opcion.equals("6")){
                dos.writeUTF(opcion);
                listarDisenadores(socketCliente);
            } else if (opcion.equals("7")){
                dos.writeUTF("7");
                horaFin(socketCliente);


            }

            socketCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para enviar un Usuario
     * @param socketCliente
     * @param usuario
     */
    public void InsertaryEliminar (Socket socketCliente, Usuario usuario) {
        try {
            //El cliente contruye el objetco y lo envia al servidor
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(usuario);
            //Se cierra la comunicación
            oos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Usuario IniciarSesion (Socket socketCliente, Usuario u) {
        try {
            //El cliente contruye el objetco y lo envia al servidor
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(u);
            //Espero el objeto completo
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            usuario = (Usuario) ois.readObject();

            //Se cierra la comunicación
            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return usuario;
    }

    public Participante mostrarParticipante (Socket socketCliente, Participante p) {
        try {

            //El cliente contruye el objeto y lo envia al servidor
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);

            //Espero el objeto completo
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            participante = (Participante) ois.readObject();
            //Se cierra la comunicación
            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return participante;
    }

    public ArrayList<Participante> listarDisenadores (Socket socketCliente) {
        try {

            //Espero la lista
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            listaParticipantes = (ArrayList<Participante>) ois.readObject();

            //Se cierra la comunicación
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return listaParticipantes;
    }

    public Configuracion horaFin (Socket socketCliente) {
        try {
            //Espero la lista
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            configuracion = (Configuracion) ois.readObject();
            //Se cierra la comunicación
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return configuracion;
    }

    }

