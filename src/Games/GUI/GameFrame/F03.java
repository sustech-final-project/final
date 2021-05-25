package Games.GUI.GameFrame;

import Games.GUI.GameFrame.layout.AfXLayout;
import Games.GUI.GameFrame.layout.AfYLayout;

import Games.Map.Data;
import Games.Map.Player;
import Games.Map.Timer;
import Games.components.CountDown;
import Games.components.Music;
import Games.components.pic;
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
    Boolean hasgone = false;
    static int order = 0;
    static GameController gc;
    static char[][] map;


    //游戏面板组件
    static ArrayList<MouseListener> listeners = new ArrayList<>();
    static ArrayList<JButton> buttons = new ArrayList<>();
    static ArrayList<JPanel> imagePanel = new ArrayList<>();
    static ArrayList<JLabel> labels = new ArrayList<>();
    static ArrayList<JPanel> upperPanel = new ArrayList<>();
    static ArrayList<JPanel> cardContainer = new ArrayList<>();
    static ArrayList<CardLayout> layouts = new ArrayList<>();
    static JPanel board = new JPanel(new GridLayout());
    ImageIcon timer1 = pic.getTimer();
    //ImageIcon Player1 = pic.getPlayer();

    //其他组件
    static JLabel[] playerInf;
    JPanel timerPanel = new Timer(new JPanel()).getPanel1();
    //CountDown timer = new CountDown();
   static ArrayList<Player> players = new ArrayList<>();
   JLabel mi = new JLabel();

   public static void clear() {

       //游戏面板组件
 listeners = new ArrayList<>();
        buttons = new ArrayList<>();
       imagePanel = new ArrayList<>();
       labels = new ArrayList<>();
       upperPanel = new ArrayList<>();
       cardContainer = new ArrayList<>();
       layouts = new ArrayList<>();
       board = new JPanel(new GridLayout());

       //其他组件
       playerInf = new JLabel[0];
       JPanel timerPanel = new Timer(new JPanel()).getPanel1();
       //CountDown timer = new CountDown();
       players = new ArrayList<>();
   }

    public F03() {}
    public F03(String title, GameController gc) {
        //初始化参数
        super(title);
        timer1.setImage(timer1.getImage().getScaledInstance(294,108,Image.SCALE_DEFAULT));
        //Player1.setImage(Player1.getImage().getScaledInstance(294,138,Image.SCALE_DEFAULT));
        F03.gc = gc;
        order = gc.getOrder();
        map = gc.getMap();

        Container contentPane = getContentPane();
        contentPane.setLayout(new AfXLayout());
        JPanel mine = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon show1 = Pic.MINE.getIcon();
        show1.setImage(show1.getImage().getScaledInstance(70, 70,Image.SCALE_DEFAULT ));
        mine.add(new JLabel(show1));
        mine.add(mi = new JLabel("X " + Data.getMines()));

        //建立游戏面板
        board.setLayout(new GridLayout(map.length, map[0].length));
        for (int i = 0; i < map.length * map[0].length; i++) {
            //初始化
            //todo
            ImageIcon show = Pic.BLANK.getIcon();
            show.setImage(show.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
            buttons.add(new JButton(show));
            labels.add(new JLabel());
            layouts.add(new CardLayout());
            upperPanel.add(new JPanel(new GridLayout(1, 1)));
            imagePanel.add(new JPanel(new GridLayout(1, 1)));
            cardContainer.add(new JPanel(layouts.get(i)));


            //加入组件
            buttons.get(i).setSize(upperPanel.get(i).getSize());
            buttons.get(i).setEnabled(true);
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
            System.out.println(order);
            if (order != 0 && gc.isPrint(row, column)) {//Todo:修复读档的bug
                showNum(i);
            }
        }

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border border1 = BorderFactory.createLineBorder(Color.RED, 2);
        Border empty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        board.setBorder(border);
        timerPanel.setBorder(border);

        if (board.isVisible()) {
            Dimension size = board.getPreferredSize();
            board.setBounds(0, 0, size.width, size.height);
        }

        if (timerPanel.isVisible()) {
            Dimension size = timerPanel.getPreferredSize();
            timerPanel.setBounds(70 * map.length, 0, size.width * 2, size.height);
        }
        timerPanel.add(new JLabel(timer1));
        contentPane.add(board, "70%");
        JPanel left = new JPanel(new AfYLayout());
        left.add(mi);
        left.add(timerPanel, "20%");
        JPanel score = new JPanel(new AfYLayout());
        score.setBorder(new LineBorder(Color.BLACK, 2));
        players = gc.getPlayers();
        playerInf = new JLabel[players.size()];
       // score.add(new JLabel(Player1));

        System.out.println(players.size());
        for (int i = 0; i < players.size(); i++) {
            score.add(playerInf[i] = new JLabel("<html><body>" + "Player:" + players.get(i).getName() + "<br>" + "Score:" + players.get(i).getScore() + "<br>Mistake:" + players.get(i).getMistake() + "<body></html>"), (int) (1 / (double) (players.size()) * 100) + "%");

        }
        playerInf[players.indexOf(gc.whoseTurn())].setBorder(border1);
        left.add(score, "1w");
        contentPane.add(left, "1w");


        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenu viewMenu = new JMenu("视图");
        JMenu helpMenu = new JMenu("帮助");

        JMenuItem save = new JMenuItem("保存");
        JMenuItem restart = new JMenuItem("新游戏");
        JMenuItem reLoad = new JMenuItem("载入");
        JMenuItem cheat = new JMenuItem("作弊模式");
        JMenuItem creator = new JMenuItem("制作人的话");
        JMenuItem about = new JMenuItem("关于...");
        JMenuItem music = new JMenuItem("关闭音乐");
        JMenu changeTheme = new JMenu("更换主题");

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
        //JMenuItem Theme3 = new JMenuItem("主题3");
        changeTheme.add(Theme1);
        changeTheme.add(Theme2);
        //changeTheme.add(Theme3);

        Theme1.addActionListener(i ->{
            Data.zhuti=1;
            //Todo 更新地图
        });
        Theme2.addActionListener(i ->{
            Data.zhuti=2;
            //TODO 更新地图
        });

        viewMenu.add(changeTheme);
        viewMenu.add(music);

        music.addActionListener(listener -> {

            if (music.getText().equals("关闭音乐")) {
                music.setText("开启音乐");
                Music.stopMusic();
            }
            else if (music.getText().equals("开启音乐")) {
                music.setText("关闭音乐");
                Music.playMusic("BGM");
            }
        });

        save.addActionListener(listener -> {
            gc.save();
        });

        restart.addActionListener(listener -> {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gc.clear();
                    clear();

                    f02();
                    dispose();
                }
            });
        });
        reLoad.addActionListener(listener -> {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gc.clear();
                    clear();
                    f01();
                }
            });
        });

        cheat.addActionListener(listener -> {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    //f00();
                    //Todo:修改作弊模式
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map.length; j++) {
                            int index = i * map[0].length + j;
                            JButton button = buttons.get(index);
                            button.doClick();
                            {
                                //    order = gc.getOrder();
                                //int index = listeners.indexOf(this);
                                int r = index / map[0].length;
                                int c = index % map[0].length;

                                //  if (order == 0) gc.createMap(r, c);
                                if (buttons.get(index).isVisible() && !gc.getChar(r, c).equals("M")) {
                                    left(r, c);
                                } else if (buttons.get(index).isVisible()) {
                                    ImageIcon show = Pic.FLAG.getIcon();
                                    Dimension size = cardContainer.get(index).getSize();
                                    show.setImage(show.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
                                    labels.get(index).setIcon(show);
                                    layouts.get(index).last(cardContainer.get(index));
                                }


                                buttons.get(index).setEnabled(false);
//                            if (gc.isEnd()) {
//                                dispose();
//                                SwingUtilities.invokeLater(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        f04();
//                                    }
//                                });
//                            }
                            }
                        }
                    }
                }
            });
        });
        creator.addActionListener(listener -> {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    f00();
                }
            });
        });
        about.addActionListener(listener -> {
            SwingUtilities.invokeLater(new Runnable() {
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
        show.setImage(show.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
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
            if (order == 0) {
                gc.createMap(r, c);
            }

            click(r, c, e.getButton());


            System.out.println(gc.whoseTurn().getLevel());
            AIClick();
            if (gc.isEnd() && !hasgone) {
                hasgone = true;
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

    public void AIClick() {
        while (gc.whoseTurn().getLevel() != 0 && !gc.isEnd()) {
            System.out.println("进入");
            String[] str = gc.whoseTurn().AiClick(gc.getMap()).split(" ");
            int row = Integer.parseInt(str[0]);
            int column = Integer.parseInt(str[1]);
            int type = Integer.parseInt(str[2]);
            click(row, column, type);
            Timer.resetTimer();
        }

    }

    private void click(int row, int column, int type) {
        int index = row * map[0].length + column;
        if (type == 0) return;
        if (buttons.get(index).isVisible() && type == MouseEvent.BUTTON1) {
            if (gc.getChar(row, column).equals("M")) {
                try {
                    Fgif.Tnt();
                }catch (Exception e){

                }

            }
        }
        if (buttons.get(index).isVisible() && type == MouseEvent.BUTTON3) {
            if (!gc.getChar(row, column).equals("M")) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            Thread thread = new Thread() {
//                                public void run() {
//                                    try {
//                                        Fgif.Chaqi();
//                                    } catch (InterruptedException exception) {
//                                        exception.printStackTrace();
//                                    }
//                                }
//                            };
                            Fgif.Chaqi();
                        } catch (Exception interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                });

            }
        }
        if (buttons.get(index).isVisible() && type == MouseEvent.BUTTON1) {
            left(row, column);
        }
        else if (buttons.get(index).isVisible() && type == MouseEvent.BUTTON3 && gc.getChar(row,column).equals("M") ) {
            ImageIcon show = Pic.FLAG.getIcon();
            Dimension size = cardContainer.get(index).getSize();
            show.setImage(show.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
            labels.get(index).setIcon(show);
            layouts.get(index).last(cardContainer.get(index));
        }
        else if (buttons.get(index).isVisible() && type == MouseEvent.BUTTON1 && gc.getChar(row,column).equals("M")){
            ImageIcon show = Pic.MINE.getIcon();
            Dimension size = cardContainer.get(index).getSize();
            show.setImage(show.getImage().getScaledInstance(size.width, size.height,Image.SCALE_DEFAULT ));
            labels.get(index).setIcon(show);
            layouts.get(index).last(cardContainer.get(index));
        }
        else {
            int index123 = row * map[0].length + column;
            showNum(index123);
        }
        buttons.get(index).setEnabled(false);
        gc.Click(row, column, type);
        for (int i = 0; i < players.size(); i++) {
            playerInf[i].setText("<html><body>" + "Player:" + players.get(i).getName() + "<br>" + "Score:" + players.get(i).getScore() + "<br>Mistake:" + players.get(i).getMistake() + "<body></html>");
        }
        Border border = BorderFactory.createLineBorder(Color.RED, 2);
        Border empty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        playerInf[(players.indexOf(gc.whoseTurn()) + players.size() - 1) % players.size()].setBorder(empty);
        playerInf[players.indexOf(gc.whoseTurn())].setBorder(border);

        int minenum = 0;
        for (int i=0;i< map.length;i++){
            for (int j=0;j<map[0].length;j++){
                if(Data.getHasClicked(i,j) == 0 && map[i][j] == 'M') minenum++;
            }
        }
        mi.setText("X " + minenum);

        if (gc.isEnd() && !hasgone) {
            hasgone = true;
            dispose();
            Timer.stop();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    f04();
                }
            });
        }
        Timer.resetTimer();


    }

    public static void resetBorder(){
        if (players.size()!=0) {
            Border border = BorderFactory.createLineBorder(Color.RED, 2);
            Border empty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
            playerInf[(players.indexOf(gc.whoseTurn()) + players.size() - 1) % players.size()].setBorder(empty);
            playerInf[players.indexOf(gc.whoseTurn())].setBorder(border);

            if (gc.whoseTurn().getLevel() != 0) {
                Connection.connect();
            }
        }
    }

    private void left(int r, int c) {
        int index = r * map[0].length + c;
        showNum(index);
        if (map[r][c] == '0') {
            if (!gc.isPrint(r - 1, c + 1)) try {
                left(r - 1, c + 1);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r - 1, c)) try {
                left(r - 1, c);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r - 1, c - 1)) try {
                left(r - 1, c - 1);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r, c + 1)) try {
                left(r, c + 1);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r, c)) try {
                left(r, c);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r, c - 1)) try {
                left(r, c - 1);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r + 1, c + 1)) try {
                left(r + 1, c + 1);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r + 1, c)) try {
                left(r + 1, c);
            } catch (Exception ignored) {
            }
            if (!gc.isPrint(r + 1, c - 1)) try {
                left(r + 1, c - 1);
            } catch (Exception ignored) {
            }
        }
    }


}


