import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { UsuarioService } from "../../services/usuario.service";
import { UsuarioDTO } from "../../models/usuario.model";

@Component({
  selector: "app-editar-aluno",
  templateUrl: "./editar-aluno.component.html",
})
export class EditarAlunoComponent implements OnInit {
  aluno: UsuarioDTO = {
    id: 0,
    nome: "",
    cpf: "",
    dataNascimento: "",
    perfil: "",
  };

  loading: boolean = false;
  salvando: boolean = false;
  alunoId: number = 0;

  constructor(
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit() {
    this.alunoId = Number(this.route.snapshot.paramMap.get("id"));

    if (this.alunoId && this.alunoId > 0) {
      this.carregarAluno();
    } else {
      alert("ID do aluno inválido.");
      this.router.navigate(["/personal/alunos"]);
    }
  }

  carregarAluno() {
    this.loading = true;

    this.usuarioService.obterPerfil(this.alunoId).subscribe(
      (data) => {
        this.aluno = data;
        this.loading = false;
      },
      (err) => {
        console.error("Erro ao carregar aluno:", err);
        alert("Erro ao carregar dados do aluno.");
        this.loading = false;
        this.router.navigate(["/personal/alunos"]);
      },
    );
  }

  salvar() {
    if (!this.aluno.nome || !this.aluno.cpf || !this.aluno.dataNascimento) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    this.salvando = true;

    const cmd = {
      idUsuario: this.aluno.id,
      nome: this.aluno.nome,
      cpf: this.aluno.cpf,
      dataNascimento: this.aluno.dataNascimento,
      perfil: this.aluno.perfil,
      novaSenha: null, // ou '' se você preferir
    };

    this.usuarioService.editarAluno(cmd).subscribe(
      () => {
        alert("Aluno atualizado com sucesso!");
        this.salvando = false;
        this.router.navigate(["/personal/alunos"]);
      },
      (err) => {
        console.error("Erro ao atualizar aluno:", err);
        alert("Erro ao atualizar aluno.");
        this.salvando = false;
      },
    );
  }

  cancelar() {
    this.router.navigate(["/personal/alunos"]);
  }
}
