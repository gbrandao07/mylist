import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormComponent } from '../cards/form/form.component';
import { CardService } from 'src/app/shared/service/card.service';
import { CachedCard } from 'src/app/shared/model/responses/CachedCard';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  cardsInTodo: Array<CachedCard> = [];
  cardsInDoing: Array<CachedCard> = [];
  cardsInDone: Array<CachedCard> = [];

  formCardName: string;
  formCardAuthorName: string;
  formCardDescription: string;

  constructor(public dialog: MatDialog, public cardService: CardService) {}

  openDialog(titleWindow: string, $event): void {

    const dialogRef = this.dialog.open(FormComponent, {
      width: '300px',
      data: {
        formCardName: $event == null ? this.formCardName : $event.formCardName,
        formCardAuthorName: $event == null ? this.formCardAuthorName : $event.formCardAuthorName,
        formCardDescription: $event == null ? this.formCardDescription : $event.formCardDescription,
        titleWindow: titleWindow,
        id: $event == null ? '' : $event.id,
        columnName: $event == null ? '' : $event.columnName,
        showRemoveButton: $event == null ? 'hidden' : $event.showRemoveButton
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      var id = result.id;
      if (id == undefined || id == '') {
        this.cardService.createCardInTodoList(result.formCardName, result.formCardAuthorName, result.formCardDescription).subscribe(response => {
          console.log(response);
          this.cardsInTodo.push(new CachedCard(response["id"], response["cachedName"]));
        })
      } else {
        // call update service
        this.cardService.updateCard(result.id, result.formCardName, result.formCardAuthorName, result.formCardDescription, result.columnName).subscribe(response => {
          console.log(response);
          if (result.columnName == "todo") {
            this.cardsInTodo.forEach( (card, index) => {
              if(card.id === result.id) {
                this.cardsInTodo.splice(index, 1);
                this.cardsInTodo.push(new CachedCard(response["id"], response["cachedName"]));
              }
            });
          } else if (result.columnName == "doing") {
            this.cardsInDoing.forEach( (card, index) => {
              if(card.id === result.id) {
                this.cardsInDoing.splice(index, 1);
                this.cardsInDoing.push(new CachedCard(response["id"], response["cachedName"]));
              }
            });
          } else if (result.columnName == "done") {
            this.cardsInDone.forEach( (card, index) => {
              if(card.id === result.id) {
                this.cardsInDone.splice(index, 1);
                this.cardsInDone.push(new CachedCard(response["id"], response["cachedName"]));
              }
            });
          }
          alert("Card with ID " + id + " successfully updated.");
        })
      }
    });
  }

  ngOnInit(): void {
  }

}
