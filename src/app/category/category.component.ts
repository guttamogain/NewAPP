import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search.service';
import { Router, ActivatedRoute, ParamMap, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { News } from '../news';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  public categoryNews: News[] = [];
  public category: string ;
  constructor(private searchService: SearchService, private router: Router, private route:ActivatedRoute, private newsService:NewsService) { }

  ngOnInit() {
    this.route.params.subscribe((value)=>{    
    this.searchService.category(value.category).subscribe( res =>{
      this.categoryNews = res.articles.slice(0,10);      
     });
   });

  }
  public addToWatchList(author: string, title:string, description: string, url:string, urlToImage:string, publishedAt: string, content: string){
    this.newsService.addToWatchList(author,title,description,url,urlToImage,publishedAt,content).subscribe( res => {

    }, error =>{
      alert("Unable to Add News... Already Exists in WatchList... ");
    } ) ;
  }

}

