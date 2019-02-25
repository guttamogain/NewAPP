import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SearchResultsComponent } from "../search-results/search-results.component";
import { AuthGuard } from "./auth.guard.service";
import { LoginComponent } from "../login/login.component";
import { RegisterComponent } from "../register/register.component";
import { LogoutComponent } from "../logout/logout.component";
import { WatchlistComponent } from "../watchlist/watchlist.component";
import { CategoryComponent } from "../category/category.component";
import { HomeComponent } from "../home/home.component";
import { NewsitemComponent } from "../newsitem/newsitem.component";
import { ShownewsComponent } from "../shownews/shownews.component";




const routes: Routes = [
    {path: "search/:searchText", component:SearchResultsComponent, canActivate:[AuthGuard]},
    {path: "watchlist", component:WatchlistComponent, canActivate: [AuthGuard]},
    {path: "shownews/:title", component:ShownewsComponent, canActivate: [AuthGuard]},
    {path: "home" , component:HomeComponent, canActivate: [AuthGuard]},
	{ path: "login", component: LoginComponent},
	{ path: "register", component: RegisterComponent },
    { path: "logout", component: LogoutComponent },
    { path: "category/:category", component: CategoryComponent, canActivate: [AuthGuard] },
    { path: "news/:title", component: NewsitemComponent, canActivate:[AuthGuard]},
	{ path: "",	component:localStorage.getItem("token") === null ? LoginComponent : HomeComponent,canActivate: [AuthGuard]	}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
    
}