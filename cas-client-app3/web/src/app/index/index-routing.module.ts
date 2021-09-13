import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {environment} from 'src/environments/environment';
import {SetTitleGuard} from '../utils/set-title.guard';
import {IndexComponent} from './index.component';

const routes: Routes = [
    {
        path: '', component: IndexComponent, data: {title: `${environment.title}-登录`}, canActivate: [SetTitleGuard],
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class IndexRoutingModule { }
