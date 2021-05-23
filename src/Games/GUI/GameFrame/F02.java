package Games.GUI.GameFrame;

import Games.GUI.GameFrame.layout.AfXLayout;
import Games.GUI.GameFrame.layout.AfYLayout;
import Games.listener.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static Games.GUI.GameFrame.MainLocal.*;

public class F02 extends JFrame {
    GameController gc;
    String[] typeList = new String[]{"无","玩家", "简单人机", "中级人机", "高级人机"};
    String[] characteristicList = new String[]{"无", "角色1","角色2"};
    JPanel[] sub_1 = new JPanel[4];
    ArrayList<JComboBox<String>> type = new ArrayList<>();
    ArrayList<JComboBox<String>> characteristic = new ArrayList<>();
    ArrayList<JTextField> name = new ArrayList<>();
    JLabel row = new JLabel("行数：");
    JLabel column = new JLabel("列数：");
    JLabel mine = new JLabel("雷数：");
    JLabel turn = new JLabel("回合数：");
    JTextField nR = new JFormattedTextField("10");
    JTextField nC = new JFormattedTextField("10");
    JTextField nM = new JFormattedTextField("10");
    JTextField nT = new JFormattedTextField("3");
    JPanel LRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel LColumn = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel LMine = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel LTurn = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public F02(String title, GameController gc) {
        super(title);
        this.gc = gc;
        nR.setPreferredSize(new Dimension(40,20));
        nC.setPreferredSize(new Dimension(40,20));
        nM.setPreferredSize(new Dimension(40,20));
        nT.setPreferredSize(new Dimension(40,20));
        LRow.add(row);
        LRow.add(nR);
        LColumn.add(column);
        LColumn.add(nC);
        LMine.add(mine);
        LMine.add(nM);
        LTurn.add(turn);
        LTurn.add(nT);
        for (int i = 0; i < sub_1.length; i++) {
            sub_1[i] = new JPanel(new AfYLayout());
        }
        sub_1[0].add(new JLabel("玩家类型"), "20%");
        sub_1[1].add(new JLabel("昵称"), "20%");
        sub_1[2].add(new JLabel("人物"), "20%");
        sub_1[3].add(LRow, "20%");
        sub_1[3].add(LColumn, "20%");
        sub_1[3].add(LMine, "20%");
        sub_1[3].add(LTurn, "20%");
        JButton begin = new JButton("开始游戏");
        sub_1[3].add(begin, "20%");
        for (int i = 0; i < 4; i++) {
            type.add(new JComboBox<String>(typeList));
            type.get(i).setSelectedIndex(1);
            characteristic.add(new JComboBox<String>(characteristicList));
            name.add(new JTextField("请在此处输入昵称"));
            sub_1[0].add(type.get(i), "20%");
            sub_1[1].add(name.get(i), "20%");
            sub_1[2].add(characteristic.get(i), "20%");
        }
        JPanel main = new JPanel(new AfXLayout(10));
        for (JPanel panel : sub_1) {
            main.add(panel, "25%");
        }
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        main.setSize(1000,500);
        contentPane.add(main, BorderLayout.CENTER);

        type.forEach(ty -> {
            ty.addActionListener(listener -> {
                if (ty.getSelectedIndex() == 0) {
                    name.get(type.indexOf(ty)).setText("无");
                    name.get(type.indexOf(ty)).setEnabled(false);
                    characteristic.get(type.indexOf(ty)).setSelectedIndex(0);
                    characteristic.get(type.indexOf(ty)).setEnabled(false);
                } else {
                    name.get(type.indexOf(ty)).setEnabled(true);
                    characteristic.get(type.indexOf(ty)).setEnabled(true);
                }
            });
        });

        begin.addActionListener(listener -> {
            this.dispose();
            type.forEach(ty ->{
                if (!Objects.equals(ty.getSelectedItem(), "无")) gc.addPlayer(name.get(type.indexOf(ty)).getText(), (String) characteristic.get(type.indexOf(ty)).getSelectedItem());
            });

            int row = 0;
            int column = 0;
            int mine = 0;
            int turn = 0;
            try {
                row = Integer.parseInt(nR.getText());
                column = Integer.parseInt(nC.getText());
                mine = Integer.parseInt(nM.getText());
                turn = Integer.parseInt(nT.getText());
            } catch (Exception e1) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        exception();
                    }
                });
            }

            gc.setSize(row, column, mine);
            gc.setTurns(turn);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    f03();
                }
            });
        });
    }
}