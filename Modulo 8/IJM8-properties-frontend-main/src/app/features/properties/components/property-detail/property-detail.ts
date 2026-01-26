import { CurrencyPipe, DecimalPipe } from '@angular/common';
import { Component, OnInit, computed, effect, input, signal } from '@angular/core';
import { Property } from '../../model/property';
import { PropertyService } from '../../service/property-service';

@Component({
  selector: 'app-property-detail',
  imports: [CurrencyPipe, DecimalPipe],
  templateUrl: './property-detail.html',
  styleUrl: './property-detail.css',
})
export class PropertyDetail implements OnInit {
  protected id = input<string | undefined>();

  private idNumeric = computed<number>(() => {
    if(typeof this.id() === 'string') {
      return parseInt(this.id()!);
    }
    return 0;
  });

  protected property = signal<Property | undefined>(undefined);

  constructor(
      private propertyService: PropertyService,
  ) {}

  ngOnInit(): void {
    if(this.idNumeric() !== 0) {
      this.propertyService.getById(this.idNumeric()).subscribe({
        next: data => this.property.set(data)
      });
    } else {
      this.property.set(undefined);
    }
  }
}


// !
// ??
// ?: