package com.longhchen.sparkcore

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

import scala.collection.mutable

/**
 * Create by longhchen on  2021-01-06 0:20
 */
object Acc {
  def main(args: Array[String]): Unit = {
    //创建Spark配置对象
    val sparkConf: SparkConf = new SparkConf().setAppName("sortby").setMaster("local[*]")

    //创建Spark 上下文对象
    val sc = new SparkContext(sparkConf)

    //从集合中创建rdd
    val rdd= sc.makeRDD(List("spark hadoop", "scala", "java hello scala"))

    //创建累加器
    val acc = new WordCountAccumulator

    //注册累加器
    sc.register(acc)

    //调用累加器
    rdd.flatMap(_.split(" ")).foreach(
      word => acc.add(word)
    )

    //获取当前累加器的值
    val value: mutable.Map[String, Int] = acc.value
    for (elem <- value) {
      println(elem)
    }

    sc.stop()
  }

}
