package androidres

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * 转换iOS风格的资源命名 为android
        **/

object ConvertiOS2Android {

    @JvmStatic
    fun main(args : Array<String>) {
        //1.新建目录 xhdpi  xxhdpi,不存在则新建
        //2. 遍历目标文件夹，通过@2x @3x结尾的文件 ，分割文件名，并且在xhdpi ,xxhdpi 分别copy 过去
        convert("\\\\192.168.6.88\\Design Sharing1\\GuildChat\\v1.7.1\\切图")

    }

    fun convert(path : String){
        if (path.length == 0) {
            return
        }

        val rootFile = File(path)
        val xhdpiFloder = File(rootFile,"xhdpi")
        val xxhdpiFloder = File(rootFile,"xxhdpi")

        if (!xhdpiFloder.exists()) {
            xhdpiFloder.mkdirs()
        }

        if (!xxhdpiFloder.exists()) {
            xxhdpiFloder.mkdirs()
        }

        val files = rootFile.listFiles()

        val method : (String) -> String = {
            val pos = it.lastIndexOf(".",it.length)
            it.substring(pos)
        }

        files.forEach {
            if (it.isFile) {
                if (it.name.contains("@2x")) {
                    val split2x = it.name.split("@2x")
                    val sub2xFile = File(xhdpiFloder,split2x[0] + method.invoke(it.name))
                    copyFile(it,sub2xFile)
                }

                if (it.name.contains("@3x")) {
                    val split3x = it.name.split("@3x")
                    val sub3xFile = File(xxhdpiFloder,split3x[0] + method.invoke(it.name))
                    copyFile(it,sub3xFile)
                }
            }
        }

    }

    fun copyFile(src : File,dst : File) {
        if (!dst.exists()) {
            dst.createNewFile()
        }

        val fis = FileInputStream(src)
        val fos = FileOutputStream(dst)

        val buffer = ByteArray(2048)
        var len = -1
        len = fis.read(buffer)
        while (len != -1) {
            fos.write(buffer,0,len)
            len = fis.read(buffer)
        }

        fos.flush()

        fis.close()
        fos.close()

    }

}