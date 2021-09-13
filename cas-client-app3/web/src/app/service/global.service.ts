import {Injectable} from '@angular/core';
import {NzModalService} from "ng-zorro-antd/modal";

@Injectable({
	providedIn: 'root'
})
export class GlobalService {

	constructor(private modalService: NzModalService) {
	}

	nativeGlobal() {
		return window
	}

	error(title: string, error: any) {
		let modal = this.modalService.error({nzTitle: title, nzContent: `${error}`, nzOkText: null});
		setTimeout(() => {
			modal.destroy()
		}, 2000)
	}

	success(title: string, msg: any) {
		let modal = this.modalService.success({nzTitle: title, nzContent: `${msg}`, nzOkText: null});
		setTimeout(() => {
			modal.destroy()
		}, 1000)
	}

	warn(title: string, msg: any) {
		let modal = this.modalService.warning({nzTitle: title, nzContent: `${msg}`, nzOkText: null});
		setTimeout(() => {
			modal.destroy()
		}, 2000)
	}


	info(title: string, msg: any) {
		let modal = this.modalService.info({
			nzTitle: title,
			nzContent: `${msg}`,
			nzMaskClosable: true,
			nzOnOk: () => null
		});
	}
	
	showError(title: string, msg: any) {
		let modal = this.modalService.error({
			nzTitle: title,
			nzContent: `${msg}`,
			nzMaskClosable: true,
			nzOnOk: () => null
		});
	}

}
