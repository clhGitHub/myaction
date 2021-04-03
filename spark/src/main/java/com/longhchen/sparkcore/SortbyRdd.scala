package com.longhchen.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Create by longhchen on  2021-01-05 23:28
 */
object SortbyRdd {
  def main(args: Array[String]): Unit = {
    //创建Spark配置对象
    val sparkConf: SparkConf = new SparkConf().setAppName("sortby").setMaster("local[*]")

    //创建Spark 上下文对象
    val sc = new SparkContext(sparkConf)

    //从集合中创建rdd
    val rdd: RDD[Int] = sc.makeRDD(List(1, 3, 5, 0),2)

    val sortRDD: RDD[Int] = rdd.sortBy(x => x,false)

    //sortRDD.collect().foreach(println)
    sortRDD.foreach(println)

    //关闭连接
    sc.stop()
  }

}
