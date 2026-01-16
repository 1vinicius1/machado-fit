import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { LoginCmd } from '../../models/usuario.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  cmd: LoginCmd = {};
  erro: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  logar() {
    this.authService.login(this.cmd).subscribe(
      user => {
        // Redirecionamento baseado no Perfil (RF13, RF14)
        if (user.perfil === 'PERSONAL') {
          this.router.navigate(['/personal/alunos']);
        } else {
          this.router.navigate(['/aluno/meus-treinos']);
        }
      },
      error => {
        this.erro = true;
        setTimeout(() => this.erro = false, 3000); // Esconde erro após 3s
      }
    );
  }
}