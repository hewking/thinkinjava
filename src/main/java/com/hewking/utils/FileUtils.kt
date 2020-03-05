package com.hewking.utils

import java.io.File

object FileUtils {


    fun rename(file: File, subfix: String = ""): Boolean {
//        val com.hewking.demo.file = File(path)
        if (!file.exists()) {
            return false
        }

        if (file.isDirectory) {
            for (subFile in file.listFiles()) {
                rename(subFile, subfix)
            }
        } else {
            file.renameTo(File(file.parentFile, "${trueName(file.name.toLowerCase()) + subfix}"))
//            com.hewking.demo.file.renameTo(File(com.hewking.demo.file.parentFile,com.hewking.demo.file.name.substring(0,com.hewking.demo.file.name.length - 2)))
        }
        return true
    }

    fun trueName(name: String): String {
        val i = name.lastIndexOf(".")
        if (i < 0) {
            return name
        }
        return name.substring(0, i)
    }


    @JvmStatic
    fun main(args: Array<String>) {
        rename(File("E:\\private_project\\Ope\\UI\\ui\\币LOGO. 80px"), "_80.png")
    }

}