package br.com.fiap.bemestarsofttek.network

object ApiConfig {
    const val BASE_URL = NetworkConfig.BASE_URL
    const val TIMEOUT_SECONDS = NetworkConfig.TIMEOUT_SECONDS
    
    // Endpoints
    object Endpoints {
        const val AUTH = "/usuarios/autenticar"
        const val MOOD_ENTRIES = "/api/mood-entries"
        const val ASSESSMENTS = "/api/assessments"
        const val EMPLOYEE_PROFILES = "/api/employee-profiles"
        const val RESOURCES = "/api/resources"
        const val NOTIFICATIONS = "/api/notifications"
        const val ANALYTICS = "/api/analytics"
    }
}
