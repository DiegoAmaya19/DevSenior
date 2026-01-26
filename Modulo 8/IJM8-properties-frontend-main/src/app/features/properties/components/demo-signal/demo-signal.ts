import { Component, computed, effect, signal } from "@angular/core";

@Component({
    selector: 'app-demo-signal',
    imports: [],
    templateUrl: './demo-signal.html',
    styleUrl: './demo-signal.css'
})
export class DemoSignal {
    protected readonly count = signal(0);

    private readonly message = computed(() => `Ha cambiado el valor del contador a ${this.count()}`);

    constructor() {
        effect(() => {
            console.log(this.message());
        });
    }

    increment(): void {
        this.count.update(current => current + 1);
    }
}