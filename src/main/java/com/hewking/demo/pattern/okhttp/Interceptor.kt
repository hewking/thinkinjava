package com.hewking.demo.pattern.okhttp

interface Interceptor {

    fun intercept(chain: RealChain): Response

}