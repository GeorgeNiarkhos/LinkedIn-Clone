import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Imports of our Components
import { HomepageComponent } from './homepage/homepage.component';
import { NetworkComponent } from './network/network.component';
import { JobsComponent } from './jobs/jobs.component';
import { MessagesComponent } from './messages/messages.component';
import { ProfileComponent } from './profile/profile.component';
import { SettingsComponent } from './settings/settings.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { LoginComponent } from './login/login.component'
import { RegisterComponent } from './register/register.component'
import { AuthGaurdService } from './services/auth-gaurd.service';
import { AdminComponent } from './admin/admin.component';
import { MyjobsComponent } from './myjobs/myjobs.component';
import { AdminGuardService } from './services/admin-guard.service';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login', 
    pathMatch: 'full',
    
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuardService]
  },
  {
    path: 'home',
    component: HomepageComponent, 
    canActivate: [AuthGaurdService]
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'network',
    component: NetworkComponent,
    canActivate: [AuthGaurdService]
  },
  {
    path: 'jobs',
    component: JobsComponent, 
    canActivate: [AuthGaurdService]
  },
  {
    path: 'myjobs',
    component: MyjobsComponent,
  },
  {
    path: 'messages',
    component: MessagesComponent, 
    canActivate: [AuthGaurdService]
  },
  {
    path: 'notifications',
    component: NotificationsComponent, 
    canActivate: [AuthGaurdService]
  },
  {
    path: 'profile',
    component: ProfileComponent, 
    canActivate: [AuthGaurdService]
  },
  {
    path: 'profile/:email',
    component: ProfileComponent, 
    canActivate: [AuthGaurdService]
  },
  {
    path: 'settings',
    component: SettingsComponent, 
    canActivate: [AuthGaurdService]
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
