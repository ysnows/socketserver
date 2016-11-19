package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import utils.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 继承Thread线程类
public class ClientThread extends Thread {
    // 客户列表
    private ArrayList<Client> clients = new ArrayList<Client>();
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private int min = 6;
    private int max = 57;
    private Random random = new Random();
    private Gson gson = new Gson();
    private List gamers;


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


    /**
     *
     */
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
                                transaction = session.beginTransaction();
                                gamers = session.createQuery("from CocosGamer where roomid=:roomid").setParameter("roomid", client.roomId).list();
                                transaction.commit();
                                //向房间中所有人反馈
                                for (Client item : clients) {
                                    if (item.roomId == client.roomId && client.uid != item.uid) {
                                        System.out.println(gson.toJson(gamers));
                                        sendMessage(item.socket, gson.toJson(new SocketResult<>(200, "有人进入房间", gamers)));
                                    }
                                }

                                sendMessage(socket, new Gson().toJson(new SocketResult(200, "进入成功")));
                                break;
                            case "exit":
                                //退出
                                this.removeClient(client);
                                break;
                            case "pledge":
                                //押金->成功后结算押金
                                transaction = session.beginTransaction();
                                session.createQuery("update CocosGamer gamer set gamer.cash_pledge=:cash_pledge").setParameter("cash_pledge", request.cashPledge).executeUpdate();
                                gamers = session.createQuery("from CocosGamer where roomid=:roomid").setParameter("roomid", client.roomId).list();
                                transaction.commit();

                                //向房间中所有人反馈
                                for (Client item : clients) {
                                    if (item.roomId == client.roomId && client.uid != item.uid) {
                                        System.out.println(gson.toJson(gamers));
                                        sendMessage(item.socket, gson.toJson(new SocketResult<>(200, "有人押金", gamers)));
                                    }
                                }
                                sendMessage(socket, new Gson().toJson(new SocketResult(200, "抵押成功")));
                                break;
                            case "ok":
                                //点击开始 uid roomid
                                //向同一房间内的所有socket发送状态
                                //更改玩家的游戏状态->通知状态改变
                                transaction = session.beginTransaction();
                                session.createQuery("update CocosGamer gamer set gamer.status=1 where gamer.uid=:uid and gamer.roomid=:roomId").setParameter("uid", request.uid).setParameter("roomId", request.roomId).executeUpdate();
                                transaction.commit();
                                //获取房间中所有玩家信息并返回
                                transaction = session.beginTransaction();
                                gamers = session.createQuery(" from CocosGamer as gamer where gamer.roomid=:roomid").setParameter("roomid", request.roomId).list();
                                Short personCount = session.get(CocosRoom.class, request.roomId).getPersonCount();
                                transaction.commit();

                                //向房间中所有人反馈
                                for (Client item : clients) {
                                    if (item.roomId == client.roomId && client.uid != item.uid) {
                                        System.out.println(gson.toJson(gamers));
                                        sendMessage(item.socket, gson.toJson(new SocketResult<>(200, "有人准备", gamers)));
                                    }
                                }

                                sendMessage(socket, new Gson().toJson(new SocketResult(200, "准备成功")));
                                if (gamers.size() >= personCount) {//房间中所有人都准备了，那就开始，并结束循环
                                    transaction.begin();
                                    //变更房间状态
                                    session.createQuery("update CocosRoom room set room.roomStatus=1").executeUpdate();
                                    transaction.commit();
                                    break;
                                }
                                break;
                            case "getCard":
                                //补牌，返回房间中所有人的牌
                                transaction = session.beginTransaction();
                                ArrayList<Integer> integers;
                                //获取已发的牌
                                CocosRoom cocosRoom = session.get(CocosRoom.class, client.roomId);
                                String json = cocosRoom.getCards();
                                if (!TextUtils.isEmpty(json)) {
                                    System.out.println(json);
                                    integers = gson.fromJson(json, new TypeToken<List<Integer>>() {
                                    }.getType());
                                } else {
                                    integers = new ArrayList<>();
                                }
                                //随机发一张牌
                                getCard(client, min, max, random, gson, integers, cocosRoom);
                                transaction.commit();

                                break;
                            default:
                                sendMessage(socket, "请发送正确的指令--" + read);
                                break;
                        }
                    } catch (Exception e) {
                        sendMessage(socket, "请发送正确的指令--" + read);
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

    private void getCard(Client client, int min, int max, Random random, Gson gson, ArrayList<Integer> integers, CocosRoom cocosRoom) throws IOException {
        int s = random.nextInt(max) % (max - min + 1) + min;
        if (!integers.contains(s)) {//如果这张牌没有发
            integers.add(s);
            //添加至room的cards列表
            cocosRoom.setCards(gson.toJson(integers));
            //添加到gamer的用户表
            List<CocosGamer> list = session.createQuery("from CocosGamer as gamer where gamer.uid=:uid and gamer.roomid=:roomid").setParameter("uid", 1).setParameter("roomid", cocosRoom.getId()).list();
            CocosGamer cocosGamer = list.get(0);

            ArrayList<Integer> gamerIntegers;
            if (!TextUtils.isEmpty(cocosGamer.getCards())) {
                gamerIntegers = gson.fromJson(cocosGamer.getCards(), new TypeToken<List<Integer>>() {
                }.getType());
            } else {
                gamerIntegers = new ArrayList<>();
            }
            gamerIntegers.add(s);
            cocosGamer.setCards(gson.toJson(gamerIntegers));
            //判断结果
            Object[] sumCount = (Object[]) session.createQuery("select sum(card.num) as cardSum, count(*) as cardCount   from CocosCard   card where card.id in(:ids)").setParameterList("ids", gamerIntegers).iterate().next();

            if ((double) sumCount[0] > 10.5) {
                //这个人输了，然后把房间中所有人的牌返回给所有人
                List gamers = session.createQuery("from CocosGamer where roomid=:roomid").setParameter("roomid", cocosRoom.getId()).list();
                System.out.println(gson.toJson(gamers));

                //向房间中所有人反馈
                for (Client item : clients) {
                    if (item.roomId == client.roomId && client.uid != item.uid) {
                        System.out.println(gson.toJson(gamers));
                        sendMessage(item.socket, gson.toJson(new SocketResult<>(200, "有人输了！！！", gamers)));
                    }
                }

                sendMessage(client.socket, gson.toJson(new SocketResult<>(200, "你输了", gamers)));
            } else if ((double) sumCount[0] == 10.5) {
                //赢了
                gamers = session.createQuery("from CocosGamer where roomid=:roomid").setParameter("roomid", cocosRoom.getId()).list();
                System.out.println(gson.toJson(gamers));
//                sendMessage();


                //向房间中所有人反馈
                for (Client item : clients) {
                    if (item.roomId == client.roomId && client.uid != item.uid) {
                        System.out.println(gson.toJson(gamers));
                        sendMessage(item.socket, gson.toJson(new SocketResult<>(200, "有人十点半！！！", gamers)));
                    }
                }

                sendMessage(client.socket, gson.toJson(new SocketResult<>(200, "十点半！！！你赢了", gamers)));

            } else if ((double) sumCount[0] < 10.5 && (int) sumCount[1] == 5) {
                //小龙
                gamers = session.createQuery("from CocosGamer where roomid=:roomid").setParameter("roomid", cocosRoom.getId()).list();
                System.out.println(gson.toJson(gamers));

                //向房间中所有人反馈
                for (Client item : clients) {
                    if (item.roomId == client.roomId && client.uid != item.uid) {
                        System.out.println(gson.toJson(gamers));
                        sendMessage(item.socket, gson.toJson(new SocketResult<>(200, "有人小龙", gamers)));
                    }
                }

                sendMessage(client.socket, gson.toJson(new SocketResult<>(200, "小龙！！！你赢了", gamers)));
            } else {
                //继续
                //s
                gamers = session.createQuery("from CocosGamer where roomid=:roomid").setParameter("roomid", cocosRoom.getId()).list();
                //向房间中所有人反馈
                for (Client item : clients) {
                    if (item.roomId == client.roomId && client.uid != item.uid) {
                        System.out.println(gson.toJson(gamers));
                        sendMessage(item.socket, gson.toJson(new SocketResult<>(200, "继续", gamers)));
                    }
                }
                sendMessage(client.socket, gson.toJson(new SocketResult<>(200, "继续", s)));
            }
        } else {//如果已经发了这张牌，就继续抽
            getCard(client, min, max, random, gson, integers, cocosRoom);
        }
    }

}
