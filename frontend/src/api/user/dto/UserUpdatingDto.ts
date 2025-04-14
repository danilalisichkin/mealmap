export interface UserUpdatingDto {
  email: string; // Max length: 50
  firstName: string; // Max length: 50
  lastName: string; // Max length: 50
  organizationId: number; // Must not be null
}
