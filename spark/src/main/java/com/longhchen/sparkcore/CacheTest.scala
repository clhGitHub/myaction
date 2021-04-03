package com.longhchen.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Create by longhchen on  2021-01-06 0:05
 */
object CacheTest {
  def main(args: Array[String]): Unit = {
    //配置对象
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("CacheTest")

    //上下文对象
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 3, 46, 5),2)

    val rdd1: RDD[Int] = rdd.map(num => {
      println("map..." + num)
      num
    })

    rdd1.map(_*2).collect().foreach(print)

    println("\n******************")

    rdd1.collect().foreach(print)


  }

}
