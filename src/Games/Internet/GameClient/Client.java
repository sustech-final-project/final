package Games.Internet.GameClient;


import Games.utils.GameStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

    Socket client;
    BufferedReader reader;
    PrintWriter writer;
    int port = 8001;

    public Client() {
  //      this.ui = ui;
        try {
            String ip = "127.0.0.1"; //得到输入的ip地址
            //InetAddress ip = Inet4Address.getByName("chat.sustc.icu");
            //int port = Integer.parseInt(ui.tfPort.getText()); //得到输入的端口
            int port = 8001;
            client = new Socket(ip, port);//这里设置连接服务器端的IP的端口
            println("连接服务器 gamehall.sustc.icu 成功，服务器端口地址：" + port);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            String name = Data.getPlayer().name;
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
            } catch (Exception e) {
                println("服务器断开连接");
                break;
            }
            Player player = Data.getPlayer();
            String [] arr = msg.split("\\s+");
            if (arr[0].equals("play")){
                Data.setaChar(arr[2].charAt(0));//Todo:这里应该改成显示之类的，或者给一个扳机，触发ui重写
                Data.setRow(Integer.parseInt(arr[3]));
                Data.setColumn(Integer.parseInt(arr[4]));
                if (player.id==Integer.parseInt(arr[1])){
                    player.mistake=Integer.parseInt(arr[5]);
                    player.score=Integer.parseInt(arr[6]);
                }
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
           // println(e.toString());
        }
    }

    public void gaming(GameStatus gameStatus,int playernumber,int row,int column) {
        try {
            writer.println("play " + playernumber + " " + row + " " + column);
        } catch (Exception e) {
        }
    }

    public void println(String s) {
        if (s != null) {
         //   this.ui.taShow.setText(this.ui.taShow.getText() + s + "\n");
            System.out.println(s + "\n");
        }
    }

}

