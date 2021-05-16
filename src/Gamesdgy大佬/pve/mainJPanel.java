package Gamesdgy大佬.pve;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class mainJPanel extends JPanel{
        private JPanel[][] m_panel;
        private JLabel[][] m_label;
        private JButton[][] m_button;
        private JLabel[][] m_flag;
        private JLabel[][] m_correctFlag;
        private int X;
        private int Y;
        private int blockNum;
        private boolean play;
        private Station[][] Array;
        private Station[][] currentArray;
        private static boolean result;

        public mainJPanel(Station[][] Array, int X, int Y,  int N) {
            this.X = X;
            this.Y = Y;
            this.Array = Array;
            this.blockNum = N;
            this.currentArray = new Station[X + 1][Y + 1];
            this.play = true;

            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    this.currentArray[i][j] = Station.unknown;
                }
            }
            this.setLayout(new GridLayout(X, Y, 0, 0));
            m_panel = new JPanel[X + 1][Y + 1];
            m_label = new JLabel[X + 1][Y + 1];
            m_button = new JButton[X + 1][Y + 1];
            m_flag = new JLabel[X + 1][Y + 1];
            m_correctFlag = new JLabel[X + 1][Y + 1];

            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    m_panel[i][j] = new JPanel();
                    m_panel[i][j].setLayout(new CardLayout());
                    m_button[i][j] = new JButton();
                    m_button[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
                    m_panel[i][j].add(m_button[i][j], "card1");

                    m_button[i][j].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for(int i = 1; i <= X; ++i) {
                                for(int j = 1; j <= Y; ++j) {
                                    if(e.getSource() == m_button[i][j]) {
                                        if(e.getButton() == MouseEvent.BUTTON1) {
                                            solveLeftButtonEvents(i, j);
                                        }
                                        if(e.getButton() == MouseEvent.BUTTON3) {
                                            solveRightButtonEvents(i, j);
                                        }
                                    }
                                }
                            }
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseExited(MouseEvent e) {}
                        @Override
                        public void mousePressed(MouseEvent e) {}
                        @Override
                        public void mouseReleased(MouseEvent arg0) {}
                    });

                    m_label[i][j] = new JLabel();
                    m_label[i][j].setBorder(BorderFactory.createLoweredBevelBorder());//设置JLabel显示为凹下去，便于美观
                    m_label[i][j].setFont(new java.awt.Font("微软雅黑", 1, 14));


                    m_flag[i][j] = new JLabel();
                    m_flag[i][j].setBorder(BorderFactory.createLoweredBevelBorder());
                    ImageIcon img = new ImageIcon("./src/img/flag.jpg");
                    m_flag[i][j].setIcon(img);

                    m_correctFlag[i][j] = new JLabel();
                    m_correctFlag[i][j].setBorder(BorderFactory.createLoweredBevelBorder());
                    img = new ImageIcon("./src/img/flag2.jpg");
                    m_correctFlag[i][j].setIcon(img);

                    m_flag[i][j].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            for(int i = 1; i <= X; ++i) {
                                for(int j = 1; j <= Y; ++j) {
                                    if(e.getSource() == m_flag[i][j]) {
                                        if(e.getButton() == MouseEvent.BUTTON3) {
                                            solveRightButtonEvents(i, j);
                                        }
                                    }
                                }
                            }
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseExited(MouseEvent e) {}
                        @Override
                        public void mousePressed(MouseEvent e) {}
                        @Override
                        public void mouseReleased(MouseEvent e) {}
                    });
                    if(Array[i][j] == Station.block){
                        img = new ImageIcon("./src/img/boom.jpg");
                        m_label[i][j].setIcon(img);
                    }//若为雷则显示地雷图片
                    else if(Array[i][j] == Station.zero){
                        m_label[i][j].setText("");
                    }
                    else{
                        switch(Array[i][j]) {
                            default:
                                System.out.println("Error");
                                break;
                            case one:
                                m_label[i][j].setForeground(Color.BLUE);
                                m_label[i][j].setText("1");
                                break;
                            case two:
                                m_label[i][j].setForeground(Color.ORANGE);
                                m_label[i][j].setText("2");
                                break;
                            case three:
                                m_label[i][j].setForeground(Color.RED);
                                m_label[i][j].setText("3");
                                break;
                            case four:
                                m_label[i][j].setForeground(Color.cyan);
                                m_label[i][j].setText("4");
                                break;
                            case five:
                                m_label[i][j].setForeground(Color.BLACK);
                                m_label[i][j].setText("5");
                                break;
                            case six:
                                m_label[i][j].setForeground(Color.MAGENTA);
                                m_label[i][j].setText("6");
                                break;
                            case seven:
                                m_label[i][j].setForeground(Color.DARK_GRAY);
                                m_label[i][j].setText("7");
                                break;
                        }
                    }//若为周围雷的个数则设置每个数字的颜色不同
                    m_panel[i][j].add(m_label[i][j], "card2");
                    m_panel[i][j].add(m_flag[i][j], "card3");
                    m_panel[i][j].add(m_correctFlag[i][j], "card4");
                    this.add(m_panel[i][j]);
                }
            }
        }

        public void solveLeftButtonEvents(int x, int y) {
            if(Array[x][y] == Station.block) {
                System.out.println("you lose");
                result = false;
                gameOver();
                return;
            }
            else if(Array[x][y] == Station.zero){
                CardLayout clayout = (CardLayout) m_panel[x][y].getLayout();
                clayout.show(m_panel[x][y], "card2");
                dfsForAutoClick(x, y);
            }
            else {
                CardLayout clayout = (CardLayout) m_panel[x][y].getLayout();
                clayout.show(m_panel[x][y], "card2");
                currentArray[x][y] = Array[x][y];
            }
            check();
        }

        public int check() {
            int currentVisited = 0;
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    if(currentArray[i][j] != Station.unknown && currentArray[i][j] != Station.block) {
                        currentVisited++;
                    }
                }
            }
            if(currentVisited == X * Y - blockNum) {
                System.out.println("you win");
                result = true;
                this.play = false;
            }
            return currentVisited;
        }

        public void solveDFSpoint(int x, int y) {
            if(currentArray[x][y] != Station.unknown) {
                return;
            }
            if(Array[x][y] == Station.zero) {
                CardLayout clayout = (CardLayout) m_panel[x][y].getLayout();
                clayout.show(m_panel[x][y], "card2");
                dfsForAutoClick(x, y);
            }
            else {
                CardLayout clayout = (CardLayout) m_panel[x][y].getLayout();
                clayout.show(m_panel[x][y], "card2");
                currentArray[x][y] = Array[x][y];
            }
        }

        public void dfsForAutoClick(int x, int y) {
            currentArray[x][y] = Array[x][y];
            if(x - 1 >= 1 && y - 1 >= 1) {
                solveDFSpoint(x - 1, y - 1);
            }
            if(x - 1 >= 1) {
                solveDFSpoint(x - 1, y);
            }
            if(x - 1 >= 1 && y + 1 <= Y) {
                solveDFSpoint(x - 1, y + 1);
            }
            if(y - 1 >= 1) {
                solveDFSpoint(x, y - 1);
            }
            if(y + 1 <= Y) {
                solveDFSpoint(x, y + 1);
            }
            if(x + 1 <= X && y - 1 >= 1) {
                solveDFSpoint(x + 1, y - 1);
            }
            if(x + 1 <= X) {
                solveDFSpoint(x + 1, y);
            }
            if(x + 1 <= X && y + 1 <= Y) {
                solveDFSpoint(x + 1, y + 1);
            }
        }

        public void solveRightButtonEvents(int x, int y) {
            CardLayout clayout = (CardLayout) m_panel[x][y].getLayout();
            if(currentArray[x][y] == Station.block) {
                currentArray[x][y] = Station.unknown;
                clayout.show(m_panel[x][y], "card1");
            }
            else {
                currentArray[x][y] = Station.block;
                clayout.show(m_panel[x][y], "card3");
            }
        }

        public void gameOver() {
            this.play = false;
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    if(Array[i][j] == Station.block && currentArray[i][j] == Station.block) {
                        CardLayout clayout = (CardLayout) m_panel[i][j].getLayout();
                        clayout.show(m_panel[i][j], "card4");
                    }
                    if(Array[i][j] == Station.block && currentArray[i][j] != Station.block){
                        CardLayout clayout = (CardLayout) m_panel[i][j].getLayout();
                        clayout.show(m_panel[i][j], "card2");
                    }
                    m_button[i][j].setEnabled(false);
                }
            }
        }
        public boolean isPlaying() {
            return this.play;
        }
        public Station[][] getCurrentArray(){
            return currentArray;
        }
        public void setcurrentArray(int x, int y, Station cur) {
            currentArray[x][y] = cur;
        }
        public void first(int X, int Y) {
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    CardLayout clayout = (CardLayout) m_panel[i][j].getLayout();
                    clayout.first(m_panel[i][j]);
                }
            }
        }
        public boolean getResult() {
            return this.result;
        }
        public void clear() {
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    m_panel[i][j].remove(m_button[i][j]);
                    m_panel[i][j].remove(m_flag[i][j]);
                    m_panel[i][j].remove(m_label[i][j]);
                    this.remove(m_panel[i][j]);
                }
            }
        }
    }

