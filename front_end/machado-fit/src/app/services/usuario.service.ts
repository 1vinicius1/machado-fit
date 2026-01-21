import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { UsuarioDTO } from "../models/usuario.model";

@Injectable({ providedIn: "root" })
export class UsuarioService {
  private apiUrl = "http://localhost:8080/usuario";

  constructor(private http: HttpClient) {}

  listarAlunos(): Observable<UsuarioDTO[]> {
    return this.http.get<UsuarioDTO[]>(`${this.apiUrl}/listar-alunos`);
  }

  cadastrar(cmd: any): Observable<string> {
    return this.http.post(`${this.apiUrl}/cadastrar`, cmd, {
      responseType: "text",
    });
  }

  inativar(id: number): Observable<string> {
    return this.http.put<{ null: string }> (`${this.apiUrl}/inativar/${id}`, {
      responseType: 'text',
    });
  }

  obterPerfil(idUsuario: number): Observable<UsuarioDTO> {
    return this.http.get<UsuarioDTO>(`${this.apiUrl}/perfil/${idUsuario}`);
  }

  editarAluno(cmd: any): Observable<string> {
    return this.http.put(`${this.apiUrl}/atualizar`, cmd, {
      responseType: "text",
    });
  }
}
