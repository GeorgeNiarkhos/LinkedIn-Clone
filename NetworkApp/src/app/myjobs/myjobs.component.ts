import { Component, OnInit } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';
import { User } from '../user';
import {NgForm, FormsModule} from '@angular/forms';
import { Job } from '../job';
import { JobsService } from '../services/jobs.service';
import { Proposal } from '../proposal';

@Component({
  selector: 'app-myjobs',
  templateUrl: './myjobs.component.html',
  styleUrls: ['./myjobs.component.css']
})
export class MyjobsComponent implements OnInit {

  user : User;
  myJobs : Array<Job>;
  proposals : Array<Proposal>;
  selected : Job;
  all : boolean;
  

  constructor(private lSession:SessionStorageService , private service : JobsService) { }

  ngOnInit() {
    // Initialize user object
    this.user = new User();
    this.user.email = this.lSession.retrieve('email');
    this.user.pass = this.lSession.retrieve('password');
    this.getMyJobs();
    this.all = true;
    this.proposals = new Array<Proposal>();
  }

  createJob( form: NgForm ) {
    
    var job = new Job();
    job.description = form.value.description;
    job.title = form.value.title;
    job.email = form.value.email;
    console.log(job);
    this.service.newJob(job).subscribe( job => {
      this.myJobs.push(job);
    });

  }

  getMyJobs() {
    this.service.getJobs(this.user.email).subscribe( 
      data => {
        this.myJobs = data;
      });
  }

  selectJob( job : Job ) {
    this.selected = job;
    this.all = false;
    this.getProposals(job.jobId);
  }

  getProposals(jobId : number ) {
    this.service.getProposals(jobId).subscribe(
      data => {
        this.proposals = data;
      });
  }

  isEmpty() : boolean {
    return this.proposals.length == 0;
  }


}
