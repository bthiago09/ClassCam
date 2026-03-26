package br.edu.univasf.classcam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.univasf.classcam.ui.theme.ClassCamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClassCamTheme {
                // Inicia o controlador de navegação
                val navController = rememberNavController()

                // Configura as rotas do app
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("list") { SubjectListScreen(navController) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ClassCam") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    // Botão para ir para a lista de disciplinas
                    IconButton(onClick = { navController.navigate("list") }) {
                        Icon(Icons.Default.List, contentDescription = "Ver Disciplinas")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aula Atual: Detecção Pendente",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(32.dp))
            LargeFloatingActionButton(
                onClick = { /* ML Kit em breve! */ },
                modifier = Modifier.size(120.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Escanear Quadro",
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Toque para escanear o quadro")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectListScreen(navController: NavController) {
    // Dados mockados baseados na grade de Ciência da Computação da UNIVASF
    val dummySubjects = listOf(
        "Introdução à Programação" to "Segunda • 14:00 - 16:00",
        "Cálculo Integral e Diferencial I" to "Terça • 08:00 - 10:00",
        "Algoritmos e Estruturas de Dados I" to "Quarta • 14:00 - 16:00",
        "Matemática Discreta" to "Quinta • 10:00 - 12:00"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minhas Disciplinas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Botão de voltar
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Ir para tela de cadastro */ }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Disciplina")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dummySubjects) { subject ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = subject.first,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = subject.second,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}