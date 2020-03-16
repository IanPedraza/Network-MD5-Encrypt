package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class GestorPeticion extends Thread {

    private BufferedReader entrada = null;
    private PrintWriter salida = null;
    private Socket s;

    public GestorPeticion(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

            while (true) {
                String str = entrada.readLine();
                System.out.println("Recibo: " + str);
                salida.println(str);
                
                if (str.equals("bye")) {
                    break;
                }
            }

            salida.close();
            entrada.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
