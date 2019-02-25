import { Component, OnInit } from '@angular/core';
import { NewsService } from '../services/news.service';
import { Router } from '@angular/router';
import { News } from '../news';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  public watchListNews: News[] = [];
  public isAdmin: boolean = false;
  public temp: string;
  constructor(private newsService: NewsService, private router:Router) { }

  
  ngOnInit() {
    this.newsService.getWatchList().then( res => {
      this.watchListNews = res;
      
    });
    //this.isAdmin = localStorage.getItem('username') === "admin" ? true : false; 
    
  }
  public updateNews(title: string){
    if(localStorage.getItem("username") === "admin"){
      this.temp = title.replace("%", "%25");
      this.router.navigate(["/news/"+this.temp]);
    }else{
      alert("Only Admin Have Access To Update News... ");
    }
  }
  public deleteNews(author: string, title:string, description: string, url:string, urlToImage:string, publishedAt: string, content: string){
    this.temp = title.replace("%", "%25");
    this.newsService.removeNews(this.temp).subscribe(response=>{
    this.ngOnInit();
    });
 }

}
