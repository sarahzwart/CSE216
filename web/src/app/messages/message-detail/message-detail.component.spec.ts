import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MessageDetailComponent } from './message-detail.component';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

describe('MessageDetailComponent', () => {
  let component: MessageDetailComponent;
  let fixture: ComponentFixture<MessageDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MessageDetailComponent],
      imports: [
        MatCardModule,
        MatIconModule
      ]
    });
    fixture = TestBed.createComponent(MessageDetailComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an edit and delete button button', () => {
    const bannerElement: HTMLElement = fixture.nativeElement;
    const _button = bannerElement.querySelector('mat-card')!;
    component.ngOnInit();
    expect(_button.textContent).toEqual('edit Edit delete Delete ');
  });
});