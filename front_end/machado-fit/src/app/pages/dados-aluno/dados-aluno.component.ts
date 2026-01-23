import { Component, OnInit } from '@angular/core';
import { UsuarioDTO, UsuarioLogadoDTO } from "../../models/usuario.model";
import { ActivatedRoute, Router } from "@angular/router";
import { UsuarioService } from "../../services/usuario.service";
import { AuthService } from "../../services/auth.service";

@Component({
  selector: 'app-dados-aluno',
  templateUrl: './dados-aluno.component.html'
})
export class DadosAlunoComponent implements OnInit {

  usuario: UsuarioLogadoDTO = {
    id: 0,
    nome: "",
    perfil: "",
  };

  loading: boolean = false;
  salvando: boolean = false;
  usuarioId: number;

  constructor(
    private usuarioService: UsuarioService,
    private authService: AuthService,
    private router: Router,
  ) { }

  ngOnInit() {
    const usuarioLogado = this.authService.usuarioLogado;

    if (usuarioLogado && usuarioLogado.id > 0) {
      this.usuarioId = usuarioLogado.id;
      this.carregarUsuario();
    } else {
      alert("Usuário não autenticado. Por favor, faça login novamente.");
      this.router.navigate(["/login"]);
    }
  }

  carregarUsuario() {
    this.loading = true;
    
    this.usuarioService.obterPerfil(this.usuarioId).subscribe(
      (data) => {
        this.usuario = data;
        this.loading = false;
      },
      (err) => {
        console.error("Erro ao carregar usuário:", err);
        alert("Erro ao carregar dados do usuário.");
        this.loading = false;
        this.router.navigate([""]);
      },
    );  
}
}
