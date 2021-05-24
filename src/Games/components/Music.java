package Games.components;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Music extends Applet {
    public final static int on=1;
    public final static int down=0;
    public static int now=1;
    private static URL cb;
    private static AudioClip auu;

    /**
     * 作者：戴郭轶
     * 日期：2021.5.21
     * 本程序用于实现音乐播放器的功能
     * @param str
     */
    public static void playMusic(String str) {
        if (now == on) {
            try {
                File f = null;
                if ("boom".equals(str)) {

                    //f = new File("final\\src\\音效\\单个爆炸音.wav");
                    f = new File("src\\音效\\单个爆炸音.wav");

                    //f = new File("src\\音效\\单个爆炸音.wav");
                    f = new File("final\\src\\音效\\单个爆炸音.wav");

                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.loop();
                } else if ("leftclick".equals(str)) {

                    //f = new File("final\\src\\音效\\左键单击.wav");
                    f = new File("src\\音效\\左键单击.wav");

                    //f = new File("src\\音效\\左键单击.wav");
                    f = new File("final\\src\\音效\\左键单击.wav");

                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();
                } else if ("refreshmap".equals(str)) {

                   // f = new File("final\\src\\音效\\开局刷新地图.wav");
                    //f = new File("final\\src\\音效\\开局刷新地图.wav");
                    f = new File("src\\音效\\开局刷新地图.wav");
                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();
                } else if ("successmine".equals(str)) {
                    //f = new File("final\\src\\音效\\扫雷成功.wav");
                    f = new File("src\\音效\\扫雷成功.wav");
                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();

                }else if ("failmine".equals(str)){
                    //f = new File("final\\src\\音效\\点错音效");
                    f = new File("src\\音效\\点错音效");
                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();

                }else if ("boom2".equals(str)){
                    //f = new File("final\\src\\音效\\爆炸尾音.wav");
                    f = new File("src\\音效\\爆炸尾音.wav");
                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();
                }
                else if ("realBoom".equals(str)){
                    //f = new File("final\\src\\音效\\tnt.wav");
                    f = new File("src\\音效\\tnt.wav");
                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();
                }
                else if ("BGM".equals(str)){
                    //f = new File("final\\src\\音效\\bgm.wav");
                    f = new File("src\\音效\\bgm.wav");

                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();
                }
                else if ("Begin".equals(str)){
                    //f = new File("final\\src\\音效\\开头.wav");
                    f = new File("src\\音效\\开头.wav");
                    cb = f.toURL();
                    auu = Applet.newAudioClip(cb);
                    auu.play();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

        public static void playCircleMusic(String musicLocation)
        {
            try
            {
                File musicPath = new File(musicLocation);

                if(musicPath.exists())
                {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                else
                {

                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }



    public static void stopMusic() { auu.stop(); }
}
