import { Injectable } from "@angular/core";
import { NewsHttpService } from "./news.http.service";
import { Observable } from "rxjs";

@Injectable()
export class NewsService{
    private serviceUrl = "http://localhost:8081/api/v1/newsservice/";
    constructor( private http: NewsHttpService){}
    
    addToWatchList(author: string, title: string, description: string,url: string, urlToImage: string, publishedAt: string, content: string){
        const url1 = this.serviceUrl+ 'news/';
        const json = { author: author, title: title, description: description, url: url, urlToImage: urlToImage, publishedAt: publishedAt, content: content};
         return this.http.post(url1, json);
    }

    getWatchList(){
        const url1 = this.serviceUrl +'news';
        return this.http.get(url1).toPromise().then(res => (res ? res.json() : null ))
        .catch(this.handleError);
    }

    getWatchListExternal(){
        const url1 = this.serviceUrl +'news/external';
        return this.http.get(url1).toPromise().then(res => (res ? res.json() : null ))
        .catch(this.handleError);
    }
    
    getNewsByTitle(title: string){
        const url1 = this.serviceUrl +'news/';
        const uri = `${url1}${title}`;
        return this.http.get(uri).toPromise().then(res => (res ? res.json() : null ))
        .catch(this.handleError);
    }
    updateNews(author: string, title: string,url: string, urlToImage: string, publishedAt: string, description: string, content: string){
        const url1 = this.serviceUrl+'news/';
        const uri = `${url1}${title}`;
        const json = { //newsId: 1000,
            author: author,
            title: title,
            description: description,
            url: url,
            urlToImage: urlToImage,
            publishedAt: publishedAt,
            content: content};
            console.log(author,title,url,urlToImage,publishedAt,description,content);
        return this.http.put(uri, json).toPromise().catch(this.handleError);
    }
    removeNews(title: string):Observable<any>{
        
        const url1 = this.serviceUrl+ 'news/';
        const uri = `${url1}${title}`
        return this.http.delete(uri);
    }
    private handleError(error: any){
        console.error('An Error Occured', error);
    }
}