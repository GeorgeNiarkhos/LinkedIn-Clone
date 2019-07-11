import { Component, OnInit } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';
import { JobsService } from '../services/jobs.service';
import { Job } from '../job';
import { User } from '../user';
import { NgForm } from '@angular/forms';
import { Proposal } from '../proposal';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {

  user : User;
  jobs : Array<Job>;
  selected : Job;
  all : boolean;

  constructor(private lSession:SessionStorageService , private service : JobsService) { }

  ngOnInit() {
    // Initialize user object
    this.user = new User();
    this.user.email = this.lSession.retrieve('email');
    this.user.pass = this.lSession.retrieve('password');
    this.getMarket();
    
    this.selected = new Job();
    this.selected.jobId = -1;
    this.all = true;
  }

  getMarket() {
    this.service.getMarket(this.user.email).subscribe( 
      data => {
        this.jobs = data;
      }
    );
  }

  selectJob( job : Job ) {
    this.selected = job;
    this.all = false;
  }

  submitApplication( form : NgForm , job : Job ) {
    var proposal = new Proposal();
    proposal.jobId = job.jobId;
    proposal.email= form.value.email;
    proposal.message = form.value.message;
    this.service.sendProposal(proposal).subscribe(
      data => { this.all = true; }
    );
  }

}
