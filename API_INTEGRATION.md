# Integração API Bem-Estar Softtek

## Configuração

### 1. URL da API
Para alterar a URL da API, edite o arquivo `app/src/main/java/br/com/fiap/bemestarsofttek/network/NetworkConfig.kt`:

```kotlin
object NetworkConfig {
    // Para desenvolvimento local
    const val BASE_URL = "http://localhost:8080"
    
    // Para emulador Android
    // const val BASE_URL = "http://10.0.2.2:8080"
    
    // Para dispositivo físico (substitua pelo IP da sua máquina)
    // const val BASE_URL = "http://192.168.1.100:8080"
}
```

### 2. Logs de Rede
Para controlar os logs de rede, altere o `LOG_LEVEL` no mesmo arquivo:
- `NONE`: Sem logs
- `BASIC`: Logs básicos
- `HEADERS`: Logs com headers
- `BODY`: Logs completos (recomendado para desenvolvimento)

## Funcionalidades Implementadas

### ✅ Autenticação
- Tela de login integrada
- Armazenamento seguro do token JWT
- Verificação automática de login

### ✅ Assessments
- Envio de avaliações para a API
- Salvamento local como backup
- Feedback visual de sucesso/erro

### ✅ Estrutura de Rede
- Retrofit para comunicação HTTP
- OkHttp para interceptors e logging
- Gson para serialização JSON
- Timeout configurável

## Como Testar

1. **Inicie o servidor da API** na porta 8080
2. **Execute o app** no emulador ou dispositivo
3. **Faça login** com credenciais válidas
4. **Complete uma avaliação** e verifique se foi enviada para a API

## Próximos Passos

Para implementar outras funcionalidades da API:

1. **Mood Entries**: Use `MoodEntryService` e `MoodEntryRepository`
2. **Resources**: Use `ResourcesService` (a ser implementado)
3. **Analytics**: Use `AnalyticsService` (a ser implementado)
4. **Notifications**: Use `NotificationsService` (a ser implementado)

## Estrutura de Arquivos

```
app/src/main/java/br/com/fiap/bemestarsofttek/
├── network/
│   ├── ApiClient.kt              # Cliente Retrofit
│   ├── ApiConfig.kt              # Configurações da API
│   ├── NetworkConfig.kt          # Configurações de rede
│   ├── AuthManager.kt            # Gerenciador de autenticação
│   ├── dto/                      # Data Transfer Objects
│   │   ├── AuthDto.kt
│   │   ├── AssessmentDto.kt
│   │   └── MoodEntryDto.kt
│   └── service/                  # Interfaces Retrofit
│       ├── AuthService.kt
│       ├── AssessmentService.kt
│       └── MoodEntryService.kt
├── repository/
│   └── AssessmentRepository.kt   # Repositório de assessments
└── viewmodel/
    └── AssessmentViewModel.kt    # ViewModel para assessments
```

## Troubleshooting

### Erro de Conexão
- Verifique se a API está rodando
- Confirme a URL no `NetworkConfig.kt`
- Para emulador, use `10.0.2.2:8080`
- Para dispositivo físico, use o IP da máquina

### Erro de Autenticação
- Verifique as credenciais
- Confirme se o token está sendo salvo
- Verifique os logs de rede para detalhes

### Erro de Compilação
- Execute `./gradlew clean build`
- Verifique se todas as dependências estão corretas
- Confirme se o KSP está configurado
