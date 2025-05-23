package br.com.fiap.bemestarsofttek.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.bemestarsofttek.model.*
import br.com.fiap.bemestarsofttek.ui.theme.*
import br.com.fiap.bemestarsofttek.viewmodel.MoodEntryViewModel
import br.com.fiap.bemestarsofttek.viewmodel.MoodEntryViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodTrackerScreen() {
    val mockMoodOptions = listOf(
        MoodOption("Muito Triste", "😭"),
        MoodOption("Triste", "😢"),
        MoodOption("Neutro", "😐"),
        MoodOption("Feliz", "😊"),
        MoodOption("Muito Feliz", "😄")
    )
    
    val mockFeelingOptions = listOf(
        FeelingOption("Ansioso"),
        FeelingOption("Estressado"),
        FeelingOption("Relaxado"),
        FeelingOption("Motivado"),
        FeelingOption("Cansado"),
        FeelingOption("Energizado")
    )

    val context = LocalContext.current
    val viewModel: MoodEntryViewModel = viewModel(
        factory = MoodEntryViewModelFactory(context.applicationContext as Application)
    )


    var selectedMood by remember { mutableStateOf<MoodOption?>(null) }
    var selectedFeeling by remember { mutableStateOf<FeelingOption?>(null) }
    var notes by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Acompanhamento Diário",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Gray50
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Humor") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Sentimentos") }
                )
            }
        }
        
        item {
            when (selectedTab) {
                0 -> MoodTab(
                    selectedMood = selectedMood,
                    onMoodSelected = { selectedMood = it },
                    moodOptions = mockMoodOptions
                )
                1 -> FeelingTab(
                    selectedFeeling = selectedFeeling,
                    onFeelingSelected = { selectedFeeling = it },
                    notes = notes,
                    onNotesChanged = { notes = it },
                    onSubmit = {
                        if (selectedMood != null && selectedFeeling != null) {
                            showSuccessDialog = true
                        } else {
                            showErrorDialog = true
                        }
                    },
                    feelingOptions = mockFeelingOptions
                )
            }
        }
    }
    
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Registro Salvo!") },
            text = { Text("Seu humor e sentimentos foram registrados com sucesso.") },
            confirmButton = {
                TextButton(onClick = { showSuccessDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
    
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Atenção") },
            text = { Text("Por favor selecione seu humor e como você se sente hoje.") },
            confirmButton = {
                TextButton(onClick = { showErrorDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
private fun MoodTab(
    selectedMood: MoodOption?,
    onMoodSelected: (MoodOption) -> Unit,
    moodOptions: List<MoodOption>
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Como está seu humor hoje?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Selecione a opção que melhor representa como você está se sentindo.",
            fontSize = 14.sp,
            color = Gray600,
            modifier = Modifier.padding(top = 4.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(300.dp)
        ) {
            items(moodOptions) { mood ->
                MoodButton(
                    mood = mood,
                    isSelected = selectedMood == mood,
                    onClick = { onMoodSelected(mood) }
                )
            }
        }
    }
}

@Composable
private fun FeelingTab(
    selectedFeeling: FeelingOption?,
    onFeelingSelected: (FeelingOption) -> Unit,
    notes: String,
    onNotesChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    feelingOptions: List<FeelingOption>
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Como você se sente hoje?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Selecione a opção que melhor representa seu estado emocional atual.",
            fontSize = 14.sp,
            color = Gray600,
            modifier = Modifier.padding(top = 4.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(feelingOptions) { feeling ->
                FeelingButton(
                    feeling = feeling,
                    isSelected = selectedFeeling == feeling,
                    onClick = { onFeelingSelected(feeling) }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Observações adicionais (opcional)",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = notes,
            onValueChange = onNotesChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = { 
                Text("Como foi seu dia? Aconteceu algo que você gostaria de registrar?") 
            },
            maxLines = 4
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Blue600)
        ) {
            Text("Salvar registro", color = Color.White)
        }
    }
}

@Composable
private fun MoodButton(
    mood: MoodOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp),
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
                text = mood.emoji,
                fontSize = 48.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mood.displayName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun FeelingButton(
    feeling: FeelingOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Blue600 else Color.Transparent,
            contentColor = if (isSelected) Color.White else Gray700
        ),
        shape = RoundedCornerShape(8.dp),
        border = if (!isSelected) ButtonDefaults.outlinedButtonBorder(enabled = true) else null
    ) {
        Text(
            text = feeling.displayName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
} 