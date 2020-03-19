package network;

import interfaces.IRequestManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import static model.Encrypt.*;
import static model.REQUEST_TYPE.*;
import model.User;
import static model.Utils.*;

public class RequestManager extends Thread implements IRequestManager {

    private BufferedReader entrada = null;
    private PrintWriter salida = null;
    
    private Socket s;
    private DataBaseManager databaseManager;

    public RequestManager(Socket s, DataBaseManager databaseManager) {
        this.s = s;
        this.databaseManager = databaseManager;
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

            String reuest = entrada.readLine();

            processRequest(reuest);

            salida.close();
            entrada.close();
        } catch (Exception ex) {
            System.out.println("Error al leer petición: " + ex);
        }
    }

    @Override
    public void processRequest(String request) {
        String decrypt = decrypt(request);
        
        String type = decrypt.substring(0, 12);
        String data = decrypt.substring(12, decrypt.length());

        switch (type) {
            case REQUEST_TYPE_USER:
                processUser(data);
                break;

            case REQUEST_TYPE_MD5:
                System.out.println("received md5");
                break;

            case REQUEST_TYPE_MESSAGE:
                System.out.println("received message");
                break;

            case REQUEST_TYPE_RESPONSE:
                System.out.println("received response");
                break;

            case REQUEST_TYPE_ERROR:
                System.out.println("received error");
                break;

            default:
                System.out.println("NO_OPERATION_AVAILABLE");
        }

    }

    @Override
    public void processUser(String user) {
        int index = databaseManager.findUser(user);

        if (index != -1) {
            User u = databaseManager.getUsers().get(index);

            String randomMessage = createMessage();
            u.setMessage(randomMessage);

            reply(REQUEST_TYPE_MESSAGE, randomMessage);
        } else {
            reply(REQUEST_TYPE_ERROR, "El usuario no existe");
        }

    }

    @Override
    public void reply(String type, String reply) {
        try {
            String message = encrypt(type + reply);            
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
            salida.println(message);
            salida.close();
        } catch (Exception ex) {
            System.out.println("Error al leer petición: " + ex);
        }
    }
}
