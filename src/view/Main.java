package view;

import network.DataBaseManager;
import network.ServerService;

public class Main {

    public static void main(String[] args) {               
        DataBaseManager dataBaseManager = new DataBaseManager();     
        dataBaseManager.loadData("users.txt");
        
        ServerService serverService = new ServerService(dataBaseManager);
        serverService.run();
    }
    
}
