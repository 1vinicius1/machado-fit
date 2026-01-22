import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

@Injectable({ providedIn: "root" })
export class AuthGuard implements CanActivate {
  canActivate(): boolean {
    return true;
  }
}
@Injectable({ providedIn: "root" })
export class PerfilGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const perfisPermitidos = route.data["perfis"] as string[];
    const perfilUsuario = this.authService.getPerfilUsuario();

    if (perfisPermitidos.includes(perfilUsuario)) {
      return true;
    }
    this.router.navigate(["/unauthorized"]);
    return false;
  }
}
