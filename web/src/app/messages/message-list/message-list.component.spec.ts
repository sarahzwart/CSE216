import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MessageListComponent } from './message-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('MessageListComponent', () => {
  let component: MessageListComponent;
  let fixture: ComponentFixture<MessageListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [MessageListComponent]
    });
    fixture = TestBed.createComponent(MessageListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});