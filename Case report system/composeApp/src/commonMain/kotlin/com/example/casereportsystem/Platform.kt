package com.example.casereportsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform