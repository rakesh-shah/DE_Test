package utility
import org.apache.spark.sql.SparkSession;

object CreateSparkSessionInc {
  private var spark : SparkSession = null
  def getSparkSession(jobName:String):SparkSession={
    spark = SparkSession.builder()
                        .appName(jobName)
                        .config("spark.master", "local")
                        .getOrCreate()
    return spark 
  }
}