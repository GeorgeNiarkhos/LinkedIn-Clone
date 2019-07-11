import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { LoginService } from './login.service';


@Injectable()
export class AuthGaurdService implements CanActivate{
  constructor(private lService : LoginService, private router : Router) { }

  canActivate() {    
    var answer = this.lService.isLogedIn();
    return answer;
  }
  
}
