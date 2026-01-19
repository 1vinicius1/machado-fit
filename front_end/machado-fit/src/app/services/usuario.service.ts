import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioDTO } from '../models/usuario.model';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private apiUrl = 'http://localhost:8080/usuario';

  constructor(private http: HttpClient) {}

  listarAlunos(): Observable<UsuarioDTO[]> {
    return this.http.get<UsuarioDTO[]>(`${this.apiUrl}/listar-alunos`);
  }

  cadastrar(cmd: any): Observable<string> {
    // Backend retorna String, então definimos responseType: 'text'
    return this.http.post(`${this.apiUrl}/cadastrar`, cmd, { responseType: 'text' });
  }

  excluir(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/excluir/${id}`, { responseType: 'text' });
  }

  obterPerfil(id: number): Observable<UsuarioDTO> {
    return this.http.get<UsuarioDTO>(`${this.apiUrl}/perfil/${id}`);
  }

  editarAluno(cmd: any): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/atualizar`, cmd);
}

}