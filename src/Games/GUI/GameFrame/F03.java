package Games.GUI.GameFrame;

import Games.Map.Map;
import Games.Map.Timer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class F03 extends JFrame {

    boolean[][] tem;

    static int order = 0;
    Map map;
    MouseListener[][] listeners;
    Button[][] buttons;
    JPanel[] panels;
    JPanel timerPanel = new Timer(new JPanel()).getPanel1();
    JPanel board = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP, 1, 1));

    public F03(String title, Map map1, String [] playerList) {
        super(title);

        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        Layout layout = new Layout();
        contentPane.setLayout(layout);
        map = map1;
        buttons = new Button[map.getRow()][map.getColumn()];
        listeners = new MouseListener[map.getRow()][map.getColumn()];
        panels = new JPanel[map.getRow()];
        for (int i = 0; i < map.getRow(); i++) {
            panels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
            for (int j = 0; j < map.getColumn(); j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setFocusable(false);
                listeners[i][j] = new MouseListener();
                buttons[i][j].setPreferredSize(new Dimension(41, 41));
                buttons[i][j].addMouseListener(listeners[i][j]);
                panels[i].add(buttons[i][j]);
            }
            board.add(panels[i]);
        }
        contentPane.add(timerPanel);
        contentPane.add(board);

        //
        tem = new boolean[map.getRow()][map.getRow()];
        for (int i = 0; i < tem.length; i++) {
            for (int j = 0; j < tem[0].length; j++) {
                tem[i][j] = false;
            }
        }
        //


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


    private class Layout implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();
            if (board.isVisible()) {
                board.setBounds(0, 0, 43 * map.getRow(), 44 * map.getColumn());
            }

            if (timerPanel.isVisible()){
                Dimension size = timerPanel.getPreferredSize();
                timerPanel.setBounds(43 * map.getRow(),0,size.width * 2,size.height);
            }


        }

        @Override
        public void addLayoutComponent(String name, Component comp) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return null;
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return null;
        }
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
            if (order == 0) map.createMap(r, c);
            if (!buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON1) {
                left(r, c);
            } else if (!buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3){
                buttons[r][c].setLabel("F");
            }
            else if (buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3){
                buttons[r][c].setLabel("");
            }
            order++;
        }
    }

    private void left(int r, int c) {
        buttons[r][c].setLabel("" + map.getMap(r, c));
        buttons[r][c].setEnabled(false);
        tem[r][c] = true;
        if (map.getMap(r, c) == '0'){
            if (has(r - 1, c +1)) try {left(r - 1, c +1);} catch (Exception ignored) {};
            if (has(r - 1, c)) try {left(r - 1, c);} catch (Exception ignored) {};
            if (has(r - 1, c - 1)) try {left(r - 1, c - 1);} catch (Exception ignored) {};
            if (has(r, c + 1)) try {left(r, c + 1);} catch (Exception ignored) {};
            if (has(r, c)) try {left(r, c);} catch (Exception ignored) {};
            if (has(r, c - 1)) try {left(r, c - 1);} catch (Exception ignored) {};
            if (has(r + 1, c + 1)) try {left(r + 1, c + 1);} catch (Exception ignored) {};
            if (has(r + 1, c)) try {left(r + 1, c);} catch (Exception ignored) {};
            if (has(r + 1, c - 1)) try {left(r + 1, c - 1);} catch (Exception ignored) {};
        }
    }

    private boolean has(int r, int c) {
        try {
            return !tem[r][c];
        } catch (Exception e) {
            return false;
        }
    }

}


