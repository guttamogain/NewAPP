import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { FormsModule } from '@angular/forms';
import { SearchResultsComponent } from './search-results/search-results.component';
import { LogoutComponent } from './logout/logout.component';
import { NewsitemComponent } from './newsitem/newsitem.component';
import { CategoryComponent } from './category/category.component';
import { AppRoutingModule } from './services/routing';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { SearchService } from './services/search.service';
import { AuthGuard } from './services/auth.guard.service';
import { AuthService } from './services/auth.service';
import { NewsHttpService } from './services/news.http.service';
import { NewsService } from './services/news.service';
import { AlertComponent } from './alert/alert.component';
import { AlertService } from './services/alert.service';
import { ShownewsComponent } from './shownews/shownews.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    WatchlistComponent,
    SearchResultsComponent,
    LogoutComponent,
    NewsitemComponent,
    CategoryComponent,
    AlertComponent,
    ShownewsComponent
  ],
  imports: [
    BrowserModule,AppRoutingModule, HttpModule, RouterModule, FormsModule
  ],
  providers: [SearchService,AuthGuard,AuthService,NewsHttpService,NewsService,AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }


