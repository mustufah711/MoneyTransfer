package com.moneytransfer.app;

import com.moneytransfer.app.controller.MoneyTransferController;

public class MoneyTransferApplication {
    public static void main(String [] args) {
        //Initializing endpoints
        MoneyTransferController.init();
    }
}
