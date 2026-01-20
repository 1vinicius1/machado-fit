import { Component, OnInit } from "@angular/core";
import { TreinoService } from "../../services/treino.service";
import { AuthService } from "../../services/auth.service";
import { HistoricoService } from "../../services/historico.service";
import { ExerciciosAlunoDTO } from "../../models/exercicio.model";

@Component({
  selector: "app-aluno-treino",
  templateUrl: "./aluno-treino.component.html",
})
export class AlunoTreinoComponent implements OnInit {
  treinosAgrupados: { [idTreino: number]: ExerciciosAlunoDTO[] } = {};
  loading: boolean = false;

  constructor(
    private treinoService: TreinoService,
    private authService: AuthService,
    private historicoService: HistoricoService,
  ) {}

  ngOnInit() {
    this.carregarTreinos();
  }

  // --- CORREÇÃO AQUI: Helper para o HTML ---
  // Verifica se o objeto tem chaves (se não está vazio)
  get temTreinos(): boolean {
    return (
      this.treinosAgrupados && Object.keys(this.treinosAgrupados).length > 0
    );
  }
  // ----------------------------------------

  carregarTreinos() {
    const usuario = this.authService.usuarioLogado;
    if (usuario) {
      this.loading = true;
      this.treinoService.listarExerciciosPorAluno(usuario.id).subscribe(
        (lista) => {
          this.treinosAgrupados = lista.reduce((acc, curr) => {
            curr.concluido = false;
            const key = curr.idTreino;
            if (!acc[key]) {
              acc[key] = [];
            }
            acc[key].push(curr);
            return acc;
          }, {});
          this.loading = false;
        },
        (err) => {
          alert("Erro ao carregar treinos.");
          this.loading = false;
        },
      );
    }
  }

  todosConcluidos(exercicios: ExerciciosAlunoDTO[]): boolean {
    return (
      exercicios &&
      exercicios.length > 0 &&
      exercicios.every((e) => e.concluido)
    );
  }

  finalizarTreino(idTreino: number) {
    const idAluno = this.authService.usuarioLogado.id;
    const dataHoje = new Date().toISOString().split("T")[0];

    const cmd = {
      idAluno: idAluno,
      idTreino: idTreino,
      dataFinalizacao: dataHoje,
    };

    if (
      confirm("Deseja finalizar este treino? Ele será salvo no seu histórico.")
    ) {
      this.historicoService.finalizarTreino(cmd).subscribe(
        (res) => {
          alert("Parabéns! Treino finalizado com sucesso.");
          // Limpa a tela ou reseta
          if (this.treinosAgrupados[idTreino]) {
            this.treinosAgrupados[idTreino].forEach(
              (e) => (e.concluido = false),
            );
          }
        },
        (err) => alert("Erro ao finalizar treino. Tente novamente."),
      );
    }
  }
}
