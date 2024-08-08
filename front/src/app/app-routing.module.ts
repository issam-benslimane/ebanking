import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountOperationsComponent } from './components/account-operations/account-operations.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import { CustomerAccountsComponent } from './components/customer-accounts/customer-accounts.component';
import { CustomersComponent } from './components/customers/customers.component';
import { LoginComponent } from './components/login/login.component';
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { NotAuthorizedComponent } from './components/not-authorized/not-authorized.component';
import { authentificationGuard } from './guards/authentification/authentification.guard';
import { authorizationGuard } from './guards/authorization/authorization.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'admin',
    component: AdminTemplateComponent,
    canActivate: [authentificationGuard],
    children: [
      { path: 'customers', component: CustomersComponent },
      { path: 'accounts', component: AccountsComponent },
      {
        path: 'new-customer',
        component: NewCustomerComponent,
        canActivate: [authorizationGuard],
        data: { role: 'ADMIN' },
      },
      { path: 'customer-accounts/:id', component: CustomerAccountsComponent },
      { path: 'account-operations/:id', component: AccountOperationsComponent },
      { path: 'notAuthorized', component: NotAuthorizedComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
