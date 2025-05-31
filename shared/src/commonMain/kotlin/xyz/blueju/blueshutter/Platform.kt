package xyz.blueju.blueshutter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
