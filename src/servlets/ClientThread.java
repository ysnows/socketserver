package servlets;

import com.google.gson.Gson;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 继承Thread线程类
public class ClientThread extends Thread {
    // 客户列表
    private ArrayList<Client> clients = new ArrayList<Client>();
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    // 添加客户
    public void addClient(Client client) {
        clients.add(client);
    }

    // 删除客户
    public void removeClient(Client client) {
        clients.remove(client);
    }

    // 向客户发送数据
    public void sendMessage(Socket socket, String data) throws IOException {
        // 给玩家发送数据
        OutputStream os = socket.getOutputStream();
        os.write(data.getBytes("UTF-8"));
    }


    @Override
    public void run() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        while (true) {
            try {
                for (int i = clients.size() - 1; i >= 0; i--) {
                    Client client = clients.get(i);
                    // 获取客户端发来的数据
                    Socket socket = client.socket;
                    InputStream is = socket.getInputStream();
                    int len = is.available() + 1;
                    byte[] buff = new byte[len];
                    int flag = is.read(buff);

                    // read()返回-1，说明客户端的socket已断开
//                    if (flag == -1) {
//                        System.out.println("有客户断开连接~");
//                        this.removeClient(socket);
//                        break;
//                    }

                    // 输出接收到的数据

                    String read = new String(buff).trim();
//                    System.out.println(read);

                    try {
                        SocketRequest request = new Gson().fromJson(read, SocketRequest.class);
                        switch (request.action) {
                            case "login":
                                //登录 uid
                                client.uid = request.uid;
                                sendMessage(socket, new Gson().toJson(new SocketResult(200, "登录成功")));
                                break;
                            case "enterRoom":
                                //调用完 http 的进入房间接口成功后调用这个 socket 接口
                                client.uid = request.uid;
                                client.roomId = request.roomId;
                                sendMessage(socket, new Gson().toJson(new SocketResult(200, "进入成功")));
                                break;
                            case "exit":
                                //退出
                                this.removeClient(client);
                                break;
                            case "ok":
                                //点击开始 uid roomid
                                //向同一房间内的所有socket发送状态
                                for (Client item : clients) {
                                    if (item.uid == 0 || item.roomId == 0) {
                                        continue;
                                    }
                                    if (item.roomId == request.roomId) {
                                        //更改玩家的游戏状态->通知状态改变
                                        transaction = session.beginTransaction();
                                        session.createQuery("update CocosGamer gamer set gamer.status=1 where gamer.uid=:uid and gamer.roomid=:roomId").setParameter("uid", request.uid).setParameter("roomId", request.roomId).executeUpdate();
                                        transaction.commit();
                                        //获取房间中所有玩家信息并返回
                                        transaction = session.beginTransaction();
                                        List list = session.createQuery(" from CocosGamer as gamer where gamer.roomid=:roomid").setParameter("roomid", request.roomId).list();
                                        Short personCount = session.get(CocosRoom.class, request.roomId).getPersonCount();
                                        transaction.commit();
                                        sendMessage(socket, new Gson().toJson(new SocketResult<List<CocosGamer>>(200, "准备成功", list)));
                                        if (list.size() >= personCount) {//房间中所有人都准备了，那就开始，并结束循环
                                            break;
                                        }
                                    }
                                }

                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    // 给玩家发送数据
//                    String data = "恭喜你，连接成功啦~~";
//                    Database database = new Database();
//
//                    try {
//                        String sql = "select * from cocos_user";
//                        ResultSet resultSet = database.executeQuery(sql);
//                        if (resultSet.next()) {
//                            String phone = resultSet.getString("phone");
//                            sendMessage(socket, phone);
//                        }
////                        sendMessage(socket, data);
//
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
                }
                sleep(10);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
