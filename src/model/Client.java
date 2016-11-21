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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (uid != client.uid) return false;
        if (roomId != client.roomId) return false;
        return socket != null ? socket.equals(client.socket) : client.socket == null;

    }

    @Override
    public int hashCode() {
        int result = socket != null ? socket.hashCode() : 0;
        result = 31 * result + uid;
        result = 31 * result + roomId;
        return result;
    }
}
