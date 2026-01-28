import {Injectable} form "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class CarroService {
  // Define the expected properties of a carro object
  // Example properties:
  marca: string;
  modelo: string;
  anio: number;
  [key: string]: any; // Adjust as needed
}
