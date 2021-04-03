package com.longhchen.sparkcore

import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

//自定义累加器
class WordCountAccumulator extends AccumulatorV2[String, mutable.Map[String, Int]] {
  private var wordCountMap: mutable.Map[String, Int] = mutable.Map[String, Int]()
  //方法1：判断当前的累加器是初始化
  override def isZero: Boolean = {
    wordCountMap.isEmpty
  }
  //方法2：复制一个累加器
  override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = {
    new WordCountAccumulator
  }
  //方法3：重置累加器
  override def reset(): Unit = {
    wordCountMap.clear()
  }
  //-方法4：向累加器中增加值
  override def add(word: String): Unit = {
    wordCountMap(word) = wordCountMap.getOrElse(word, 0) + 1

  }
  //方法5：合并当前累加器和其他累加器，两两合并，此方法由Driver端调用，合并由executor返回的多个累加器
  override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
    val map1 = wordCountMap
    val map2 = other.value

    wordCountMap = map1.foldLeft(map2)((map, kv) => {
      map(kv._1) = map.getOrElse(kv._1, 0) + kv._2
      map
    }
    )

  }
  //方法6：返回当前累加器的值
  override def value: mutable.Map[String, Int] = {
    wordCountMap
  }
}
