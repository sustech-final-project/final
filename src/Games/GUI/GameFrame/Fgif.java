package Games.GUI.GameFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class Fgif {

    /**
     * @param args
     */
    public static void main(String[] args) {
// TODO Auto-generated method stub
        final JFrame frame = new JFrame();
        frame.setUndecorated(true);
        Border b = new CompoundBorder(new EtchedBorder(),new LineBorder(Color.RED));
        final JPanel panel = new JPanel();
        panel.setSize(200,100);
        panel.setBorder(b);


        panel.addMouseMotionListener(new MouseAdapter() {
            private boolean top = false;
            private boolean down = false;
            private boolean left = false;
            private boolean right = false;
            private boolean drag = false;
            private Point lastPoint = null;
            private Point draggingAnchor = null;
            @Override
            public void mouseMoved(MouseEvent e) {
                if(  e.getPoint().getY() == 0 ){
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    top = true;
                }else if(Math.abs(e.getPoint().getY()- frame.getSize().getHeight()) <= 1 ){
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    down = true;
                }else if(e.getPoint().getX() == 0 ){
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    left = true;
                }else if(Math.abs(e.getPoint().getX()- frame.getSize().getWidth()) <=1 ){
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    right = true;
                }else{
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    draggingAnchor = new Point(e.getX() + panel.getX(), e.getY() + panel.getY());
                    top = false;
                    down = false;
                    left = false;
                    right = false;
                    drag = true;
                }

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Dimension dimension = frame.getSize();
                if(top){

                    dimension.setSize(dimension.getWidth() ,dimension.getHeight()-e.getY());
                    frame.setSize(dimension);
                    frame.setLocation(frame.getLocationOnScreen().x, frame.getLocationOnScreen().y + e.getY());
                }else if(down){

                    dimension.setSize(dimension.getWidth() , e.getY());
                    frame.setSize(dimension);

                }else if(left){

                    dimension.setSize(dimension.getWidth() - e.getX() ,dimension.getHeight() );
                    frame.setSize(dimension);


                    frame.setLocation(frame.getLocationOnScreen().x + e.getX(),frame.getLocationOnScreen().y );

                }else if(right){

                    dimension.setSize(e.getX(),dimension.getHeight());
                    frame.setSize(dimension);
                }else {
                    frame.setLocation(e.getLocationOnScreen().x - draggingAnchor.x, e.getLocationOnScreen().y - draggingAnchor.y);
                }
            }
        });

        frame.setLocation(200,100);
        frame.getContentPane().add(panel);
        frame.setSize(200,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.show();

    }

}