import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {GlobalService} from "../service/global.service";

@Component({
    selector: 'cas-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    username: string = null;
    password: string = null;
    loading: boolean = false;

    constructor(private authService: AuthService, private gs: GlobalService) {
    }

    ngOnInit(): void {
    }

    login() {
        if (!this.username || this.username.trim().length <= 0) {
            this.gs.warn('warning', `请输入用户名`);
            return;
        }
        if (!this.password || this.password.trim().length <= 0) {
            this.gs.warn('warning', `请输入密码`);
            return;
        }
        this.loading = true;
        this.authService.login(this.username, this.password).then(res => {
            this.gs.success('success', '登录成功')
        }).catch(error => {
            this.gs.error("error", `${error.result}`);
        }).finally(() => {
            this.loading = false;
        })
    }

}
