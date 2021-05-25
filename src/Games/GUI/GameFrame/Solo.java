package Games.GUI.GameFrame;

import Games.GUI.GameFrame.layout.AfXLayout;
import Games.GUI.GameFrame.layout.AfYLayout;
import Games.Map.Data;
import Games.Map.Player;
import Games.Map.Timer;
import Games.listener.GameController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Games.GUI.GameFrame.MainLocal.*;




    public class Solo extends JFrame {
        //参数
        int order = 0;
        GameController gc;
        char[][] map;
        int heartNum = 3;
        int DunNum = 0;


        //游戏面板组件
        ArrayList<MouseListener> listeners = new ArrayList<>();
        ArrayList<JButton> buttons = new ArrayList<>();
        ArrayList<JPanel> imagePanel = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JPanel> upperPanel = new ArrayList<>();
        ArrayList<JPanel> cardContainer  = new ArrayList<>();
        ArrayList<CardLayout> layouts= new ArrayList<>();
        JPanel board = new JPanel(new GridLayout());
        ArrayList<JLabel> tool = new ArrayList<>();

        //其他组件
        JLabel[] playerInf;
        //JPanel timerPanel = new Timer(new JPanel()).getPanel1();
        ArrayList<Player> players = new ArrayList<>();

        public Solo(String title, GameController gc) {
            //初始化参数
            super(title);
            this.gc = gc;
            order = gc.getOrder();
            map = gc.getMap();
            Data.initializeData(map.length,map[0].length);

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
                System.out.println(order);
                if (order != 0 && gc.isPrint(row, column)) {
                    showNum(i);
                }
            }

            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            Border border1 = BorderFactory.createLineBorder(Color.RED, 2);
            Border empty = BorderFactory.createEmptyBorder(2,2,2,2);
            board.setBorder(border);
            //timerPanel.setBorder(border);

            if (board.isVisible()) {
                Dimension size = board.getPreferredSize();
                board.setBounds(0, 0, size.width, size.height);
            }

//            if (timerPanel.isVisible()){
//                Dimension size = timerPanel.getPreferredSize();
//                timerPanel.setBounds(70 * map.length,0,size.width * 2,size.height);
//            }
            contentPane.add(board, "70%");
            JPanel left = new JPanel(new AfYLayout());
//            left.add(timerPanel, "20%");
            JPanel score = new JPanel(new AfYLayout());
            JPanel heart = new JPanel(new FlowLayout());
            JPanel dun = new JPanel(new FlowLayout());
            for (int i = 0; i < 3; i++) {
                ImageIcon h = Pic.XIN.getIcon();
                ImageIcon d = Pic.DUN.getIcon();
                h.setImage(h.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
                d.setImage(d.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT ));
                tool.add(new JLabel(h));
                heart.add(tool.get(2 * i));
                tool.add(new JLabel(d));
                dun.add(tool.get(2 * i + 1));
            }
            score.setBorder(new LineBorder(Color.BLACK, 2));
            players = gc.getPlayers();
            playerInf = new JLabel[players.size()];

            //System.out.println(players.size());
            for (int i = 0; i < players.size(); i++) {
                score.add(playerInf[i] = new JLabel("<html><body>" + "Player:" + players.get(i).getName() + "<br>" + "Score:" + players.get(i).getScore() + "<br>Mistake:" + players.get(i).getMistake() + "<body></html>"), (int) (1 / (double) (players.size()) * 100) + "%" );
            }
            playerInf[players.indexOf(gc.whoseTurn())].setBorder(border1);
            left.add(heart, "10%");
            left.add(dun, "10%");
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
                        dispose();
                    }
                });
            });
            reLoad     .addActionListener(listener ->{
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        gc.clear();
                        f01();
                        dispose();
                    }
                });
            });

            cheat      .addActionListener(listener ->{
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //f00();
                        //Todo:修改作弊模式
                        for (int i=0;i<map.length;i++){
                            for (int j=0;j<map.length;j++){
                                int index = i*map[0].length+j;
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
                                    } else if (buttons.get(index).isVisible()){
                                        ImageIcon show = Pic.FLAG.getIcon();
                                        Dimension size = cardContainer.get(index).getSize();
                                        show.setImage(show.getImage().getScaledInstance(size.width, size.height,Image.SCALE_DEFAULT ));
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
            });  //作弊模式
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

            if (heartNum == 1){
                tool.get(0).setVisible(true);
                tool.get(2).setVisible(false);
                tool.get(4).setVisible(false);
            } else if (heartNum == 2){
                tool.get(0).setVisible(true);
                tool.get(2).setVisible(true);
                tool.get(4).setVisible(false);
            } else if (heartNum == 3) {
                tool.get(0).setVisible(true);
                tool.get(2).setVisible(true);
                tool.get(4).setVisible(true);
            }
            if (DunNum == 0){
                tool.get(1).setVisible(false);
                tool.get(3).setVisible(false);
                tool.get(5).setVisible(false);
            }
            else if (DunNum == 1){
                tool.get(1).setVisible(true);
                tool.get(3).setVisible(false);
                tool.get(5).setVisible(false);
            } else if (DunNum == 2){
                tool.get(1).setVisible(true);
                tool.get(3).setVisible(true);
                tool.get(5).setVisible(false);
            } else if (DunNum == 3) {
                tool.get(1).setVisible(true);
                tool.get(3).setVisible(true);
                tool.get(5).setVisible(true);
            }
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
                int index = listeners.indexOf(this);
                int r = index / map[0].length;
                int c = index % map[0].length;
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                Border empty = BorderFactory.createEmptyBorder(2,2,2,2);
                System.out.println(order);
                if (order == 0){
                    gc.createMap(r, c);
                }
                if (heartNum < 4 && Data.getTool(r,c) == 1){
                    heartNum++;
                    System.out.println("heart++");
                }
                if (DunNum < 4 && Data.getTool(r,c) == 2){
                    DunNum++;
                    System.out.println("DUN++");
                }

                if (buttons.get(index).isVisible() && e.getButton() == MouseEvent.BUTTON1){
                    if (gc.getChar(r,c).equals("M")) {
                        if(DunNum > 0){
                            DunNum--;
                        } else if (heartNum > 0) {
                            heartNum--;
                        } else {
                            //TODO 结束游戏
                            dispose();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    f04();
                                }
                            });
                        }
                        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        //  @Override
                        // public void run() {
                        try {
                            Fgif.Tnt();
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                        //                                  }
                        // });
                    }
                }
                if (buttons.get(index).isVisible() && e.getButton() == MouseEvent.BUTTON3){
                    if (!gc.getChar(r,c).equals("M")) {
                        try {
                            Fgif.Chaqi();
                        }catch (Exception exception){

                        }
                    }
                }

                if (buttons.get(index).isVisible() && e.getButton() == MouseEvent.BUTTON1) {
                    left(r, c);
                } else if (buttons.get(index).isVisible() && e.getButton() == MouseEvent.BUTTON3&& gc.getChar(r,c).equals("M")){
                    ImageIcon show = Pic.FLAG.getIcon();
                    Dimension size = cardContainer.get(index).getSize();
                    show.setImage(show.getImage().getScaledInstance(size.width, size.height,Image.SCALE_DEFAULT ));
                    labels.get(index).setIcon(show);
                    layouts.get(index).last(cardContainer.get(index));
                }else {
                    ImageIcon show = Pic.MINE.getIcon();
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
                playerInf[(players.indexOf(gc.whoseTurn()) + players.size() - 1) % players.size()].setBorder(empty);
                playerInf[players.indexOf(gc.whoseTurn())].setBorder(border);

//            try{
//                String move = gc.whoseTurn().Aiclick
//            }catch ()
                if (heartNum == 1){
                    tool.get(0).setVisible(true);
                    tool.get(2).setVisible(false);
                    tool.get(4).setVisible(false);
                } else if (heartNum == 2){
                    tool.get(0).setVisible(true);
                    tool.get(2).setVisible(true);
                    tool.get(4).setVisible(false);
                } else if (heartNum == 3) {
                    tool.get(0).setVisible(true);
                    tool.get(2).setVisible(true);
                    tool.get(4).setVisible(true);
                }
                if (DunNum == 0){
                    tool.get(1).setVisible(false);
                    tool.get(3).setVisible(false);
                    tool.get(5).setVisible(false);
                }
                else if (DunNum == 1){
                    tool.get(1).setVisible(true);
                    tool.get(3).setVisible(false);
                    tool.get(5).setVisible(false);
                } else if (DunNum == 2){
                    tool.get(1).setVisible(true);
                    tool.get(3).setVisible(true);
                    tool.get(5).setVisible(false);
                } else if (DunNum == 3) {
                    tool.get(1).setVisible(true);
                    tool.get(3).setVisible(true);
                    tool.get(5).setVisible(true);
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