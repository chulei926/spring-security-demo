import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {Title} from '@angular/platform-browser';

@Injectable({
    providedIn: 'root'
})
export class SetTitleGuard implements CanActivate {

    constructor(private titleService: Title) {
    }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        this.titleService.setTitle(next.data['title']);
        return true;
    }

}
