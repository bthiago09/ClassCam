# ClassCam 📸 

> Automação inteligente para organização de fotos de quadros em salas de aula acadêmicas.

## 📌 O Problema
Estudantes universitários frequentemente tiram fotos do quadro branco para registrar a matéria, resultando em uma galeria de imagens desorganizada e misturada com fotos pessoais. 

## 💡 A Solução
O **ClassCam** é um aplicativo Android nativo que resolve esse problema atuando no momento do clique. Baseado na grade horária do usuário, o sistema cruza o horário atual (via `LocalDateTime`) com o banco de dados local para identificar a disciplina em curso, roteando a foto escaneada diretamente para a pasta correspondente no armazenamento do dispositivo.

## 🚀 Status do Projeto: MVP em Desenvolvimento

### Funcionalidades (Escopo do MVP)
- [x] Interface declarativa com Jetpack Compose (Material Design 3).
- [x] Navegação com transições fluidas (Navigation Compose).
- [x] Modelagem e persistência de dados locais com Room Database.
- [ ] Formulário de cadastro de disciplinas com seletores de dia e hora.
- [ ] Integração com Google ML Kit Document Scanner para captura e recorte de quadros.
- [ ] Salvamento automático de arquivos na hierarquia de pastas correta.

## 🛠️ Stack Tecnológica e Decisões de Arquitetura

Este projeto foi construído priorizando as práticas mais modernas de desenvolvimento Android:

* **Linguagem:** Kotlin.
* **Interface (UI):** Jetpack Compose.
* **Arquitetura:** MVVM (Model-View-ViewModel), garantindo a separação clara entre a lógica de negócios e a interface gráfica.
* **Banco de Dados:** Room Database.
    * *Destaque Técnico:* Migração do KAPT para **KSP (Kotlin Symbol Processing)** para otimização do tempo de build e compatibilidade moderna.
    * *Modelagem:* Relação 1:N entre Disciplinas e Horários. Os horários são armazenados como inteiros (minutos decorridos desde a meia-noite) para permitir consultas SQL de alta performance (`BETWEEN`) no momento do clique.
* **Assincronismo:** Kotlin Coroutines e `viewModelScope` para garantir operações de I/O na *Worker Thread*, prevenindo bloqueios na UI.

## 💻 Como rodar o projeto localmente

1. Clone este repositório: `git clone https://github.com/SEU_USUARIO/ClassCam.git`
2. Abra o projeto no **Android Studio** (Recomendado: Panda 2 ou superior).
3. Aguarde o *Gradle Sync* finalizar (certifique-se de ter a propriedade `android.disallowKotlinSourceSets=false` no `gradle.properties` devido ao KSP).
4. Conecte um dispositivo físico via Depuração USB ou utilize um Emulador (API 26+).
5. Execute o projeto (Shift + F10).

---
*Desenvolvido com foco em resolver dores reais do ambiente acadêmico.*
