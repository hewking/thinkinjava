package com.hewking.utils


fun reduce(actionFunction: (accval: Int,value: Int)-> Int,list: List<Int>,initialValue:Int?) : Int{
    var accmount: Int
    var i = 0
    if (initialValue != null) {
        accmount = initialValue
    } else {
        accmount = list[i]
        i ++
    }
    while (i < list.size) {
        accmount = actionFunction(accmount,list[i])
        i ++
    }
    return accmount
}
