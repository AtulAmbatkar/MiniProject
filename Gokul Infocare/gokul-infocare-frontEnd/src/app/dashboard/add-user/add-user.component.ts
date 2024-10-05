import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  addUser: FormGroup = new FormGroup({
    firstName :new FormControl("",[Validators.max(50),Validators.required]),
    lastName: new FormControl("",[Validators.max(50),Validators.required]),
    phoneNumber:new FormControl("",[Validators.required]),
    email:new FormControl("",[Validators.required]),
    address:new FormControl("",[Validators.required])
  });

   http =  inject(HttpClient)
  router =inject(Router)

  
submitForm() {
  debugger
  console.log(this.addUser.value);
  this.http.post('http://localhost:8080/UserManagementApp/api/user/addUer', this.addUser.value,).subscribe(
     (Response:any) =>{
      alert(Response.message);
    }
  );
}


}
