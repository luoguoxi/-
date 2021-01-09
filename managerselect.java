package supermarket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
public class managerselect {
    JFrame frame;
    JButton total;//统计按钮
    JButton find;//查找按钮
    JButton changedelete;//修改与删除按钮
    JButton repelish;//仓库进货按钮
    JButton ret;//返回按钮
    public  void init(){
        frame=new JFrame("选择");
        total=new JButton("统计");
        find=new JButton("查找");
        changedelete=new JButton("商品管理");
        repelish=new JButton("仓库进货");
        ret=new JButton("返回");
        //准备窗口
        frame.setSize(400,300);
        frame.setLocation(200,200);
        //设置布局方式
        frame.setLayout(null);
        //设置组件大小与位置并添加到窗口
        total.setBounds(100,20,200,20);
        frame.add(total);
        find.setBounds(100,70,200,20);
        frame.add(find);
        changedelete.setBounds(100,120,200,20);
        frame.add(changedelete);
        repelish.setBounds(100,170,200,20);
        frame.add(repelish);
        ret.setBounds(100,220,200,20);
        frame.add(ret);

        //设置顶层容器可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //统计按钮触发事件
        total.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//设置当前界面不可见
                new totalwindows().init();//跳转统计界面
            }
        });
        //查找按钮触发事件
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//设置当前界面不可见
                new findwindows().init();//跳转查找界面
            }
        });
        //仓库管理按钮触发事件
        changedelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//当前界面不可见
                new changedelete().init();//跳转管理界面
            }
        });
        //仓库按钮触发事件
        repelish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//当前界面不可见
                new warehouse().init();//跳转仓库进货界面
            }
        });
        //返回按钮触发事件
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//当前界面不可见
                new manager().init();//返回登录界面
            }
        });



    }
    public static void main(String args[]){
        new managerselect().init();

    }
}
