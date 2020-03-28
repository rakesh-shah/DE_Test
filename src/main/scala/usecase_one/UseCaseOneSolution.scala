package usecase_one
import utility.CreateSparkSessionInc;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.functions.{col,lead,lag,when}
object UseCaseOneSolution {
  def main(args: Array[String]) = {
    val spark = CreateSparkSessionInc.getSparkSession("usecaseone")
    val usecaseone = new UseCaseOne();
    val path = usecaseone.getFilePath();
    val loadIotDF = spark.read.format("csv")
                              .option("InferSchema", "true")
                              .option("header", "true")
                              .option("path",path)
                              .load()
    loadIotDF.show()
    val result = loadIotDF.select(col("Sensor")
                                 ,col("Mnemonic")
                                 ,col("data")
                                 ,col("timestamp")
                                 ,lag("data",1)
                          .over(Window.partitionBy(col("Sensor"),col("Mnemonic"))
                          .orderBy(col("Sensor"),col("Mnemonic"))).alias("last_data")
                          ,lead(col("data"), 1).over(Window.partitionBy(col("sensor"),col("mnemonic")).orderBy(col("sensor"), col("mnemonic"))).alias("next_data")
                          ,lag(col("timestamp"), 1).over(Window.partitionBy(col("sensor"), col("mnemonic")).orderBy(col("sensor"), col("mnemonic"))).alias("last_timestamp")
                          ,lead(col("timestamp"), 1).over(Window.partitionBy(col("sensor"), col("mnemonic")).orderBy(col("sensor"), col("mnemonic"))).alias("next_timestamp")).where(col("data") !== col("last_data"))
                     .select(col("sensor")
                            ,col("mnemonic")
                            ,col("data")
                            ,col("last_data")
                            ,when(col("next_data")===null,col("data")).otherwise(col("last_data")).alias("data")
                            ,when(col("next_data")===null,col("timestamp")).otherwise(col("last_timestamp")).alias("start_date")
                            ,when(col("next_data")===null,null).otherwise(col("timestamp")).alias("end_date"))
     result.show()
  }
}