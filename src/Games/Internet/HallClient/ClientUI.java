package Games.Internet.HallClient;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientUI extends JFrame {

//    public static void main(String[] args) {
//        new ClientUI();
//    }
    public String name;

    public JButton btStart;
    public JButton btSend;
    public JTextField tfSend; //装在输入文字
    public JTextPane nameText; //输入名字
    public JTextPane ipTex; //输入名字
    public JTextPane portText; //输入名字
    public JTextField tfName; //服务器ip
    //public JTextField tfIP; //服务器ip
    //public JTextField tfPort; //服务器端口
    public JTextArea taShow;
    public Client server;
    public String value;

    public ClientUI() {
        super("扫雷游戏大厅");
        btStart = new JButton("登录");
        btSend = new JButton("匹配一个旗鼓相当的对手");
        //tfSend = new JTextField(20);
//        tfIP = new JTextField(8);
//        tfPort = new JTextField(3);
        tfName = new JTextField(6);
        nameText = new JTextPane();
        nameText.setText("登录名");
        nameText.setEditable(false);
//        ipTex = new JTextPane();
//        ipTex.setText("服务地址");
//        ipTex.setEditable(false);
//        portText = new JTextPane();
//        //portText.setText("服务端口");
//        portText.setEditable(false);
        final ImageIcon imageIcon = new ImageIcon("src\\Games\\pic\\test2.png");
        taShow = new JTextArea(){
            Image image = imageIcon.getImage();

            Image grayImage = GrayFilter.createDisabledImage(image);
            {
                setOpaque(false);
            }
            public void paint(Graphics g) {
                g.drawImage(imageIcon.getImage(), 0, 0, this);
                super.paint(g);
            }
        };
        Font x = new Font("Serif",1,20);
        taShow.setFont(x);


//    };
//    JScrollPane scrollPane = new JScrollPane(textArea);
//    Container content = frame.getContentPane();
//    content.add(scrollPane, BorderLayout.CENTER);
//    frame.setSize(250, 250);
//    frame.setVisible(true);
//}

        //启动链接按钮事件
        btStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                server = new Client(ClientUI.this);
            }
        });
        //发送按钮事件
//        tfSend.addKeyListener(new KeyListener() {
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    btSend.doClick();
//                  //  value = tfSend.getText();
//                    // System.out.println("Enter " + value);
//                    //FindAction.getAction(value);
//                    // JTextField text;String value;都在之前定义好了
//                    // FindAction.getAction()方法，其中FindAction实现的ActionListener接口
//                    // getAction()方法是从actionPerformed(ActionEvent e)中抽象出来的
//                }
//                // System.out.println("Text " + value);
//            }
//            public void keyReleased(KeyEvent e) {
//            }
//            public void keyTyped(KeyEvent e) {
//            }
//        });

        btSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = tfName.getText();
                if (name == null || "".equals(name)) {
                    name = "匿名者";
                }
                server.sendMsg("over" +" " + name);
                //Todo:
                
               // tfSend.setText("");
                System.exit(0);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
                        JOptionPane.YES_NO_OPTION);
                if (a == 1) {
                    server.sendMsg("quit" + name);
                    System.exit(0); // 关闭
                }
            }
        });
        //底部的发送信息框与链接按钮
        JPanel top = new JPanel(new FlowLayout());
      //  top.add(tfSend); //发送文本
        top.add(btSend); //发送按钮
        this.add(top, BorderLayout.SOUTH); //加载到底部

        //头部放连接服务的
        JPanel northJpannel = new JPanel(new FlowLayout());
        northJpannel.add(nameText);
        northJpannel.add(tfName);
       // northJpannel.add(ipTex);
        //northJpannel.add(tfIP);
        //northJpannel.add(portText);
        //northJpannel.add(tfPort);
        northJpannel.add(btStart);
        this.add(northJpannel, BorderLayout.NORTH);  //加载到头部

        final JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setViewportView(this.taShow);
        this.taShow.setEditable(false);
        this.add(sp, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 1200);
        this.setLocation(600, 200);
        this.setVisible(true);
    }
}
