package Games.Internet.GameClient;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import Games.GUI.GameFrame.layout.AfXLayout;
import Games.GUI.GameFrame.layout.AfYLayout;
import Games.Map.Timer;
import static Games.GUI.GameFrame.MainLocal.*;
import static Games.GUI.GameFrame.MainLocal.f00;
import static Games.utils.ClickType.LEFT_CLICK;
import static Games.utils.ClickType.RIGHT_CLICK;

public class Muti extends JFrame {

    ArrayList<MouseListener> listeners = new ArrayList<>();
    ArrayList<JButton> buttons = new ArrayList<>();
    JPanel[] panels;
    JLabel[] playerInf;
    JPanel timerPanel = new Timer(new JPanel()).getPanel1();
    JPanel board = new JPanel();
    ArrayList<Player> players = new ArrayList<>();
    Client client = new Client();

    public Muti(String title) {
        super(title);
        players.add(Data.getPlayer());
        players.add(Data.getRival());
        Container contentPane = getContentPane();
        contentPane.setLayout(new AfXLayout());
        {


            board.setLayout(new GridLayout(16, 16));
            panels = new JPanel[16];
            for (int i = 0; i < 16 * 16; i++) {
                int row = i / 16;
                int column = i % 16;
                buttons.add(new JButton(""));
                listeners.add(new MouseListener());
                buttons.get(i).setFocusable(false);
                buttons.get(i).addMouseListener(listeners.get(i));
                board.add(buttons.get(i));
            }
        }//建立board

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border empty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        board.setBorder(border);
        timerPanel.setBorder(border);
        if (board.isVisible()) {
            Dimension size = board.getPreferredSize();
            board.setBounds(0, 0, size.width, size.height);
        }

        if (timerPanel.isVisible()) {
            Dimension size = timerPanel.getPreferredSize();
            timerPanel.setBounds(43 * 16, 0, size.width * 2, size.height);
        }
        contentPane.add(board, "70%");
        JPanel left = new JPanel(new AfYLayout());
        left.add(timerPanel, "20%");
        JPanel score = new JPanel(new AfYLayout());
        score.setBorder(new LineBorder(Color.BLACK, 2));
        playerInf = new JLabel[players.size()];

        for (int i = 0; i < players.size(); i++) {
            score.add(playerInf[i] = new JLabel("Player:" + players.get(i).getName() + "       Score:" + players.get(i).getScore() + "       Mistake:" + players.get(i).getMistake()), (int) (1 / (double) (players.size()) * 100) + "%");
        }
        left.add(score, "30%");
        contentPane.add(left, "1w");


        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenu viewMenu = new JMenu("视图");
        JMenu helpMenu = new JMenu("帮助");

        JMenuItem creator = new JMenuItem("制作人的话");
        JMenuItem about = new JMenuItem("关于...");
        JMenuItem changeTheme = new JMenuItem("更换主题");

        fileMenu.addSeparator();
        helpMenu.addSeparator();
        helpMenu.add(creator);
        helpMenu.add(about);

        JMenuItem Theme1 = new JMenuItem("主题1");
        JMenuItem Theme2 = new JMenuItem("主题2");
        JMenuItem Theme3 = new JMenuItem("主题3");
        changeTheme.add(Theme1);
        changeTheme.add(Theme2);
        changeTheme.add(Theme3);

        viewMenu.add(changeTheme);

        creator.addActionListener(listener -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    f00();
                }
            });
        });
        about.addActionListener(listener -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    f00();
                }
            });
        });

        bar.add(fileMenu);
        bar.add(viewMenu);
        bar.add(helpMenu);

        this.setJMenuBar(bar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(16 * (int) (43.0 / 0.7), 16 * 42 + 60);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int r = listeners.indexOf(this) / 16;
            int c = listeners.indexOf(this) % 16;

            if (!buttons.get(listeners.indexOf(this)).getText().equals("F") && e.getButton() == MouseEvent.BUTTON1) {
                client.gaming(players.get(1).getId(), r, c, LEFT_CLICK);
                if (Data.getaChar() == 'M') {
                    buttons.get(listeners.indexOf(this)).setText("M");
                } else {
                    buttons.get(listeners.indexOf(this)).setText(Data.getaChar() + "");
                }

            } else if (!buttons.get(listeners.indexOf(this)).getText().equals("F") && e.getButton() == MouseEvent.BUTTON3){
                client.gaming(players.get(1).getId(), r, c, RIGHT_CLICK);
                if (Data.getaChar() == 'M') {
                    buttons.get(listeners.indexOf(this)).setText("F");
                } else {
                    buttons.get(listeners.indexOf(this)).setText(Data.getaChar() + "");
                }
            }

            buttons.get(listeners.indexOf(this)).setEnabled(false);
            for (int i = 0; i < players.size(); i++) {
                playerInf[i].setText("Player:" + players.get(i).getName() + "       Score:" + players.get(i).getScore() + "       Mistake:" + players.get(i).getMistake());
            }
        }
    }
}
