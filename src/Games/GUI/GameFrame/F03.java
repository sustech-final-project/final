package Games.GUI.GameFrame;

import Games.Map.Timer;
import Games.listener.GameController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;


public class F03 extends JFrame {
    int order = 0;
    GameController gc;
    char[][] map;
    MouseListener[][] listeners;
    Button[][] buttons;
    JPanel[] panels;
    JPanel timerPanel = new Timer(new JPanel()).getPanel1();
    JPanel board = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP, 0, 0));

    public F03(String title, GameController gc) {
        super(title);
            this.gc = gc;
            setLocationRelativeTo(null);
            Container contentPane = getContentPane();
            contentPane.setLayout(new Af);
        {
            map = gc.getMap();
            buttons = new Button[map.length][map[0].length];
            listeners = new MouseListener[map.length][map[0].length];
            panels = new JPanel[map.length];
            for (int i = 0; i < map.length; i++) {
                panels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                for (int j = 0; j < map[0].length; j++) {
                    buttons[i][j] = new Button("");
                    if (gc.isMapLoad() && gc.isPrint(i, j)) {
                        buttons[i][j].setLabel(map[i][j] + "");
                        buttons[i][j].setEnabled(false);
                        order = gc.getOrder();
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
        contentPane.add(timerPanel);
        contentPane.add(board);




//        buttonYes.addActionListener((l) -> {
//            this.dispose();
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
////                    f04(); 游戏开始
//                }
//            });
//        });

//        buttonNo.addActionListener((l) -> {
//            this.dispose();
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//
//                    f02();
//                }
//            });
//        });
    }


    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
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
            if (!gc.isMapLoad()){
                if (order == 0) gc.createMap(r, c);
                if (!buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON1) {
                    left(r, c);
                } else if (!buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3){
                    buttons[r][c].setLabel("F");
                }
                else if (buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3){
                    buttons[r][c].setLabel("");
                }
            }
            order++;
            gc.setOrder(order);
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


