package network;

import interfaces.IDataBaseManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.User;

public class DataBaseManager implements IDataBaseManager {

    private List<User> users;

    public DataBaseManager() {
        this.users = new ArrayList();
    }

    @Override
    public void loadData(String path) {

        int numberLine = 1;

        try {
            //Abrir el archivo
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                
                if (data.length == 3) {
                    String userId = data[0];
                    String userName = data[1];
                    String password = data[2];

                    users.add(new User(userId, userName, password, ""));
                } else {
                    System.out.println("error al obtener el registro de la líea: " + numberLine);
                }

                numberLine++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("error al cargar los datos: " + ex);
        }
    }

    @Override
    public int findUser(String userName) {
        int index = -1;
        
        for(User user : users){
            if(user.getUserName().equals(userName)){
                index = users.indexOf(user);
                break;
            }
        }
        
        return index;
    }

    public List<User> getUsers() {
        return users;
    }

}
