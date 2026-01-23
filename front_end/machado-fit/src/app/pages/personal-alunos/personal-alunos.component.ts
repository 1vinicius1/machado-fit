import { Component, OnInit } from "@angular/core";
import { UsuarioService } from "../../services/usuario.service";
import { UsuarioDTO } from "../../models/usuario.model";

@Component({
  selector: "app-personal-alunos",
  templateUrl: "./personal-alunos.component.html",
})
export class PersonalAlunosComponent implements OnInit {
  alunos: UsuarioDTO[] = [];
  alunosFiltrados: UsuarioDTO[] = [];
  loading: boolean = false;
  termoBusca: string = "";
  
  // Paginação
  paginaAtual: number = 1;
  itensPorPagina: number = 10;
  paginasTotal: number = 0;
  alunosPaginados: UsuarioDTO[] = [];

  constructor(private usuarioService: UsuarioService) {}

  isPersonal(idUsuario: number): boolean {
    if (idUsuario != 1) {
      return true;
    }
  }

  ngOnInit() {
    this.carregarAlunos();
  }

  carregarAlunos() {
    this.loading = true;
    this.usuarioService.listarAlunos().subscribe(
      (data) => {
        this.alunos = data;
        this.alunosFiltrados = data;
        this.paginaAtual = 1;
        this.atualizarPaginacao();
        this.loading = false;
      },
      (err) => {
        alert("Erro ao carregar alunos.");
        this.loading = false;
      },
    );
  }

  atualizarPaginacao() {
    this.paginasTotal = Math.ceil(this.alunosFiltrados.length / this.itensPorPagina);
    
    if (this.paginaAtual > this.paginasTotal && this.paginasTotal > 0) {
      this.paginaAtual = this.paginasTotal;
    }

    const inicio = (this.paginaAtual - 1) * this.itensPorPagina;
    const fim = inicio + this.itensPorPagina;
    this.alunosPaginados = this.alunosFiltrados.slice(inicio, fim);
  }

  irParaPagina(pagina: number) {
    if (pagina >= 1 && pagina <= this.paginasTotal) {
      this.paginaAtual = pagina;
      this.atualizarPaginacao();
    }
  }

  proximaPagina() {
    if (this.paginaAtual < this.paginasTotal) {
      this.paginaAtual++;
      this.atualizarPaginacao();
    }
  }

  paginaAnterior() {
    if (this.paginaAtual > 1) {
      this.paginaAtual--;
      this.atualizarPaginacao();
    }
  }

  buscar() {
    if (!this.termoBusca.trim()) {
      this.alunosFiltrados = this.alunos;
    } else {
      const termo = this.termoBusca.toLowerCase();
      this.alunosFiltrados = this.alunos.filter((aluno) => {
        return (
          aluno.nome.toLowerCase().startsWith(termo) ||
          aluno.cpf.startsWith(termo)
        );
      });
    }
    this.paginaAtual = 1;
    this.atualizarPaginacao();
  }

  limparBusca() {
    this.termoBusca = "";
    this.alunosFiltrados = this.alunos;
    this.paginaAtual = 1;
    this.atualizarPaginacao();
  }

  inativar(id: number) {
    if (
      confirm(
        "Tem certeza que deseja remover este aluno e todos os seus dados?",
      )
    ) {
      this.usuarioService.inativar(id).subscribe(
        () => {
          alert("Aluno removido com sucesso!");
          this.carregarAlunos();
        },

        (error) => {
          alert("Não foi possível remover o aluno.");
        },
      );
    }
  }
}
