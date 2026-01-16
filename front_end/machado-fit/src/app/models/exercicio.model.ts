import { DiaSemana } from "./enums";

interface ExercicioDTO {
  id: number;
  nome: string;
  repeticoes: string;
  carga: string;
  observacoes?: string;
  treinoId: number;
}

interface ExerciciosAlunoDTO {
  idTreino: number;
  nomeTreino: string;
  diaSemana: DiaSemana;
  nomeExercicio: string;
  repeticoes: string;
  carga: string;
  observacoes: string;
  idAluno: number;
  concluido?: boolean;
}

export { ExercicioDTO, ExerciciosAlunoDTO };
