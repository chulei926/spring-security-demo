import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CommonModule, registerLocaleData} from '@angular/common';
import zh from '@angular/common/locales/zh';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

// 引入你需要的图标，比如你需要 fill 主题的 AccountBook Alert 和 outline 主题的 Alert，推荐 ✔️
import {AccountBookFill, AlertFill, AlertOutline} from '@ant-design/icons-angular/icons';
import {NzIconModule} from "ng-zorro-antd/icon";
import {IconDefinition} from '@ant-design/icons-angular';
import {HttpClientModule} from "@angular/common/http";

registerLocaleData(zh);

const icons: IconDefinition[] = [AccountBookFill, AlertOutline, AlertFill];

// 引入全部的图标，不推荐 ❌
// import * as AllIcons from '@ant-design/icons-angular/icons';

// const antDesignIcons = AllIcons as {
//   [key: string]: IconDefinition;
// };
// const icons: IconDefinition[] = Object.keys(antDesignIcons).map(key => antDesignIcons[key])

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        NzIconModule.forRoot(icons),
        BrowserModule,
        BrowserAnimationsModule,
        CommonModule,
	    HttpClientModule,
        AppRoutingModule
    ],
	providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
