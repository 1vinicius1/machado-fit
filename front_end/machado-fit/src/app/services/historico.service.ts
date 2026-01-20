import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({ providedIn: "root" })
export class HistoricoService {
  private apiUrl = "http://localhost:8080/historico";

  constructor(private http: HttpClient) {}

  finalizarTreino(cmd: any): Observable<string> {
    return this.http.post(`${this.apiUrl}/finalizar`, cmd, {
      responseType: "text",
    });
  }

  obterCalendario(idAluno: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/calendario/${idAluno}`);
  }
}
