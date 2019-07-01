package service;

import model.SendUser;
import model.User;

import java.util.Collection;
import java.util.HashMap;

public class UserServiceImpl implements UserService {
    private HashMap<String, User> userMap;

    public UserServiceImpl() {
        userMap = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
    }

    @Override
    public Collection<User> getUsers() {
        return userMap.values();
    }

    @Override
    public User getUserInfo(String userName) {
        return userMap.get(userName);
    }

    @Override
    public boolean sendMoney(SendUser sendAmount) {
        BankInfoService bankInfoService = new BankInfoServiceImpl();
        User sender = userMap.get(sendAmount.getSender());
        User receiver = userMap.get(sendAmount.getReceiver());
        int amount = sendAmount.getSendAmount();
        boolean usersExists = userExists(sender, receiver);
        if(usersExists) {
            boolean canSend = bankInfoService.canSendMoney(sender, receiver, amount);
            if(canSend) return true;
        }
        return false;
    }

    /*
        Verify if both sender and receiver exists
     */
    private boolean userExists(User sender, User receiver) {
        if(sender.getUserName().equals(receiver.getUserName())) return false;
        boolean senderExist = userMap.containsKey(sender.getUserName());
        boolean receiverExist = userMap.containsKey(receiver.getUserName());
        System.out.println(senderExist + " " + receiverExist);
        if(senderExist && receiverExist) return true;
        return false;
    }
}
