import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {IndexRoutingModule} from './index-routing.module';
import {IndexComponent} from './index.component';
import {NgxLoadingModule} from "ngx-loading";


@NgModule({
    declarations: [
        IndexComponent
    ],
	imports: [
		CommonModule,
		IndexRoutingModule,
		NgxLoadingModule
	],
    exports: [
        IndexComponent
    ]
})
export class IndexModule { }
