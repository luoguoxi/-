package supermarket;
import com.xrtero.dao.UserDao;
import com.xrtero.utils.DruidConnection;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class manager {
    JFrame frame;
    JLabel Labname;//用户名标签
    JLabel Labpassword;//密码标签
    JTextField Txtname;//用户名单行文本
    JPasswordField Txtpassword;//密码单行文本
    JButton resert;//重置按钮
    JButton register;//登录按钮
    JButton signup;//注册按钮
    JButton ret;//返回按钮
    JLabel warn;//提示标签
    //FlowLayout flowlayout;//流式布局管理器
    public void init(){
        frame=new JFrame("登陆界面");
        Labname=new JLabel("用户名:");
        Labpassword=new JLabel("密 码:");
        Txtname=new JTextField();
        Txtpassword=new JPasswordField();
        resert=new JButton("重置");
        register=new JButton("登录");
        signup=new JButton("注册");
        ret=new JButton("返回");
        warn=new JLabel("如果未有账号，请先注册");
        warn.setForeground(Color.RED);
        //准备容器
        frame.setSize(400,300);
        frame.setLocation(200,200);
        //设置容器的布局方式胃口
        frame.setLayout(null);
        //设置组件大小与位置并把组件加入容器
        Labname.setBounds(50,50,60,20);
        frame.add(Labname);
        Txtname.setBounds(120,50,150,30);
        frame.add(Txtname);
        Labpassword.setBounds(50,100,60,20);
        frame.add(Labpassword);
        Txtpassword.setBounds(120,100,150,30);
        frame.add(Txtpassword);
        register.setBounds(120,150,60,30);
        frame.add(register);
        resert.setBounds(210,150,60,30);
        frame.add(resert);
        signup.setBounds(120,200,60,30);
        frame.add(signup);
        ret.setBounds(210,200,60,30);
        frame.add(ret);
        warn.setBounds(10,20,200,20);
        frame.add(warn);
        //设置顶层容器为可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //登录按钮触发事件
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Txtname.getText();//获得文本的用户名
                String password = Txtpassword.getText();//获得文本的密码
                if (name.length() == 0 || password.length() == 0) {//用户名或密码为空，弹出“警告”显示信息窗口
                    JOptionPane.showMessageDialog(register, "用户名或密码不能为空", "警告", JOptionPane.INFORMATION_MESSAGE);
                } else {//把数据传入结果集进行比较，是否为已注册的账户
                    int t = 0;//接收查询的个数
                    try {
                        t = new UserDao().queryUser(name,password);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (t == 0)//未在数据库中找到此用户
                    {
                        JOptionPane.showMessageDialog(register, "用户名或密码错误", "警告", JOptionPane.INFORMATION_MESSAGE);
                    } else {//查找到此管理员用户，进入管理员选择界面
                        frame.setVisible(false);
                        new managerselect().init();

                    }

                }
            }
        });
        //重置按钮触发事件
        resert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //用户名和密码文本框内容设置为空
                Txtname.setText("");
                Txtpassword.setText("");
            }
        });
        //注册按钮触发事件
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//设置当前界面为不可见
                new signupwindows().init();//转入注册界面
            }
        });
        //返回按钮触发事件
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//设置当前窗口不可见
                new IDselect().init();//返回身份选择界面
            }
        });


    }
    public static void main(String agr[]){
         new manager().init();
    }

}
