import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { Token } from '@angular/compiler';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authservice:AuthService,private router:Router,private alert:AlertService) { }
  username: string;
  password: string;
  loading: string;
  ngOnInit() {
    //console.log("-"+this.username+"-");
    //  console.log("-"+this.authservice.getusername()+"-");
  }

  
  loginUser(){
    this.authservice.login(this.username,this.password)
    .subscribe(res => {if(res["token"]){
      this.authservice.setToken(res["token"]);
      this.authservice.setUsername(res["username"]);
      this.router.navigate(['/home']);
      window.location.reload();
      
    }
    },error => {
      if(error.status === 401 || error.status === 500){
        
       this.alert.error(' Username or Password is incorrect. ');
        
      }
    }
    )
  }

}
