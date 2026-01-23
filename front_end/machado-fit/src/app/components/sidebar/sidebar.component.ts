import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {

  perfil: string | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.perfil = this.authService.getPerfilUsuario();

    this.authService.usuarioSubject.subscribe(user => {
      this.perfil = user ? user.perfil : null;
    });
  }

  get isPersonal(): boolean {
    return this.perfil === 'PERSONAL';
  }

  get isAluno(): boolean {
    return this.perfil === 'ALUNO';
  }
}
