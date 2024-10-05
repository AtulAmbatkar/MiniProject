import { NgFor } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [RouterOutlet,RouterLink,NgFor],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
    
  
  userList: any[] = []; 
  
    constructor(private http: HttpClient, private router: Router) {}


    ngOnInit(): void {
      this.getAllUsers();
      this. userList;
    
    }
  
    getAllUsers() {
      console.log('atu: ' + this.userList);
      this.http.get("http://localhost:8080/UserManagementApp/api/user/allUser").subscribe(
        (result: any) => {
          this.userList = result;
          console.log(this.userList);
        }
      );
    }

    onEdit(user : any) {
      this.router.navigate(['/edit-user',user]);
    }

    addUser(){
      this.router.navigateByUrl('/add-user')
    }

    deleteUser(userId : number){
      debugger
      confirm("Are you sure you want to delete this item?")
      this.http.delete(`http://localhost:8080/UserManagementApp/api/user/delete/${userId}`).subscribe(()=>{
        this.userList = this.userList.filter(user => user.id !== userId);
        console.log(`User with ID ${userId} deleted`);
        this.getAllUsers();
      },
      (error) => {
        console.error('Delete failed', error);
      }
    );
    }
  } 