import { OrganizationType } from "../enums/OrganizationType";

export interface OrganizationDto {
    id: number;
    upn: number;
    name: string;
    legalAddress: string;
    phoneNumber: string;
    email: string;
    type: OrganizationType;
    createdAt: string; // ISO date string
}