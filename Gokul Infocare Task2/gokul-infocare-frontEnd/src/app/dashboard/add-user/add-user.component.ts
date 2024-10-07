import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [ReactiveFormsModule,NgIf],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  addUser: FormGroup = new FormGroup({
    firstName :new FormControl("", [Validators.required, Validators.maxLength(50)]),
    lastName: new FormControl("", [Validators.required, Validators.maxLength(50)]),
    phoneNumber:new FormControl("",[Validators.required]),
    email:new FormControl("", [Validators.required, Validators.email]),
    address:new FormControl("",[Validators.required])
  });

   http =  inject(HttpClient)
   router = inject(Router)

  
submitForm() {
  debugger
  console.log(this.addUser.value);
  this.http.post('http://localhost:8080/UserManagementApp/api/user/addUser', this.addUser.value,).subscribe(
     (Response:any) =>{
      alert(Response.message);
    }
  );
}


}
