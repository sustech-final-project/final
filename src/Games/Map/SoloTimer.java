package Games.Map;

import Games.GUI.GameFrame.F03;

import javax.swing.*;

public class SoloTimer implements Runnable							//计时器类
{
    private static int time;													//计时变量

    static JPanel panel1;												//面板

    public JPanel getPanel1() {
        return panel1;
    }

    static JTextField TimerField;										//计时器文本框
    static Thread TimerThread;								    //为计时器创建新线程

    public static void stop(){
        TimerThread.stop();
    }

    public static int gettime(){
        return time;
    }

    public SoloTimer(JPanel panel)									//构造函数
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
        return time;
    }

    @Override
    public void run()											//计时器功能
    {
        while(true)
        {
            try
            {
                Thread.sleep(1000);								//每次停顿一秒
                time += 1;										//计时变量+1
                TimerField.setText(String.valueOf(time));       //显示计时
            }
            catch (InterruptedException e)
            {
                TimerField.setText("interrupted.");				//线程扰乱错误抛出
            }
        }
    }
}