package br.edu.univasf.classcam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ClassCamDao {

    @Insert
    suspend fun inserirDisciplina(disciplina: Disciplina): Long // Retorna o ID gerado

    @Insert
    suspend fun inserirHorario(horario: Horario)

    // A nossa consulta SQL otimizada para achar a aula no momento da foto!
    @Query("""
        SELECT d.* FROM disciplinas d
        INNER JOIN horarios h ON d.id = h.disciplinaId
        WHERE h.diaSemana = :diaAtual 
        AND :minutosAtuais BETWEEN h.horaInicioMinutos AND h.horaFimMinutos
    """)
    suspend fun buscarDisciplinaAtual(diaAtual: Int, minutosAtuais: Int): Disciplina?

    @Query("SELECT * FROM disciplinas")
    suspend fun listarTodasDisciplinas(): List<Disciplina>
}