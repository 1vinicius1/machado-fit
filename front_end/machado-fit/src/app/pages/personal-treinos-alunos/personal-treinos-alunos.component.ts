import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExerciciosAlunoDTO } from 'src/app/models/exercicio.model';
import { TreinoService } from 'src/app/services/treino.service';

@Component({
  selector: 'app-personal-treinos-alunos',
  templateUrl: './personal-treinos-alunos.component.html'
})

export class PersonalTreinosAlunosComponent implements OnInit {
  exerciciosAluno: ExerciciosAlunoDTO[] = [];
  loading: boolean = false;
  alunoId: number = 0;

  constructor(
    private treinoService: TreinoService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    this.alunoId = Number(this.route.snapshot.paramMap.get("id"));

    if (this.alunoId && this.alunoId > 0) {
      this.carregarTreinoAluno(this.alunoId);
    } else {
      alert("ID do aluno inválido.");
      this.router.navigate(["/personal/alunos"]);
    }
  }

  carregarTreinoAluno(idAluno: number) {
    this.loading = true;
    this.treinoService.listarExerciciosPorAluno(idAluno).subscribe(
      (data) => {
        this.exerciciosAluno = data;
        this.loading = false;
      },
      (err) => {
        console.error('Erro ao carregar exercícios do aluno:', err);
        alert('Erro ao carregar exercícios do aluno.');
        this.loading = false;
      }
    );
  }
  


}
