package service;

import model.User;

public class BankInfoServiceImpl implements BankInfoService {

    @Override
    public boolean userExists(User sender, User receiver) {
        UserService userService = new UserServiceImpl();
        boolean senderExist = ((UserServiceImpl) userService).userMap.containsKey(sender.getUserName());
        boolean receiverExist = ((UserServiceImpl) userService).userMap.containsKey(receiver.getUserName());
        if(senderExist && receiverExist)
            return true;
        return false;
    }

    @Override
    public boolean canSendMoney(User sender, int sendAmount) {
        if(sendAmount > sender.getBankAmount()) {
            return false;
        }
        return true;
    }
}
