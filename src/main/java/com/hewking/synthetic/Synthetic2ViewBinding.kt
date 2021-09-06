package com.hewking.synthetic

import com.hewking.synthetic.XmlPullParserHandler
import com.hewking.utils.StringUtil
import org.junit.Test
import java.io.File
import java.io.FileInputStream

/**
 *@Description:
 *@Author: jianhao
 *@Date:   2021-09-03 15:09
 *@License: Copyright Since 2020 Hive Box Technology. All rights reserved.
 *@Notice: This content is limited to the internal circulation of
 *  Hive Box, and it is prohibited to leak or used for other commercial purposes.
 */
class Synthetic2ViewBinding {

    @Test
    fun test() {
        val path = "E:\\private_project\\AndroidDeveloper\\app\\src\\main\\res\\layout\\activity_compat_androidq.xml"
        val srcPath = "E:\\private_project\\AndroidDeveloper\\app\\src\\main\\java\\com\\hewking\\develop\\demo\\CompatAndroidQActivity.kt"

        val parserHandler = XmlPullParserHandler()
        val fis = FileInputStream(path)
        val ids = parserHandler.parse(fis)
        println(ids)

        val idBindingMap = mutableMapOf<String,String>()
        val bindingIds = ids.map {
            val sb = StringBuffer()
            sb.append("binding.")
            val words = it.split("_")
            var len = words.size
            while (len > 0) {
                sb.append(if (len == words.size) words[words.size - len]
                else StringUtil.toUpperCase4Index(words[words.size - len]))
                len--
            }
            Pair(it, sb.toString())
        }.fold(idBindingMap) { r, t ->
            r[t.first] = t.second
            r
        }

        val srcFile = File(srcPath)
        var srcText = srcFile.readText()

        bindingIds.forEach { (k, v) ->
            srcText = srcText.replace(k, v)
        }

        srcFile.writeText(srcText)

        println(bindingIds)

    }

}