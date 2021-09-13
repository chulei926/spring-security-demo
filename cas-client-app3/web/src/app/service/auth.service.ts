import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {CommonsUtil} from "../utils/commons-util";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    urlPrefix = '';

    constructor(private http: HttpClient) {
        this.urlPrefix = `${environment.apiUrl}${this.urlPrefix}`;
    }

    login(username: string, password: string): Promise<any> {
        let params = CommonsUtil.initHttpParams().set('username', username).set('password', password);
        let options: any = CommonsUtil.initHttpOptions();
        options.params = params;
        return this.http.get(`${this.urlPrefix}login`, options).toPromise()
            .then(CommonsUtil.extractData).catch(CommonsUtil.handleError);
    }

    loginOut() {
        let params = CommonsUtil.initHttpParams();
        let options: any = CommonsUtil.initHttpOptions();
        options.params = params;
        return this.http.get(`${this.urlPrefix}loginOut`, options).toPromise()
            .then(CommonsUtil.extractData).catch(CommonsUtil.handleError);
    }

}
