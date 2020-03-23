package com.hewking.test

//import io.reactivex.Observable
import com.hewking.demo.pattern.singleton.Singleton
import org.junit.Test

class Demo3 {

    @Test
    fun test() {
//        Observable.create<String> {
//            it.onNext("start")
//            Thread {
//                try {
//                    System.out.println("start open ...")
//                    it.onNext("start open ...")
//                    val stream = URL("https://www.baidu.com").openStream()
//                    System.out.println("after url ...")
//                    it.onNext("after url")
//                    val br = stream.bufferedReader()
//                    if (!it.isDisposed) {
//                        var text = br.readText()
//                        it.onNext(text)
//                    }
//                    stream.close()
//                    br.close()
//                    it.onNext("after open ...")
//                    if (!it.isDisposed) {
//                        it.onComplete()
//                    }
//                } catch (e: java.lang.Exception) {
//                    System.out.println(e)
//                    e.printStackTrace()
//                    it.onError(e)
//                }
//            }.start()
//        }.subscribe(System.out::println) {
//            it.printStackTrace()
//            System.out.println("what the fuck")
//        }
    }

    @Test
    fun test2() {
        Singleton.getInstance()
    }

}