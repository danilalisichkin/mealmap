import { Role } from "../enums/Role";
import { UserStatus } from "./UserStatus";

export interface UserDto {
  id: string; // UUID as a string
  phoneNumber: string;
  email: string;
  firstName: string;
  lastName: string;
  organizationId: number;
  status: UserStatus;
  roles: Role[];
  createdAt: string; // ISO date string
}
