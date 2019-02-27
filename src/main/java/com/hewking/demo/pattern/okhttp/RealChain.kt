package com.hewking.demo.pattern.okhttp

class RealChain(var request: Request
                , var index: Int
                , var interceptors: List<Interceptor>) {

    fun proceed(rq: Request): Response {
        if (index + 1 > interceptors.size) {
            return Response(body = "妈卖批")
        }
        val next = RealChain(rq, index + 1, interceptors)
        val interceptor = interceptors[index]

        return interceptor.intercept(next)
    }

}