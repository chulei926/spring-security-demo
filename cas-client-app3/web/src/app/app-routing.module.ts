import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

const routes: Routes = [
    {path: 'login', loadChildren: () => import('./login/login.module').then(m => m.LoginModule)},
    {path: 'index', loadChildren: () => import('./index/index.module').then(m => m.IndexModule)},
    {path: '**', loadChildren: () => import('./login/login.module').then(m => m.LoginModule)},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
