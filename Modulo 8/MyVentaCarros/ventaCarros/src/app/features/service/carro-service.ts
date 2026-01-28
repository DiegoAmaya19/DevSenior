import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class CarroService {
  // Define the expected properties of a carro object
  // Example properties:
  marca: string = "";
  modelo: string = "";
  anio: number = 0;
  [key: string]: any; // Adjust as needed
}