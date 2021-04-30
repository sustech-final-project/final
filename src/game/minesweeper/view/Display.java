package game.minesweeper.view;

import game.minesweeper.listener.*;
import game.minesweeper.map.Block;
import game.minesweeper.utils.ClickType;
import game.minesweeper.utils.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Display extends JFrame implements MapListener, GameStatusListener, Listenable<InputListener> {
    Container container;
    private final ArrayList<InputListener> listenerList = new ArrayList<>();

    public Display() {
        setLayout(new GridLayout(10, 9, 5, 5));
        container = getContentPane();
        addHead();
        addMines();
        setTitle("MineSweeper");
        setVisible(true);
        setSize(500, 550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addHead() {
        for (int i = 0; i < 9; i++) {
            JLabel label = new JLabel();
            if (i == 4) {
                JButton restart = new JButton("R");
                restart.addActionListener(e -> listenerList.forEach(InputListener::onGameRestart));
                this.container.add(restart);
            } else {
                this.container.add(label);
            }
        }
    }

    public void addMines() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JButton button = new JButton();
                int finalJ = j;
                int finalI = i;
                button.addMouseListener(new MouseAdapter() {
                    boolean isDown = false;
                    @Override
                    public void mousePressed(MouseEvent e) {
                        isDown = true;
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (isDown) {
                            isDown = false;
                            ClickType type = ClickType.NULL;
                            if (e.getButton() == MouseEvent.BUTTON1) {
                                type = ClickType.LEFT_CLICK;
                            } else if (e.getButton() == MouseEvent.BUTTON3) {
                                type = ClickType.RIGHT_CLICK;
                            }
                            ClickType finalType = type;
                            listenerList.forEach(listener -> listener.onPlayerClickBoard(finalJ, finalI, finalType));
                        }
                    }
                });
                this.container.add(button);
            }
        }
    }

    @Override
    public void onBlockClicked(Block block) {
        int location = block.getLocation().toOneDimension();
        JButton button = (JButton) getContentPane().getComponent(location);
        if (block.hasClicked()) {
            button.setEnabled(false);
            if (block.getState() != 0) button.setText(String.valueOf(block.getState()));
            return;
        }
        if (block.hasFlag()) {
            button.setText("F");
        } else {
            button.setText("");
        }
    }

    @Override
    public void onMapReload() {
        for (int i = 9; i < 90; i++) {
            JButton button = (JButton) getContentPane().getComponent(i);
            button.setText("");
            button.setEnabled(true);
        }
    }

    @Override
    public void onGameStatusChanged(GameStatus status) {
        String message = "YOU ";
        switch (status) {
            case WIN:
                message += "WIN!";
                break;
            case LOSE:
                message += "LOSE!";
                break;
            case PROGRESS:
                return;
        }
        JOptionPane.showMessageDialog(null, message, null, JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void registerListener(InputListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(InputListener listener) {
        listenerList.remove(listener);
    }
}