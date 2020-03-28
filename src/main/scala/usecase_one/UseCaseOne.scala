package usecase_one
import java.util.Properties
import java.io.File
import java.io.FileInputStream

class UseCaseOne {
  var properties : Properties=null
  def getFilePath():String={
    val propsFile = new File("info.properties")
    properties = new Properties()
    properties.load(new FileInputStream(propsFile))
    val filePath = properties.getProperty("input_file_path")
    return filePath
  }
}