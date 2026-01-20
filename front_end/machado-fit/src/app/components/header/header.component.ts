import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";
import { UsuarioService } from "src/app/services/usuario.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
})
export class HeaderComponent {

  constructor(private router: Router, private authService: AuthService) {}

  sair() {
    this.authService.logout();
  }
}
