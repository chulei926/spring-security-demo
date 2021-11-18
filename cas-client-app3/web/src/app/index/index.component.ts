import {Component, OnInit} from '@angular/core';
import {IndexService} from "../service/index.service";
import {GlobalService} from "../service/global.service";

@Component({
	selector: 'cas-index',
	templateUrl: './index.component.html',
	styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

	loading: boolean = false;
	curUser: any = null;

	constructor(private gs: GlobalService, private indexService: IndexService) {
	}

	ngOnInit(): void {
		setTimeout(()=>this.initUser(), 1000);
	}

	initUser() {
		this.loading = true;
		this.indexService.curUser().then(res => {
			this.curUser = res;
			this.gs.success('success', '获取到用户信息')
		}).catch(error => {
			this.gs.error("error", `${error.result}`);
		}).finally(() => {
			this.loading = false;
		})
	}

}
