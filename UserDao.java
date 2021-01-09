package com.xrtero.dao;

import com.xrtero.utils.DruidConnection;

import java.sql.*;

public class UserDao {
    public int queryUser(String name,String password)throws SQLException{//根据用户输入的用户名和密码在数据库进行查询
        String sql="select * from user where username=? and password=?";//SQL语句
        Connection connection = DruidConnection.getConnection();//建立与数据库连接
        int count=0;//设置初始值为0，判断账户是否在数据库user表中存在
        try {

            PreparedStatement ps = connection.prepareStatement(sql);//获取预处理语句
            ps.setString(1,name);//name替代第一个占位符
            ps.setString(2,password);//password替代第二个占位符
            ResultSet set=ps.executeQuery();
            while(set.next()){
                count++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();//关闭数据库连接
        return count;
    }
    public boolean findUsername(String name)throws SQLException{//根据用户输入的用户名在数据库进行查询
        String sql="select * from user where username=?";
        Connection connection = DruidConnection.getConnection();//建立与数据库连接
        int count=0;//设置初始值为0
        try {

            PreparedStatement ps = connection.prepareStatement(sql);//获取预处理语句
            ps.setString(1,name);//name替代第一个占位符
            ResultSet set=ps.executeQuery();
            while(set.next()){
                count++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();//关闭数据库
       if(count==0){//当count=0时,用户名在数据库中不存在,返回ture
           return true;
       }
       else//否则,用户名在数据库中已存在,返回false
       {
           return false;
       }
    }
    //在数据库中插入账户
    public int interuser(String name,String password)throws SQLException{
        int count=0;//初值为0，判断注册账号用户名是否在数据库已存在
        int row=0;//SQL在数据库中所影响的行数
        if(findUsername(name)) {//调用findUsername方法,判断注册账号用户名是否在数据库已存在
            count++;//注册账号用户名未在数据库已存在
            String sql = "insert into user(username,password) values (?,?)";//SQL语句
            Connection connection = DruidConnection.getConnection();//建立与数据库的连接
            try {
                PreparedStatement ps = connection.prepareStatement(sql);//获取预处理对象语句
                ps.setString(1, name);//name替代第一个占位符
                ps.setString(2, password);//password替代第二个占位符
                row = ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            connection.close();//关闭数据库连接
        }
       return count+row;//返回count与row相加
    }

    public static void main(String args[]){

    }




}
