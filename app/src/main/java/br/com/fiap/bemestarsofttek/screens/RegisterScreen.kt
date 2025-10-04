package br.com.fiap.bemestarsofttek.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.bemestarsofttek.network.AuthManager
import br.com.fiap.bemestarsofttek.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {
    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }
    val coroutineScope = rememberCoroutineScope()
    
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo/Título
        Text(
            text = "BemEstar Softtek",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Blue700,
            textAlign = TextAlign.Center
        )
        
        Text(
            text = "Crie sua conta para continuar",
            fontSize = 16.sp,
            color = Gray600,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )
        
        // Formulário de Cadastro
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo Nome
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome completo") },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = "Nome")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = name.isNotEmpty() && name.length < 3
                )
                
                if (name.isNotEmpty() && name.length < 3) {
                    Text(
                        text = "Nome deve ter pelo menos 3 caracteres",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                
                // Campo Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = "Email")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = email.isNotEmpty() && !isValidEmail(email)
                )
                
                if (email.isNotEmpty() && !isValidEmail(email)) {
                    Text(
                        text = "Digite um email válido",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                
                // Campo Senha
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = "Senha")
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = password.isNotEmpty() && password.length < 6
                )
                
                if (password.isNotEmpty() && password.length < 6) {
                    Text(
                        text = "Senha deve ter pelo menos 6 caracteres",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                
                // Campo Confirmar Senha
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmar senha") },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = "Confirmar senha")
                    },
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (confirmPasswordVisible) "Ocultar senha" else "Mostrar senha"
                            )
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = confirmPassword.isNotEmpty() && password != confirmPassword
                )
                
                if (confirmPassword.isNotEmpty() && password != confirmPassword) {
                    Text(
                        text = "As senhas não coincidem",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                
                // Mensagem de erro
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                // Mensagem de sucesso
                if (successMessage.isNotEmpty()) {
                    Text(
                        text = successMessage,
                        color = Color.Green,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                // Botão de Cadastro
                Button(
                    onClick = {
                        if (validateForm(name, email, password, confirmPassword)) {
                            isLoading = true
                            errorMessage = ""
                            successMessage = ""
                            
                            // Chamada da API com coroutines
                            coroutineScope.launch {
                                authManager.register(name, email, password)
                                    .onSuccess { registerResponse ->
                                        isLoading = false
                                        successMessage = "Conta criada com sucesso! Redirecionando..."
                                        
                                        // Aguardar um pouco e redirecionar
                                        kotlinx.coroutines.delay(2000)
                                        onRegisterSuccess()
                                    }
                                    .onFailure { exception ->
                                        isLoading = false
                                        errorMessage = exception.message ?: "Erro no cadastro"
                                    }
                            }
                        } else {
                            errorMessage = "Preencha todos os campos corretamente"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White
                        )
                    } else {
                        Text(
                            text = "Criar Conta",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
        
        // Link para login
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Já tem uma conta? Faça login",
                color = Blue600,
                fontSize = 14.sp
            )
        }
    }
}

private fun validateForm(
    name: String,
    email: String,
    password: String,
    confirmPassword: String
): Boolean {
    return name.length >= 3 &&
            isValidEmail(email) &&
            password.length >= 6 &&
            password == confirmPassword
}

private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
