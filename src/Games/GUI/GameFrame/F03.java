package Games.GUI.GameFrame;

import Games.Map.Map;
import com.sun.javafx.scene.control.skin.VirtualFlow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class F03 extends JFrame {

    static int order = 0;
    Map map;
    MouseListener[][] listeners;
    Button[][] buttons;
    JPanel[] panels;
    JPanel board = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP, 1, 1));

    public F03(String title, Map map1) {
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
                listeners[i][j] = new MouseListener();
                buttons[i][j].setPreferredSize(new Dimension(41, 41));
                buttons[i][j].addMouseListener(listeners[i][j]);
                panels[i].add(buttons[i][j]);
            }
            board.add(panels[i]);
        }
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

    private class LayoutP implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
//            System.out.println(width + " " + height);
            for (JPanel panel : panels) {
                panel.setSize(width, 41);
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

    private class Layout implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

//            System.out.println(width + " " + height);

            if (board.isVisible()) {
//                Dimension size = board.getPreferredSize();
//                int x = (width - size.width)/2;
//                int y = (height - size.height) / 3;
                board.setBounds(0, 0, 500 * map.getRow(), 500 * map.getColumn());
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
                buttons[r][c].setLabel("" + map.getMap(r, c));
                buttons[r][c].setEnabled(false);
            } else if (!buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3) buttons[r][c].setLabel("F");
            else if (buttons[r][c].getLabel().equals("F") && e.getButton() == MouseEvent.BUTTON3) buttons[r][c].setLabel("");
            order++;
        }
    }


}

/*addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==e.BUTTON1){
					int x=e.getX();
					int y=e.getY();
					String str="您点击的是左键，鼠标当前点击位置的坐标是(" + x + "," + y+")";
					label.setText(str);
				}else if(e.getButton()==e.BUTTON2){
					int x=e.getX();
					int y=e.getY();
					String str="您点击的是滑轮，鼠标当前点击位置的坐标是(" + x + "," + y+")";
					label.setText(str);
				}
				else if(e.getButton()==e.BUTTON3){
					int x=e.getX();
					int y=e.getY();
					String str="您点击的是右键，鼠标当前点击位置的坐标是(" + x + "," + y+")";
					label.setText(str);
				}
			}
		});

*/