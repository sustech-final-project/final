package Games.Internet.GameServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.List;


public class ServerUI extends JFrame {



   //public JButton btStart;//启动服务器
    public JButton btSend;//发送信息按钮
    public JTextField tfSend;//需要发送的文本信息

    public JTextArea taShow;//信息展示
    public Server server;//用来监听客户端连接
    static List<Socket> clients;//保存连接到服务器的客户端

    public ServerUI() {
        super("服务器端");
        //btStart = new JButton("启动服务");
        btSend = new JButton("发送信息");
        tfSend = new JTextField(10); //装在输入文字
        taShow = new JTextArea();
        //点击按钮，所做的是事情，启动服务器
//        btStart.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                server = new Server(ServerUI.this);
//            }
//        });
        server = new Server(ServerUI.this);

        //点击发送消息按钮
        btSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                server.sendMsg(tfSend.getText());
                tfSend.setText("");
            }
        });
        //初始化界面
        this.addWindowListener(new WindowAdapter() {
            //关闭按钮点击事件
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
                        JOptionPane.YES_NO_OPTION);
                if (a == 1) {
                    server.closeServer();
                    System.exit(0); // 关闭
                }
            }
        });
        //底部启动服务按钮与发送消息按钮
        JPanel top = new JPanel(new FlowLayout());
        top.add(tfSend);
        top.add(btSend);
        //top.add(btStart);
        this.add(top, BorderLayout.SOUTH);
        //中部显示消息栏  信息展示
        final JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setViewportView(this.taShow);
        this.taShow.setEditable(false);
        this.add(sp, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocation(100, 200);
        this.setVisible(true);
    }


}
