package com.moneytransfer.app.service;

import com.moneytransfer.app.model.User;

public class TransferService {
    private static TransferService transferServiceInstance = null;

    private TransferService(){}

    public static TransferService getInstance() {
        if (transferServiceInstance == null)
            transferServiceInstance = new TransferService();
        return transferServiceInstance;
    }
    /*
        Check if user has enough balance to send money
     */
    public boolean sendMoney(User sender, User receiver, int sendAmount) {
        if(sendAmount > sender.getBankAmount()) {
            return false;
        }
        receiver.setBankAmount(receiver.getBankAmount() + sendAmount);
        sender.setBankAmount(sender.getBankAmount() - sendAmount);
        return true;
    }
}
