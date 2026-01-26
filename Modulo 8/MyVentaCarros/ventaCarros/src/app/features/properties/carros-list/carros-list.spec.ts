import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarrosList } from './carros-list';

describe('CarrosList', () => {
  let component: CarrosList;
  let fixture: ComponentFixture<CarrosList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarrosList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarrosList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
