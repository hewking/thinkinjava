package com.hewking.synthetic

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

/**
 *@Description:
 *@Author: jianhao
 *@Date:   2021-09-03 15:30
 *@License: Copyright Since 2020 Hive Box Technology. All rights reserved.
 *@Notice: This content is limited to the internal circulation of
 *  Hive Box, and it is prohibited to leak or used for other commercial purposes.
 */
class XmlPullParserHandler {

    private val ids = ArrayList<String>()

    fun parse(inputStream: InputStream): List<String> {
        val saf = SAXParserFactory.newInstance()
        val parser = saf.newSAXParser()
        parser.parse(inputStream, MyHandler())
        return ids
    }

    inner class MyHandler : DefaultHandler() {

        override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
            super.startElement(uri, localName, qName, attributes)
            val id = attributes?.getValue("android:id") ?: ""
            if(id.isNotEmpty()) {
                val index = id.indexOf("/")
                if (index != -1) {
                    ids.add(id.substring(index + 1))
                }
            }
        }

        override fun endDocument() {
            super.endDocument()
        }

    }

}