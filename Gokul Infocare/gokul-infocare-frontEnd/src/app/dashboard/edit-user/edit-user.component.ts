import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './edit-user.component.html',
  styleUrl: './edit-user.component.css'
})
export class EditUserComponent  implements OnInit {

   employee : any ;
  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  addUser: FormGroup = new FormGroup({
    id:new FormControl(""),
    firstName :new FormControl("",[Validators.max(50),Validators.required]),
    lastName: new FormControl("",[Validators.max(50),Validators.required]),
    phoneNumber:new FormControl("",[Validators.required]),
    email:new FormControl("",[Validators.required]),
    address:new FormControl("",[Validators.required])
  });

  ngOnInit(): void {
    // Extract parameters from the URL
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      const firstName = params.get('firstName');
      const lastName = params.get('lastName');
      const phoneNumber = params.get('phoneNumber');
      const email = params.get('email');
      const address = params.get('address');

      // Set form values
      this.addUser.setValue({
        id : id ||'',
        firstName: firstName || '',
        lastName: lastName || '',
        phoneNumber: phoneNumber || '',
        email: email || '',
        address: address || ''
      });
    });
  }

    saveChanges(){
    const id = this.addUser.get('id')?.value;

    this.http.put(`http://localhost:8080/UserManagementApp/api/user/updateUser`, this.addUser.value)
      .subscribe((response: any) => {
        alert(response.message);
      });
    }
}
