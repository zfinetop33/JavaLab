package zfine.top.mysql.index.compositeIndex;

import com.mysql.cj.jdbc.PreparedStatement;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

/**
 * @describe: 验证执行效率和组合索引命中数的关系
 * @author: ZF
 * @date: 2020/4/11 15:49
 */
public class LabCompositeIndex {
    @Test
    public void insertMultiple(){
        int count=5;
        for(int i=0;i<count;i++){
            insertData();
        }
    }

    @Test
    public void insertData(){
        String MYSQL_DRIVER="com.mysql.cj.jdbc.Driver";
        String MYSQL_URL="jdbc:mysql://192.168.133.142:3306/mysql";
        String MYSQL_NAME="zf";
        String MYSQL_PASSWORD="qwer1234";
        int COUNT=100 * 1000;
        String INSERTSQL="insert into lab_order(order_no,user_id,goods_id,order_status) values(?,?,?,1)";

        try {
            Class.forName(MYSQL_DRIVER);
            Connection con = DriverManager.getConnection(MYSQL_URL, MYSQL_NAME, MYSQL_PASSWORD);
            con.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(INSERTSQL);
            String orderNo=null;
            int userIdRandom=0;
            int goodsIdRandom=0;
            for (int i = 0; i < COUNT; i++) {
                orderNo=UUID.randomUUID().toString();
                pst.setString(1,orderNo);
                userIdRandom = (int) (Math.random() * 100000) % 100+1000;
                pst.setInt(2,userIdRandom);
                goodsIdRandom = (int) (Math.random() * 100000) % 100+1000;
                pst.setInt(3,goodsIdRandom);
                pst.addBatch();
            }
            pst.executeBatch();
            con.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("用时：" + (endTime - startTime));
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

