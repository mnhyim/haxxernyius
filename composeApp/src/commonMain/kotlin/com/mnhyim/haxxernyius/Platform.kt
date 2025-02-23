package com.mnhyim.haxxernyius

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform