package Games.components;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 作者：戴郭轶
 * 日期：2021.5.13
 * 本程序用于执行计时器代码
 */
public class CountDown {

    public static void main(String[] args) {
        new CountDown();
    }

    public CountDown() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private Timer timer;
        private long startTime = -1;
        private long duration = 30000;

        private JLabel label;
        private JLabel label2;
        private JLabel label3;

        public TestPane() {
            setLayout(new GridBagLayout());
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (startTime < 0) {
                        startTime = System.currentTimeMillis();
                    }
                    long now = System.currentTimeMillis();
                    long clockTime = now - startTime;
                    if (clockTime >= duration) {
                        clockTime = duration;
                        timer.stop();
                        //Todo:加入执行；
                    }
                    SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                    label.setText(df.format(duration - clockTime));
                }
            });
            //timer.setInitialDelay(0);
            label = new JLabel("...");
            label2 = new JLabel("您还有");
            label3 = new JLabel("秒");
            add(label2);
            add(label);
            add(label3);
            timer.start();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

    }

}
