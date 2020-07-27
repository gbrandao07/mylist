import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CachedCard } from '../model/responses/CachedCard';
import { Card } from '../model/responses/Card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  // To run locally, replace baseUrl in the methods and start app with 'ng serve --port=333 --proxy-config proxy.conf.json'
  // baseUrl: string = "/mylist"

  constructor(
    private httpClient: HttpClient
  ) { }

  // GET's
  public getAllCardsInTodoColumn(): Observable<CachedCard[]> {
    //return this.httpClient.get<CachedCard[]>(this.baseUrl + "/todo/cards");
    return this.httpClient.get<CachedCard[]>("http://todo-service:8080/mylist/todo/cards");
  }

  public getAllCardsInDoingColumn(): Observable<CachedCard[]> {
    //return this.httpClient.get<CachedCard[]>(this.baseUrl + "/doing/cards");
    return this.httpClient.get<CachedCard[]>("http://doing-service:8080/mylist/doing/cards");
  }

  public getAllCardsInDoneColumn(): Observable<CachedCard[]> {
    //return this.httpClient.get<CachedCard[]>(this.baseUrl + "/done/cards");
    return this.httpClient.get<CachedCard[]>("http://done-service:8080/mylist/done/cards");
  }

  // POST's
  public createCardInTodoList(name: string, authorName: string, description: string): Observable<any> {
    var formData: any = new FormData();
    formData.append("name", name);
    formData.append("authorName", authorName);
    formData.append("description", description);
    //return this.httpClient.post(this.baseUrl + "/todo/cards", formData, { responseType: "json" } );
    return this.httpClient.post("http://todo-service:8080/mylist/todo/cards", formData, { responseType: "json" } );
  }

  public moveCardToDoingColumn(cardId: string): Observable<any> {
    //return this.httpClient.post(this.baseUrl + "/todo/cards/" + cardId + "/move", {}, { responseType: "text" } )
    return this.httpClient.post("http://todo-service:8080/mylist/todo/cards" + cardId + "/move", {}, { responseType: "text" } )
  }

  public moveCardToDoneColumn(cardId: string): Observable<any> {
    //return this.httpClient.post(this.baseUrl + "/doing/cards/" + cardId + "/move?to=done", {}, { responseType: "text" } );
    return this.httpClient.post("http://doing-service:8080/mylist/doing/cards/" + cardId + "/move?to=done", {}, { responseType: "text" } );
  }

  public moveCardToTodoColumn(cardId: string): Observable<any> {
    //return this.httpClient.post(this.baseUrl + "/doing/cards/" + cardId + "/move?to=todo", {}, { responseType: "text" } );
    return this.httpClient.post("http://doing-service:8080/mylist/doing/cards/" + cardId + "/move?to=todo", {}, { responseType: "text" } );
  }

  // GET's
  public findCardById(cardId: string): Observable<Card> {
    //return this.httpClient.get<Card>(this.baseUrl + "/cards/" + cardId, {});
    return this.httpClient.get<Card>("http://card-service:8080/mylist/cards/" + cardId, {});
  }

  // PUT's
  public updateCard(cardId: string, name: string, authorName: string, description: string, columnName: string): Observable<any> {
    var formData: any = new FormData();
    formData.append("id", cardId);
    formData.append("name", name);
    formData.append("authorName", authorName);
    formData.append("description", description);
    //return this.httpClient.put(this.baseUrl + "/" + columnName + "/cards", formData, { responseType: "json" } );
    return this.httpClient.put("http://card-service:8080/mylist/" + columnName + "/cards", formData, { responseType: "json" } );
  }
}
