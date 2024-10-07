import { Routes } from '@angular/router';
import {DashboardComponent} from "./dashboard/dashboard.component"
import {AddUserComponent} from "./dashboard/add-user/add-user.component";
import {EditUserComponent} from  "./dashboard/edit-user/edit-user.component"
import {UserListComponent} from "./dashboard/user-list/user-list.component"


export const routes: Routes = [

    {
        path : '',
        redirectTo : 'dashboard',
         pathMatch : 'full'
       },
       {
        path:'dashboard',
        component:DashboardComponent
       },{
    
          path : 'add-user',
          component:AddUserComponent
       },
       {
    
        path:'edit-user',
        component:EditUserComponent
       },
       {
        path:'all-userList',
        component:UserListComponent
       }

];
