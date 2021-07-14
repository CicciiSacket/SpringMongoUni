import { TestBed } from '@angular/core/testing';

import { ValutationService } from './valutation.service';

describe('ValutationService', () => {
  let service: ValutationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ValutationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
