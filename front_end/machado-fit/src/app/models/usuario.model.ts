interface UsuarioDTO {
  id: number;
  nome: string;
  cpf: string;
  dataNascimento: string;
  perfil: string;
}
''
interface UsuarioLogadoDTO {
  id: number;
  nome: string;
  perfil: string;
}

interface LoginCmd {
  cpf?: string;
  senha?: string;
}

export { UsuarioDTO, UsuarioLogadoDTO, LoginCmd };
