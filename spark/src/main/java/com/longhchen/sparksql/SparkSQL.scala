package com.longhchen.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * Create by longhchen on  2021-01-06 19:48
 */
object SparkSQL {
  def main(args: Array[String]): Unit = {
    //创建环境配置对象
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")

    //创建上下文对象
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()

    //引入隐式转换
    import  spark.implicits._

    //读取文件
    val frame: DataFrame = spark.read.json("data/people.json")

    frame.printSchema()

    frame.select("name","age").show()

    /**
     * 说明:
     * 1. 涉及到运算的时候, 每列都必须"使用$, 或者采用引号表达式：单引号+字段名"
     * 2. as 取别名
     */
    frame.select('age,'age+1 as "newage").show()

    frame.select($"age",$"age"+1 as "newage").show()

    println("****************************")
    //自定义UDF函数
    val udf: UserDefinedFunction = spark.udf.register("newName", (x: String) => "name:" + x)

    //创建临时表
    frame.createOrReplaceTempView("people")
    spark.sql("select newName(name) as name2 from people").show()

    spark.sql(
      """
        |CREATE TABLE `user_visit_action`(
        |
        |)
        |""".stripMargin
    )
  }

}
