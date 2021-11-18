import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {CommonsUtil} from "../utils/commons-util";

@Injectable({
  providedIn: 'root'
})
export class IndexService {

	urlPrefix = 'api/index';

	constructor(private http: HttpClient) {
		this.urlPrefix = `${environment.apiUrl}${this.urlPrefix}`;
	}

	curUser(): Promise<any> {
		let params = CommonsUtil.initHttpParams();
		let options: any = CommonsUtil.initHttpOptions();
		options.params = params;
		return this.http.get(`${this.urlPrefix}/getCurrentUser`, options).toPromise()
			.then(CommonsUtil.extractData).catch(CommonsUtil.handleError);
	}

}
