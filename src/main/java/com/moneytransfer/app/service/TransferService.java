package com.moneytransfer.app.service;

import com.moneytransfer.app.model.User;

import java.util.logging.Logger;

public class TransferService {
    private static TransferService transferServiceInstance = null;
    private static final Logger LOGGER = Logger.getLogger(TransferService.class.getName());


    private TransferService(){}

    public static TransferService getInstance() {
        if (transferServiceInstance == null)
            transferServiceInstance = new TransferService();
        return transferServiceInstance;
    }

    public boolean sendMoney(User sender, User receiver, int sendAmount) {
        if(sendAmount > sender.getBankAmount()) {
            LOGGER.info("The amount " + sendAmount + " is too high");
            return false;
        }
        receiver.setBankAmount(receiver.getBankAmount() + sendAmount);
        sender.setBankAmount(sender.getBankAmount() - sendAmount);
        LOGGER.info("Money has been transferred");
        return true;
    }
}
