import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';

import { Router, ActivatedRoute,ParamMap, convertToParamMap } from '@angular/router';
import { NewsService } from '../services/news.service';
import { SearchService } from '../services/search.service';
import { switchMap } from 'rxjs/operators';
import { Title } from '@angular/platform-browser';
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Component({
  selector: 'app-newsitem',
  templateUrl: './newsitem.component.html',
  styleUrls: ['./newsitem.component.css']
})
export class NewsitemComponent implements OnInit {
  public news;
  public inWatchList = true;
  public title: string;
  public titleForUpdate: string;
  public description: string;
  public content: string;
  constructor(private router: Router, private newsService: NewsService, private searchService: SearchService, private route: ActivatedRoute) { }
  
   ngOnInit(){
		this.title = this.route.snapshot.params['title'];
    this.newsService.getNewsByTitle(this.title).then( res => {
    this.news = res;
  });
   }
   public updateNews(author: string, title: string, url: string, urlToImage: string, publishedAt: string){
     //console.log(author,title,url,urlToImage,publishedAt,this.description,this.content);
     this.titleForUpdate = this.route.snapshot.params['title'];
    this.newsService.updateNews(author,this.titleForUpdate,url,urlToImage,publishedAt,this.description,this.content); 
    window.location.reload();
   }
  


}

