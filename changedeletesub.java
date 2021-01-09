package supermarket;
import com.xrtero.utils.DruidConnection;

import java.sql.*;
public class changedeletesub {
    Connection connection;
    Statement statement;
    PreparedStatement ps;
    ResultSet set;
    //通过number在数据库查找数据
    public boolean findnumber(String number)throws SQLException{
        String sql="select * from goods where number=?";
        int count=0;//判断是否已出现在goods表中
        connection= DruidConnection.getConnection();//取得与数据库的连接
        try {
            ps=connection.prepareStatement(sql);//获得预处理语句
            ps.setString(1,number);//number替代第一个占位符
            ResultSet set=ps.executeQuery();//取得结果集
            while(set.next()){//判断结果集有多少个数据
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       connection.close();
        if(count==0){//当未出现，返回true
            return true;
        }
        else //当出现，返回false
            return false;
    }
    //通过tradename与sort在数据库查找数据
    public boolean findBetween(String tradename,String sort)throws SQLException{
        String sql="select * from goods where tradename=? and sort=?";
        int count=0;//判断是否已出现在goods表中
        connection= DruidConnection.getConnection();//取得与数据库的连接
        try {
            ps=connection.prepareStatement(sql);//获得预处理语句
            ps.setString(1,tradename);//tradename替代第一个占位符
            ps.setString(2,sort);//sort替代第二个占位符
            set=ps.executeQuery();//取得结果集
            while(set.next()){//判断结果集有多少个数据
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
        if(count==0){//当未出现，返回true
            return true;
        }
        else //当出现，返回false
            return false;
    }
    //向数据库添加商品信息
    public boolean insertgoods(String number,String tradename,String sort,String price,String quantity)throws SQLException {
        String sql = "insert into goods(number,tradename,sort,price,stock) values (?,?,?,?,?)";
        connection = DruidConnection.getConnection();//取得与数据库的连接
        int row = 0;//初值为0，判断是否插入成功
        try {
            ps = connection.prepareStatement(sql);//获得预处理语句
            ps.setString(1, number);
            ps.setString(2, tradename);//tradename替代第一个占位符
            ps.setString(3, sort);//sort替代第二个占位符
            ps.setString(4, price);
            ps.setString(5, quantity);
            row = ps.executeUpdate();//返回语句影响的行数
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
        if(row==0)//插入失败，返回false
            return false;
        else//插入成功，返回true
            return true;

    }

    public int insert(String number,String tradename,String sort,String price,String quantity)throws SQLException{
        int count=0;
        if( findnumber(number)){//查找需要添加商品信息，编号是否与已有商品编号重复
            count++;

            if(findBetween(tradename,sort)){//查找需要添加商品信息，是否在已有商品中
                count++;

                if(insertgoods(number,tradename,sort,price,quantity)){//是否添加成功
                    count++;

                }




            }

        }
        return count;

    }
    //删除数据库中number编号的数据
    public boolean delete(String number){
        String sql="delete from goods where number=?";
        connection=DruidConnection.getConnection();//取得与数据库连接
        int row=0;//初值为0，判断是否删除成功
        try {
            ps=connection.prepareStatement(sql);//获得预处理对象
            ps.setString(1,number);//number替代第一个占位符
            row=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(row==0){//当row=0时，删除失败，返回false
            return false;
        }
        else//否则,删除成功，返回true
            return true;
    }
    //修改数据库中编号为number的数据
    public boolean change(String number,String tradename,String sort,String price,String quantity){
        String sql="update goods set tradename=?,sort=?,price=?,stock=? where number=? ";
        int row=0;//初值为0，判断是否修改成功
        connection=DruidConnection.getConnection();
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,tradename);
            ps.setString(2,sort);
            ps.setString(3,price);
            ps.setString(4,quantity);
            ps.setString(5,number);
            row=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();//关闭数据库连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(row==0){//当row=0时，修改失败，返回false
            return false;
        }
        else//否则,修改成功，返回true
            return true;


    }
    //增加数据库中编号为number商品的库存量
    public boolean addgoodsquantity(String number,String addquantity)throws SQLException{
        String sql="update  goods set stock=? where number=?";
        int total=findstock(number)+Integer.parseInt(addquantity);//调用findstock方法获取编号为number的库存量，加上进货数量addquantity，为总数量
        String total1=String.valueOf(total);//把total从int型转化为String型
        int row=0;//初值为0，判断是否进货成功
        connection=DruidConnection.getConnection();//取得与数据库连接
        try {
            ps=connection.prepareStatement(sql);//获取预处理对象
            ps.setString(1,total1);//total1替代第一个占位符
            ps.setString(2,number);//number替代第二个占位符
            row= ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();//关闭数据库连接
        if(row==0){//当row=0时，进货失败,返回false
            return false;
        }
        else//否则，进货成功，返回true
            return true;
    }
    //减少数据库中编号为number商品的库存量
    public boolean reducegoodsquantity(String number,String reducequantity)throws SQLException{
        String sql="update  goods set stock=? where number=?";
        int total=findstock(number)-Integer.parseInt(reducequantity);//调用findstock方法获取编号为number的库存量，减去进货数量reducequantity，为总数量
        if(total<0){//库存量不足
            return false;
        }
        String total1=String.valueOf(total);//把total从int型转化为String型
        int row=0;//初值为0，判断是否购买成功
        connection=DruidConnection.getConnection();//取得与数据库连接
        try {
            ps=connection.prepareStatement(sql);//获取预处理对象
            ps.setString(1,total1);//total1替代第一个占位符
            ps.setString(2,number);//number替代第二个占位符
            row= ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();//关闭数据库连接
        if(row==0){//当row=0时，购买失败,返回false
            return false;
        }
        else//否则购买成功，返回true
            return true;
    }
    //通过在数据库中查找编号为number数据的库存量
    public int findstock(String number)throws SQLException{
        String sql="select stock from goods where number=?";
        int count=0;//判断是否已出现在goods表中
        int m=0;//设置初值为0,存放库存量
        connection= DruidConnection.getConnection();//取得与数据库的连接
        try {
            ps=connection.prepareStatement(sql);//获得预处理语句
            ps.setString(1,number);//number替代第一个占位符
            ResultSet set=ps.executeQuery();//取得结果集
            while(set.next()){//判断结果集有多少个数据
                m=set.getInt(1);//在结果集中取得库存量
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
       return m;//返回库存量
    }
    //根据编号查找,查找goods表商品的单价
    public int findgoodsprice(String number){
        String sql="select price from goods where number=?";//SQL语句
        int  pce=0;//初值为0,存放商品编号为number的单价
        connection=DruidConnection.getConnection();//取得与数据库的连接
        try {
            ps=connection.prepareStatement(sql);//获取预处理对象语句
            ps.setString(1,number);//number替代第一个占位符
            set=ps.executeQuery();
            while(set.next()){
                pce=set.getInt(1);//取得结果集第一行的数据
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();//关闭数据库连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pce;//返回商品的单价
    }
    public static void main(String args[])throws SQLException{

    }

}
