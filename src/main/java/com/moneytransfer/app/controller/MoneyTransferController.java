package com.moneytransfer.app.controller;

import com.google.gson.Gson;
import com.moneytransfer.app.response.Response;
import com.moneytransfer.app.model.SendUser;
import com.moneytransfer.app.model.User;
import com.moneytransfer.app.response.StandardResponse;
import com.moneytransfer.app.service.UserService;

import static spark.Spark.*;

public class MoneyTransferController {
    private MoneyTransferController() {}

    public static void init(){
        final UserService userService = UserService.getInstance();
        get("/users", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new StandardResponse(Response.SUCCESS, new Gson().toJsonTree(userService.getUsers())));
        });

        post("/users", (req, res) -> {
            res.type("application/json");
            User user = new Gson().fromJson(req.body(), User.class);
            userService.addUser(user);
            return new Gson().toJson(new StandardResponse(Response.USER_ADDED));
        });

        get("/user/:userName", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new StandardResponse(Response.SUCCESS, new Gson().toJsonTree(userService.getUserInfo(req.params(":userName")))));
        });

        delete("/user/:userName", (req, res) -> {
            res.type("application/json");
            boolean deleteUser = userService.deleteUser(req.params(":userName"));
            if(deleteUser) {
                return new Gson().toJson(new StandardResponse(Response.USER_DELETED));
            }
            return new Gson().toJson(new StandardResponse(Response.USER_DOESNT_EXIST));
        });

        post("/send-money", (req, res) -> {
            res.type("application/json");
            SendUser sendAmount = new Gson().fromJson(req.body(), SendUser.class);
            boolean transfer = userService.sendMoney(sendAmount);
            if(transfer)
                return new Gson().toJson(new StandardResponse(Response.SUCCESS));
            else
                return new Gson().toJson(new StandardResponse(Response.BAD_TRANSFER));
        });
    }
}
