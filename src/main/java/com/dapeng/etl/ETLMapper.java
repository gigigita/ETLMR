package com.dapeng.etl;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class ETLMapper extends Mapper<LongWritable, Text,NullWritable,Text> {

    Text text=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.读取数据
        String s = value.toString();
        //2.过滤数据
        String etlstr = ETLUtils.etlstr(s);
        if (etlstr==null) return;
        //3.写出数据
        text.set(etlstr);
        context.write(NullWritable.get(),text);
    }
}
