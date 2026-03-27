package br.edu.univasf.classcam.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Tabela 1: Disciplina
@Entity(tableName = "disciplinas")
data class Disciplina(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val nomePasta: String
)

// Tabela 2: Horário (Vinculada à disciplina por Chave Estrangeira)
@Entity(
    tableName = "horarios",
    foreignKeys = [
        ForeignKey(
            entity = Disciplina::class,
            parentColumns = ["id"],
            childColumns = ["disciplinaId"],
            onDelete = ForeignKey.CASCADE // Se apagar a matéria, apaga os horários dela
        )
    ]
)
data class Horario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val disciplinaId: Int,
    val diaSemana: Int, // 1 = Domingo, 2 = Segunda... 7 = Sábado
    val horaInicioMinutos: Int, // Ex: 14:30 = (14 * 60) + 30 = 870
    val horaFimMinutos: Int
)