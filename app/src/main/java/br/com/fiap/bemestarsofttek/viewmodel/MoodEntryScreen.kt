package br.com.fiap.bemestarsofttek.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.bemestarsofttek.database.entity.MoodEntryEntity
import br.com.fiap.bemestarsofttek.viewmodel.MoodEntryViewModel
import br.com.fiap.bemestarsofttek.viewmodel.MoodEntryViewModelFactory
import java.time.LocalDate

@Composable
fun MoodEntryScreen() {
    val context = LocalContext.current
    val viewModel: MoodEntryViewModel = viewModel(
        factory = MoodEntryViewModelFactory(context.applicationContext as android.app.Application)
    )

    val moodList by viewModel.moodEntries.collectAsState()

    var mood by remember { mutableStateOf("") }
    var feeling by remember { mutableStateOf("") }
    var observation by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(text = "Registrar Humor", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = mood,
            onValueChange = { mood = it },
            label = { Text("Humor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = feeling,
            onValueChange = { feeling = it },
            label = { Text("Sentimento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = observation,
            onValueChange = { observation = it },
            label = { Text("Observações") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val entry = MoodEntryEntity(
                    date = LocalDate.now(),
                    emoji = "🙂",
                    mood = mood,
                    feeling = feeling,
                    workload = "Média",
                    symptoms = "Raramente",
                    bossRelationship = 3,
                    colleaguesRelationship = 4,
                    observations = observation
                )
                viewModel.addMoodEntry(entry)
                mood = ""
                feeling = ""
                observation = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Text(text = "Registros", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(moodList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Data: ${item.date}")
                        Text(text = "Humor: ${item.mood}")
                        Text(text = "Sentimento: ${item.feeling}")
                        if (item.observations.isNotEmpty()) {
                            Text(text = "Obs: ${item.observations}")
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextButton(onClick = {
                                viewModel.deleteMoodEntry(item)
                            }) {
                                Text("Excluir")
                            }
                        }
                    }
                }
            }
        }
    }
}
