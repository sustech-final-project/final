package Games.GUI.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Set;

public class paiming extends JFrame {
    public paiming(){
        super("单人模式排行榜");
        final ImageIcon imageIcon = new ImageIcon("src\\Games\\pic\\test2.png");
        JTextArea taShow = new JTextArea(){
            Image image = imageIcon.getImage();

            Image grayImage = GrayFilter.createDisabledImage(image);
            {
                setOpaque(false);
            }
            public void paint(Graphics g) {
                g.drawImage(imageIcon.getImage(), 0, 0, this);
                super.paint(g);
            }
        };
        Font x = new Font("Serif",1,40);
        taShow.setFont(x);
        taShow.setEditable(false);
        SoloData.readpaiming();
        Map<Double,String> paiming = SoloData.getPaiming();
        Set<Double> keySet = paiming.keySet();
        int i=1;
        taShow.setText("排名        玩家ID           用时");
        for (Double key : keySet) {
            taShow.setText(taShow.getText()+"\n"+i+"              "+paiming.get(key)+ "           " + key);
            i++;
        }
        //taShow.setText("abcd");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
                        JOptionPane.YES_NO_OPTION);
                if (a == 1) {
                    System.exit(0); // 关闭
                }
            }
        });
        final JScrollPane sp = new JScrollPane();
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setViewportView(taShow);
        this.add(sp, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 1200);
        this.setLocation(600, 200);
        this.setVisible(true);
    }
}
