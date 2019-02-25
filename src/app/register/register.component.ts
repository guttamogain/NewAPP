import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { User } from '../user';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string;
  password: string;
  firstname: string;
  lastname: string;
  constructor(private authservice: AuthService, private router:Router, private alert:AlertService) { }

  ngOnInit() {
  }
  user=new User();
  
  registerUser() {
    this.authservice.registerUser(this.username,this.password,this.firstname,this.lastname).then(res=>{
        if(res){this.router.navigate(['/login']);}
    }, error => { if(error.status === 500){
      alert("User Already Exists... Please try Someother");
    }});
  }
}
