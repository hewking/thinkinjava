package string

object StatisticWords {

    @JvmStatic
    fun main(args : Array<String>) {
        val str = "fff  sdfs  sdf dsfs sdfs sdfs"
        val words = statistic(str)
        print(words)
    }

    private fun statistic(statments : String) : MutableList<String>{
        val words = mutableListOf<String>()
        var index = 0
        var count = 0
        var lastCh = ' '
        val sb = StringBuffer()
        while (index < statments.length ) {
            val ch = statments[index]
            index ++
            if (ch != ' ') {

            sb.append(ch)
            }
            if (ch == ' ' && lastCh != ' ') {
                count ++
                words.add(sb.toString())
                sb.setLength(0)
            }
            lastCh = ch
        }
        return words
    }

}