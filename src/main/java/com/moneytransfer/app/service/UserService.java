package com.moneytransfer.app.service;

import com.moneytransfer.app.model.SendUser;
import com.moneytransfer.app.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

public class UserService {
    private HashMap<String, User> userMap;
    private static UserService userServiceInstance = null;
    private TransferService transferService = TransferService.getInstance();
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    private UserService() {
        userMap = new HashMap<>();
    }

    public static UserService getInstance() {
        if (userServiceInstance == null)
            userServiceInstance = new UserService();
        return userServiceInstance;
    }

    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
        LOGGER.info("User: " + user.getName() + " was added");
    }

    public boolean deleteUser(String userName) {
        if(userMap.containsKey(userName)) {
            userMap.remove(userName);
            LOGGER.info("UserName: " + userName + " was deleted");
            return true;
        }
        LOGGER.info("UserName: " + userName + " was not deleted");
        return false;
    }

    public Collection<User> getUsers() {
        return userMap.values();
    }

    public User getUserInfo(String userName) {
        return userMap.get(userName);
    }

    public boolean sendMoney(SendUser sendAmount) {
        User sender = userMap.get(sendAmount.getSender());
        User receiver = userMap.get(sendAmount.getReceiver());
        int amount = sendAmount.getSendAmount();
        if(!userExists(sender, receiver)) {
            return false;
        }
        return transferService.sendMoney(sender, receiver, amount);
    }

    /*
        Verify if both sender and receiver exists
     */
    private boolean userExists(User sender, User receiver) {
        if(sender.getUserName().equals(receiver.getUserName())) return false;
        boolean senderExist = userMap.containsKey(sender.getUserName());
        boolean receiverExist = userMap.containsKey(receiver.getUserName());
        System.out.println(senderExist + " " + receiverExist);
        return senderExist && receiverExist;
    }
}
