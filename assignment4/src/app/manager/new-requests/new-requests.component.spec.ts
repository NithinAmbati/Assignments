import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRequestsComponent } from './new-requests.component';

describe('NewRequestsComponent', () => {
  let component: NewRequestsComponent;
  let fixture: ComponentFixture<NewRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewRequestsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
