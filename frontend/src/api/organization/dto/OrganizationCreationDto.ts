import { OrganizationType } from "../enums/OrganizationType";

export interface OrganizationCreationDto {
    upn: number; // Must be positive
    name: string; // Between 2 and 100 characters
    legalAddress: string; // Between 2 and 150 characters
    phoneNumber: string; // Between 3 and 20 characters
    email: string; // Valid email format
    type: OrganizationType; // Must not be null
}