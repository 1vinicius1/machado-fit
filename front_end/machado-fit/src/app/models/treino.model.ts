import { DiaSemana } from "./enums";

interface TreinoDTO {
  id: number;
  nome: string;
  diaSemana: DiaSemana;
}

interface CadastrarTreinoCmd {
  diaSemana: DiaSemana;
  nomeTreino: string;
  idAluno: number;
}

export { TreinoDTO, CadastrarTreinoCmd };
