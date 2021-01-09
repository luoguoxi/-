package supermarket;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class IDselect {
    JFrame frame;
    ImageIcon background;//设置背景
    JLabel select;
    JLabel image;//存放图片的标签
    JButton manager;//管理员身份选择按钮
    JButton user;//客户身份选择按钮

    public void init(){
        frame=new JFrame("超市管理系统");
        background=new ImageIcon("C:/Users/86132/Desktop/supermarket.jpg");//设置背景图片的位置
        image=new JLabel();
        select=new JLabel("请选择您的身份：");
        manager=new JButton("管理员");
        user=new JButton("客户");
        //准备窗口
        frame.setSize(400,300);//设置窗口大小
        frame.setLocation(200,200);//设置窗口位置
        //设置容器的布局方式
        frame.setLayout(null);//布局管理器为空
        //设置组件位置，并把把组件加入容器
        image.setIcon(background);
        image.setBounds(0,0,400,300);
        user.setBounds(50,100,280,30);
        manager.setBounds(50,50,280,30);
        select.setBounds(10,10,100,30);
        frame.add(select);
        frame.add(user);
        frame.add(manager);
        frame.add(image);

        //设置顶层窗口可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭方式
        //动作事件
       manager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//设置当前窗口不可见
                new manager().init();//进入管理员登录窗口
            }
    });
       user.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.setVisible(false);//设置当前窗口不可见
               new cilent().init();//进入客户登录窗口
           }
       });
    }
    public static void main(String agr[]){
        try
        {
            //设置本属性将改变窗口边框样式定义
            System.setProperty("sun.java2d.noddraw", "true");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        new IDselect().init();
    }
}
