package app;

import app.config.ThymeleafConfig;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static app.UserServices.login;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static Map<String, String> userMap= new HashMap<>();
    private static List<String> itemList = new ArrayList<>();
    private static List<String> taskList= new ArrayList<>();

    public static void main(String[] args)
    {

        //Initializin userMap
        initUserMap();



        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            JavalinThymeleaf.init(ThymeleafConfig.templateEngine());
        }).start(7070);

        // Routing

        app.get("/", ctx ->  ctx.render("index.html"));
        app.post("/login", ctx -> login(ctx));
        app.get("/remove", ctx -> ctx.render("remove.html"));
        app.get("/items", ctx -> ctx.render("items.html"));
        app.get("/createuser", ctx -> ctx.render("createuser.html"));

    }

    private static void initUserMap() {

        userMap.put("hh", "44");
    }
    public static void login(Context ctx){

        String userName = ctx.formParam("username");
        String passWord = ctx.formParam("password");

        /*if (userMap.get(userName).equals(passWord)) {*/
        if (userMap.containsKey(userName) && userMap.get(userName).equals(passWord)){

            ctx.render("items.html");
        } else {

            ctx.attribute("message", "Incorrect username and/or password");
            ctx.render("index.html");
        }
    }
}