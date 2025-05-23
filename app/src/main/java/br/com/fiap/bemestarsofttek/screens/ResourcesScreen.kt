package br.com.fiap.bemestarsofttek.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.bemestarsofttek.ui.theme.*

data class Resource(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val content: String
)

@Composable
fun ResourcesScreen() {
    val resources = listOf(
        Resource(
            title = "Técnicas de Respiração",
            description = "Aprenda exercícios simples para controlar ansiedade e estresse",
            icon = Icons.Default.SelfImprovement,
            color = Blue600,
            content = "1. Respiração 4-7-8: Inspire por 4 segundos, segure por 7, expire por 8.\n" +
                    "2. Respiração abdominal: Coloque uma mão no peito e outra no abdômen.\n" +
                    "3. Respiração quadrada: Inspire, segure, expire e pause - cada fase por 4 segundos."
        ),
        Resource(
            title = "Exercícios Físicos",
            description = "Atividades físicas que melhoram o bem-estar mental",
            icon = Icons.Default.FitnessCenter,
            color = Green500,
            content = "• Caminhada de 30 minutos diários\n" +
                    "• Yoga ou pilates\n" +
                    "• Exercícios de alongamento\n" +
                    "• Dança ou atividades recreativas\n" +
                    "• Exercícios aeróbicos leves"
        ),
        Resource(
            title = "Mindfulness",
            description = "Práticas de atenção plena para reduzir ansiedade",
            icon = Icons.Default.Psychology,
            color = Color(0xFF8B5CF6), // Purple
            content = "• Meditação de 5-10 minutos diários\n" +
                    "• Observação da respiração\n" +
                    "• Escaneamento corporal\n" +
                    "• Atenção plena nas atividades cotidianas\n" +
                    "• Apps de meditação recomendados"
        ),
        Resource(
            title = "Apoio Social",
            description = "A importância das conexões sociais para a saúde mental",
            icon = Icons.Default.Group,
            color = Orange500,
            content = "• Converse com amigos e familiares\n" +
                    "• Participe de grupos de apoio\n" +
                    "• Procure ajuda profissional quando necessário\n" +
                    "• Mantenha contato regular com pessoas queridas\n" +
                    "• Não hesite em pedir ajuda"
        ),
        Resource(
            title = "Leituras Recomendadas",
            description = "Livros e artigos sobre saúde mental e bem-estar",
            icon = Icons.Default.Book,
            color = Blue700,
            content = "• 'O Poder do Agora' - Eckhart Tolle\n" +
                    "• 'Ansiedade: Como enfrentar o mal do século' - Augusto Cury\n" +
                    "• 'Inteligência Emocional' - Daniel Goleman\n" +
                    "• 'Mindset' - Carol Dweck\n" +
                    "• Artigos sobre neurociência e bem-estar"
        ),
        Resource(
            title = "Vídeos e Podcasts",
            description = "Conteúdo audiovisual sobre saúde mental",
            icon = Icons.Default.VideoLibrary,
            color = Red500,
            content = "• TED Talks sobre saúde mental\n" +
                    "• Podcasts de psicologia positiva\n" +
                    "• Canais do YouTube sobre mindfulness\n" +
                    "• Aplicativos de meditação guiada\n" +
                    "• Documentários sobre bem-estar"
        )
    )
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Recursos para Bem-Estar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Blue700
            )
            Text(
                text = "Descubra ferramentas e técnicas para cuidar da sua saúde mental",
                fontSize = 16.sp,
                color = Gray600,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        
        items(resources) { resource ->
            ResourceCard(resource = resource)
        }
        
        item {
            // Card de contato profissional
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Blue50),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Precisa de Ajuda Profissional?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Blue700
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Se você está passando por um momento difícil, procure ajuda profissional. " +
                                "A Softtek oferece suporte psicológico para todos os funcionários.",
                        fontSize = 14.sp,
                        color = Gray700
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { /* Abrir contatos de apoio */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Blue600),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Contatos de Apoio", color = Color.White)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ResourceCard(resource: Resource) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = resource.icon,
                    contentDescription = resource.title,
                    tint = resource.color,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = resource.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = resource.description,
                        fontSize = 14.sp,
                        color = Gray600
                    )
                }
            }
            
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = resource.content,
                    fontSize = 14.sp,
                    color = Gray700,
                    lineHeight = 20.sp
                )
            }
        }
    }
} 