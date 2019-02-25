import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search.service';
import { News } from '../news';
import { ActivatedRoute } from '@angular/router';
import { NewsService } from '../services/news.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {
  public searchTextNews: News[] = [];
  public searchText: String = "";
  constructor(private searchService: SearchService, private route: ActivatedRoute, private newsService:NewsService) { }

  ngOnInit() {
  this.searchText = this.route.snapshot.params['searchText'];
  this.searchService.searchText(this.route.snapshot.params['searchText']).subscribe( res =>{
      this.searchTextNews = res.articles.slice(0,10);
  });
}
public addToWatchList(author: string, title:string, description: string, url:string, urlToImage:string, publishedAt: string, content: string){
  this.newsService.addToWatchList(author,title,description,url,urlToImage,publishedAt,content).subscribe( res => {
    
  }, error=>{
    alert("Unable to Add News... Already Exists in WatchList... ");
  });
  
  }
}
