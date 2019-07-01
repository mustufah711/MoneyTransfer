package service;

import model.User;

public class BankInfoServiceImpl implements BankInfoService {

    /*
        Check if user has enough balance to send money
     */
    @Override
    public boolean canSendMoney(User sender, User receiver, int sendAmount) {
        if(sendAmount > sender.getBankAmount()) {
            return false;
        }
        receiver.setBankAmount(receiver.getBankAmount() + sendAmount);
        sender.setBankAmount(sender.getBankAmount() - sendAmount);
        return true;
    }
}
