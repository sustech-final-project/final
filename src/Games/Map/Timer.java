package Games.Map;

import Games.GUI.GameFrame.F03;

import java.awt.*;
import java.text.*;

/**
 * 作者：戴郭轶
 * 日期：2021.5.2
 * 本程序用于实现计时器功能
 * 默认时间为
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Timer implements Runnable							//计时器类
{
    private static double time;													//计时变量

    static JPanel panel1;												//面板

    public JPanel getPanel1() {
        return panel1;
    }

    static JTextField TimerField;										//计时器文本框
    static Thread TimerThread;								    //为计时器创建新线程

    public static void stop(){
        TimerThread.stop();
    }

    public Timer(JPanel panel)									//构造函数
    {
        time = 0;
        this.panel1 = panel;			//获取主面板

        JLabel labelTime = new JLabel("用 时:");					//"计时"字样标签
        labelTime.setHorizontalAlignment(JLabel.CENTER);
        labelTime.setFocusable(false);
        panel1.add(labelTime);									//添加至主面板中

        TimerField = new JTextField("0", 3);						//计时数字文本框
        TimerField.setHorizontalAlignment(JTextField.CENTER);
        TimerField.setEditable(false);
        TimerField.setFocusable(false);
        panel1.add(TimerField);									//添加至主面板中
        TimerThread = new Thread(this,"TimerThread");			//创建新线程
        TimerThread.start();									//开启线程
    }

    public void ResetTimer()									//计时器重置
    {
        time = 0;
        TimerField.setText(String.valueOf(time));
    }

    public static void resetTimer()									//计时器重置
    {
        time = 0;
        TimerField.setText(String.valueOf(time));
    }

    public int getTime()
    {
        return (int)time;
    }

    @Override
    public void run()											//计时器功能
    {
        while(true)
        {
            try
            {
                Thread.sleep(1000);								//每次停顿一秒
                time += 0.5;										//计时变量+1
                TimerField.setText(String.valueOf((int)time));       //显示计时
                if (time==30){
                    ResetTimer();
                    GameController.setOrder(Data.getOrder()+1);
                    F03.resetBorder();
                }

            }
            catch (InterruptedException e)
            {
                TimerField.setText("interrupted.");				//线程扰乱错误抛出
            }
        }
    }
}