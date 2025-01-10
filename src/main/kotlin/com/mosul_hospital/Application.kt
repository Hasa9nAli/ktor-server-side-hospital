package com.mosul_hospital

import com.mosul_hospital.database.DatabaseFactory
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {

    configureSerialization()
    DatabaseFactory.init()
    configureDatabases()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
