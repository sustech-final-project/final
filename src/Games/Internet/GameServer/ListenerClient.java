package Games.Internet.GameServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

public class ListenerClient extends Thread{


    BufferedReader reader;
    PrintWriter writer;
    ServerUI ui;
    Socket client;
    Map map;

    public ListenerClient(ServerUI ui, Socket client) {
        this.ui = ui;
        this.client=client;
        this.start();
    //    startgame();
    }




    //为每一个客户端创建线程等待接收信息，然后把信息广播出去
    //msg的形式为
    @Override
    public void run() {
      //  startgame();
        String msg = "";
        map=Data.getMap();
        int id = Data.getId();
        String[] name1 = Data.getName1();
        while (true) {
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);
                msg = reader.readLine();
                String [] arr = msg.split("\\s+");//msg的格式为"play 玩家编号 点击位置"
                if (arr[0].equals("getid")){
                    sendMsg("id"+" "+id+" "+arr[1]);
                    name1[id]=arr[1];
                    if (id==1) id=2;
                    if (id==2) {
                        id = 1;
                        sendMsg("people"+" "+name1[1]+" "+name1[2]);
                     //   startgame();
                    }
                    Data.setName1(name1);
                }
                if (arr[0].equals("play")){
                    int row = Integer.parseInt(arr[2]);
                    int column = Integer.parseInt(arr[3]);
                    String clickType = arr[4];
                    System.out.println(msg);
                    System.out.println(Data.point1+" "+Data.point2);
                    if (Integer.parseInt(arr[1])==1){
                        judge1(row, column, clickType);
                    }
                    if (Integer.parseInt(arr[1])==2){
                        judge2(row, column, clickType);
                    }
                    boolean test=false;
                    for (int i=0;i<=15;i++){
                        for (int j=0;j<=15;j++){
                            if (Data.getHasClicked(i,j)==0) {
                                test=true;
                                break;
                            }
                        }
                    }
                    if (test==false){
                        if (Data.point1>Data.point2) sendMsg("end "+1);
                        else if (Data.point1<Data.point2) sendMsg("end "+2);
                        else sendMsg("end "+3);
                    }

                }
            } catch (IOException e) {
                println(e.toString());
                break;
            }
            if (msg != null && msg.trim() != "") {
                println("客户端 " + msg);
            }
        }
    }

    public void judge1(int row, int column, String clickType) {
        if (map.getMap(row, column) == '0'&&clickType.equals("LEFT_CLICK")) {
            kaizero(row, column, 1);
            Data.setPoint1(Data.point1 + 1);
        } else {
            Data.HasClicked(row, column);
            if (map.getMap(row, column) == 'M' && clickType.equals("LEFT_CLICK")) {
                Data.setMistake1(Data.mistake1 + 1);
                Data.setPoint1(Data.point1 - 1);
                sendMsg("play " + 1 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
            }
            if (map.getMap(row, column) == 'M' && clickType.equals("RIGHT_CLICK")) {
                //Data.setMistake1(Data.mistake1+1);
                Data.setPoint1(Data.point1 + 1);
                sendMsg("play " + 1 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
            }
            if (map.getMap(row, column) != 'M' && clickType.equals("LEFT_CLICK")) {
                //Data.setMistake1(Data.mistake1+1);
                Data.setPoint1(Data.point1 + 1);
                sendMsg("play " + 1 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
            }
            if (map.getMap(row, column) != 'M' && clickType.equals("RIGHT_CLICK")) {
                Data.setMistake1(Data.mistake1+1);
                Data.setPoint1(Data.point1 + 1);
                sendMsg("play " + 1 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
            }
        }
    }
    public void judge2(int row, int column, String clickType) {
        if (map.getMap(row,column)=='0'&&clickType.equals("LEFT_CLICK")){
            kaizero(row,column,2);
            Data.setPoint2(Data.point2 + 1);
        }
        else {
            Data.HasClicked(row, column);
            if (map.getMap(row, column) == 'M' && clickType.equals("LEFT_CLICK")) {
                Data.setMistake2(Data.mistake2 + 1);
                Data.setPoint2(Data.point2 - 1);
               // System.out.println(map.getMap(row, column));
                sendMsg("play " + 2 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake2 + " " + Data.point2);
            }
            if (map.getMap(row, column) == 'M' && clickType.equals("RIGHT_CLICK")) {
                //Data.setMistake1(Data.mistake1+1);
                Data.setPoint2(Data.point2 + 1);
                sendMsg("play " + 2 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake2 + " " + Data.point2);
               // System.out.println("play " + 2 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
            }
            if (map.getMap(row, column) != 'M' && clickType.equals("LEFT_CLICK")) {
                //Data.setMistake1(Data.mistake1+1);
                Data.setPoint2(Data.point2 + 1);
                sendMsg("play " + 2 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake2 + " " + Data.point2);
            }
            if (map.getMap(row, column) != 'M' && clickType.equals("RIGHT_CLICK")) {
                Data.setMistake2(Data.mistake2+1);
                Data.setPoint2(Data.point2 + 1);
                sendMsg("play " + 2 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake2 + " " + Data.point2);
            }
        }
    }

    public void kaizero(int row,int column,int playerid) {
        if (row <= 15 && column <= 15&&row>=0&&column>=0) {
            if (map.getMap(row, column) == '0' && Data.getHasClicked(row, column) == 0) {
                Data.HasClicked(row, column);
                kaizero(row - 1, column, playerid);
                kaizero(row, column + 1, playerid);
                kaizero(row, column - 1, playerid);
                kaizero(row + 1, column, playerid);
                if (playerid == 1) {
                    sendMsg("play " + 1 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
                }
                if (playerid == 2) {
                    sendMsg("play " + 2 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake2 + " " + Data.point2);
                }
            }
            if (map.getMap(row, column) != 'M' && Data.getHasClicked(row, column) == 0) {
                Data.HasClicked(row, column);
                if (playerid == 1) {
                    sendMsg("play " + 1 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake1 + " " + Data.point1);
                }
                if (playerid == 2) {
                    sendMsg("play " + 2 + " " + map.getMap(row, column) + " " + row + " " + column + " " + Data.mistake2 + " " + Data.point2);
                }
            }
        }
    }

    //把信息广播到所有用户
    public synchronized void sendMsg(String msg) {
        System.out.println(msg);
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

