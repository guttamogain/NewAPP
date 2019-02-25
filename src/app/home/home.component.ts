import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from '../services/search.service';
import { switchMap } from 'rxjs/operators';
import { News } from '../news';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public tendingNews : News[] = [];
  public externalNews : News[] = [];
  public isAdmin: boolean = false;
  public newsExists: boolean = false;
  constructor(private searchService:SearchService, private router:Router, private newsService:NewsService, private route: ActivatedRoute) { }
  
  ngOnInit() {
  this.searchService.trending().subscribe( response => {
  this.tendingNews = response.articles.splice(0,30)});
      
  this.isAdmin = localStorage.getItem("username") === "admin" ? true: false;
  this.newsService.getWatchListExternal().then(res => { this.externalNews = res;});
 }
    
 

  public addToWatchList(author: string, title:string, description: string, url:string, urlToImage:string, publishedAt: string, content: string){
           
    this.newsService.addToWatchList(author,title,description,url,urlToImage,publishedAt,content).subscribe(response=>{
    }, error=>{
    alert("Unable to Add News... Already Exists in WatchList... ");
    });
    this.newsExists = true;
  }
}
