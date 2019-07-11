import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgForm, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NetworkComponent } from './network/network.component';
import { JobsComponent } from './jobs/jobs.component';
import { MessagesComponent } from './messages/messages.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { ProfileComponent } from './profile/profile.component';
import { SettingsComponent } from './settings/settings.component';
import { LoginComponent } from './login/login.component';
import { ArticleComponent } from './article/article.component';
import { RegisterComponent } from './register/register.component';

import { UsersService } from './services/users.service'
import { RegisterService } from './services/register.service';
import { LoginService } from './services/login.service';
import { AuthGaurdService } from './services/auth-gaurd.service';
import { SessionStorageService } from 'ngx-webstorage';
import { AdminComponent } from './admin/admin.component';
import { MyjobsComponent } from './myjobs/myjobs.component';
import { AdminGuardService } from './services/admin-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HomepageComponent,
    NetworkComponent,
    JobsComponent,
    MessagesComponent,
    NotificationsComponent,
    ProfileComponent,
    SettingsComponent,
    LoginComponent,
    ArticleComponent,
    RegisterComponent,
    AdminComponent,
    MyjobsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UsersService, RegisterService, LoginService, AuthGaurdService, SessionStorageService, AdminGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
