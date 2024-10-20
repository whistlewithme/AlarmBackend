package com.whistlewithme.alarm.helper

import com.whistlewithme.alarm.config.Config
import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger

object AppContext {

    private const val ENVIRONMENT = "test"
    private lateinit var config: Config  // Use lateinit for better safety
    private var loggerEnabled: Boolean = false
    private val logger: Logger = Logger.getLogger(AppContext::class.java.name)

    fun isLoggerEnabled(): Boolean {
        return loggerEnabled
    }

    fun getProp(prop: String): String? {
        return config.getString(prop)  // Removed nullable check; config should be initialized
    }

    @Throws(RuntimeException::class)
    fun init(arguments: Array<String>) {
        // Ensure config is initialized
        config = Config()

        if (arguments.isEmpty()) {
            throw RuntimeException("Configuration file is not provided")
        }

        // Load the configuration files
        val loadingErrors = mutableListOf<String>()  // Collect errors
        arguments.forEach { arg ->
            try {
                config.load(arg)
                logger.log(Level.INFO, "Loaded configuration from: $arg")
            } catch (e: IOException) {
                loadingErrors.add("Failed to load configuration from $arg: ${e.message}")
                logger.log(Level.WARNING, "Some exception while loading property file: ${e.message}")
            }
        }

        if (loadingErrors.isNotEmpty()) {
            throw RuntimeException("Errors loading configuration: ${loadingErrors.joinToString(", ")}")
        }

        // Set up logger if enabled
        loggerEnabled = config.getBoolean("logger.enable") ?: false
        if (loggerEnabled) {
            try {
                LogHelper.setupLogger(config)
            } catch (e: IOException) {
                logger.log(Level.WARNING, "Some exception while setting up logging tool.")
            }
        }

        // Load environment variables
        setSystemEnvironmentVariables(config)
    }

    private fun setSystemEnvironmentVariables(config: Config) {
        // Load environment variables from the system
        val envVariables = System.getenv()
        envVariables.forEach { (key, value) ->
            config.put(key, value)
        }
    }

    fun getEnvironment(): String {
        return config.getString("environment") ?: ENVIRONMENT
    }
}
