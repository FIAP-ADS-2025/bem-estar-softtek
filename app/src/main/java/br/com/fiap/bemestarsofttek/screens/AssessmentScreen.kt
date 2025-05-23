package br.com.fiap.bemestarsofttek.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.bemestarsofttek.model.*
import br.com.fiap.bemestarsofttek.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentScreen(
    onComplete: () -> Unit,
    onCancel: () -> Unit
) {
    var assessment by remember { mutableStateOf(DailyAssessment()) }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Avaliação Psicossocial",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Blue700
            )
            Text(
                text = "Responda às perguntas abaixo para avaliarmos seu bem-estar atual",
                fontSize = 16.sp,
                color = Gray600,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        
        // 1. Emoji
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "1. Escolha o seu emoji de hoje!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.height(160.dp)
                    ) {
                        items(EmojiChoice.values().toList()) { emoji ->
                            EmojiButton(
                                emoji = emoji,
                                isSelected = assessment.emojiChoice == emoji,
                                onClick = { assessment = assessment.copy(emojiChoice = emoji) }
                            )
                        }
                    }
                }
            }
        }
        
        // 2. Feeling
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "2. Como você se sente hoje?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    FeelingChoice.values().forEach { feeling ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = assessment.feelingChoice == feeling,
                                    onClick = { assessment = assessment.copy(feelingChoice = feeling) }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = assessment.feelingChoice == feeling,
                                onClick = { assessment = assessment.copy(feelingChoice = feeling) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = feeling.displayName)
                        }
                    }
                }
            }
        }
        
        // 3. Workload
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "3. Como você avalia sua carga de trabalho?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    WorkloadLevel.values().forEach { level ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = assessment.workloadLevel == level,
                                    onClick = { assessment = assessment.copy(workloadLevel = level) }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = assessment.workloadLevel == level,
                                onClick = { assessment = assessment.copy(workloadLevel = level) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = level.displayName)
                        }
                    }
                }
            }
        }
        
        // 4. Workload affects life
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "4. Sua carga de trabalho afeta sua qualidade de vida?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    FrequencyLevel.values().forEach { level ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = assessment.workloadAffectsLife == level,
                                    onClick = { assessment = assessment.copy(workloadAffectsLife = level) }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = assessment.workloadAffectsLife == level,
                                onClick = { assessment = assessment.copy(workloadAffectsLife = level) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = level.displayName)
                        }
                    }
                }
            }
        }
        
        // 5. Works overtime
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "5. Você trabalha além do seu horário regular?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    FrequencyLevel.values().forEach { level ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = assessment.worksOvertime == level,
                                    onClick = { assessment = assessment.copy(worksOvertime = level) }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = assessment.worksOvertime == level,
                                onClick = { assessment = assessment.copy(worksOvertime = level) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = level.displayName)
                        }
                    }
                }
            }
        }
        
        // 6. Has symptoms
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "6. Você tem apresentado sintomas como insônia, irritabilidade ou cansaço extremo?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    FrequencyLevel.values().forEach { level ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = assessment.hasSymptoms == level,
                                    onClick = { assessment = assessment.copy(hasSymptoms = level) }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = assessment.hasSymptoms == level,
                                onClick = { assessment = assessment.copy(hasSymptoms = level) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = level.displayName)
                        }
                    }
                }
            }
        }
        
        // 7. Mental health affects work
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "7. Você sente que sua saúde mental prejudica sua produtividade no trabalho?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    FrequencyLevel.values().forEach { level ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = assessment.mentalHealthAffectsWork == level,
                                    onClick = { assessment = assessment.copy(mentalHealthAffectsWork = level) }
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = assessment.mentalHealthAffectsWork == level,
                                onClick = { assessment = assessment.copy(mentalHealthAffectsWork = level) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = level.displayName)
                        }
                    }
                }
            }
        }
        
        // 8. Relationship with boss
        item {
            RatingCard(
                title = "8. Como está o seu relacionamento com seu chefe numa escala de 1 a 5?",
                subtitle = "(Sendo 01 - ruim e 05 - Ótimo)",
                rating = assessment.relationshipWithBoss,
                onRatingChange = { assessment = assessment.copy(relationshipWithBoss = it) }
            )
        }
        
        // 9. Relationship with colleagues
        item {
            RatingCard(
                title = "9. Como está o seu relacionamento com seus colegas de trabalho numa escala de 1 a 5?",
                subtitle = "(Sendo 01 - ruim e 05 - Ótimo)",
                rating = assessment.relationshipWithColleagues,
                onRatingChange = { assessment = assessment.copy(relationshipWithColleagues = it) }
            )
        }
        
        // 10. Respect from colleagues
        item {
            RatingCard(
                title = "10. Sinto que sou tratado(a) com respeito pelos meus colegas de trabalho.",
                rating = assessment.respectFromColleagues,
                onRatingChange = { assessment = assessment.copy(respectFromColleagues = it) }
            )
        }
        
        // 11. Team collaboration
        item {
            RatingCard(
                title = "11. Consigo me relacionar de forma saudável e colaborativa com minha equipe.",
                rating = assessment.teamCollaboration,
                onRatingChange = { assessment = assessment.copy(teamCollaboration = it) }
            )
        }
        
        // 12. Freedom to express
        item {
            RatingCard(
                title = "12. Tenho liberdade para expressar minhas opiniões sem medo de retaliações.",
                rating = assessment.freedomToExpress,
                onRatingChange = { assessment = assessment.copy(freedomToExpress = it) }
            )
        }
        
        // 13. Team welcoming
        item {
            RatingCard(
                title = "13. Me sinto acolhido(a) a parte do time onde trabalho.",
                rating = assessment.teamWelcoming,
                onRatingChange = { assessment = assessment.copy(teamWelcoming = it) }
            )
        }
        
        // 14. Team cooperation
        item {
            RatingCard(
                title = "14. Sinto que existe espírito de cooperação entre os colaboradores.",
                rating = assessment.teamCooperation,
                onRatingChange = { assessment = assessment.copy(teamCooperation = it) }
            )
        }
        
        // 15. Clear instructions
        item {
            RatingCard(
                title = "15. Recebo orientações claras e objetivas sobre minhas atividades e responsabilidades.",
                rating = assessment.clearInstructions,
                onRatingChange = { assessment = assessment.copy(clearInstructions = it) }
            )
        }
        
        // 16. Open communication with leadership
        item {
            RatingCard(
                title = "16. Sinto que posso me comunicar abertamente com minha liderança.",
                rating = assessment.openCommunicationWithLeadership,
                onRatingChange = { assessment = assessment.copy(openCommunicationWithLeadership = it) }
            )
        }
        
        // 17. Efficient information
        item {
            RatingCard(
                title = "17. As informações importantes circulam de forma eficiente dentro da empresa.",
                rating = assessment.efficientInformation,
                onRatingChange = { assessment = assessment.copy(efficientInformation = it) }
            )
        }
        
        // 18. Clear goals
        item {
            RatingCard(
                title = "18. Tenho clareza sobre as metas e os resultados esperados de mim.",
                rating = assessment.clearGoals,
                onRatingChange = { assessment = assessment.copy(clearGoals = it) }
            )
        }
        
        // 19. Leadership cares
        item {
            RatingCard(
                title = "19. Minha liderança demonstra interesse pelo meu bem-estar no trabalho",
                rating = assessment.leadershipCares,
                onRatingChange = { assessment = assessment.copy(leadershipCares = it) }
            )
        }
        
        // 20. Leadership available
        item {
            RatingCard(
                title = "20. Minha liderança está disponível para me ouvir quando necessário.",
                rating = assessment.leadershipAvailable,
                onRatingChange = { assessment = assessment.copy(leadershipAvailable = it) }
            )
        }
        
        // 21. Comfortable reporting
        item {
            RatingCard(
                title = "21. Me sinto confortável para reportar problemas ou dificuldades ao meu líder",
                rating = assessment.comfortableReporting,
                onRatingChange = { assessment = assessment.copy(comfortableReporting = it) }
            )
        }
        
        // 22. Recognized by leadership
        item {
            RatingCard(
                title = "22. Minha liderança reconhece minhas entregas e esforços",
                rating = assessment.recognizedByLeadership,
                onRatingChange = { assessment = assessment.copy(recognizedByLeadership = it) }
            )
        }
        
        // 23. Trust in leadership
        item {
            RatingCard(
                title = "23. Existe confiança e transparência na relação com minha liderança",
                rating = assessment.trustInLeadership,
                onRatingChange = { assessment = assessment.copy(trustInLeadership = it) }
            )
        }
        
        // Observations
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Observações adicionais (opcional)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    OutlinedTextField(
                        value = assessment.observations,
                        onValueChange = { assessment = assessment.copy(observations = it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        placeholder = { 
                            Text("Descreva qualquer situação específica que possa estar afetando seu bem-estar...") 
                        },
                        maxLines = 5
                    )
                }
            }
        }
        
        // Action buttons
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text("Cancelar")
                }
                
                Button(
                    onClick = {
                        val entry = MoodEntryEntity(
                            date = LocalDate.now(),
                            emoji = assessment.emojiChoice?.emoji ?: "🙂",
                            mood = assessment.emojiChoice?.displayName ?: "Neutro",
                            feeling = assessment.feelingChoice?.displayName ?: "Indefinido",
                            workload = assessment.workloadLevel?.displayName ?: "Média",
                            symptoms = assessment.hasSymptoms?.displayName ?: "Raramente",
                            bossRelationship = assessment.relationshipWithBoss,
                            colleaguesRelationship = assessment.relationshipWithColleagues,
                            observations = assessment.observations
                        )

                        viewModel.addMoodEntry(entry)

                        onComplete()
                    }
                    ,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue600),
                    enabled = assessment.emojiChoice != null && 
                            assessment.feelingChoice != null && 
                            assessment.workloadLevel != null
                ) {
                    Text("Enviar Avaliação", color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun EmojiButton(
    emoji: EmojiChoice,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Blue600 else Color.Transparent,
            contentColor = if (isSelected) Color.White else Gray700
        ),
        shape = RoundedCornerShape(8.dp),
        border = if (!isSelected) ButtonDefaults.outlinedButtonBorder(enabled = true) else null
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = emoji.emoji,
                fontSize = 32.sp
            )
            Text(
                text = emoji.displayName,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun RatingCard(
    title: String,
    subtitle: String? = null,
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            
            subtitle?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = Gray600,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            RatingBar(
                rating = rating,
                onRatingChange = onRatingChange
            )
        }
    }
}

@Composable
private fun RatingBar(
    rating: Int,
    onRatingChange: (Int) -> Unit,
    maxRating: Int = 5
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (1..maxRating).forEach { index ->
            Button(
                onClick = { onRatingChange(index) },
                modifier = Modifier.size(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (index <= rating) Blue600 else Gray200,
                    contentColor = if (index <= rating) Color.White else Gray600
                ),
                shape = RoundedCornerShape(24.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = index.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
} 