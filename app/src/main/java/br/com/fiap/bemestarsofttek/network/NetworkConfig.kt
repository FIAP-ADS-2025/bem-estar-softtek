package br.com.fiap.bemestarsofttek.network

object NetworkConfig {
    // Configuração da API - Fácil de alterar
    // const val BASE_URL = "http://localhost:8080"
    
    // Para desenvolvimento com emulador Android, use:
    const val BASE_URL = "https://arsenous-daina-exigent.ngrok-free.dev"
    
    // Para dispositivo físico, use o IP da sua máquina:
    // const val BASE_URL = "http://192.168.1.100:8080"
    
    const val TIMEOUT_SECONDS = 30L
    const val LOG_LEVEL = "BODY" // NONE, BASIC, HEADERS, BODY
}
