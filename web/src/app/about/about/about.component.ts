import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  // Variables

  // Constructors
  constructor(private router: Router) {}

  // Methods
  public returnToMessages(): void {
    // This gets us back to the messages component page.
    this.router.navigate(['/messages']);
  }
}