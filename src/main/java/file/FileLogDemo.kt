package file

import org.jetbrains.annotations.NotNull
import java.io.*
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

object FileLogDemo {

    @JvmField
    val format = "yyyyMMdd hh:mm:ss.SSS"
    @JvmField
    val formater = SimpleDateFormat(format)

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("\\debug2.log")
//        append(file,"--end--")
//        append(file,"\r\n")
//        appendDate(file,"excption")

        val index = indexEnd2(file, object : OnEndListener {
            override fun accept(rule: String): Boolean {
                return rule == "--end--"
            }
        })

        val text = FileUtils.subShrink("\\debug2.log", 12,/*file.length().toInt() -*/ index.toInt() /*-1*/)

        println("text $text")
    }

    @JvmStatic
    fun appendDate(file: File, log: String) {
        val sb = StringBuilder()
        sb.append(formater.format(Date()))
        sb.appendln(log)
        append(file, sb.toString())
    }

    fun append(file: File, msg: String) {
        if (!file.exists()) {
            if (!file.parentFile.exists()) {
                file.mkdirs()
            } else {
                file.mkdir()
            }
        }

        val buffer = BufferedWriter(FileWriter(file, true))
        buffer.write(msg)
        buffer.flush()
        buffer.close()
    }

    @NotNull
    fun read(file: File): String {
        val fis = FileInputStream(file)
        val bis = BufferedInputStream(fis)
        val bos = ByteArrayOutputStream()
        val bbos = BufferedOutputStream(bos)

        var len = -1
        val buff = ByteArray(2048)
        len = bis.read(buff)
        while (len != -1) {
            bbos.write(buff, 0, len)
            len = bis.read(buff)
        }
        bbos.flush()
        bbos.close()
        return bos.toString("UTF-8")
    }

    @NotNull
    fun readText(file: File): String {
        val reader = BufferedInputStream(FileInputStream(file)).bufferedReader()
        val baos = ByteArrayOutputStream()
        val writer = BufferedOutputStream(baos).bufferedWriter()
        var line = reader.readLine()
        while (line != null) {
            if (line == "--end--") {
                break
            }
            writer.write(line)
            line = reader.readLine()
        }
        writer.flush()
        reader.close()
        writer.close()
        return baos.toString()
    }

    @NotNull
    fun indexEnd(file: File, filter: OnEndListener): Long {
        val bw = BufferedReader(FileReader(file))
        var offset = 0L
        var line = bw.readLine()
        while (line != null) {
            if (filter.accept(line)) {
                break
            }
            offset += line.length
            line = bw.readLine()
        }
        return offset
    }

    @NotNull
    fun readFromEnd(file: File, filter: OnEndListener): String {
        val baos = ByteArrayOutputStream()
        val bos = BufferedOutputStream(baos).bufferedWriter()
        val rf = RandomAccessFile(file, "r")
        val start = rf.filePointer
        val length = rf.length()
        var index = start + length - 1
        rf.seek(index)
        var c = -1
        val result = ""
        while (index > start) {
            c = rf.read()
            if (c == '\r'.toInt() || c == '\n'.toInt()) {
                val readLine = rf.readLine()
                if (readLine == null) {
                    continue
                }
                val line = String(readLine.toByteArray(Charset.forName("ISO8859-1")))
                if (filter.accept(line)) {
                    break
                }
                println(line)
                bos.write(line)
                index--
            }
            index--
            rf.seek(index)
        }

        bos.flush()
        bos.close()
        baos.flush()
        baos.close()
        return baos.toString("ISO8859-1")
    }

    @NotNull
    fun indexEnd2(file: File, filter: OnEndListener): Long {
        val rf = RandomAccessFile(file, "rw")
        val start = rf.filePointer
        val len = rf.length()
        var index = start + len - 1
        var c = -1
        rf.seek(index)
        while (index > start) {
            c = rf.read()
            if (c == '\n'.toInt() || c == '\r'.toInt()) {
                val line = rf.readLine()
                if (filter.accept(line)) {
                    break
                }
                index--
            }
            index--
            rf.seek(index)
        }
        return index

    }

    interface OnEndListener {
        fun accept(rule: String): Boolean
    }

}