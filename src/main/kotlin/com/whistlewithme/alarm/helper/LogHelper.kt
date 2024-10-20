package com.whistlewithme.alarm.helper

import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger

object LogHelper {
    private val logger: Logger = Logger.getLogger(LogHelper::class.java.name)

    @Throws(IOException::class)
    fun setupLogger(config: com.whistlewithme.alarm.config.Config?) {
        val logLevel = config?.getString("logger.level") ?: "INFO"

        // Set the log level based on the config
        when (logLevel.toUpperCase()) {
            "SEVERE" -> logger.level = Level.SEVERE
            "WARNING" -> logger.level = Level.WARNING
            "INFO" -> logger.level = Level.INFO
            "CONFIG" -> logger.level = Level.CONFIG
            "FINE" -> logger.level = Level.FINE
            "FINER" -> logger.level = Level.FINER
            "FINEST" -> logger.level = Level.FINEST
            else -> logger.level = Level.INFO
        }

        logger.log(Level.INFO, "Logger initialized with level: $logLevel")
    }
}
