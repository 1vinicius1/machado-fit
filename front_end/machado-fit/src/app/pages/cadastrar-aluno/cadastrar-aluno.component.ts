import { Component, OnInit } from "@angular/core";
import { UsuarioService } from "src/app/services/usuario.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-cadastrar-aluno",
  templateUrl: "./cadastrar-aluno.component.html",
})
export class CadastrarAlunoComponent implements OnInit {
  // Model do formulário (não precisa ser o mesmo DTO do back)
  aluno = {
    nome: "",
    cpf: "",
    senha: "",
    dataNascimento: "", // input type="date" => "YYYY-MM-DD"
    perfil: "ALUNO",
  };

  salvando: boolean = false;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
  ) {}

  ngOnInit() {}

  salvar() {
    this.salvando = true;

    const cmd = {
      nome: this.aluno.nome,
      cpf: this.aluno.cpf,
      senha: this.aluno.senha,
      dataNascimento: this.aluno.dataNascimento,
      perfil: this.aluno.perfil,
    };

    this.usuarioService.cadastrar(cmd).subscribe(
      () => {
        alert("Aluno cadastrado com sucesso!");
        this.salvando = false;
        this.router.navigate(["/personal/alunos"]);
      },
      (err) => {
        console.error("Erro ao cadastrar aluno:", err);
        alert("Erro ao cadastrar aluno.");
        this.salvando = false;
      },
    );
  }

  cancelar() {
    this.router.navigate(["/personal/alunos"]);
  }
}
