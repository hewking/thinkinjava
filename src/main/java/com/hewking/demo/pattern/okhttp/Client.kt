package com.hewking.demo.pattern.okhttp

/**
 * 模拟 okhttp 中的责任链模式
 * 1.需要构建Request
 * 2.创建 Interceptor
 * 3.构建链 RealChain
 * 4.通过链 获取Response
 */
object Client {

    @JvmStatic
    fun main(args: Array<String>) {

        val interceptor1 = object : Interceptor {
            override fun intercept(chain: RealChain): Response {
                val rq = chain.request
                rq.value++
                println("第1个拦截器")
                return chain.proceed(rq)
            }
        }

        val interceptor2 = object : Interceptor {
            override fun intercept(chain: RealChain): Response {
                val rq = chain.request
                rq.value++
                println("第2个拦截器")
                return chain.proceed(rq)
            }
        }

        val interceptor3 = object : Interceptor {
            override fun intercept(chain: RealChain): Response {
                val rq = chain.request
                rq.value++
                println("第3个拦截器")
                return chain.proceed(rq)
            }
        }

        val interceptor4 = object : Interceptor {
            override fun intercept(chain: RealChain): Response {
                val rq = chain.request
                rq.value++
                println("第4个拦截器")
                return chain.proceed(rq)
            }
        }

        val list = mutableListOf<Interceptor>(interceptor1, interceptor2, interceptor3, interceptor4)
        val request = Request(1)
        val chian = RealChain(request, 0, list)

        chian.proceed(request)

    }

}