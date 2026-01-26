// Importar Injectable para hacer que la clase sea un servicio de Angular
import { Injectable } from "@angular/core";
// Importar el modelo de datos Property (interfaz/clase)
import { Property } from "../model/property";
// Importar operadores de RxJS para manejo de Observables
import { delay, Observable, of, throwError } from "rxjs";

// Decorador que registra esta clase como servicio inyectable
// providedIn: 'root' significa que está disponible en toda la aplicación
@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  // Array privado que almacena todas las propiedades en memoria
  private properties: Property[] = [];

  // Constructor: se ejecuta cuando se crea una instancia del servicio
  constructor() {
    // Intenta cargar las propiedades guardadas en localStorage
    this.loadProperties();
    
    // Inicializa el array con 4 propiedades de ejemplo
    // Esto simula datos que vendría de un servidor API
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
    
  }

  /**
   * Carga las propiedades guardadas en localStorage
   * Si existen datos guardados, los usa; si no, inicializa un array vacío
   */
  private loadProperties(): void {
    // Intenta obtener el valor guardado con clave 'properties'
    const data = localStorage.getItem('properties');
    
    // Si existe datos guardados (no es null)
    if (data !== null) {
      // Convierte el JSON string a un objeto JavaScript
      const jsonData = JSON.parse(data);
      // Asigna los datos al array de propiedades
      this.properties = jsonData;
    } else {
      // Si no hay datos guardados, inicia con un array vacío
      this.properties = [];
    }
  }

  /**
   * Guarda todas las propiedades actuales en localStorage
   * Convierte el array a JSON para almacenamiento persistente
   */
  private saveProperties(): void {
    // Obtiene el array de propiedades
    const jsonData = this.properties;
    // Convierte el objeto JavaScript a string JSON
    const data = JSON.stringify(jsonData);
    // Guarda en localStorage con clave 'properties'
    localStorage.setItem('properties', data);
  }

  /**
   * Obtiene todas las propiedades disponibles
   * @returns Observable con el array completo de propiedades
   */
  getAll(): Observable<Property[]> {
    // of() crea un Observable que emite el valor inmediatamente
    // pipe(delay(300)) simula un delay de 300ms (como si viniera del servidor)
    return of(this.properties).pipe(delay(300));
  }

  /**
   * Busca una propiedad por su ID
   * @param id - El ID de la propiedad a buscar
   * @returns Observable con la propiedad encontrada o undefined
   */
  getById(id: number): Observable<Property | undefined> {
    // find() busca el primer elemento que cumple la condición
    return of(this.properties.find(p => p.id === id)).pipe(delay(300));
  }

  /**
   * Filtra propiedades por ciudad (búsqueda case-insensitive)
   * @param city - Nombre o parte del nombre de la ciudad a buscar
   * @returns Observable con el array de propiedades encontradas
   */
  getByCity(city: string): Observable<Property[]> {
    // filter() retorna un nuevo array con elementos que cumplan la condición
    // toLowerCase() hace la búsqueda insensible a mayúsculas/minúsculas
    // includes() verifica si la ciudad contiene el texto buscado
    return of(this.properties
      .filter(p => p.city.toLowerCase().includes(city.toLowerCase())))
      .pipe(delay(300));
  }

  /**
   * Filtra propiedades por descripción (búsqueda case-insensitive)
   * @param description - Texto a buscar en la descripción
   * @returns Observable con el array de propiedades encontradas
   */
  getByDescription(description: string): Observable<Property[]> {
    // Similar a getByCity, pero busca en el campo description
    return of(this.properties
      .filter(p => p.description.toLowerCase().includes(description.toLowerCase())))
      .pipe(delay(300));
  }

  /**
   * Crea una nueva propiedad y la agrega al array
   * @param info - Datos de la propiedad (sin ID, se genera automáticamente)
   * @returns Observable que se completa después de crear la propiedad
   */
  create(info: Omit<Property, 'id'>): Observable<undefined> {
    // Omit<Property, 'id'> significa que recibe todos los campos de Property EXCEPTO id
    
    // Encuentra el ID más alto existente para generar uno nuevo
    const maxId = this.properties.length === 0 ? 0
      : this.properties
          .map(p => p.id)           // Extrae todos los IDs
          .reduce((a, b) => a > b ? a : b); // Encuentra el máximo
    
    // Crea una nueva propiedad con los datos recibidos + el nuevo ID
    const newProperty: Property = { ...info, id: maxId + 1 };

    // Agrega la nueva propiedad al array (crea un nuevo array con spread operator)
    this.properties = [...this.properties, newProperty];

    // Guarda los cambios en localStorage para persistencia
    this.saveProperties();
    
    // Log en consola para debugging
    console.log('Propiedad agregada');
    
    // Retorna un Observable vacío con delay (simula operación asincróna)
    return of(undefined).pipe(delay(300));
  }
}