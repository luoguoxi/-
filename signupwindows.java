package supermarket;
import com.xrtero.dao.UserDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
public class signupwindows {
    JFrame frame;
    JLabel Labname;//用户名标签
    JLabel Labpassword;//密码标签
    JLabel again;//第二次密码标签
    JTextField Txtname;//用户名单行文本
    JPasswordField Txtpassword;//密码单行文本
    JPasswordField Txtagain;//再次输入密码单行文本
    JButton resert;//重置按钮
    JButton affirm;//确认按钮
    JButton ret;//返回按钮
    public void init(){
        frame=new JFrame("注册");
        Labname=new JLabel("用户名:");
        Labpassword=new JLabel("密 码:");
        again=new JLabel("确认密码：");
        Txtname=new JTextField();
        Txtpassword=new JPasswordField();
        Txtagain=new JPasswordField();
        resert=new JButton("重置");
        affirm=new JButton("确认");
        ret=new JButton("返回");
        //准备容器
        frame.setSize(400,300);
        frame.setLocation(200,200);
        //设置容器的布局方式
        frame.setLayout(null);//设置布局方式为空
        //设置组件的大小和位置并把组件加入容器
        Labname.setBounds(5,5,60,20);
        frame.add(Labname);
        Txtname.setBounds(80,5,150,30);
        frame.add(Txtname);
        Labpassword.setBounds(6,50,60,20);
        frame.add(Labpassword);
        Txtpassword.setBounds(80,50,150,30);
        frame.add(Txtpassword);
        again.setBounds(5,95,120,20);
        frame.add(again);
        Txtagain.setBounds(80,95,150,30);
        frame.add(Txtagain);
        affirm.setBounds(30,150,80,30);
        frame.add(affirm);
        resert.setBounds(130,150,80,30);
        frame.add(resert);
        ret.setBounds(230,150,80,30);
        frame.add(ret);
        //设置顶层容器可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //确认按钮触发事件
        affirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=Txtname.getText();
                String password1=Txtpassword.getText();//获得文本的第一次密码
                String password2=Txtagain.getText();//获得文本的第二次密码
                if(username.length()==0||password1.length()==0||password2.length()==0){//用户名或密码为空，弹出“警告”显示信息对话框
                    JOptionPane.showMessageDialog(affirm,"用户名或密码不能为空","警告",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(password1.compareTo(password2)==0){//两次密码一样，把用户名和密码存放进入数据库，并且返回登录界面
                    int n= 0;//获取返回语句影响的行数判断是否注册成功
                    try {
                        n = new UserDao().interuser(username,password1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if(n==2) {//注册成功，弹出“注册成功”显示信息对话框
                        JOptionPane.showMessageDialog(affirm,"注册成功","提醒",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);//设置当前窗口不可见
                        new manager().init();//返回登录管理员登录窗口
                    }
                    else if(n==1){//当n=1时注册失败，弹出“注册失败”显示信息对话框
                        JOptionPane.showMessageDialog(affirm,"注册失败，请重新注册","警告",JOptionPane.INFORMATION_MESSAGE);

                    }
                    else if(n==0){//当n=0时用户名在数据库已有，弹出“用户名已存在”显示信息对话框
                        JOptionPane.showMessageDialog(affirm,"用户名已存在","警告",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                else{//两次密码不一致，弹出“警告”显示信息对话框
                    JOptionPane.showMessageDialog(affirm,"两次密码不一致","警告",JOptionPane.INFORMATION_MESSAGE);
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
                Txtagain.setText("");
            }
        });
        //返回按钮触发事件
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//设置当前窗口不可见
                new manager().init();//返回管理员登陆界面
            }
        });

    }
    public static void main(String agr[]){
        new signupwindows().init();
    }
}
