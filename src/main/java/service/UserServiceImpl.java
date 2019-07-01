package service;

import model.SendUser;
import model.User;

import java.util.Collection;
import java.util.HashMap;

public class UserServiceImpl implements UserService {
    protected HashMap<String, User> userMap;

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
        boolean userExists = bankInfoService.userExists(sender, receiver);
        int amount = sendAmount.getSendAmount();
        receiver.setBankAmount(receiver.getBankAmount() + amount);
        sender.setBankAmount(sender.getBankAmount() - amount);
        return true;

    }
}
