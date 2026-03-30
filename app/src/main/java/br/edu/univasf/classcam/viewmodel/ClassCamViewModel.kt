package br.edu.univasf.classcam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import br.edu.univasf.classcam.data.AppDatabase
import br.edu.univasf.classcam.data.ClassCamDao
import br.edu.univasf.classcam.data.Disciplina
import br.edu.univasf.classcam.data.Horario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClassCamViewModel(private val dao: ClassCamDao) : ViewModel() {

    // Função que a tela vai chamar ao clicar no botão
    fun salvarDisciplinaCompleta(
        nome: String,
        nomePasta: String,
        diaSemana: Int,
        horaInicioMinutos: Int,
        horaFimMinutos: Int
    ) {
        // Lança a operação em segundo plano (IO = Input/Output)
        viewModelScope.launch(Dispatchers.IO) {

            // 1. Salva a matéria e o Room nos devolve o ID gerado automaticamente
            val disciplinaId = dao.inserirDisciplina(Disciplina(nome = nome, nomePasta = nomePasta))

            // 2. Cria o horário vinculando ao ID da matéria que acabamos de salvar
            val horario = Horario(
                disciplinaId = disciplinaId.toInt(),
                diaSemana = diaSemana,
                horaInicioMinutos = horaInicioMinutos,
                horaFimMinutos = horaFimMinutos
            )

            // 3. Salva o horário
            dao.inserirHorario(horario)
        }
    }
}

// A Fábrica que ensina o Android a criar nosso ViewModel passando o Banco de Dados
val ClassCamViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val application = checkNotNull(extras[APPLICATION_KEY])
        val database = AppDatabase.getDatabase(application)
        return ClassCamViewModel(database.classCamDao()) as T
    }
}