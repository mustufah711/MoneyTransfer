package com.moneytransfer.app.model;

public class SendUser {
    private String sender;
    private String receiver;
    private int sendAmount;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(int sendAmount) {
        this.sendAmount = sendAmount;
    }

    @Override
    public String toString() {
        return this.sender + ", " + this.receiver + ", " + this.sendAmount;
    }
}
