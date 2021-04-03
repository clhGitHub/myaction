package com.longhchen.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Create by longhchen on  2021-01-06 21:52
 */
object SparkStreaming {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming")
    val ssc = new StreamingContext(sparkConf,Seconds(5))

    // 需求：使用netcat工具向9999端口不断的发送数据，通过SparkStreaming读取端口数据并统计不同单词出现的次数

    // 1. 获取netcat工具9999端口的连接，并开始接收数据
    // 从socket中获取数据：一行一行的获取

    //val socketDS: ReceiverInputDStream[String] = ssc.socketTextStream("localhost",9999)

    val socketDS: DStream[String] = ssc.textFileStream("data/word.txt")

    // 2. 数据处理
    val wordDS: DStream[String] = socketDS.flatMap(_.split(" "))

    val wordToSumDS: DStream[(String, Int)] = wordDS.map((_,1)).reduceByKey(_ + _ )

    // 3. 打印数据
    wordToSumDS.print()

    // 4. Driver程序执行时，streaming处理过程不能结束

    // 采集器在正常情况下启动后就不应该停止，除非特殊情况

    // 启动采集器
    ssc.start()

    // 等待采集器的结束
    ssc.awaitTermination()
  }

}
