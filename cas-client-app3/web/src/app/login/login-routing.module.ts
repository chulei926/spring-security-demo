import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {environment} from 'src/environments/environment';
import {LoginComponent} from './login.component';
import {SetTitleGuard} from '../utils/set-title.guard'

const routes: Routes = [
    {
        path: '', component: LoginComponent, data: {title: `${environment.title}-登录`}, canActivate: [SetTitleGuard],
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LoginRoutingModule { }
