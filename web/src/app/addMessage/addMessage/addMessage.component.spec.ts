import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddComponent } from './addMessage.component';

describe('AboutComponent', () => {
  let component: AddComponent;
  let fixture: ComponentFixture<AddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddComponent]
    });
    fixture = TestBed.createComponent(AddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
