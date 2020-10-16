package org.example

import org.apache.spark
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author Administrator
 * @Date 2020/10/15 14:29
 * @Version 1.0
 * description
 */
object Spark_etl {

  def main(args: Array[String]): Unit = {

    //创建SparkConf()并且设置App的名称
    val conf = new SparkConf().setAppName("wordCount").setMaster("local[2]");
    //创建SparkContext,该对象是提交spark app的入口
    val sc = new SparkContext(conf);

    //取文件系统
    val filePath = new org.apache.hadoop.fs.Path( "hdfs://node-1:9000/user/aabbcc/c" )
    val fileSystem = filePath.getFileSystem( sc.hadoopConfiguration )

    // 判断路径是否存在
    val bool: Boolean = fileSystem.exists(filePath)
    if (bool) fileSystem.delete(filePath, true)
    //使用sc创建rdd,并且执行相应的transformation和action
    val value1: RDD[Array[String]] = sc.textFile("hdfs://node-1:9000/0222").map(_.split("\t"))
    val value: RDD[StringBuffer] = value1.filter(_.length >= 9).map(x => {
      val builder = new StringBuffer()
      x(3) = x(3).replaceAll(" ", "")
      for (i <- 0 to x.length - 1) {
        if (i < 9) {
          if (i == x.length - 1) {
            builder.append(x(i))
          } else {
            builder.append(x(i) + "\t")
          }
        } else {
          if (i == x.length - 1) {
            builder.append(x(i))
          } else {
            builder.append(x(i) + "&")
          }
        }

      }
      builder

    })
//    value.foreach(println(_))
    value.saveAsTextFile("hdfs://node-1:9000/user/aabbcc/c")
//    val value: RDD[StringBuffer] = sc.textFile("D:\\学习文件\\hive学习\\2.资料\\04_data\\guiliVideo\\video\\2008\\0222").map(x => {
//      val builder = new StringBuffer()
//      val strings: Array[String] = x.split("\t")
//      val strings1: Array[String] = strings.filter(_.length >= 9)
//            if (strings1.length >= 9) {
//              strings(3) = strings(3).replaceAll(" ", "")
//                    for (i <- 0 to strings1.length-1) {
//                      if (i < 9) {
//                        if (i == strings1.length - 1) {
//                          builder.append(strings1(i))
//                        } else {
//                          builder.append(strings1(i) + "\t")
//                        }
//                      } else {
//                        if (i == strings1.length - 1) {
//                          builder.append(strings1(i))
//                        } else {
//                          builder.append(strings1(i) + "&")
//                        }
//                      }
//                    }
//            }
//      builder
//
//    })
//    value.saveAsTextFile("d:\\aac")


    //停止sc，结束该任务
    sc.stop();

  }
}
