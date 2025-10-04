# Integração API Bem-Estar Softtek

## Configuração

### 1. URL da API
Para alterar a URL da API, edite o arquivo `app/src/main/java/br/com/fiap/bemestarsofttek/network/NetworkConfig.kt`:

```kotlin
object NetworkConfig {
    // Para desenvolvimento local (não funciona no emulador)
    // const val BASE_URL = "http://localhost:8080"
    
    // Para emulador Android (RECOMENDADO)
    const val BASE_URL = "http://10.0.2.2:8080"
    
    // Para dispositivo físico (substitua pelo IP da sua máquina)
    // const val BASE_URL = "http://192.168.1.100:8080"
}
```

**⚠️ Importante**: 
- **Emulador Android**: Use `10.0.2.2:8080` (já configurado)
- **Dispositivo físico**: Use o IP da sua máquina na rede local
- **localhost**: Não funciona no emulador Android

### 2. Configuração de Segurança de Rede
O app está configurado para permitir comunicação HTTP com localhost (necessário para desenvolvimento). A configuração está em `app/src/main/res/xml/network_security_config.xml`:

```xml
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">localhost</domain>
        <domain includeSubdomains="true">10.0.2.2</domain>
        <domain includeSubdomains="true">127.0.0.1</domain>
    </domain-config>
</network-security-config>
```

### 3. Logs de Rede
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
- **Redirecionamento automático para login em caso de 401**

### ✅ Assessments
- Envio de avaliações para a API
- Salvamento local como backup
- Feedback visual de sucesso/erro

### ✅ Estrutura de Rede
- Retrofit para comunicação HTTP
- OkHttp para interceptors e logging
- Gson para serialização JSON
- Timeout configurável
- **Token adicionado automaticamente em todas as requisições**
- **Interceptor de autenticação que detecta 401 e faz logout automático**

## Como Testar

1. **Inicie o servidor da API** na porta 8080
2. **Execute o app** no emulador ou dispositivo
3. **Faça login** com credenciais válidas
4. **Complete uma avaliação** e verifique se foi enviada para a API

### Testando Redirecionamento Automático (401)

1. **Faça login** no app
2. **Simule token expirado** (pare a API ou use token inválido)
3. **Tente fazer uma requisição** (completar avaliação)
4. **O app deve redirecionar automaticamente** para a tela de login

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
