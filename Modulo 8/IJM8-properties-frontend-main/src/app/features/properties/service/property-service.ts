import { Injectable } from "@angular/core";
import { Property } from "../model/property";
import { delay, Observable, of, throwError } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  private properties: Property[] = [];

  constructor() {
    this.loadProperties();
    /*
    this.properties = [
      {
        id: 1,
        address: "Cra 1 # 2 - 03",
        description: "Una casa bien ubicada",
        price: 2000,
        city: "Pereira",
        bathrooms: 2,
        bedrooms: 3,
        image: "https://images.pexels.com/photos/186077/pexels-photo-186077.jpeg?cs=srgb&dl=pexels-binyamin-mellish-186077.jpg&fm=jpg"
      },
      {
        id: 2,
        address: "Cra 1 # 2 - 04",
        description: "Una casa bien ubicada en el mejor lugar de la ciudad. Tambien encontrarás los mejores lugares para disfrutar de rumba y actividades culturales",
        price: 3500,
        city: "Cali",
        bathrooms: 3,
        bedrooms: 5,
        image: "https://tse3.mm.bing.net/th/id/OIP.QyuhtVr9Y99YQ9aATPv5ZgHaF7?cb=ucfimg2ucfimg=1&rs=1&pid=ImgDetMain&o=7&rm=3"
      },
      {
        id: 3,
        address: "Cra 1 # 2 - 05",
        description: "Una casa a las afueras de la ciudad",
        price: 4500,
        city: "Bogota",
        bathrooms: 3,
        bedrooms: 5,
        image: "https://wallpaperaccess.com/full/3885499.jpg"
      },
      {
        id: 4,
        address: "Cra 1 # 2 - 07",
        description: "Una casa a las afueras de la ciudad",
        price: 4500,
        city: "Bogota",
        bathrooms: 3,
        bedrooms: 5,
        image: "https://wallpaperaccess.com/full/3885499.jpg"
      }

    ];
    */
  }

  private loadProperties(): void {
    const data = localStorage.getItem('properties');
    if (data !== null) {
      const jsonData = JSON.parse(data);
      this.properties = jsonData;
    } else {
      this.properties = [];
    }
  }

  private saveProperties(): void {
    const jsonData = this.properties;
    const data = JSON.stringify(jsonData);
    localStorage.setItem('properties', data);
  }

  getAll(): Observable<Property[]> {
    return of(this.properties).pipe(delay(300));
  }

  getById(id: number): Observable<Property | undefined> {
    return of(this.properties.find(p => p.id === id)).pipe(delay(300));
  }

  getByCity(city: string): Observable<Property[]> {
    return of(this.properties
      .filter(p => p.city.toLowerCase().includes(city.toLowerCase())))
      .pipe(delay(300));
  }

  getByDescription(description: string): Observable<Property[]> {
    return of(this.properties
      .filter(p => p.description.toLowerCase().includes(description.toLowerCase())))
      .pipe(delay(300));
  }

  create(info: Omit<Property, 'id'>): Observable<undefined> {
    // Si el ID esta vacio, genere un nuevo ID
    const maxId = this.properties.length === 0 ? 0
      : this.properties.map(p => p.id)
          .reduce((a, b) => a > b ? a : b);
    const newProperty: Property = { ...info, id: maxId + 1 };

    this.properties = [...this.properties, newProperty];

    this.saveProperties();
    console.log('Propiedad agregada');
    return of(undefined).pipe(delay(300));
  }
}