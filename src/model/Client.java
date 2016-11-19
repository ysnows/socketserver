package model;

import java.net.Socket;

/**
 * Created by xianguangjin on 2016/11/14.
 */
public class Client {
    public Socket socket;
    public int uid = 0;
    public int roomId = 0;


    public Client(Socket socket) {
        this.socket = socket;
    }
}
