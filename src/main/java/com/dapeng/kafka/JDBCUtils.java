package com.dapeng.kafka;

import com.alibaba.fastjson.JSON;
import com.dapeng.json.DetailBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/10/22 14:12
 * @Version 1.0
 * description
 */
public class JDBCUtils {
    public static ResultSet getRS() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        //2.建立连接
        Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","postgres","123456");
        //3.创建语句执行对象
        java.sql.Statement st=conn.createStatement();//使用Statement对象
        //4.执行语句
        ResultSet rs=st.executeQuery("select id,chapter_node,handin,score,handin_num from dwd_student_stage_work_detail_new limit 20");

        return rs;
    }


    public static HashMap getString() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        //2.建立连接
        Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","postgres","123456");
        //3.创建语句执行对象
        java.sql.Statement st=conn.createStatement();//使用Statement对象
        //4.执行语句
        ResultSet rs=st.executeQuery("select id,chapter_node,handin,score,handin_num from dwd_student_stage_work_detail_new limit 20");
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
//        String string = JSON.toJSONString(stringListHashMap);
        return stringListHashMap;
    }


}
