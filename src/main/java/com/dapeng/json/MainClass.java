package com.dapeng.json;



import com.alibaba.fastjson.JSON;
import com.dapeng.json.DetailBean;

import java.sql.*;
import java.util.*;

/**
 * @Author Administrator
 * @Date 2020/10/20 17:44
 * @Version 1.0
 * description
 */
public class MainClass {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");
        //2.建立连接
        Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","postgres","123456");
        //3.创建语句执行对象
        java.sql.Statement st=conn.createStatement();//使用Statement对象
        //4.执行语句
        ResultSet rs=st.executeQuery("select id,chapter_node,handin,score,handin_num from dwd_student_stage_work_detail_new");
        HashMap<String, List<DetailBean>> stringListHashMap = new HashMap<>();


        //5.处理结果
        while(rs.next()){

            DetailBean detailBean = new DetailBean(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            if(!stringListHashMap.containsKey(rs.getString(1))){
                ArrayList<DetailBean> detailBeans = new ArrayList<DetailBean>();
                detailBeans.add(detailBean);
                stringListHashMap.put(rs.getString(1),detailBeans);
            }else{
                List<DetailBean> detailBeans = stringListHashMap.get(rs.getString(1));
                detailBeans.add(detailBean);
                stringListHashMap.put(rs.getString(1),detailBeans);
            }


        }
        String string = JSON.toJSONString(stringListHashMap);
        String sql = "insert into test values(?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,string);
        preparedStatement.executeUpdate();
        //6.释放资源,资源rs、st、conn的释放顺序与创建顺序相反
        rs.close();
        st.close();
        conn.close();

    }
}
