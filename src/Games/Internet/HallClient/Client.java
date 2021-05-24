package Games.Internet.HallClient;


import Games.Internet.GameClient.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class Client extends Thread {

    ClientUI ui;
    Socket client;
    BufferedReader reader;
    PrintWriter writer;
    int port = 8001;

    public Client(ClientUI ui) {
        this.ui = ui;
        try {
            String ip = "127.0.0.1"; //得到输入的ip地址
            //InetAddress ip = Inet4Address.getByName("chat.sustc.icu");
            //int port = Integer.parseInt(ui.tfPort.getText()); //得到输入的端口
            int port = 8001;
            client = new Socket(ip, port);//这里设置连接服务器端的IP的端口
            println("连接服务器 gamehall.sustc.icu 成功，服务器端口地址：" + port);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            String name = ui.tfName.getText();
            if (name == null || "".equals(name)) {
                name = "匿名者";
            }
            sendMsg("abcd"+name);
            Data.setPlayername(name);
            System.out.println(Data.getPlayername());
            ui.btStart.setEnabled(false);
            ui.tfName.setEditable(false);
            // 如果为 true，则 println、printf 或 format 方法将刷新输出缓冲区
        } catch (NumberFormatException nu) {
            println("端口请输入正确.......");
            nu.printStackTrace();
        } catch (IOException e) {
            println("连接服务器失败：请输入正确的IP地址与端口");
            println(e.toString());
            e.printStackTrace();
        }
        this.start();
    }

    public void run() {
        String msg = "";
        while (true) {
            try {
                msg = reader.readLine();
            } catch (IOException e) {
                println("服务器断开连接");
                break;
            }
            if (msg.equals("over")){
                this.ui.taShow.setText("");
                println("连接服务器 gamehall.sustc.icu 成功，服务器端口地址：" + port);
            }
            else if (msg != null && msg.trim() != "") {
                println(msg);
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            writer.println(msg);
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

