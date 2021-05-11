package Games.Internet.HallServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ListenerClient extends Thread{

    BufferedReader reader;
    PrintWriter writer;
    ServerUI ui;
    Socket client;

    public ListenerClient(ServerUI ui, Socket client) {
        this.ui = ui;
        this.client=client;
        this.start();
    }

    public void addClientName(String str){
        ArrayList<String> a = SaveClientName.getClientname();
        a.add(str);
        SaveClientName.setClientname(a);
    }
    public void deleteClientName(String str) {
        String [] arr = str.split("\\s+");
        ArrayList<String> a = SaveClientName.getClientname();
        for (int i=0;i<=a.size();i++){
            if (a.get(i).equals(arr[1])){
                a.remove(i);
            }
        }
        SaveClientName.setClientname(a);
        UpdateList();
//        a.remove(arr[1]);
//        SaveClientName.setClientname(a);
//        //System.out.println(arr[1]);//本行的arr[2]为玩家选择的对手，需要传递到下一个服务器
    }

    public void UpdateList(){
        sendMsg("over\n");
        sendMsg("序号                 用户名\n");
        int i = 1;
        ArrayList<String> strings = SaveClientName.getClientname();
        for (String tmp:strings) {
            sendMsg(i+"                      " + tmp);
            i++;
        }
    }
    //为每一个客户端创建线程等待接收信息，然后把信息广播出去
    @Override
    public void run() {
        String msg = "";
        while (true) {
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);
                msg = reader.readLine();
                if (msg.substring(0,4).equals("over")){
                    deleteClientName(msg);
                }
                if (msg.substring(0,4).equals("quit")){
                    deleteClientName(msg);
                }
                else {
                    addClientName(msg.substring(4));
                }
                UpdateList();

            } catch (IOException e) {
                println(e.toString());
                break;
            }
            if (msg != null && msg.trim() != "") {
                println("客户端 " + msg);
            }
        }
    }

    //把信息广播到所有用户
    public synchronized void sendMsg(String msg) {
        try {
            for (int i = 0; i < ui.clients.size(); i++) {
                Socket client = ui.clients.get(i);
                writer = new PrintWriter(client.getOutputStream(), true);
                writer.println(msg);
            }

        } catch (Exception e) {
            println(e.toString());
        }
    }

    public void println(String s) {
        if (s != null) {
            this.ui.taShow.setText(this.ui.taShow.getText() + s + "\n");
            System.out.println(s + "\n");
        }
    }
}

