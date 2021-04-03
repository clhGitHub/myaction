package com.longhchen.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Create by longhchen on  2021-01-05 22:53
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    //创建Spark运行配置对象
    val sparkConf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("WordCount")

    //创建Spark上下文运行对象
    val sc: SparkContext = new SparkContext(sparkConf)

    //读取文件数据
    val lineRDD: RDD[String] = sc.textFile("data/word.txt")

    //将数据进行分词
    val wordRDD: RDD[String] = lineRDD.flatMap(_.split(" "))

    //转换数据结构（word，1）
    val word2One: RDD[(String, Int)] = wordRDD.map(word => (word, 1))

    //将转换后的数据按照相同的数据进行分组
    val word2CountRDD: RDD[(String, Int)] = word2One.reduceByKey(_ + _)

    //将数据聚合结果采集到driver端
    val word2Count: Array[(String, Int)] = word2CountRDD.collect()

    //打印结果
    word2Count.foreach{println}

    //关闭spark连接
    sc.stop()


  }

}
