import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AccountOperationsComponent } from './components/account-operations/account-operations.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import { CustomerAccountsComponent } from './components/customer-accounts/customer-accounts.component';
import { CustomersComponent } from './components/customers/customers.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/nav-bar.component';
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { NotAuthorizedComponent } from './components/not-authorized/not-authorized.component';
import { AppHttpInterceptor } from './interceptors/app-http.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CustomersComponent,
    NewCustomerComponent,
    CustomerAccountsComponent,
    AccountsComponent,
    LoginComponent,
    AccountOperationsComponent,
    AdminTemplateComponent,
    NotAuthorizedComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AppHttpInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
