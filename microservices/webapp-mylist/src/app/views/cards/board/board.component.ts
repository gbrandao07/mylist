import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import { CardService } from 'src/app/shared/service/card.service';
import { CachedCard } from 'src/app/shared/model/responses/CachedCard';


@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  @Output() openFormEvent = new EventEmitter<any>();

  @Input() cardsInTodo: Array<CachedCard> = [];
  @Input() cardsInDoing: Array<CachedCard> = [];
  @Input() cardsInDone: Array<CachedCard> = [];

  constructor(
    public cardService: CardService
  ) { }

  ngOnInit(): void {
    this.getAllCardsInTodoColumn();
    this.getAllCardsInDoingColumn();
    this.getAllCardsInDoneColumn();
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      // move in same column
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      // -> or <-

      var cardId = event.previousContainer.data[event.previousIndex]["id"];

      // move from todo to doing
      if (event.previousContainer.id == "cardsInTodo" && event.container.id == "cardsInDoing") {
        this.cardService.moveCardToDoingColumn(cardId).subscribe(response => {
          console.log(response);
        });
      } else
        // move from doing to done
        if (event.previousContainer.id == "cardsInDoing" && event.container.id == "cardsInDone") {
          this.cardService.moveCardToDoneColumn(cardId).subscribe(response => {
            console.log(response);
          });
      } else
        // move from doing to todo
        if (event.previousContainer.id == "cardsInDoing" && event.container.id == "cardsInTodo") {
          this.cardService.moveCardToTodoColumn(cardId).subscribe(response => {
            console.log(response);
          });
      } else {
        // invalid move
        alert("Warning: cards 'done' cannot be updated to 'doing' or 'to do' status.");
        return;
      }

      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);

    }
  }

  getAllCardsInTodoColumn() {
    this.cardService.getAllCardsInTodoColumn().subscribe(cachedCards => {
      cachedCards.forEach(cachedCard => {
        this.cardsInTodo.push(cachedCard);
      });
    });
  }

  getAllCardsInDoingColumn() {
    this.cardService.getAllCardsInDoingColumn().subscribe(cachedCards => {
      cachedCards.forEach(cachedCard => {
        this.cardsInDoing.push(cachedCard);
      });
    });
  }

  getAllCardsInDoneColumn() {
    this.cardService.getAllCardsInDoneColumn().subscribe(cachedCards => {
      cachedCards.forEach(cachedCard => {
        this.cardsInDone.push(cachedCard);
      });
    });
  }

  callParentToOpenForm(cardId: string, columnName: string) {
    this.cardService.findCardById(cardId).subscribe(fullCardData => {
      this.openFormEvent.emit({
        event: 'openFormEvent',
        id: fullCardData.id,
        formCardName: fullCardData.name,
        formCardAuthorName: fullCardData.authorName,
        formCardDescription: fullCardData.description,
        columnName: columnName,
        showRemoveButton: 'visible'
      });
    });
  }
}

