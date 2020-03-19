package interfaces;

public interface IRequestManager {
    public void processRequest(String request);
    public void processUser(String user);    
    public void reply(String type, String reply);    
}
