package Games.GUI.GameFrame;

import Games.GUI.GameFrame.layout.AfXLayout;
import Games.GUI.GameFrame.layout.AfYLayout;
import Games.Map.Player;
import Games.Map.Timer;
import Games.listener.GameController;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static Games.GUI.GameFrame.MainLocal.*;


public class F03 extends JFrame {
    //参数
    int order = 0;
    GameController gc;
    char[][] map;


    //游戏面板组件
    ArrayList<MouseListener> listeners = new ArrayList<>();
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<JPanel> imagePanel = new ArrayList<>();
    ArrayList<JLabel> labels = new ArrayList<>();
    ArrayList<JPanel> upperPanel = new ArrayList<>();
    ArrayList<JPanel> cardContainer  = new ArrayList<>();
    ArrayList<CardLayout> layouts= new ArrayList<>();
    JPanel board = new JPanel(new GridLayout());

    //其他组件
    JLabel[] playerInf;
    JPanel timerPanel = new Timer(new JPanel()).getPanel1();
    ArrayList<Player> players = new ArrayList<>();

    public F03(String title, GameController gc) {
        //初始化参数
        super(title);
        this.gc = gc;
        order = gc.getOrder();
        map = gc.getMap();

        Container contentPane = getContentPane();
        contentPane.setLayout(new AfXLayout());

        //建立游戏面板
        board.setLayout(new GridLayout(map.length, map[0].length));
        for (int i = 0; i < map.length * map[0].length; i++) {
            //初始化
            //todo
            ImageIcon show = Pic.BLANK.getIcon();
            show.setImage(show.getImage().getScaledInstance(70, 70,Image.SCALE_DEFAULT ));
            buttons.add(new JButton(show));
            labels.add(new JLabel());
            layouts.add(new CardLayout());
            upperPanel.add(new JPanel(new GridLayout(1,1)));
            imagePanel.add(new JPanel(new GridLayout(1,1)));
            cardContainer.add(new JPanel(layouts.get(i)));

            //加入组件
            buttons.get(i).setSize(upperPanel.get(i).getSize());
            upperPanel.get(i).add(buttons.get(i));
            imagePanel.get(i).add(labels.get(i));
            cardContainer.get(i).add(upperPanel.get(i));
            cardContainer.get(i).add(imagePanel.get(i));

            //监听
            listeners.add(new MouseListener());
            buttons.get(i).setFocusable(false);
            buttons.get(i).addMouseListener(listeners.get(i));

            //整体封装
            board.add(cardContainer.get(i));

            //如有存档初始化地图
            int row = i / map[0].length;
            int column = i % map[0].length;
            if (order != 0 && gc.isPrint(row, column)) {
                showNum(i);
            }
        }

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border empty = BorderFactory.createEmptyBorder(2,2,2,2);
        board.setBorder(border);
        timerPanel.setBorder(border);

        if (board.isVisible()) {
            Dimension size = board.getPreferredSize();
            board.setBounds(0, 0, size.width, size.height);
        }

        if (timerPanel.isVisible()){
            Dimension size = timerPanel.getPreferredSize();
            timerPanel.setBounds(70 * map.length,0,size.width * 2,size.height);
        }
        contentPane.add(board, "70%");
        JPanel left = new JPanel(new AfYLayout());
        left.add(timerPanel, "20%");
        JPanel score = new JPanel(new AfYLayout());
        score.setBorder(new LineBorder(Color.BLACK, 2));
        players = gc.getPlayers();
        playerInf = new JLabel[players.size()];

        for (int i = 0; i < players.size(); i++) {
            score.add(playerInf[i] = new JLabel("<html><body>" + "Player:" + players.get(i).getName() + "<br>" + "Score:" + players.get(i).getScore() + "<br>Mistake:" + players.get(i).getMistake() + "<body></html>"), (int) (1 / (double) (players.size()) * 100) + "%" );
        }
        left.add(score, "1w");
        contentPane.add(left, "1w");


        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenu viewMenu = new JMenu("视图");
        JMenu helpMenu = new JMenu("帮助");

        JMenuItem save        = new JMenuItem("保存");
        JMenuItem restart     = new JMenuItem("新游戏");
        JMenuItem reLoad      = new JMenuItem("载入");
        JMenuItem cheat       = new JMenuItem("作弊模式");
        JMenuItem creator     = new JMenuItem("制作人的话");
        JMenuItem about       = new JMenuItem("关于...");
        JMenuItem changeTheme = new JMenuItem("更换主题");

        fileMenu.add(save);
        fileMenu.add(reLoad);
        fileMenu.addSeparator();
        fileMenu.add(restart);
        helpMenu.add(cheat);
        helpMenu.addSeparator();
        helpMenu.add(creator);
        helpMenu.add(about);

        JMenuItem Theme1 = new JMenuItem("主题1");
        JMenuItem Theme2 = new JMenuItem("主题2");
        JMenuItem Theme3 = new JMenuItem("主题3");
        changeTheme.add(Theme1);
        changeTheme.add(Theme2);
        changeTheme.add(Theme3);

        viewMenu.add(changeTheme);

        save.addActionListener(listener ->{
            gc.save();
        });

        restart.addActionListener(listener ->{
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gc.clear();
                f02();
            }
        });
        });
        reLoad     .addActionListener(listener ->{
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gc.clear();
                f01();
            }
        });
        });

        cheat      .addActionListener(listener ->{
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f00();//Todo:修改
            }
        });
        });
        creator    .addActionListener(listener ->{
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f00();
            }
        });
        });
        about      .addActionListener(listener ->{
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f00();
            }
        });
        });

        bar.add(fileMenu);
        bar.add(viewMenu);
        bar.add(helpMenu);

        this.setJMenuBar(bar);
    }

    private void showNum(int index) {
        //todo 显示该位置的数字
        ImageIcon show = Pic.getIcon(gc.getChar(index / map[0].length, index % map[0].length));
        Dimension size = cardContainer.get(index).getSize();
        show.setImage(show.getImage().getScaledInstance(size.width, size.height,Image.SCALE_DEFAULT ));
        labels.get(index).setIcon(show);
        layouts.get(index).last(cardContainer.get(index));
    }


    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            order = gc.getOrder();
            int index = listeners.indexOf(this);
            int r = index / map[0].length;
            int c = index % map[0].length;

                if (order == 0) gc.createMap(r, c);

                if (buttons.get(index).isVisible() && e.getButton() == MouseEvent.BUTTON1) {
                    left(r, c);

                } else if (buttons.get(index).isVisible() && e.getButton() == MouseEvent.BUTTON3){
                    ImageIcon show = Pic.FLAG.getIcon();
                    Dimension size = cardContainer.get(index).getSize();
                    show.setImage(show.getImage().getScaledInstance(size.width, size.height,Image.SCALE_DEFAULT ));
                    labels.get(index).setIcon(show);
                    layouts.get(index).last(cardContainer.get(index));
                }

                buttons.get(index).setEnabled(false);
                gc.Click(r, c, e.getButton());
                for (int i = 0; i < players.size(); i++) {
                    playerInf[i].setText("<html><body>" + "Player:" + players.get(i).getName() + "<br>" + "Score:" + players.get(i).getScore() + "<br>Mistake:" + players.get(i).getMistake() + "<body></html>");
                }
                if (gc.isEnd()) {
                    dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            f04();
                        }
                    });
                }
        }
    }

    private void left(int r, int c) {
        int index = r * map[0].length + c;
        showNum(index);
        if (map[r][c] == '0'){
            if (!gc.isPrint(r - 1, c +1)) try {left(r - 1, c +1);} catch (Exception ignored) {}
            if (!gc.isPrint(r - 1, c)) try {left(r - 1, c);} catch (Exception ignored) {}
            if (!gc.isPrint(r - 1, c - 1)) try {left(r - 1, c - 1);} catch (Exception ignored) {}
            if (!gc.isPrint(r, c + 1)) try {left(r, c + 1);} catch (Exception ignored) {}
            if (!gc.isPrint(r, c)) try {left(r, c);} catch (Exception ignored) {}
            if (!gc.isPrint(r, c - 1)) try {left(r, c - 1);} catch (Exception ignored) {}
            if (!gc.isPrint(r + 1, c + 1)) try {left(r + 1, c + 1);} catch (Exception ignored) {}
            if (!gc.isPrint(r + 1, c)) try {left(r + 1, c);} catch (Exception ignored) {}
            if (!gc.isPrint(r + 1, c - 1)) try {left(r + 1, c - 1);} catch (Exception ignored) {}
        }
    }


}


