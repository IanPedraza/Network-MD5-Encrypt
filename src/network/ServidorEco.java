package network;

import java.io.*;
import java.net.*;

public class ServidorEco {

    public void run() {
        ServerSocket ss = null;
        Socket s = null;

        try {
            ss = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Escuchando en el puerto 9999: " + ss);

        while (true) {
            try {
                s = ss.accept();
                System.out.println("Nueva conexion aceptada: " + s);
                new GestorPeticion(s).start();
                s = null;
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

    }

}
