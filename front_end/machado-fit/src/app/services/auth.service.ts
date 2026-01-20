import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { map } from "rxjs/operators";
import { Router } from "@angular/router";
import { LoginCmd, UsuarioLogadoDTO } from "../models/usuario.model";

@Injectable({ providedIn: "root" })
export class AuthService {
  private apiUrl = "http://localhost:8080/usuario";

  // Mantém o estado do usuário atual (logado ou null)
  public usuarioSubject = new BehaviorSubject<UsuarioLogadoDTO | null>(null);

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
    // Ao iniciar, verifica se já tem alguém salvo no navegador
    const savedUser = localStorage.getItem("usuarioLogado");
    if (savedUser) {
      this.usuarioSubject.next(JSON.parse(savedUser));
    }
  }

  login(cmd: LoginCmd): Observable<UsuarioLogadoDTO> {
    return this.http.post<UsuarioLogadoDTO>(`${this.apiUrl}/login`, cmd).pipe(
      map((user) => {
        // Salva no LocalStorage para não perder ao dar F5
        localStorage.setItem("usuarioLogado", JSON.stringify(user));
        this.usuarioSubject.next(user);
        return user;
      }),
    );
  }

  logout() {
    localStorage.removeItem("usuarioLogado");
    this.usuarioSubject.next(null);
    this.router.navigate(["/login"]);
  }

  // Helper para pegar o valor atual sem precisar de subscribe
  get usuarioLogado(): UsuarioLogadoDTO | null {
    return this.usuarioSubject.value;
  }
}
