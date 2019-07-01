package service;

import model.User;

public interface BankInfoService {
    public boolean userExists(User sender, User receiver);

    public boolean canSendMoney(User sender, int sendAmount);
}
