import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {NzIconModule} from 'ng-zorro-antd/icon';
import {LoginRoutingModule} from './login-routing.module';
import {LoginComponent} from './login.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NzInputModule} from "ng-zorro-antd/input";
import {GlobalService} from "../service/global.service";
import {NzModalModule} from "ng-zorro-antd/modal";


@NgModule({
	declarations: [
		LoginComponent
	],
	imports: [
		CommonModule,
		FormsModule,
		LoginRoutingModule,
		HttpClientModule,
		NzFormModule,
		NzButtonModule,
		NzIconModule,
		NzInputModule,
		NzModalModule,
	],
	providers: [GlobalService],
	exports: [
		LoginComponent
	]
})
export class LoginModule {
}
