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
import java.util.Arrays;

import static Games.GUI.GameFrame.MainLocal.*;


public class F03 extends JFrame {
    int order = 0;
    GameController gc;
    char[][] map;
//    MouseListener[][] listeners;
//    JButton[][] buttons;
    ArrayList<MouseListener> listeners = new ArrayList<>();
    ArrayList<JButton> buttons = new ArrayList<>();
    JPanel[] panels;
    JLabel[] playerInf;
    JPanel timerPanel = new Timer(new JPanel()).getPanel1();
    JPanel board = new JPanel();
    ArrayList<Player> players = new ArrayList<>();

    public F03(String title, GameController gc) {
        super(title);
            this.gc = gc;
            Container contentPane = getContentPane();
            contentPane.setLayout(new AfXLayout());
        {
            map = gc.getMap();
            System.out.println(Arrays.deepToString(map));
            board.setLayout(new GridLayout(map.length, map[0].length));
            panels = new JPanel[map.length];
            order = gc.getOrder();
            for (int i = 0; i < map.length * map[0].length; i++) {
                int row = i / map[0].length;
                int column = i % map[0].length;
                buttons.add(new JButton(""));
                listeners.add(new MouseListener());
                if (order != 0 && gc.isPrint(row, column)) {
                    buttons.get(i).setText(map[row][column] + "");
                    buttons.get(i).setEnabled(false);
                }
                buttons.get(i).setFocusable(false);
                buttons.get(i).addMouseListener(listeners.get(i));
                board.add(buttons.get(i));
            }
        }//建立board

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
            timerPanel.setBounds(43 * map.length,0,size.width * 2,size.height);
        }
        contentPane.add(board, "70%");
        JPanel left = new JPanel(new AfYLayout());
        left.add(timerPanel, "20%");
        JPanel score = new JPanel(new AfYLayout());
        score.setBorder(new LineBorder(Color.BLACK, 2));
        players = gc.getPlayers();
        playerInf = new JLabel[players.size()];

        for (int i = 0; i < players.size(); i++) {
            score.add(playerInf[i] = new JLabel("Player:" + players.get(i).getName() + "       Score:" + players.get(i).getScore() + "       Mistake:" + players.get(i).getMistake()), (int) (1 / (double) (players.size()) * 100) + "%" );
        }
        left.add(score, "30%");
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
                f00();
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


    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            order = gc.getOrder();
            int r = listeners.indexOf(this) / map[0].length;
            int c = listeners.indexOf(this) % map[0].length;

                if (order == 0) gc.createMap(r, c);

                if (!buttons.get(listeners.indexOf(this)).getText().equals("F") && e.getButton() == MouseEvent.BUTTON1) {
                    left(r, c);

                } else if (!buttons.get(listeners.indexOf(this)).getText().equals("F") && e.getButton() == MouseEvent.BUTTON3){
                    //buttons.get(listeners.indexOf(this)).setText("F");
                    ImageIcon Chaqi = new ImageIcon("src\\Games\\image\\方块插旗.jpg");
                    //Chaqi.setImage(Chaqi.getImage().getScaledInstance(10,10,Image.SCALE_DEFAULT));
                    buttons.get(listeners.indexOf(this)).setIcon(Chaqi);
                }

                buttons.get(listeners.indexOf(this)).setEnabled(false);
                gc.Click(r, c, e.getButton());
                for (int i = 0; i < players.size(); i++) {
                    playerInf[i].setText("Player:" + players.get(i).getName() + "       Score:" + players.get(i).getScore() + "       Mistake:" + players.get(i).getMistake());
                }
                if (gc.isEnd()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                            f04();
                        }
                    });
                }
        }
    }

    private void left(int r, int c) {
        ImageIcon show = null;
        ImageIcon num0 = new ImageIcon("src\\Games\\image\\空白.jpg");
        ImageIcon num1 = new ImageIcon("src\\Games\\image\\1.jpg");
        ImageIcon num2 = new ImageIcon("src\\Games\\image\\2.jpg");
        ImageIcon num3 = new ImageIcon("src\\Games\\image\\3.jpg");
        ImageIcon num4 = new ImageIcon("src\\Games\\image\\4.jpg");
        ImageIcon num5 = new ImageIcon("src\\Games\\image\\5.jpg");
        ImageIcon num6 = new ImageIcon("src\\Games\\image\\6.jpg");
        ImageIcon num7 = new ImageIcon("src\\Games\\image\\7.jpg");
        if (gc.getChar(r,c).equals("0")) show=num0;
        if (gc.getChar(r,c).equals("1")) show=num1;
        if (gc.getChar(r,c).equals("2")) show=num2;
        if (gc.getChar(r,c).equals("3")) show=num3;
        if (gc.getChar(r,c).equals("4")) show=num4;
        if (gc.getChar(r,c).equals("5")) show=num5;
        if (gc.getChar(r,c).equals("6")) show=num6;
        if (gc.getChar(r,c).equals("7")) show=num7;

        buttons.get(r * map[0].length + c).setIcon(show);
        buttons.get(r * map[0].length + c).setText(gc.getChar(r, c));
       // buttons.get(r * map[0].length + c).setEnabled(false);
        if (buttons.get(r * map[0].length + c).getText().equals("0")){
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


