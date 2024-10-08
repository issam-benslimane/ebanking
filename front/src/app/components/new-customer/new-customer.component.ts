import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer/customer.service';
import { Customer } from '../../shared/models/customer/customer.model';

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.scss'],
})
export class NewCustomerComponent implements OnInit {
  newCustomerFormGroup!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private router: Router
  ) {}
  ngOnInit() {
    this.newCustomerFormGroup = this.fb.group({
      name: this.fb.control(null, [
        Validators.required,
        Validators.minLength(4),
      ]),
      email: this.fb.control(null, [Validators.required, Validators.email]),
    });
  }

  handleSaveCustomer() {
    let customer: Customer = this.newCustomerFormGroup.value;
    console.log("Customer: ", customer);
    this.customerService.saveCustomer(customer).subscribe({
      next: (data) => {
        alert('Customer saved successfully!');
        this.router.navigateByUrl('/customers');
        //this.newCustomerFormGroup.reset();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
