import { Component, OnInit } from "@angular/core";
import { UsuarioService } from "../../services/usuario.service";
import { UsuarioDTO } from "../../models/usuario.model";

@Component({
  selector: "app-personal-alunos",
  templateUrl: "./personal-alunos.component.html",
})
export class PersonalAlunosComponent implements OnInit {
  alunos: UsuarioDTO[] = [];
  loading: boolean = false;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit() {
    this.carregarAlunos();
  }

  carregarAlunos() {
    this.loading = true;
    this.usuarioService.listarAlunos().subscribe(
      (data) => {
        this.alunos = data;
        this.loading = false;
      },
      (err) => {
        alert("Erro ao carregar alunos.");
        this.loading = false;
      },
    );
  }

  inativar(id: number) {
    if (
      confirm(
        "Tem certeza que deseja remover este aluno e todos os seus dados?",
      )
    ) {
      this.usuarioService.inativar(id).subscribe(
        () => {
          alert("Aluno inativado com sucesso!");
          this.carregarAlunos();
        },

        (error) => {
          alert("Não foi possível inativar o aluno.");
        },
      );
    }
  }
}
