package service;

import model.User;

public interface BankInfoService {
    public boolean canSendMoney(User sender, User Receiver, int sendAmount);
}
