import {HttpErrorResponse, HttpParams} from '@angular/common/http';
import {environment} from "../../environments/environment";

export class CommonsUtil {


    static extractData(result: any) {
        if (result) {
            return result.result || {};
        }
        return result || {};
    }

    static handleError(errResponse: HttpErrorResponse) {
        if (!errResponse) {
            return Promise.reject('未知异常！');
        }
        if (errResponse.error) {
            return Promise.reject(errResponse.error);
        } else {
            return Promise.reject('请求出错！');
        }
    }

    static initHttpParams(): HttpParams {
        return new HttpParams().set('_', new Date().getTime().toString());
    }

    static initHttpOptions() {
        return {withCredentials: true};
    }

    static newGuid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    static randomColor() {
        let r = Math.floor(Math.random() * 256);
        let g = Math.floor(Math.random() * 256);
        let b = Math.floor(Math.random() * 256);
        let color = '#' + r.toString(16) + g.toString(16) + b.toString(16);
        return color;
    }

    static stringIsEmpty(str) {
        return null == str || undefined == str || 'undefined' == str || 'null' == str || '' === String(str).trim();

    }

}

