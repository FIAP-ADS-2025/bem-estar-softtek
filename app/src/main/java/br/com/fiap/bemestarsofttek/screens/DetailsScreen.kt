package br.com.fiap.bemestarsofttek.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.bemestarsofttek.model.MoodEntry
import br.com.fiap.bemestarsofttek.model.WeeklyMoodData
import br.com.fiap.bemestarsofttek.ui.theme.*
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen() {
    var showHistory by remember { mutableStateOf(false) }
    var selectedRecord by remember { mutableStateOf<MoodEntry?>(null) }
    
    val mockWeeklyData = listOf(
        WeeklyMoodData("Seg", 8, 5, 2),
        WeeklyMoodData("Ter", 7, 6, 3),
        WeeklyMoodData("Qua", 5, 4, 5),
        WeeklyMoodData("Qui", 6, 7, 3),
        WeeklyMoodData("Sex", 8, 8, 1),
        WeeklyMoodData("Sáb", 4, 3, 6),
        WeeklyMoodData("Dom", 3, 2, 7)
    )
    
    val mockHistoricalData = listOf(
        MoodEntry(
            id = 1,
            date = LocalDate.now().minusDays(7),
            emoji = "Triste",
            mood = "Triste",
            feeling = "Estressado",
            workload = "Alta",
            symptoms = "Frequentemente",
            bossRelationship = 2,
            colleaguesRelationship = 3,
            observations = "Semana com muitas entregas e deadlines apertados."
        ),
        MoodEntry(
            id = 2,
            date = LocalDate.now().minusDays(14),
            emoji = "Cansado",
            mood = "Cansado",
            feeling = "Preocupado",
            workload = "Muito Alta",
            symptoms = "Frequentemente",
            bossRelationship = 3,
            colleaguesRelationship = 4,
            observations = "Projeto com cliente difícil."
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
                text = "Detalhes do Bem-Estar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Blue700
            )
        }
        
        item {
            // Card de Acompanhamento
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Acompanhamento do Bem-Estar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Visualize a evolução do seu estado emocional ao longo da semana",
                        fontSize = 14.sp,
                        color = Gray600,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Gráfico simplificado
                    WeeklyChart(data = mockWeeklyData)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = { showHistory = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Gray200),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Ver histórico completo", color = Gray700)
                    }
                }
            }
        }
        
        item {
            // Card de Resultado da Última Avaliação
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Resultado da Última Avaliação",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Consolidado das suas últimas 10 avaliações",
                        fontSize = 14.sp,
                        color = Gray600,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Resultado atual
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Orange100)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Resultado atual: Nível Agudo",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Sentimento intenso e profundo, afetando sua qualidade de vida e bem-estar.",
                                fontSize = 14.sp,
                                color = Gray700,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            // Barra de progresso
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(16.dp)
                                    .background(Gray200, RoundedCornerShape(8.dp))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .fillMaxHeight()
                                        .background(Red500, RoundedCornerShape(8.dp))
                                )
                            }
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "0%", fontSize = 12.sp, color = Gray600)
                                Text(text = "50%", fontSize = 12.sp, color = Gray600)
                                Text(text = "100%", fontSize = 12.sp, color = Gray600)
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Métricas
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = Gray50)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Ansioso",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "80%",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Orange500
                                )
                            }
                        }
                        
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = Gray50)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Estressado",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "70%",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Orange500
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    
    // Modal para histórico
    if (showHistory) {
        AlertDialog(
            onDismissRequest = { showHistory = false },
            title = { Text("Histórico Completo de Avaliações") },
            text = {
                LazyColumn {
                    items(mockHistoricalData.size) { index ->
                        val record = mockHistoricalData[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = record.date.toString(), fontWeight = FontWeight.Medium)
                                    Text(text = "${record.emoji} - ${record.feeling}", color = Gray600)
                                }
                                IconButton(onClick = { selectedRecord = record }) {
                                    Icon(Icons.Default.Visibility, contentDescription = "Ver detalhes")
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showHistory = false }) {
                    Text("Fechar")
                }
            }
        )
    }
    
    // Modal para detalhes do registro
    selectedRecord?.let { record ->
        AlertDialog(
            onDismissRequest = { selectedRecord = null },
            title = { Text("Detalhes da Avaliação - ${record.date}") },
            text = {
                Column {
                    Text("Emoji: ${record.emoji}")
                    Text("Sentimento: ${record.feeling}")
                    Text("Carga de Trabalho: ${record.workload}")
                    Text("Sintomas: ${record.symptoms}")
                    Text("Observações: ${record.observations}")
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedRecord = null }) {
                    Text("Fechar")
                }
            }
        )
    }
}

@Composable
private fun WeeklyChart(data: List<WeeklyMoodData>) {
    Column {
        data.forEach { dayData ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dayData.day,
                    modifier = Modifier.width(40.dp),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    // Barra Ansioso
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .weight(dayData.anxious.toFloat())
                            .background(Orange500, RoundedCornerShape(2.dp))
                    )
                    // Barra Estressado
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .weight(dayData.stressed.toFloat())
                            .background(Red500, RoundedCornerShape(2.dp))
                    )
                    // Barra Alegre
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .weight(dayData.happy.toFloat())
                            .background(Green500, RoundedCornerShape(2.dp))
                    )
                }
            }
        }
        
        // Legenda
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LegendItem(color = Orange500, label = "Ansioso")
            LegendItem(color = Red500, label = "Estressado")
            LegendItem(color = Green500, label = "Alegre")
        }
    }
}

@Composable
private fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, fontSize = 12.sp)
    }
} 