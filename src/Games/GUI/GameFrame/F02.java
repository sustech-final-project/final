package Games.GUI.GameFrame;

import Games.Map.Map;

import javax.swing.*;
import java.awt.*;

import static Games.GUI.GameFrame.MainLocal.*;

public class F02 extends JFrame {
    JRadioButton easy = new JRadioButton("简单", true);
    JRadioButton middle = new JRadioButton("中等");
    JRadioButton hard = new JRadioButton("困难");
    JRadioButton other = new JRadioButton("自定义");
    ButtonGroup group = new ButtonGroup();
    JLabel row = new JLabel("行数：");
    JLabel column = new JLabel("列数：");
    JLabel mine = new JLabel("雷数：");
    JLabel player = new JLabel("游戏人数：");
    JTextField nR = new JFormattedTextField("100");
    JTextField nC = new JFormattedTextField("100");
    JTextField nM = new JFormattedTextField("30");
    JTextField nP = new JFormattedTextField("2");
    JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    int people = 0;


    JButton oK = new JButton("OK");

    public F02(String title) {
        super(title);
        Container contentPane = getContentPane();
        Layout layout = new Layout();
        contentPane.setLayout(layout);
        group.add(easy);
        group.add(middle);
        group.add(hard);
        group.add(other);
        contentPane.add(easy);
        contentPane.add(middle);
        contentPane.add(hard);
        contentPane.add(other);
        panel1.add(row);
        panel1.add(nR);
        panel2.add(column);
        panel2.add(nC);
        panel3.add(mine);
        panel3.add(nM);
        panel4.add(player);
        panel4.add(nP);
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);
        contentPane.add(panel4);
        contentPane.add(oK);
        nR.setEnabled(false);
        nC.setEnabled(false);
        nM.setEnabled(false);

        other.addActionListener((e) -> {
            nR.setEnabled(true);
            nC.setEnabled(true);
            nM.setEnabled(true);
        });
        easy.addActionListener((e) -> {
            nR.setEnabled(false);
            nC.setEnabled(false);
            nM.setEnabled(false);
        });
        middle.addActionListener((e) -> {
            nR.setEnabled(false);
            nC.setEnabled(false);
            nM.setEnabled(false);
        });
        hard.addActionListener((e) -> {
            nR.setEnabled(false);
            nC.setEnabled(false);
            nM.setEnabled(false);
        });
        oK.addActionListener((e -> {
            int row = 0;
            int column = 0;
            int mine = 0;

            int error = 0;
            try {
                people = Integer.parseInt(nP.getText());
            } catch (Exception e1) {
                error++;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        exception();
                    }
                });
            }
            if (other.isSelected()) {
                try {
                    row = Integer.parseInt(nR.getText());
                    column = Integer.parseInt(nC.getText());
                    mine = Integer.parseInt(nM.getText());
                } catch (Exception e1) {
                    error++;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            exception();
                        }
                    });
                }
            } else if (easy.isSelected()) {
                row = 9;
                column = 9;
                mine = 10;
            } else if (middle.isSelected()) {
                row = 16;
                column = 16;
                mine = 40;
            } else {
                row = 16;
                column = 33;
                mine = 99;
            }

            if (error == 0) {
                Map map = new Map(row, column, mine);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String[] playerList = new String[people];
                        for (int i = 0; i < playerList.length; i++) {
                            playerList[i] = JOptionPane.showInputDialog(this, "input here");
                        }
                        f03(new F03("扫雷", map, playerList));
                    }
                });
                this.dispose();
            }
        }));


    }


    private class Layout implements LayoutManager {

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();
//            System.out.println(width + " " + height);

            {
                Dimension size = easy.getPreferredSize();
                int x = (width - size.width) / 4;
                int y = (height - size.height) / 5;
                easy.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = middle.getPreferredSize();
                int x = 2 * (width - size.width) / 4;
                int y = (height - size.height) / 5;
                middle.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = hard.getPreferredSize();
                int x = 3 * (width - size.width) / 4;
                int y = (height - size.height) / 5;
                hard.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = other.getPreferredSize();
                int x = (width - size.width) / 4;
                int y = 2 * (height - size.height) / 5;
                other.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = panel1.getPreferredSize();
                int x = (width - size.width) / 4;
                int y = 3 * (height - size.height) / 5;
                panel1.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = panel2.getPreferredSize();
                int x = 2 * (width - size.width) / 4;
                int y = 3 * (height - size.height) / 5;
                panel2.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = panel3.getPreferredSize();
                int x = 3 * (width - size.width) / 4;
                int y = 3 * (height - size.height) / 5;
                panel3.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = oK.getPreferredSize();
                int x = 3 * (width - size.width) / 4;
                int y = 4 * (height - size.height) / 5;
                oK.setBounds(x, y, size.width, size.height);
            }
            {
                Dimension size = panel4.getPreferredSize();
                int x = (width - size.width) / 4;
                int y = 4 * (height - size.height) / 5;
                panel4.setBounds(x, y, size.width, size.height);
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

}