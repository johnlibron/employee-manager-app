import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Employee } from './employee';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiServerUrl = environment.apiBaseUrl + '/employees';

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.apiServerUrl);
  }

  public getEmployee(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiServerUrl}/${id}`);
  }

  public addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.apiServerUrl, employee);
  }

  public updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiServerUrl}/${employee.id}`, employee);
  }

  public deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${id}`);
  }
}
