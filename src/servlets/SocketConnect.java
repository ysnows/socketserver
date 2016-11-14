package servlets;

import com.google.gson.Gson;
import model.Client;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xianguangjin on 2016/11/14.
 */
public class SocketConnect extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        User user = new User(1, "1835445969", "123456", "ysnows", "ysnows", "wx_id", "avatar", "123", 11, 12, 2001);
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        String userJson = new Gson().toJson(user);
//        log(userJson);
//        out.print(userJson);

        ServerSocket ss = new ServerSocket(3302);
        ClientThread clientThread = new ClientThread();
        clientThread.start();

        System.out.println("server opened");

        while (true) {
            // 开始接收客户端的连接

            Socket socket = ss.accept();
            System.out.println("new client ~");
            clientThread.addClient(new Client(socket));
        }
    }
}
