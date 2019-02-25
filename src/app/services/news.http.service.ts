import { Injectable } from "@angular/core";
import { Http, Headers } from "@angular/http";
import { AuthService } from "./auth.service";
import { Observable } from "rxjs";

@Injectable()
export class NewsHttpService{

    constructor(private http:Http, private authService:AuthService){}

    createHeader(headers: Headers){
        headers.append('authorization', 'Bearer ' +
        localStorage.getItem("jwt_token"));
        headers.append('Content-Type','application/json');
        headers.append('Accept', 'application/json');
        headers.append('username', this.authService.getusername());
    }
     get(url){
        let headers = new Headers();
        this.createHeader(headers);
        return this.http.get(url,{  headers: headers
          });
         }
 
     post(url, data){
         let headers = new Headers();
         this.createHeader(headers);
         return this.http.post(url,data,{
                 headers: headers
               });
     }   
 
     put(url, data){
         let headers = new Headers();
         this.createHeader(headers);
         return this.http.put(url,data,{
                 headers: headers
               });
     } 
 
     delete(url): Observable<any>{
         let headers = new Headers();
         this.createHeader(headers);
         return this.http.delete(url,{
             headers: headers
           });
          }
 
}