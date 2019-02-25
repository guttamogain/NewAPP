import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
//import "rxjs/add/operator/map";
import { map } from "rxjs/operators";
import { Observable } from "rxjs";
import { News } from "../news";

@Injectable()
export class SearchService{
    public searchTextNews: News[] = [];
	public text: string;
	private apikey = "aed3b6805f0e4fd2a1eb3504fbff7217";
	
    private categoryUrl = "https://newsapi.org/v2/top-headlines?apikey=aed3b6805f0e4fd2a1eb3504fbff7217&page=1&country=in&category=";
    private searchTextUrl = "https://newsapi.org/v2/everything?apiKey=aed3b6805f0e4fd2a1eb3504fbff7217&language=en&page=1&q=";
    private trendingUrl = "https://newsapi.org/v2/top-headlines?apikey=aed3b6805f0e4fd2a1eb3504fbff7217&page=1&country=in";
    
           
    constructor(private http: Http) {}
    
	public category(category: string) {
        const url = `${this.categoryUrl}${category}`;
        //return this.http.get(url).toPromise().then( res => res.json()).catch(this.handleError);
        return this.http.get(url).pipe(map(res => res.json()));
    }
    public searchText(text: string) {
         const url = `${this.searchTextUrl}${text}`;
         //return this.http.get(url).toPromise().then( res => res.json()).catch(this.handleError);
        return this.http.get(url).pipe(map(res => res.json()));
    }
    public trending(): Observable<any> {
        return this.http.get(this.trendingUrl).pipe(map(res => res.json()));
        //return this.http.get(this.trendingUrl).toPromise().then( res => res.json).catch(this.handleError);
    }
	private handleError(error: any): Promise<any> {
		return Promise.reject(error.message || error);
	}
}