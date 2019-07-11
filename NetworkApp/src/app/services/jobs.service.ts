import { Injectable } from '@angular/core';
import { Job } from '../job';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { fromEventPattern } from 'rxjs';
import { Proposal } from '../proposal';
import { SessionStorageService } from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class JobsService {

  constructor(private http: HttpClient, private lSession:SessionStorageService) { }


  newJob( job : Job ) : Observable<any>  {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/newJob", job, httpOptions);
  }

  getJobs( email : string ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.get("https://localhost:8443/getJobs/"+email, httpOptions);
  }

  getMarket( email : string ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.get("https://localhost:8443/getMarket/"+email, httpOptions);
  }

  sendProposal ( proposal : Proposal ) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/sendProposal", proposal, httpOptions);
  }

  getProposals ( jobId : number ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.get("https://localhost:8443/getProposals/"+jobId, httpOptions);
  }

}
