import { Injectable } from "@angular/core";
import { Http, Headers, Jsonp } from "@angular/http";
import * as jwt_decode from "jwt-decode";
export const TOKEN_NAME: string = "jwt_token";
export const USER_NAME: string = "username";
import { Observable } from "rxjs";
import { map, mapTo } from "rxjs/operators";



@Injectable()
export class AuthService {

    constructor(private http: Http) { }
    private headers = new Headers({
        "Content-Type": "application/json", "Accept": "application/json"
    });
    private serviceUrl = "http://localhost:8089/api/v1/userservice/";
    username: string = "";

    getToken(): string {
        return localStorage.getItem(TOKEN_NAME);
    }
    setToken(token: string): void {
        localStorage.setItem(TOKEN_NAME, token);
    }

    setUsername(username: string) {
        this.username = username;
        localStorage.setItem(USER_NAME, username );
    }
    getusername() {
        return this.username;
    }

    isAdmin(){
        return this.username === "admin" ? true: false;
    }

    deleteToken(): void {
        localStorage.removeItem(TOKEN_NAME);
    }
    deleteUsername(): void {
        localStorage.removeItem(USER_NAME); 
    }
    getTokenExpirationDate(token: string): Date {
        const decoded: any = jwt_decode(token);
        if (decoded.exp === undefined) return null;
        const date = new Date(0);
        date.setUTCSeconds(decoded.exp);
        return date;
    }

    isTokenExpired(token?: string): boolean {
        if (!token) token = this.getToken();
        if (!token) return true;
        const date = this.getTokenExpirationDate(token);
        if (date === undefined || date === null) return false;
        return !(date.valueOf() > new Date().valueOf());
    }

    login(username: string, password: string): Observable<string> {
        const url = this.serviceUrl + "login/";
        const json = JSON.stringify({ username: username, password: password });
        return this.http.post(url, json, { headers: this.headers }).pipe(map(response=>response.json()));
    }

    registerUser(username: string, password: string, firstname: string, lastname: string) {
        const url = this.serviceUrl + "register/";
        const json = JSON.stringify({ username: username, password: password, firstname: firstname, lastname: lastname });
        return this.http.post(url, json, { headers: this.headers }).toPromise()
            .then(res => res.json());
    }
    private handleError(error: any) {
         console.error('An error occurred', error); 
    }

}