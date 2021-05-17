package Games.GUI.GameFrame;

import Games.GUI.GameFrame.layout.AfXLayout;
import Games.GUI.GameFrame.layout.AfYLayout;
import Games.Map.Timer;
import Games.listener.GameController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

import static Games.GUI.GameFrame.MainLocal.f03;
import static Games.GUI.GameFrame.MainLocal.f04;


public class F03 extends JFrame {
    int order = 0;
    GameController gc;
    char[][] map;
    MouseListener[][] listeners;
    Button[][] buttons;
    JPanel[] panels;
    JLabel[] playerInf;
    JPanel timerPanel = new Timer(new JPanel()).getPanel1();
    JPanel board = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP, 0, 0));
    String[] players;
    int[] scores;
    int[] mistakes;

    public F03(String title, GameController gc) {
        super(title);
            this.gc = gc;
            setLocationRelativeTo(null);
            Container contentPane = getContentPane();
            contentPane.setLayout(new AfXLayout());
        {
            map = gc.getMap();
            buttons = new Button[map.length][map[0].length];
            listeners = new MouseListener[map.length][map[0].length];
            panels = new JPanel[map.length];
            for (int i = 0; i < map.length; i++) {
                panels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                for (int j = 0; j < map[0].length; j++) {
                    buttons[i][j] = new Button("");
                    order = gc.getOrder();
                    if (order != 0 && gc.isPrint(i, j)) {
                        buttons[i][j].setLabel(map[i][j] + "");
                        buttons[i][j].setEnabled(false);
                    }
                    buttons[i][j].setFocusable(false);
                    listeners[i][j] = new MouseListener();
                    buttons[i][j].setPreferredSize(new Dimension(41, 41));
                    buttons[i][j].addMouseListener(listeners[i][j]);
                    panels[i].add(buttons[i][j]);
                }
                board.add(panels[i]);
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
        contentPane.add(board);
        JPanel left = new JPanel(new AfYLayout());
        left.add(timerPanel, "20%");
        JPanel score = new JPanel(new AfYLayout());
        score.setBorder(new LineBorder(Color.BLACK, 2));
        players = gc.getPlayers();
        playerInf = new JLabel[players.length];
        mistakes = gc.getMistakes();
        scores = gc.getScores();

        for (int i = 0; i < players.length; i++) {
            score.add(playerInf[i] = new JLabel("Player:" + players[i] + "       Score:" + scores[i] + "       Mistake:" + mistakes[i]), (int) (1 / (double) (players.length) * 100) + "%" );
        }
        left.add(score, "30%");
        contentPane.add(left, "1w");

    }


    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            order = gc.getOrder();
            int r = 0;
            int c = 0;
            for (int i = 0; i < listeners.length; i++) {
                for (int j = 0; j < listeners[0].length; j++) {
                    if (listeners[i][j].equals(this)) {
                        r = i;
                        c = j;
                    }
                }
            }
                if (order == 0) gc.createMap(r, c);

                if (!buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON1) {
                    left(r, c);

                } else if (!buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3){
                    buttons[r][c].setLabel("F");
                }
//                else if (buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3){
//                    buttons[r][c].setLabel("");
//                }
                buttons[r][c].setEnabled(false);
                gc.Click(r, c, e.getButton());
                for (int i = 0; i < players.length; i++) {
                    playerInf[i].setText("Player:" + players[i] + "       Score:" + scores[i] + "       Mistake:" + mistakes[i]);
                }
                playerInf[(gc.getOrder() / gc.getTurn()) % players.length].setBackground(Color.blue);
                playerInf[(gc.getOrder() / gc.getTurn()) % players.length].setBackground(null);

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
        buttons[r][c].setLabel(gc.getChar(r, c));
        buttons[r][c].setEnabled(false);
        if (buttons[r][c].getLabel().equals("0")){
            if (gc.isPrint(r - 1, c +1)) try {left(r - 1, c +1);} catch (Exception ignored) {}
            if (gc.isPrint(r - 1, c)) try {left(r - 1, c);} catch (Exception ignored) {}
            if (gc.isPrint(r - 1, c - 1)) try {left(r - 1, c - 1);} catch (Exception ignored) {}
            if (gc.isPrint(r, c + 1)) try {left(r, c + 1);} catch (Exception ignored) {}
            if (gc.isPrint(r, c)) try {left(r, c);} catch (Exception ignored) {}
            if (gc.isPrint(r, c - 1)) try {left(r, c - 1);} catch (Exception ignored) {}
            if (gc.isPrint(r + 1, c + 1)) try {left(r + 1, c + 1);} catch (Exception ignored) {}
            if (gc.isPrint(r + 1, c)) try {left(r + 1, c);} catch (Exception ignored) {}
            if (gc.isPrint(r + 1, c - 1)) try {left(r + 1, c - 1);} catch (Exception ignored) {}
        }
    }
}


