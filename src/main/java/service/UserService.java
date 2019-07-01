package service;

import model.SendUser;
import model.User;

import java.util.Collection;

public interface UserService {
    public void addUser(User user);

    public Collection<User> getUsers();

    public User getUserInfo(String id);

    public boolean sendMoney(SendUser sendAmount);
}
