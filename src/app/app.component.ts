import { Component } from '@angular/core';
import { SearchService } from './services/search.service';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public title = 'News-App';
  public isLoggedIn = false;
  public isAdmin = false;
  public searchText: string;

  constructor(private searchService: SearchService, private router: Router, private authservice:AuthService){}

  ngOnInit(){
    this.isLoggedIn = localStorage.getItem("jwt_token") === null ? false : true;
    if(this.isLoggedIn){
      this.isAdmin = localStorage.getItem("username") === "admin" ? true: false; 
    }
  }

    public search(){
     const link = ["/search/"+this.searchText];
     this.router.navigate(link);
    
    }
  
}

