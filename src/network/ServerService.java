package network;

import interfaces.IServerService;
import java.net.*;

public class ServerService implements IServerService {

    private ServerSocket ss;
    private Socket s;
    private DataBaseManager databaseManager;
    
    public ServerService(DataBaseManager databaseManager){
        this.databaseManager = databaseManager;
    }

    public void run() {
        try {
            ss = new ServerSocket(9999);
        } catch (Exception ex) {
            System.out.println("error al iniciar el servidor: " + ex);
        }

        System.out.println("Escuchando en el puerto 9999");
        listen();
    }

    @Override
    public void listen() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        s = ss.accept();
                        System.out.println("Nueva conexion aceptada");
                        new RequestManager(s, getDatabaseManager()).start();
                        //s = null;
                    } catch (Exception ex) {
                        System.out.println("error al leer los datos: " + ex);
                    }
                }
            }
        }.start();
    }

    public DataBaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(DataBaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

}
