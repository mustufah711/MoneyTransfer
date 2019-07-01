package controller;

import com.google.gson.Gson;
import model.SendUser;
import model.User;
import response.StandardResponse;
import service.UserService;
import service.UserServiceImpl;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {
    public static void main(String [] args) {
        final UserService userService = new UserServiceImpl();

        get("/users", (req, res) -> {
            res.type("application/json");
            System.out.println("Everything working");
            return new Gson().toJson(new StandardResponse("Success", new Gson().toJsonTree(userService.getUsers())));
        });

        post("/users", (req, res) -> {
           res.type("application/json");
           User user = new Gson().fromJson(req.body(), User.class);
           userService.addUser(user);
           return new Gson().toJson(new StandardResponse("User Added"));
        });

        get("/user/:userName", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new StandardResponse("Success", new Gson().toJsonTree(userService.getUserInfo(req.params(":userName")))));
        });

        post("/sendMoney", (req, res) -> {
            res.type("application/json");
            SendUser sendAmount = new Gson().fromJson(req.body(), SendUser.class);
            boolean transfer = userService.sendMoney(sendAmount);
            if(transfer)
                return new Gson().toJson(new StandardResponse("Success"));
            else
                return new Gson().toJson(new StandardResponse("Failed transfer, low balances"));
        });
    }
}
