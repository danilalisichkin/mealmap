import { Role } from "../enums/Role";

export interface UserDto {
    id: string; // UUID as a string
    phoneNumber: string;
    email: string;
    firstName: string;
    lastName: string;
    organizationId: number;
    status: string; // Assuming UserStatus is a string
    roles: Role[];
    createdAt: string; // ISO date string
}