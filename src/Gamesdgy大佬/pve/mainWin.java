package Gamesdgy大佬.pve;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class mainWin extends JFrame{
        private static mainWin m_instance;
        private static int X = 16;//8 * 8 10, 16 * 16 40, 30 * 16 99.
        private static int Y = 30;
        private static int N = 99;
        private static boolean result;
        private static int steps;

        JButton button_restart;
        JPanel panel_mainWin;
        JPanel panel_north;
        mainJPanel panel_block;
        boomInitialization block;
        JLabel label_station;
        JPanel panel_station;
        JButton button_robot;

        public static mainWin getInstance() {
            if(m_instance == null) {
                m_instance = new mainWin();
            }
            return m_instance;
        }
        public mainJPanel getPanel_block() {
            return this.panel_block;
        }
        public void setArrayX(int X) {
            this.X = X;
            restart();
        }
        public void setArrayY(int Y) {
            this.Y = Y;
            restart();
        }
        public void setBlockN(int N) {
            this.N = N;
            restart();
        }
        public int getArrayX() {
            return this.X;
        }
        public int getArrayY() {
            return this.Y;
        }
        public int getN(){
            return this.N;
        }
        private mainWin() {
            String UI = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            try {
                UIManager.setLookAndFeel(UI);
            } catch(Exception e){
                e.printStackTrace();
            }//设置整个面板显示格式
            block = new boomInitialization(X, Y, N);
            button_restart = new JButton("Restart");
            button_robot = new JButton("robot Go!!!");

            panel_block = new mainJPanel(block.Array, X, Y, N);
            panel_mainWin = new JPanel();
            panel_north = new JPanel();
            panel_north.setLayout(new GridLayout());
            panel_mainWin.setLayout(new BorderLayout());

            label_station = new JLabel();
            ImageIcon img = new ImageIcon("./src/img/lose.jpg");
            label_station.setIcon(img);
            label_station.setBorder(BorderFactory.createLoweredBevelBorder());

            panel_station = new JPanel();
            panel_station.setLayout(new CardLayout());
            panel_station.add(label_station, "card1");

            button_restart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == button_restart) {
                        restart();
                    }
                }
            });

            button_robot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == button_robot) {
                        Robotplayer hmc = new Robotplayer(X, Y);
                        m_instance.steps = hmc.getSteps();
                    }
                }
            });

            panel_north.add(button_restart);
            panel_north.add(button_robot);
            panel_mainWin.add(panel_block, BorderLayout.CENTER);
            panel_mainWin.add(panel_north ,BorderLayout.NORTH);
            this.add(panel_mainWin);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("hmc");
            this.setBounds(100, 100, 600, 360);
            this.setResizable(false);
            this.setVisible(true);
        }
        public void restart() {
            panel_block.clear();
            panel_mainWin.remove(panel_block);
            block = new boomInitialization(X, Y, N);
            panel_block = new mainJPanel(block.Array, X, Y, N);
            panel_block.first(X, Y);
            panel_mainWin.add(panel_block, BorderLayout.CENTER);
            panel_mainWin.revalidate();//刷新整个面板
        }
        public void robotgo() {
            Robotplayer hmc = new Robotplayer(X, Y);
            result = hmc.getResult();
            this.steps = hmc.getSteps();
        }
        public boolean getResult() {
            return this.result;
        }
        public int getSteps() {
            return this.steps;
        }
    }

