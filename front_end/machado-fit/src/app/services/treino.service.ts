import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { ExerciciosAlunoDTO, ExercicioDTO, CadastrarExercicioCmd } from "../models/exercicio.model";
import { CadastrarTreinoCmd, TreinoDTO } from "../models/treino.model";

@Injectable({ providedIn: "root" })
export class TreinoService {
  private apiUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {}

  // --- Treinos ---
  cadastrarTreino(cmd: CadastrarTreinoCmd): Observable<string> {
    return this.http.post(`${this.apiUrl}/treino/cadastrar`, cmd, {
      responseType: "text",
    });
  }

  obterTreino(idTreino: number): Observable<TreinoDTO> {
    return this.http.get<TreinoDTO>(`${this.apiUrl}/treino/${idTreino}`);
  }

  // --- Exercícios ---
  cadastrarExercicio(cmd: CadastrarExercicioCmd): Observable<string> {
    return this.http.post(`${this.apiUrl}/exercicio/cadastrar`, cmd, {
      responseType: "text",
    });
  }

  // Usado pelo Aluno (Visão completa da semana)
  listarExerciciosPorAluno(idAluno: number): Observable<ExerciciosAlunoDTO[]> {
    return this.http.get<ExerciciosAlunoDTO[]>(
      `${this.apiUrl}/exercicio/listar-exercicios-aluno/${idAluno}`,
    );
  }

  // Usado pelo Personal (Ver exercícios de um treino específico)
  listarExerciciosPorTreino(idTreino: number): Observable<ExercicioDTO[]> {
    return this.http.get<ExercicioDTO[]>(
      `${this.apiUrl}/exercicio/listar-exercicios-treino/${idTreino}`,
    );
  }
}
