package Games.Internet.GameClient;


import Games.utils.ClickType;
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
    int port = 8003;

    public Client() {
  //      this.ui = ui;
        try {
            String ip = "127.0.0.1"; //得到输入的ip地址
            //InetAddress ip = Inet4Address.getByName("chat.sustc.icu");
            //int port = Integer.parseInt(ui.tfPort.getText()); //得到输入的端口
            int port = 8003;
            client = new Socket(ip, port);//这里设置连接服务器端的IP的端口
            println("连接服务器 gamehall.sustc.icu 成功，服务器端口地址：" + port);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            String name = Data.Playername;
            sendMsg("getid"+" "+name);
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
            if (arr[0].equals("people")){
                if (arr[1].equals(Data.getPlayername())){
                    Player player1 = new Player(arr[2],2);
                    Data.setRival(player1);
                }
                else if (arr[2].equals(Data.getPlayername())){
                    Player player1 = new Player(arr[1],1);
                    Data.setRival(player1);
                }
            }
            if (arr[0].equals("id")){
                if (arr[2].equals(Data.getPlayername())){
                    int id = Integer.parseInt(arr[1]);
                    Player player1 = new Player(Data.getPlayername(),id);
                    Data.setPlayer(player1);
                }
            }
            if (arr[0].equals("play")){
                Data.setaChar(arr[2].charAt(0));//Todo:这里应该改成显示之类的，或者给一个扳机，触发ui重写
                Data.setRow(Integer.parseInt(arr[3]));
                Data.setColumn(Integer.parseInt(arr[4]));
                if (Integer.parseInt(arr[1])==1){
                    //player.mistake=Integer.parseInt(arr[5]);
                    //player.score=Integer.parseInt(arr[6]);
                   // Data.setPlayer(player);
                    Data.mistake1=Integer.parseInt(arr[5]);
                    Data.point1=Integer.parseInt(arr[6]);
                }
                else {
//                    Player player1 = Data.getRival();
//                    player1.mistake=Integer.parseInt(arr[5]);
//                    player1.score=Integer.parseInt(arr[6]);
//                    Data.setPlayer(player1);
                    Data.mistake2=Integer.parseInt(arr[5]);
                    Data.point2=Integer.parseInt(arr[6]);
                }
                Muti.update();
            }
            if (arr[0].equals("end")){
                if (Integer.parseInt(arr[1])==1) {
                    new Win();
                }
                else {
                    new Lose();
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

    public void gaming(int playernumber,int row,int column,ClickType clickType) {
        try {
            writer.println("play " + playernumber + " " + row + " " + column+" "+ clickType);
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

