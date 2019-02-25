import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';

import { Router, ActivatedRoute,ParamMap, convertToParamMap } from '@angular/router';
import { NewsService } from '../services/news.service';
import { SearchService } from '../services/search.service';


@Component({
  selector: 'app-shownews',
  templateUrl: './shownews.component.html',
  styleUrls: ['./shownews.component.css']
})
export class ShownewsComponent implements OnInit {
  public news;
  public inWatchList = false;
  public title: string;
  constructor(private router: Router, private newsService: NewsService, private searchService: SearchService, private route: ActivatedRoute) { }
  
   ngOnInit(){
		this.title = this.route.snapshot.params['title'];
    this.newsService.getNewsByTitle(this.title).then( res => {
    this.news = res;
  });
   }
   public addToWatchList(author: string, title:string, description: string, url:string, urlToImage:string, publishedAt: string, content: string){
    this.newsService.addToWatchList(author,title,description,url,urlToImage,publishedAt,content);
    this.inWatchList = true;
  }
  public deleteNews(author: string, title:string, description: string, url:string, urlToImage:string, publishedAt: string, content: string){
    this.newsService.removeNews(title);
    window.location.reload();
  }
}
