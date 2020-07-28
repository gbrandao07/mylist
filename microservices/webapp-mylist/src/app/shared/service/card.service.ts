import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CachedCard } from '../model/responses/CachedCard';
import { Card } from '../model/responses/Card';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  // nginx server configures proxy to other clusterIp services in k8s
  baseUrl: string = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port

  constructor(
    private httpClient: HttpClient
  ) { }

  // GET's
  public getAllCardsInTodoColumn(): Observable<CachedCard[]> {
    return this.httpClient.get<CachedCard[]>(this.baseUrl +"/mylist/todo/cards");
  }

  public getAllCardsInDoingColumn(): Observable<CachedCard[]> {
    return this.httpClient.get<CachedCard[]>(this.baseUrl + "/mylist/doing/cards");
  }

  public getAllCardsInDoneColumn(): Observable<CachedCard[]> {
    return this.httpClient.get<CachedCard[]>(this.baseUrl + "/mylist/done/cards");
  }

  // POST's
  public createCardInTodoList(name: string, authorName: string, description: string): Observable<any> {
    var formData: any = new FormData();
    formData.append("name", name);
    formData.append("authorName", authorName);
    formData.append("description", description);
    return this.httpClient.post(this.baseUrl + "/mylist/todo/cards", formData, { responseType: "json" } );
  }

  public moveCardToDoingColumn(cardId: string): Observable<any> {
    return this.httpClient.post(this.baseUrl + "/mylist/todo/cards/" + cardId + "/move", {}, { responseType: "text" } )
  }

  public moveCardToDoneColumn(cardId: string): Observable<any> {
    return this.httpClient.post(this.baseUrl + "/mylist/doing/cards/" + cardId + "/move?to=done", {}, { responseType: "text" } );
  }

  public moveCardToTodoColumn(cardId: string): Observable<any> {
    return this.httpClient.post(this.baseUrl + "/mylist/doing/cards/" + cardId + "/move?to=todo", {}, { responseType: "text" } );
  }

  // GET's
  public findCardById(cardId: string): Observable<Card> {
    return this.httpClient.get<Card>(this.baseUrl + "/mylist/cards/" + cardId, {});
  }

  // PUT's
  public updateCard(cardId: string, name: string, authorName: string, description: string, columnName: string): Observable<any> {
    var formData: any = new FormData();
    formData.append("id", cardId);
    formData.append("name", name);
    formData.append("authorName", authorName);
    formData.append("description", description);
    return this.httpClient.put(this.baseUrl + "/mylist/" + columnName + "/cards", formData, { responseType: "json" } );
  }
}
