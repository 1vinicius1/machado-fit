import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
})
export class HeaderComponent implements OnInit {

  perfil: string | null = null;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit() {
    this.perfil = this.authService.getPerfilUsuario();

    this.authService.usuarioSubject.subscribe(user => {
      this.perfil = user ? user.perfil : null;
    });
  }

  sair() {
    if (confirm("Deseja realmente sair da sua conta?") ) {
      this.authService.logout();
    }
  }

  get isPersonal(): boolean {
    return this.perfil === 'PERSONAL';
  }

  get isAluno(): boolean {
    return this.perfil === 'ALUNO';
  }
}
